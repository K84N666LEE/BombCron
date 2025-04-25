package com.src.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.vo.auto_send;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class AutoController {

	@Autowired
	public AutoService autoService;

	@Autowired
	public Environment env;

	@GetMapping("/")
	public String html_index(ModelMap model, auto_send auto) {
		log.info("Bomb Scheduler started");
		auto.setAst_fd_01("N");
		int total = this.autoService.auto_total(auto);
		model.addAttribute("total", Integer.valueOf(total));
		return "/index";
	}
}
