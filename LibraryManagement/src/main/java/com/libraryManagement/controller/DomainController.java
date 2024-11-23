package com.libraryManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class DomainController {

	@GetMapping("/get-domain")
	public String getDomain(HttpServletRequest request) {

		String domainName = request.getServerName();

		int port = request.getServerPort();
		String fullDomain = domainName + (port != 80 && port != 443 ? ":" + port : "");

		return "Domain accessed: " + fullDomain;
	}
}
