package tn.esprit.infini2.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	

	 protected void filter(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            FilterChain chain)

	            throws ServletException, IOException {

	        String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
	        if (jwt == null || !jwt.startsWith(SecurityConstants.TOCKEN_PREFIX)) {
	            chain.doFilter(request, response);
	            return;
	        }

	        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
	                .parseClaimsJws(jwt.replace(SecurityConstants.TOCKEN_PREFIX, "")).getBody();

	        String username = claims.getSubject();

	        ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
	        Collection<GrantedAuthority> authorities = new ArrayList<>();
	        if(roles!=null) {roles.forEach((r -> {
	            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
	        }));}
	        UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null,
	                authorities);
	        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	        chain.doFilter(request, response);

	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	            filter(request, response, chain);
	    }

	}