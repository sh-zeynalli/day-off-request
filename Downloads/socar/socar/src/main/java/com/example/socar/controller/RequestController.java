package com.example.socar.controller;

import com.example.socar.dto.EmployeesDto;
import com.example.socar.dto.PTypeDto;
import com.example.socar.dto.PermissionDto;
import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.PType;
import com.example.socar.persistance.entity.Permission;
import com.example.socar.persistance.entity.Roles;
import com.example.socar.persistance.service.EmployeeService;
import com.example.socar.persistance.service.PermissionService;
import com.example.socar.persistance.service.RolesService;
import com.example.socar.persistance.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Zeynalli on 20-Sep-19.
 */
@Controller
public class RequestController {

    @Autowired
    TypeService typeService;

    @Autowired
    RolesService rolesService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasRole('team_leader') or hasRole('ceo')or hasRole('chief of department')or hasRole('manager')")
    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView requests(ModelAndView modelAndView) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Permission> permissionList;
        Employees e = employeeService.findByMail(((UserDetails) principal).getUsername());
        List<Roles> role = rolesService.getRolesbyEId(e.getId());
        modelAndView.addObject("roleId", role.iterator().next().getId());
        ArrayList<Permission> newList = new ArrayList<>();
        for (int i = 0; i < role.size(); i++) {
            permissionList = permissionService.findByToRole(role.get(i).getId());
            System.out.println("size: " + permissionList.size());
            if (permissionList.size() != 0) {
                for (int j = 0; j < permissionList.size(); j++) {
                    System.out.println(permissionList.get(j).getStatus());
                    if (permissionList.get(j).getStatus() == 0) {
                        newList.add(permissionList.get(j));
                    }
                }
            } else {
                modelAndView.addObject("emptyPermissionList", "Sizə icazə sorğusu yoxdur!");
            }
        }
        if (newList.size() == 0) {
            modelAndView.addObject("emptyPermissionList", "Sizə icazə sorğusu yoxdur!");

        }
        modelAndView.setViewName("requests");
        modelAndView.addObject("type", typeService.findAllTypes());
        modelAndView.addObject("permission", new PermissionDto());
        modelAndView.addObject("permissionList", newList);

        return modelAndView;
    }

    @Transactional
    @RequestMapping(value = "/requests/accept/{id}", method = RequestMethod.GET)
    public ModelAndView accept(ModelAndView modelAndView, @PathVariable("id") Long id) {
        Permission p = permissionService.findById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employees e = employeeService.findByMail(((UserDetails) principal).getUsername());
        List<Roles> r = rolesService.getRolesbyEId(e.getId());

        if (e.getRoles().iterator().next().getId() < 4) {
            p.setDescription(e.getFullname().toUpperCase() + " " + e.getRoles().iterator().next().getName() + " müraciətə müsbət cavab verib.\nDigər vəzifəlilərin cavabı gözlənilir.");
            p.setTo(r.get(0).getId() + 1);
        } else {
            p.setDescription(e.getFullname().toUpperCase() + " " + e.getRoles().iterator().next().getName() + " müraciətə müsbət cavab verib.\nİcazəniz qəbul olunub.");
            p.setTo(Long.valueOf(0));
        }
        List<Permission> permissionList = permissionService.findAll();
        permissionService.update(id, p.getStatus(), p.getDescription());
        modelAndView.setViewName("requests");
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("redirect:/requests");
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value = "/requests/reject/{id}", method = RequestMethod.GET)
    public ModelAndView reject(ModelAndView modelAndView, @PathVariable("id") Long id, String reason) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employees e = employeeService.findByMail(((UserDetails) principal).getUsername());
        Permission p = permissionService.findById(id);
        p.setStatus(Long.valueOf(-1));
        p.setDescription("REJECTED BY " + e.getFullname().toUpperCase() + " " + e.getRoles().iterator().next().getName() + ": " + reason);
        permissionService.update(id, p.getStatus(), p.getDescription());
        System.out.println(p.getDescription());
        List<Permission> permissionList = permissionService.findAll();
        modelAndView.setViewName("requests");
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("redirect:/requests");

        return modelAndView;
    }


}
