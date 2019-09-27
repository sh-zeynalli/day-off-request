package com.example.socar.persistance.service;

import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Zeynalli on 19-Sep-19.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employees employee = employeeRepository.findByMail(s);
        Employees employeeLogin = employeeRepository.findByMailAndPassword(s, employee.getPassword());

        if (Objects.isNull(employee)) {
            throw new UsernameNotFoundException("Mail tapılmadı!");
        }
            List<SimpleGrantedAuthority> authorities =
                    employeeLogin.getRoles().stream().map(r ->
                            new SimpleGrantedAuthority("ROLE_" + r.getName())).collect(Collectors.toList());

            UserDetails userDetails = new User(employeeLogin.getMail(), employeeLogin.getPassword(), authorities);

            return userDetails;
        }
    }
