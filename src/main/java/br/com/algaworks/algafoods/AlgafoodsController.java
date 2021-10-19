package br.com.algaworks.algafoods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlgafoodsController {
	
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "Hello!!!";
	}
}
