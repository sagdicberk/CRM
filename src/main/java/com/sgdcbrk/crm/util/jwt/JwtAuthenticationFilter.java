package com.sgdcbrk.crm.util.jwt;

import com.sgdcbrk.crm.business.concretes.auth.UserDetailsImp;
import com.sgdcbrk.crm.business.concretes.auth.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImp userDetailsServiceImp;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImp userDetailsImp) {
        this.jwtUtil = jwtUtil;
        this.userDetailsServiceImp = userDetailsImp;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        // Authorization header kontrolü
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Tokeni ayıkla
        jwtToken = authHeader.substring(7);
        email = jwtUtil.extractEmail(jwtToken);

        // Email boş değilse ve authentication daha yapılmadıysa
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetailsImp userDetails = (UserDetailsImp) userDetailsServiceImp.loadUserByUsername(email);

                // Token doğrulaması: email eşleşmesi ve token süresinin geçip geçmediği
                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (UsernameNotFoundException e) {
                throw new RuntimeException(e.getMessage() + " : jwtFilter error");
            }
        }

        filterChain.doFilter(request, response);
    }
}
