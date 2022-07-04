package com.br.resistenem.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Area;
import com.br.resistenem.model.Materia;
import com.br.resistenem.repository.AreaRepository;
import com.br.resistenem.repository.MateriaRepository;

@Controller
public class MateriaController {

	@Autowired
	private MateriaRepository mr;
	@Autowired
	private AreaRepository ar;
	
	@RequestMapping(value="/materia/insertMateria", method=RequestMethod.GET)
	public ModelAndView insertMateria(HttpSession session) {
		ModelAndView mvMateria = null;
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			mvMateria = new ModelAndView("Administrador/Login");
		}else {
			List<Area> area = ar.findByStatus(true);
			mvMateria = new ModelAndView("/materia/insertMateria");
			mvMateria.addObject("Areas", area);
		}
		return mvMateria;

	}
	
	@RequestMapping(value="/materia/insertMateria", method=RequestMethod.POST)
	public String insertMateria(Materia materia, RedirectAttributes attibutes) {
		if ("".equals(materia.getMateria())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/materia/insertMateria";
		}
		mr.save(materia);
		attibutes.addFlashAttribute("menssagem", "Materia incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/materia/insertMateria";
	}
	
	@RequestMapping(value="/materia/updateMateria", method=RequestMethod.POST)
	public String updateMateria(Materia materia, RedirectAttributes attibutes) {
		if ("".equals(materia.getMateria())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/materia/editarMateria/"+materia.getId();
		}
		mr.save(materia);
		attibutes.addFlashAttribute("menssagem", "Materia atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/materia/editarMateria/"+materia.getId();
	}
	
	@RequestMapping("/materia/materias")
	public ModelAndView materias(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvLogin = new ModelAndView("Administrador/Login");
			return mvLogin;
		}
		ModelAndView mvMateria = new ModelAndView("materia/materia");
		Iterable<Materia> materias = mr.findAll();
		mvMateria.addObject("Materias", materias);
		return mvMateria;
	}
	
	@RequestMapping(value="/materia/editarMateria/{id}", method=RequestMethod.GET)
	public ModelAndView editarMateria(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvMateria = new ModelAndView("Administrador/Login");
			return mvMateria;
		}
		ModelAndView mvMateria = new ModelAndView("materia/editMateria");
		List<Area> area = ar.findByStatus(true);
		mvMateria.addObject("Areas", area);
		Materia materia = mr.findAllById(id);
		mvMateria.addObject("Materia", materia);
		return mvMateria;
	}
	
	@RequestMapping("/materia/excluirMateria/{id}")
	public String excluirMateria(@PathVariable("id") String id, HttpSession session, RedirectAttributes attibutes) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		mr.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Materia excluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/materia/materias";
	}
	
	@RequestMapping("/materia/publicarMateria/{id}")
	public String publicarMateria(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		Materia materiaNew = mr.findAllById(id);
		materiaNew.setStatus("false".equals(materiaNew.getStatus().toString())?true:false);
		mr.save(materiaNew);
		return "redirect:/materia/materias";
	}
}
