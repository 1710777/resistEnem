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

import com.br.resistenem.model.Alternativa;
import com.br.resistenem.model.Area;
import com.br.resistenem.model.Dificuldades;
import com.br.resistenem.model.Questao;
import com.br.resistenem.repository.AlternativaRepository;
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
	private AlternativaRepository altr;
	@Autowired
	private DificuldadesRepository dr;
	
	@RequestMapping(value="/questao/insertQuestao", method=RequestMethod.GET)
	public ModelAndView insertQuestao(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		Questao questao = new Questao();
		List<Area> areas = ar.findByStatus(true);
		questao.setAreas(areas);
		List<Dificuldades> dificuldades = dr.findByStatus(true);
		questao.setListaDificuldades(dificuldades);
		ModelAndView mvQuestao = new ModelAndView("questao/insertQuestao");
		mvQuestao.addObject("Questao", questao);
		return mvQuestao;
	}
	
	@RequestMapping(value="/questao/insertQuestao", method=RequestMethod.POST)
	public String insertQuestao(Questao Questao, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(Questao.getPergunta()) || "".equals(Questao.getExplicacao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/questao/insertQuestao";
		}
		qr.save(Questao);
		attibutes.addFlashAttribute("menssagem", "Questao incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/questao/insertQuestao";
	}
	
	@RequestMapping(value="/questao/updateQuestao", method=RequestMethod.POST)
	public String updateQuestao(Questao Questao, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(Questao.getPergunta()) || "".equals(Questao.getExplicacao())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/questao/editarQuestao/"+Questao.getId();
		}
		qr.save(Questao);
		attibutes.addFlashAttribute("menssagem", "Questao atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/questao/editarQuestao/"+Questao.getId();
	}
	
	@RequestMapping("/questao/questoes")
	public ModelAndView listaQuestao(HttpSession session) {
		
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
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
	public ModelAndView editarQuestao(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		Questao questao = qr.findAllById(id);
		List<Area> areas = ar.findByStatus(true);
		questao.setAreas(areas);
		List<Dificuldades> dificuldades = dr.findByStatus(true);
		questao.setListaDificuldades(dificuldades);
		ModelAndView mvQuestao = new ModelAndView("questao/editQuestao");
		mvQuestao.addObject("Questao", questao);
		return mvQuestao;
	}
	
	@RequestMapping("/questao/excluirQuestao/{id}")
	public String excluirQuestao(@PathVariable("id") String id, HttpSession session, RedirectAttributes attibutes) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		qr.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Dificuldade excluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/questao/questoes";
	}
	
	@RequestMapping("/questao/publicarQuestao/{id}")
	public String publicarQuestao(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		Questao QuestaoNew = qr.findAllById(id);
		QuestaoNew.setStatus("false".equals(QuestaoNew.getStatus().toString())?true:false);
		qr.save(QuestaoNew);
		return "redirect:/questao/questoes";
	}
	
	@RequestMapping(value="/questao/insertAlternativa/{id}", method=RequestMethod.GET)
	public ModelAndView insertAlternativa(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		Questao questao = qr.findAllById(id);
		ModelAndView mvAlternativa = new ModelAndView("/alternativa/insertAlternativa");
		mvAlternativa.addObject("Questao", questao);
		List<Alternativa> alternativa = altr.findAllByIdQuestao(id);
		mvAlternativa.addObject("Alternativas", alternativa);
		return mvAlternativa;
	}
	
	@RequestMapping(value="/questao/editarAlternativa/{id}", method=RequestMethod.GET)
	public ModelAndView editarAlternativa(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		Alternativa alternativa = altr.findAllById(id);
		Questao questao = qr.findAllById(alternativa.getIdQuestao());
		ModelAndView mvAlternativa = new ModelAndView("/alternativa/editAlternativa");
		mvAlternativa.addObject("Questao", questao);
		mvAlternativa.addObject("Alternativas", alternativa);
		return mvAlternativa;
	}
	
}
