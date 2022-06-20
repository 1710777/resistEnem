package com.br.resistenem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Header;

@Controller
public class HeaderController {

	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@Scope("session")
	public ModelAndView header(Header header, RedirectAttributes attibutes, final HttpServletRequest request) {
		ModelAndView mvHeader = new ModelAndView("index");
		request.getSession().setAttribute("isLogado", true);
		return mvHeader;
	}
}
