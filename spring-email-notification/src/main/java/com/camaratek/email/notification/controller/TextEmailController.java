package com.camaratek.email.notification.controller;

import org.apache.logging.log4j.Logger;

/*
 * @Author CAMARA Laby Damaro
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camaratek.email.notification.model.EmailTemplate;
import com.camaratek.email.notification.service.EmailService;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
@Slf4j
public class TextEmailController {
	
	//Logger log;
	
	@Autowired
	private EmailService emailService;

	@PostMapping(value="/textemail",consumes = "application/json", produces = "application/json")
	public String sendEmail(@RequestBody EmailTemplate emailTemplate) {
		try {
			//log.info("Envoyer un Simple Text par Email....");
			emailService.sendTextEmail(emailTemplate);
			return "L'email envoyé avec succès!";
		} catch (Exception ex) {
			return "Erreur d'envoie de mail: " + ex;
		}
	}
	
	
	@PostMapping(value="/attachemail",consumes = "multipart/form-data")
	public String sendEmailWithAttachment(@RequestPart(value = "file") MultipartFile file) {
		try {
			//log.info("Envoyer un Fichier par Email....");
			emailService.sendEmailWithAttachment(file);
			return "L'email envoyé avec succès!";
		} catch (Exception ex) {
			return "Erreur d'envoie de mail: " + ex;
		}
	}
	

}
