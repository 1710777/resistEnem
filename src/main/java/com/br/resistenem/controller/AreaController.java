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
	
	@RequestMapping(value="updateArea", method=RequestMethod.POST)
	public String updateArea(Area area) {
		ar.save(area);
		return "redirect:/Areas";
	}
	
	@RequestMapping("/areas")
	public ModelAndView listaArea() {
		ModelAndView mvArea = new ModelAndView("area/area");
		Iterable<Area> areas = ar.findAll();
		mvArea.addObject("Areas", areas);
		return mvArea;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView detalheArea(@PathVariable("id") String id) {
		Optional<Area> area = ar.findById(id);
		Area area1 = ar.findAllById(id);
		ModelAndView mvArea = new ModelAndView("area/editArea");
		mvArea.addObject("Area", area1);
		return mvArea;
	}
	
	@RequestMapping("excluir/{id}")
	public String excluirArea(@PathVariable("id") String id) {
		Optional<Area> area = ar.findById(id);
		ar.deleteById(id);
		return "redirect:/Areas";
	}
}
