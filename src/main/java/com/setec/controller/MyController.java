package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepo;
import com.setec.service.MyTelegramBot;

@Controller
public class MyController {
	//http://localhost:8080/
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		
		Booked booked=new Booked(
				1,"Hem SengHongly",
				"087348554",
				"hongly@gmail.com",
				"05/25/2025",
				"9:20 PM",
				5
				);
		mod.addAttribute("booked",booked);
		return"index";
	}
	
	@GetMapping({"/about"})
	public String about() {
		return "about";
	}
	
	@GetMapping({"/service"})
	public String service() {
		return "service";
	}
	
	@GetMapping({"/menu"})
	public String menu() {
		return "menu";
	}

	@GetMapping({"/reservation"})
	public String reservation(Model mod) {
		Booked booked=new Booked(
				1,"Hem SengHongly",
				"087348554",
				"hongly@gmail.com",
				"05/25/2025",
				"9:20 PM",
				5
				);
		mod.addAttribute("booked",booked);
		
		return "reservation";
	}
	

	@GetMapping({"/testimonial"})
	public String testimonial() {
		return "testimonial";
	}
	
	@GetMapping({"/contact"})
	public String contact() {
		return "contact";
	}
	@Autowired
	private BookedRepo bookedrepo;
	
	@Autowired
	private MyTelegramBot bot;
	
	
	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked) {
		bookedrepo.save(booked);
		String message = "Booking Comfirmed! ✅ " + "\n"+"\n" +
						 "🆔ID: " + booked.getId() + "\n" +
		                 "👤Name: " + booked.getName() + "\n" +
		                 "📱Phone: " + booked.getPhonenumber() + "\n" +
		                 "📧Email: " + booked.getEmail() + "\n" +
		                 "📅 Date: " + booked.getDate() + "\n" +
		                 "🕐Time: " + booked.getTime() + "\n" +
		                 "👥Person: " + booked.getPerson();
		bot.sendMessage(message);
		return "success";
	}



}
