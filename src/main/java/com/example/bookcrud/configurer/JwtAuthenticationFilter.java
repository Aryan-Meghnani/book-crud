//package com.example.bookcrud.configurer;
//
//import com.example.bookcrud.helper.JwtUtil;
//import com.example.bookcrud.service.CustomUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private CustomUserDetailService customUserDetailService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//
//        //get jwt
//        //Bearer
//        //validate
//
//        String requestTokenHeader = httpServletRequest.getHeader("Authorization");
//        String userName =null;
//        String jwtToken = null;
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//
//            try {
//                userName = this.jwtUtil.getUsernameFromToken(jwtToken);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
//            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            } else
//                System.out.println("not validated");
//
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}