package com.br.resistenem.controller;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Administrador;
import com.br.resistenem.model.Header;
import com.br.resistenem.repository.AdministradorRepository;

@Controller
public class HeaderController {

	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView header(Header header, RedirectAttributes attibutes) {
		ModelAndView mvHeader = new ModelAndView("index");
		mvHeader.addObject("Header", false);
		return mvHeader;
	}
}
