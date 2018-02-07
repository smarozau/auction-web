package com.morozov.auction.service.impl;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.exception.EmailVerificationException;
import com.morozov.auction.dao.UserCredentialsDao;
import com.morozov.auction.dao.UserDao;
import com.morozov.auction.dao.VerificationTokenDao;
import com.morozov.auction.model.User;
import com.morozov.auction.model.UserCredentials;
import com.morozov.auction.model.UserRegistrationData;
import com.morozov.auction.model.VerificationToken;
import com.morozov.auction.service.PasswordEncryptionService;
import com.morozov.auction.service.UserRegistrationService;



public class UserRegistrationServiceImpl implements UserRegistrationService {
	
	@Autowired
	private PasswordEncryptionService passwordEncryptionService;
	
	@Autowired
	private UserCredentialsDao userCredentialsDao;
	
	@Autowired
	private VerificationTokenDao verificationTokenDao;
	
	@Autowired
	private UserDao userDao;
		
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationContext appContext;

	@Override
	public void register(final UserRegistrationData registration) throws Exception {
		final String password = registration.getPassword();
		final byte[] salt = passwordEncryptionService.generateSalt();
		final byte[] encryptedPassword = passwordEncryptionService.getEncryptedPassword(password, salt);
		
		VerificationToken verificationToken = register(registration, encryptedPassword, salt);
		sendMail(registration, verificationToken);
	}

	protected void sendMail(final UserRegistrationData registration, VerificationToken verificationToken) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(registration.getUser().getEmail());
		String userName = registration.getUser().getFirstName() + " " + registration.getUser().getLastName();
		String link = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + "8080" + "/" 
				+ appContext.getApplicationName()  + "/registration/verify?token=" + verificationToken.getToken();
		String messageTextTemplate = messageSource.getMessage("mail.confirmation.template", new Object[0], LocaleContextHolder.getLocale() );
		message.setText( String.format( messageTextTemplate , userName, link) );
		message.setSubject("Confirm your registration on Auction Web");
		mailSender.send(message);
	}
	
	@Transactional
	protected VerificationToken register(UserRegistrationData registration, byte[] encryptedPassword, byte[] salt) throws Exception {
		String password = registration.getPassword();
		User user = registration.getUser();
		userDao.save(user);
		Integer userId = user.getUserId();
		UserCredentials userPassword = new UserCredentials(userId, password, encryptedPassword, salt);
		userPassword.setRole("ROLE_USER");
		userCredentialsDao.save(userPassword);
		VerificationToken verificationToken = new VerificationToken(userId);
		verificationTokenDao.save(verificationToken);
		return verificationToken;
	}

	@Override
	@Transactional
	public void confirm(String verificationToken) throws Exception {
		Integer userId = verificationTokenDao.findUserIdByVerificationToken(verificationToken);
		if ( userId == null ) {
			throw new EmailVerificationException();
		}
		verificationTokenDao.deleteVerificationToken(verificationToken);
		userCredentialsDao.confirm(userId);
	}

}
