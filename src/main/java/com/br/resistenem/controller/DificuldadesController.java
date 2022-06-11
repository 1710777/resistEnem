package com.br.resistenem.controller;

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
	public String cadastrarDificuldades() {
		return "/dificuldades/insertDificuldades";
	}
	
	@RequestMapping(value="/dificuldade/insertDificuldade", method=RequestMethod.POST)
	public String insertDificuldades(Dificuldades Dificuldades, RedirectAttributes attibutes) {
		if ("".equals(Dificuldades.getDificuldade())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/dificuldade/insertDificuldade";
		}
		dr.save(Dificuldades);
		attibutes.addFlashAttribute("menssagem", "Dificuldades incluida com sucesso!");
		return "redirect:/dificuldade/insertDificuldade";
	}
	
	@RequestMapping(value="/dificuldade/updateDificuldades", method=RequestMethod.POST)
	public String updateDificuldades(Dificuldades Dificuldades, RedirectAttributes attibutes) {
		if ("".equals(Dificuldades.getDificuldade())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/dificuldade/editarDificuldades/"+Dificuldades.getId();
		}
		dr.save(Dificuldades);
		attibutes.addFlashAttribute("menssagem", "Dificuldades atualizada com sucesso!");
		return "redirect:/dificuldade/editarDificuldades/"+Dificuldades.getId();
	}
	
	@RequestMapping("/dificuldade/dificuldades")
	public ModelAndView listaDificuldades() {
		ModelAndView mvDificuldades = new ModelAndView("dificuldades/dificuldades");
		Iterable<Dificuldades> Dificuldadess = dr.findAll();
		mvDificuldades.addObject("Dificuldades", Dificuldadess);
		return mvDificuldades;
	}
	
	@RequestMapping("/dificuldade/editarDificuldades/{id}")
	public ModelAndView editarDificuldades(@PathVariable("id") String id) {
		Dificuldades Dificuldades = dr.findAllById(id);
		ModelAndView mvDificuldades = new ModelAndView("Dificuldades/editDificuldades");
		mvDificuldades.addObject("Dificuldades", Dificuldades);
		return mvDificuldades;
	}
	
	@RequestMapping("/dificuldade/excluirDificuldades/{id}")
	public String excluirDificuldades(@PathVariable("id") String id) {
		Dificuldades DificuldadesNew = dr.findAllById(id);
		DificuldadesNew.setStatus(false);
		dr.save(DificuldadesNew);
		return "redirect:/dificuldade/dificuldades";
	}
	
	@RequestMapping("publicarDificuldades/{id}")
	public String publicarDificuldades(@PathVariable("id") String id) {
		Dificuldades DificuldadesNew = dr.findAllById(id);
		DificuldadesNew.setStatus(true);
		dr.save(DificuldadesNew);
		return "redirect:/dificuldade/dificuldades";
	}
}
