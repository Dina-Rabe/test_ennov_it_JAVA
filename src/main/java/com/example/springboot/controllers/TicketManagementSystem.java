package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketManagementSystem {

	@GetMapping("/")
	public String index() {
		return "TEST JAVA SPING BOOT ENNOV-IT";
	}

}
