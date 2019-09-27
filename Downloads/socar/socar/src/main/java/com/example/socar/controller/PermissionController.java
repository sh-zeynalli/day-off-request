package com.example.socar.controller;

import com.example.socar.dto.PermissionDto;
import com.example.socar.exception.Response;
import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.Permission;
import com.example.socar.persistance.entity.Roles;
import com.example.socar.persistance.service.EmployeeService;
import com.example.socar.persistance.service.PermissionService;
import com.example.socar.persistance.service.RolesService;
import com.example.socar.persistance.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Zeynalli on 06-Sep-19.
 */

@Controller
public class PermissionController {

    @Autowired
    TypeService typeService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RolesService rolesService;

    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public ModelAndView permission(ModelAndView modelAndView) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Employees employees = employeeService.findByMail(((UserDetails) principal).getUsername());
        List permissionList = permissionService.findByE_id(employees.getId());
        if(permissionList.size()==0){
            modelAndView.addObject("emptyList", "Hal-hazırda icazə sorğunuz yoxdur!");
        }
        for(int i=0;i<permissionList.size();i++) {
            if(permissionService.findByE_id(employees.getId()).get(i).getDescription()==null){
                permissionService.findByE_id(employees.getId()).get(i).setDescription("Müraciət gözləmə mərhələsindədir!");
            }
        }
        List<Roles> role = rolesService.getRolesbyEId(employees.getId());
        modelAndView.addObject("roleId", role.iterator().next().getId());

        modelAndView.setViewName("permissions");
        modelAndView.addObject("type", typeService.findAllTypes());
        modelAndView.addObject("permission", new PermissionDto());
        modelAndView.addObject("permissionList", permissionList);

        return modelAndView;
    }
    @PreAuthorize("hasRole('hr') or hasRole('ceo')")
    @RequestMapping(value = "/allpermissions", method = RequestMethod.GET)
    public ModelAndView allpermissions(ModelAndView modelAndView){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employees employees = employeeService.findByMail(((UserDetails) principal).getUsername());
        List<Permission> all = permissionService.findAll();
        List<Roles> role = rolesService.getRolesbyEId(employees.getId());
        modelAndView.addObject("roleId", role.iterator().next().getId());
        modelAndView.setViewName("permissions");
        modelAndView.addObject("type", typeService.findAllTypes());
        modelAndView.addObject("permission", new PermissionDto());
        modelAndView.addObject("permissionList", all);

        return modelAndView;
    }
}
