package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import tn.esprit.infini2.entities.AuthenticationRequest;
import tn.esprit.infini2.security.JwtUtil;
import tn.esprit.infini2.services.MyUserDetailsService;
import tn.esprit.infini2.services.UserService;

@RestController
public class SecurityController {

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority(@userService.Employee())")
	@GetMapping("/hello")
	public String hello() {
		return "Hello Employee world!!!";
	}
	@PreAuthorize("hasAuthority(@userService.Customer())")
	@GetMapping("/hello2")
	public String hello2() {
		return "Hello customer  world!!!";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {


		if(userService.verifAuthentifaction(authenticationRequest)) {

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(jwt);
		}
		throw new Exception("Incorrect username or password");
	}


}

