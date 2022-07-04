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

import com.br.resistenem.model.ConfiguracaoSimulado;
import com.br.resistenem.model.Dificuldades;
import com.br.resistenem.model.Materia;
import com.br.resistenem.model.TipoSimulado;
import com.br.resistenem.repository.ConfiguracaoSimuladoRepository;
import com.br.resistenem.repository.DificuldadesRepository;
import com.br.resistenem.repository.MateriaRepository;
import com.br.resistenem.repository.TipoSimuladoRepository;

@Controller
public class TipoSimuladoController {

	@Autowired
	private TipoSimuladoRepository tr;
	@Autowired
	private DificuldadesRepository dr;
	@Autowired
	private ConfiguracaoSimuladoRepository csr;
	@Autowired
	private MateriaRepository mr;
	
	@RequestMapping(value="/tipoSimulado/insertTipoSimulado", method=RequestMethod.GET)
	public ModelAndView insertTipoSimulado(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		ModelAndView mvTipoSimulado = new ModelAndView("/tipoSimulado/insertTipoSimulado");
		return mvTipoSimulado;
	}
	
	@RequestMapping(value="/tipoSimulado/insertTipoSimulado", method=RequestMethod.POST)
	public String insertTipoSimulado(TipoSimulado TipoSimulado, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(TipoSimulado.getTipoSimulado())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/tipoSimulado/insertTipoSimulado";
		}
		tr.save(TipoSimulado);
		attibutes.addFlashAttribute("menssagem", "Tipo Simulado incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/tipoSimulado/insertTipoSimulado";
	}
	
	@RequestMapping(value="/tipoSimulado/updateTipoSimulado", method=RequestMethod.POST)
	public String updateTipoSimulado(TipoSimulado TipoSimulado, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(TipoSimulado.getTipoSimulado())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/tipoSimulado/editarTipoSimulado/"+TipoSimulado.getId();
		}
		tr.save(TipoSimulado);
		attibutes.addFlashAttribute("menssagem", "Tipo Simulado atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/tipoSimulado/editarTipoSimulado/"+TipoSimulado.getId();
	}
	
	@RequestMapping("/tipoSimulado/TipoSimulados")
	public ModelAndView listaTipoSimulado(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		ModelAndView mvTipoSimulado = new ModelAndView("tipoSimulado/TipoSimulados");
		Iterable<TipoSimulado> TipoSimulados = tr.findAll();
		mvTipoSimulado.addObject("TipoSimulados", TipoSimulados);
		return mvTipoSimulado;
	}
	
	@RequestMapping(value="/tipoSimulado/editarTipoSimulado/{id}", method=RequestMethod.GET)
	public ModelAndView editarTipoSimulado(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		TipoSimulado TipoSimulado = tr.findAllById(id);
		ModelAndView mvTipoSimulado = new ModelAndView("tipoSimulado/editarTipoSimulado");
		mvTipoSimulado.addObject("TipoSimulado", TipoSimulado);
		return mvTipoSimulado;
	}
	
	@RequestMapping("/tipoSimulado/excluirTipoSimulado/{id}")
	public String excluirTipoSimulado(@PathVariable("id") String id, HttpSession session, RedirectAttributes attibutes) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		tr.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Tipo Simulado excluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/tipoSimulado/TipoSimulados";
	}
	
	@RequestMapping("/tipoSimulado/publicarTipoSimulado/{id}")
	public String publicarTipoSimulado(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		TipoSimulado TipoSimuladoNew = tr.findAllById(id);
		TipoSimuladoNew.setStatus("false".equals(TipoSimuladoNew.getStatus().toString())?true:false);
		tr.save(TipoSimuladoNew);
		return "redirect:/tipoSimulado/TipoSimulados";
	}
	
	@RequestMapping(value="/tipoSimulado/insertConfigSimulado/{id}", method=RequestMethod.GET)
	public ModelAndView insertConfigSimulado(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvArea = new ModelAndView("Administrador/Login");
			return mvArea;
		}
		TipoSimulado tipoSimulado = tr.findAllById(id);
		ModelAndView mvConfiguracaoSimulado = new ModelAndView("/simulado/insertConfiguracaoSimulado");
		mvConfiguracaoSimulado.addObject("TipoSimulado", tipoSimulado);
		List<Dificuldades> dificuldades = dr.findByStatus(true);
		mvConfiguracaoSimulado.addObject("Dificuldades", dificuldades);
		List<ConfiguracaoSimulado> configSimulado = csr.findAll();
		List<Materia> materia = mr.findByStatus(true);
		mvConfiguracaoSimulado.addObject("Materia", materia);

		mvConfiguracaoSimulado.addObject("configSimulado", configSimulado);
		return mvConfiguracaoSimulado;
	}
}
