package com.alberto.rallyslot.controller.vista;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.rallyslot.controller.BaseController;

@RestController
public class VistaController extends BaseController {

	@GetMapping("/index")
	public String home() {
		return "index";
	}

}
