package com.github.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.config.JwtResponse;
import com.github.config.JwtTokenUtil;
import com.github.config.JwtUserDetailsService;
import com.github.model.GithubUser;
import com.github.model.RepositorySummary;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class GitHubController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@ApiOperation(value = "Autentica um usuário na api", 
			nickname = "authenticate")
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody GithubUser user) throws Exception {
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getUsername());
		validatePassword(user, userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void validatePassword(GithubUser requestUser, UserDetails userDetails) throws Exception {
		if(userDetails == null || requestUser == null)
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		if(!requestUser.getPassword().equals(userDetails.getPassword()))
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		
	}
	
	
	@ApiOperation(value = "Lista todos os repositórios do GitHub de um determinado usuário", 
			nickname = "indexUsingPOST")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<RepositorySummary> indexUsingPOST(@RequestBody GithubUser user) throws URISyntaxException, IOException, InterruptedException {
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(String.format("https://api.github.com/users/%s/repos", user.getUsername())))
					.GET()
					.build();
			
			HttpResponse<String> response = HttpClient.newBuilder()
					.build()
					.send(request,  HttpResponse.BodyHandlers.ofString());
			
			
			if(response.statusCode() == 200) {
				String json = response.body().toString();
				ObjectMapper mapper = new ObjectMapper();
				List<RepositorySummary> jsonList = 
						mapper.readValue(json, new TypeReference<List<RepositorySummary>>(){});
				return jsonList;
			}else
				return null;
	}
}
