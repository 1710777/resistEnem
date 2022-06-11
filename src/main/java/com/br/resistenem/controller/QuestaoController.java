package com.br.resistenem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Area;
import com.br.resistenem.model.Dificuldades;
import com.br.resistenem.model.Questao;
import com.br.resistenem.repository.AreaRepository;
import com.br.resistenem.repository.DificuldadesRepository;
import com.br.resistenem.repository.QuestaoRepository;

@Controller
public class QuestaoController {

	@Autowired
	private QuestaoRepository qr;
	@Autowired
	private AreaRepository ar;
	@Autowired
	private DificuldadesRepository dr;
	
	@RequestMapping(value="/questao/insertQuestao", method=RequestMethod.GET)
	public ModelAndView insertQuestao() {
		Questao questao = new Questao();
		List<Area> areas = ar.findAll();
		questao.setAreas(areas);
		List<Dificuldades> dificuldades = dr.findAll();
		questao.setListaDificuldades(dificuldades);
		ModelAndView mvQuestao = new ModelAndView("questao/insertQuestao");
		mvQuestao.addObject("Questao", questao);
		return mvQuestao;
	}
	
	@RequestMapping(value="/questao/insertQuestao", method=RequestMethod.POST)
	public String insertQuestao(Questao Questao, RedirectAttributes attibutes) {
		if ("".equals(Questao.getPergunta()) || "".equals(Questao.getExplicacao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/questao/insertQuestao";
		}
		qr.save(Questao);
		attibutes.addFlashAttribute("menssagem", "Questao incluida com sucesso!");
		return "redirect:/questao/insertQuestao";
	}
	
	@RequestMapping(value="/questao/updateQuestao", method=RequestMethod.POST)
	public String updateQuestao(Questao Questao, RedirectAttributes attibutes) {
		if ("".equals(Questao.getPergunta()) || "".equals(Questao.getExplicacao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			return "redirect:/questao/editarQuestao/"+Questao.getId();
		}
		qr.save(Questao);
		attibutes.addFlashAttribute("menssagem", "Questao atualizada com sucesso!");
		return "redirect:/questao/editarQuestao/"+Questao.getId();
	}
	
	@RequestMapping("/questao/questoes")
	public ModelAndView listaQuestao() {
		ModelAndView mvQuestao = new ModelAndView("questao/questao");
		Iterable<Questao> Questaos = qr.findAll();
		for (Questao questao : Questaos) {
			Area area = ar.findAllById(questao.getIdArea());
			questao.setArea(area);
			Dificuldades diciduldade = dr.findAllById(questao.getIdDificuldade());
			questao.setDificuldade(diciduldade);
		}
		mvQuestao.addObject("Questoes", Questaos);
		return mvQuestao;
	}
	
	@RequestMapping("/questao/editarQuestao/{id}")
	public ModelAndView editarQuestao(@PathVariable("id") String id) {
		Questao questao = qr.findAllById(id);
		List<Area> areas = ar.findAll();
		questao.setAreas(areas);
		List<Dificuldades> dificuldades = dr.findAll();
		questao.setListaDificuldades(dificuldades);
		ModelAndView mvQuestao = new ModelAndView("questao/editQuestao");
		mvQuestao.addObject("Questao", questao);
		return mvQuestao;
	}
	
	@RequestMapping("/questao/excluirQuestao/{id}")
	public String excluirQuestao(@PathVariable("id") String id) {
		Questao QuestaoNew = qr.findAllById(id);
		QuestaoNew.setStatus(false);
		qr.save(QuestaoNew);
		return "redirect:/questao/questoes";
	}
	
	@RequestMapping("/questao/publicarQuestao/{id}")
	public String publicarQuestao(@PathVariable("id") String id) {
		Questao QuestaoNew = qr.findAllById(id);
		QuestaoNew.setStatus(true);
		qr.save(QuestaoNew);
		return "redirect:/questao/questoes";
	}
}
