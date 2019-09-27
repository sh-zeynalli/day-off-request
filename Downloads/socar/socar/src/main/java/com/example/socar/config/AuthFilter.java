//package com.example.socar.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by Zeynalli on 20-Sep-19.
// */
//public class AuthFilter extends OncePerRequestFilter {
//    private final String tokenHeader = "Authorization";
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        final String tokenHeader = httpServletRequest.getHeader(this.tokenHeader);
//
//    }
//}
