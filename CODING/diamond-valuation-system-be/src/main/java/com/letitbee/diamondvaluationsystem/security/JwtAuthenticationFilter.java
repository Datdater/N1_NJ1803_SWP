//package com.letitbee.diamondvaluationsystem.security;
//
//import com.letitbee.diamondvaluationsystem.security.CustomUserDetailsService;
//import com.letitbee.diamondvaluationsystem.security.JwtTokenProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private UserDetailsService userDetailsService;
//    private JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtTokenProvider jwtTokenProvider) {
//        this.userDetailsService = customUserDetailsService;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        //get JWT token from http request
//        String token = getJwtFromRequest(request);
//
//        //validate JWT token
//
//        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
//            //get username from JWT token
//            String username = jwtTokenProvider.getUsername(token);
//
//            //load the user associated with the token
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    userDetails,
//                    null,
//                    userDetails.getAuthorities()
//            );
//
//            //set authentication object to security context
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//
//        filterChain.doFilter(request,response);
//
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request){
//        String bearerToken = request.getHeader("Authorization");
//
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
//            return bearerToken.substring(7,bearerToken.length());
//        }
//        return null;
//    }
//}
