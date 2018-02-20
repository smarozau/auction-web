package com.morozov.auction.security.authentication;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.morozov.auction.model.User;
import com.morozov.auction.model.UserCredentials;
import com.morozov.auction.service.PasswordEncryptionService;
import com.morozov.auction.service.UserCredentialsService;
import com.morozov.auction.service.UserService;

@Component
public class LocalAuthenticationProvider implements AuthenticationProvider {
	
	public LocalAuthenticationProvider(){
		}
       
	private Logger logger = LoggerFactory.getLogger(LocalAuthenticationProvider.class);
	
	@Autowired
	private PasswordEncryptionService passwordEncryptionService;
	
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> target) {
		return UsernamePasswordAuthenticationToken.class.equals(target);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
		UserCredentials userCredentials = findUserCredentials(email);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String role = userCredentials.getRole();	
		System.out.println(role);
        authenticate(authentication, userCredentials);
        
        User user = findUser( userCredentials.getUserId() );
        
        authorities = authorizeUser(role);
        
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authorities);
	    
	}
	
	private UserCredentials findUserCredentials(String email) {
		UserCredentials userCredentials = null;
        try {
        	userCredentials = userCredentialsService.findCredentialsByEmail(email);        	        	
        } catch (Exception e) {
        	logger.error("Failed to obtain user credentials", e);
        	throw new AuthenticationServiceException("Failed to obtain user credentials", e);
        }
        if (userCredentials == null) {
        	logger.warn("user credentials not found");
    		throw new UsernameNotFoundException("User not found by email");
    	}
		return userCredentials;
	}
	
	private void authenticate(Authentication authentication, UserCredentials userCredentials) {
		String password = authentication.getCredentials().toString();
        byte[] encryptedPassword = userCredentials.getEncryptedPassword();
        byte[] salt = userCredentials.getSalt();
        try {
			boolean authenticated = passwordEncryptionService.authenticate(password, encryptedPassword, salt);
			if ( !authenticated ) {
				throw new BadCredentialsException("Bad credentials");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AuthenticationServiceException("Failed to verify user credentials", e);
		}
	}
	
	private User findUser(int userId) {
		User user = null;
        try {        	
			user = userService.findById(userId);
		} catch (Exception e) {
			throw new AuthenticationServiceException("Failed to obtain user", e);
		}
		return user;
	}	

	private List<GrantedAuthority> authorizeUser(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}	
	
	

}
