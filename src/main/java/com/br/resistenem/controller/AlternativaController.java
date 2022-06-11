package com.br.resistenem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Alternativa;
import com.br.resistenem.model.Questao;
import com.br.resistenem.repository.AlternativaRepository;
import com.br.resistenem.repository.QuestaoRepository;

@Controller
public class AlternativaController {

	@Autowired
	private AlternativaRepository ar;
	@Autowired
	private QuestaoRepository qr;
	
	@RequestMapping(value="/alternativa/insertAlternativa", method=RequestMethod.POST)
	public String insertAlternativa(Alternativa alternativa, RedirectAttributes attibutes) {
		if ("".equals(alternativa.getAlternativa()) || "".equals(alternativa.getIdQuestao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
		}
		ar.save(alternativa);
		attibutes.addFlashAttribute("menssagem", "Alternativa incluida com sucesso!");
		return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
	}
	
	@RequestMapping(value="/alternativa/updateAlternativa", method=RequestMethod.POST)
	public String updateAlternativa(Alternativa alternativa, RedirectAttributes attibutes) {
		if ("".equals(alternativa.getAlternativa()) || "".equals(alternativa.getIdQuestao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/alternativa/insertAlternativa";
		}
		ar.save(alternativa);
		attibutes.addFlashAttribute("menssagem", "Alternativa atualizada com sucesso!");
		return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
	}
	
	@RequestMapping(value="/alternativa/editarAlternativa/{id}", method=RequestMethod.GET)
	public String editarAlternativa(@PathVariable("id") String id) {
		return "redirect:/questao/editarAlternativa/"+id;

	}
	
	@RequestMapping("/alternativa/excluirAlternativa/{id}")
	public String excluirAlternativa(@PathVariable("id") String id, RedirectAttributes attibutes) {
		Alternativa alternativa = ar.findAllById(id);
		ar.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Alternativa excluida com sucesso!");
		attibutes.addFlashAttribute("sucesso", true);

		return "redirect:/questao/insertAlternativa/"+alternativa.getIdQuestao();
	}
	
}
