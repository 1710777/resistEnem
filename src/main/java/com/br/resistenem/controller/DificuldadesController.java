package com.br.resistenem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Dificuldades;
import com.br.resistenem.repository.DificuldadesRepository;

@Controller
public class DificuldadesController {

	@Autowired
	private DificuldadesRepository dr;
	
	@RequestMapping(value="/dificuldade/insertDificuldade", method=RequestMethod.GET)
	public String cadastrarDificuldades(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		return "/dificuldades/insertDificuldades";
	}
	
	@RequestMapping(value="/dificuldade/insertDificuldade", method=RequestMethod.POST)
	public String insertDificuldades(Dificuldades Dificuldades, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(Dificuldades.getDificuldade())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/dificuldade/insertDificuldade";
		}
		dr.save(Dificuldades);
		attibutes.addFlashAttribute("menssagem", "Dificuldades incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/dificuldade/insertDificuldade";
	}
	
	@RequestMapping(value="/dificuldade/updateDificuldades", method=RequestMethod.POST)
	public String updateDificuldades(Dificuldades Dificuldades, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(Dificuldades.getDificuldade())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/dificuldade/editarDificuldades/"+Dificuldades.getId();
		}
		dr.save(Dificuldades);
		attibutes.addFlashAttribute("menssagem", "Dificuldades atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/dificuldade/editarDificuldades/"+Dificuldades.getId();
	}
	
	@RequestMapping("/dificuldade/dificuldades")
	public ModelAndView listaDificuldades(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		ModelAndView mvDificuldades = new ModelAndView("dificuldades/dificuldades");
		Iterable<Dificuldades> Dificuldadess = dr.findAll();
		mvDificuldades.addObject("Dificuldades", Dificuldadess);
		return mvDificuldades;
	}
	
	@RequestMapping("/dificuldade/editarDificuldades/{id}")
	public ModelAndView editarDificuldades(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		Dificuldades Dificuldades = dr.findAllById(id);
		ModelAndView mvDificuldades = new ModelAndView("Dificuldades/editDificuldades");
		mvDificuldades.addObject("Dificuldades", Dificuldades);
		return mvDificuldades;
	}
	
	@RequestMapping("/dificuldade/excluirDificuldades/{id}")
	public String excluirDificuldades(@PathVariable("id") String id, HttpSession session, RedirectAttributes attibutes) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		dr.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Dificuldade excluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/dificuldade/dificuldades";
	}
	
	@RequestMapping("publicarDificuldades/{id}")
	public String publicarDificuldades(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		Dificuldades DificuldadesNew = dr.findAllById(id);
		DificuldadesNew.setStatus("false".equals(DificuldadesNew.getStatus().toString())?true:false);
		dr.save(DificuldadesNew);
		return "redirect:/dificuldade/dificuldades";
	}
}
