package com.br.resistenem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.resistenem.model.Area;
import com.br.resistenem.repository.AreaRepository;

@Controller
public class AreaController {

	@Autowired
	private AreaRepository ar;
	
	@RequestMapping(value="insertArea", method=RequestMethod.GET)
	public String insertArea() {
		return "area/frmArea";
	}
	
	@RequestMapping(value="insertArea", method=RequestMethod.POST)
	public String insertArea(Area area) {
		ar.save(area);
		return "redirect:/insertArea";
	}
	
	@RequestMapping("/areas")
	public ModelAndView listaArea() {
		ModelAndView mvArea = new ModelAndView("area/area");
		Iterable<Area> areas = ar.findAll();
		mvArea.addObject("Areas", areas);
		return mvArea;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalheArea(@PathVariable("id") String codigo) {
		Optional<Area> area = ar.findById(codigo);	
		ModelAndView mvArea = new ModelAndView("area/editArea");
		mvArea.addObject("Area", area);
		return mvArea;
	}
}
