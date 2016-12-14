package com.niit.collaborationbackend.controller;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String hello(Model model) {

		model.addAttribute("name", "Ravi Gaikwad");

		return "Hi I am Ravi and I am not Stupid";
	}
	@GetMapping("/Home")
	public String hello1(Model model) {

		model.addAttribute("name", "Ravi Gaikwad");

		return "Hi I am Mughada I am very Stupid";
	}
}