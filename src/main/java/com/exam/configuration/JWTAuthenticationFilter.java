package com.exam.configuration;

import com.exam.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;  // responsible for generating username and password

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestHeaderToken = request.getHeader("Authorization");
        System.out.println(requestHeaderToken);
        String username = null;
        String jwtToken = null;

//        get Username from token
        if (requestHeaderToken!= null && requestHeaderToken.startsWith("Bearer ")){
//            get token from header
            jwtToken = requestHeaderToken.substring(7);
            try {
//                get username from the token
                username = this.jwtUtil.extractUsername(jwtToken);
            } catch (JwtException expiredJwtException){
                System.out.println(expiredJwtException.toString() + " Jwt token has expired");
            }

        }else{
            System.out.println("invalid token ," + " or token not start with Bearer ");
        }


//        validate user
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//            if the user from database
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

            //            validate token --> if valid add to security context
            if (this.jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//               if user is authenticated, then add to securityContext
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("token is not valid");
            }
        }

//   forward the filter
        filterChain.doFilter(request,response);
    }
}
