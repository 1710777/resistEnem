package com.br.resistenem.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="/area/insertArea", method=RequestMethod.GET)
	public ModelAndView insertArea(HttpSession session) {
		ModelAndView mvArea = null;
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			mvArea = new ModelAndView("Administrador/Login");
		}else {
			mvArea = new ModelAndView("/area/insertArea");
		}
		return mvArea;

	}
	
	@RequestMapping(value="/area/insertArea", method=RequestMethod.POST)
	public String insertArea(Area area, RedirectAttributes attibutes) {
		if ("".equals(area.getArea())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/area/insertArea";
		}
		ar.save(area);
		attibutes.addFlashAttribute("menssagem", "Area incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/area/insertArea";
	}
	
	@RequestMapping(value="/area/updateArea", method=RequestMethod.POST)
	public String updateArea(Area area, RedirectAttributes attibutes) {
		if ("".equals(area.getArea())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/area/editarArea/"+area.getId();
		}
		ar.save(area);
		attibutes.addFlashAttribute("menssagem", "Area atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/area/editarArea/"+area.getId();
	}
	
	@RequestMapping("/area/areas")
	public ModelAndView listaArea(HttpSession session) {
		ModelAndView mvArea = null;
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		mvArea = new ModelAndView("area/area");
		Iterable<Area> areas = ar.findAll();
		mvArea.addObject("Areas", areas);
		return mvArea;
	}
	
	@RequestMapping(value="/area/editarArea/{id}", method=RequestMethod.GET)
	public ModelAndView editarArea(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		Area area = ar.findAllById(id);
		ModelAndView mvArea = new ModelAndView("area/editArea");
		mvArea.addObject("Area", area);
		return mvArea;
	}
	
	@RequestMapping("/area/excluirArea/{id}")
	public String excluirArea(@PathVariable("id") String id, HttpSession session, RedirectAttributes attibutes) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		ar.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Area excluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/area/areas";
	}
	
	@RequestMapping("/area/publicarArea/{id}")
	public String publicarArea(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		Area areaNew = ar.findAllById(id);
		areaNew.setStatus("false".equals(areaNew.getStatus().toString())?true:false);
		ar.save(areaNew);
		return "redirect:/area/areas";
	}
}
