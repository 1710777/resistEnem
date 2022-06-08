package com.br.resistenem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String insertArea(Area area, RedirectAttributes attibutes) {
		if ("".equals(area.getArea())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/insertArea";
		}
		ar.save(area);
		attibutes.addFlashAttribute("menssagem", "Area incluida com sucesso!");
		return "redirect:/insertArea";
	}
	
	@RequestMapping(value="updateArea", method=RequestMethod.POST)
	public String updateArea(Area area, RedirectAttributes attibutes) {
		if ("".equals(area.getArea())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/updateArea";
		}
		ar.save(area);
		attibutes.addFlashAttribute("menssagem", "Area atualizada com sucesso!");
		return "redirect:/"+area.getId();
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
	
	@RequestMapping("excluirArea/{id}")
	public String excluirArea(@PathVariable("id") String id) {
		Area areaNew = ar.findAllById(id);
		areaNew.setStatus(false);
		ar.save(areaNew);
		return "redirect:/areas";
	}
	
	@RequestMapping("publicarArea/{id}")
	public String publicarArea(@PathVariable("id") String id) {
		Area areaNew = ar.findAllById(id);
		areaNew.setStatus(true);
		ar.save(areaNew);
		return "redirect:/areas";
	}
}
