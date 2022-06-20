package com.br.resistenem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Alternativa;
import com.br.resistenem.repository.AlternativaRepository;

@Controller
public class AlternativaController {

	@Autowired
	private AlternativaRepository ar;


	@RequestMapping(value="/alternativa/insertAlternativa", method=RequestMethod.POST)
	public String insertAlternativa(Alternativa alternativa, RedirectAttributes attibutes, HttpSession session) {
		if ("false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(alternativa.getAlternativa()) || "".equals(alternativa.getIdQuestao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
		}
		ar.save(alternativa);
		attibutes.addFlashAttribute("menssagem", "Alternativa incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
	}
	
	@RequestMapping(value="/alternativa/updateAlternativa", method=RequestMethod.POST)
	public String updateAlternativa(Alternativa alternativa, RedirectAttributes attibutes, HttpSession session) {
		if ("false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(alternativa.getAlternativa()) || "".equals(alternativa.getIdQuestao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/questao/editarAlternativa/"+alternativa.getId();
		}
		ar.save(alternativa);
		attibutes.addFlashAttribute("menssagem", "Alternativa atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
	}
	
	@RequestMapping(value="/alternativa/editarAlternativa/{id}", method=RequestMethod.GET)
	public String editarAlternativa(@PathVariable("id") String id, HttpSession session) {
		if ("false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		return "redirect:/questao/editarAlternativa/"+id;

	}
	
	@RequestMapping("/alternativa/excluirAlternativa/{id}")
	public String excluirAlternativa(@PathVariable("id") String id, RedirectAttributes attibutes, HttpSession session) {
		if ("false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		Alternativa alternativa = ar.findAllById(id);
		ar.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Alternativa excluida com sucesso!");
		attibutes.addFlashAttribute("sucesso", true);

		return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
	}
	
}
