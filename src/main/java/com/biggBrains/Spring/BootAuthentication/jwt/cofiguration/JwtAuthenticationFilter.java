package com.biggBrains.Spring.BootAuthentication.jwt.cofiguration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.biggBrains.Spring.BootAuthentication.jwt.Util.JwtUtil;
import com.biggBrains.Spring.BootAuthentication.service.CustomUserDetailService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	CustomUserDetailService customUserDetailService;
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get JWT
		// Bearer
		// Validate
		String autorizationTokenHeader = request.getHeader("Authorization");
		String username = null;
		String JwtToken = null;
		if (autorizationTokenHeader != null && autorizationTokenHeader.startsWith("Bearer")) {

			JwtToken = autorizationTokenHeader.substring(7);
			try {
				username = this.jwtUtil.extractUsername(JwtToken);

			} catch (Exception e) {

				e.printStackTrace();
			}
			// security purpose

			UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails,null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("token is not valid");
			}

		}
		filterChain.doFilter(request, response);


	}
	}

