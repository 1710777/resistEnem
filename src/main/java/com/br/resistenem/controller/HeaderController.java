package com.br.resistenem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Header;

@Controller
public class HeaderController {

	@RequestMapping(value="/Login", method=RequestMethod.GET)
	public ModelAndView header(Header header, RedirectAttributes attibutes, HttpSession session) {
		ModelAndView mvHeader = new ModelAndView("index");
		session.setAttribute("isLogado", false);
		return mvHeader;
	}
}
