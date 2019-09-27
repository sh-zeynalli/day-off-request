package com.example.socar.controller;

import com.example.socar.dto.EmployeesDto;
import com.example.socar.dto.PTypeDto;
import com.example.socar.dto.PermissionDto;
import com.example.socar.exception.Response;
import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.Permission;
import com.example.socar.persistance.entity.Roles;
import com.example.socar.persistance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class Main {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    TypeService typeService;

    @Autowired
    RolesService rolesService;

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/exception")
    public String accessDenied() {
        return "exception";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employees employees = employeeService.findByMail(((UserDetails) principal).getUsername());
        List<Roles> role = rolesService.getRolesbyEId(employees.getId());
        modelAndView.addObject("roleId", role.iterator().next().getId());
        modelAndView.setViewName("index");
        modelAndView.addObject("userInfo", employees.getFullname().toUpperCase()+"\n"+ employees.getRoles().iterator().next().getName());
        modelAndView.addObject("role", role.get(0).getName());
        modelAndView.addObject("type", typeService.findAllTypes());
        modelAndView.addObject("permission", new PermissionDto());
        return modelAndView;
    }

    @PostMapping(value = "/request")
    @ResponseBody
    public Response request(@Valid String reason, @Valid Long type, @NotEmpty String startDate, @NotEmpty String startTime, @Valid String endDate, @ModelAttribute String fail, String endTime, BindingResult errors) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employees employees = employeeService.findByMail(((UserDetails) principal).getUsername());

        Response myResponse = new Response();

        Date sd = null;
        Date ed = null;

        if (type == 0) {
            myResponse.setCode(500);
            myResponse.setMessage("İcazənin tipini seçin");
        } else {
            if (!Objects.isNull(startDate) && !Objects.isNull(endDate) && startDate != "" && endDate != "") {
                try {
                    sd = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
                    ed = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (ed.before(sd) && type == 1) {
                    myResponse.setCode(500);
                    myResponse.setMessage("Gediş tarixi dönüş tarixindən əvvəl olmalıdır!");
                }
                if ((!ed.equals(sd)) && type == 2) {
                    myResponse.setCode(500);
                    myResponse.setMessage("Gediş və dönüş eyni gündə olmalıdır!");
                }
            } else {
                if ((Objects.isNull(startDate) || startDate == "") && (Objects.isNull(endDate) || endDate == "")) {
                    myResponse.setCode(500);
                    myResponse.setMessage("Gediş və dönüş tarixlərini qeyd edin!");
                } else {
                    if (Objects.isNull(startDate) || startDate == "") {
                        myResponse.setCode(500);
                        myResponse.setMessage("Gediş tarixini qeyd edin!");
                    }
                    if (Objects.isNull(endDate) || endDate == "") {
                        myResponse.setCode(500);
                        myResponse.setMessage("Dönüş tarixini qeyd edin!");
                    }
                }
            }

        }

        if (myResponse.getCode() != 500) {
            PermissionDto p = new PermissionDto();
            Employees e = employeeService.findUserById(employees.getId());
            EmployeesDto dto = new EmployeesDto();
            p.setEmployees(dto.adapter(e));
            p.setDate(LocalDateTime.now());
            // Long t_id = Long.parseLong(type);
            PTypeDto t = typeService.findById(type);
            p.setType(t);
            p.setReason(reason);
            p.setStartDate(sd);
            p.setStartTime(Time.valueOf(startTime));
            p.setEndDate(ed);
            p.setEndTime(Time.valueOf(endTime));
            for (int i = 0; i < employees.getRoles().size(); i++) {

                if (employeeService.findRoleByEId(employees.getId()).get(i).getId() <= 4) {
                    Long to_role = Long.valueOf(employeeService.findRoleByEId(employees.getId()).get(i).getId() + 1);
                    p.setTo(to_role);
                }
            }

            permissionService.save(p);
            myResponse.setCode(200);
            myResponse.setMessage("Sorğunuz aidiyyəti şəxsə göndərildi.");

        } else {
            System.out.println("errors: " + errors.getAllErrors());
        }
        return myResponse;
    }
}
