package com.br.resistenem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Alternativa;
import com.br.resistenem.model.Dificuldades;
import com.br.resistenem.model.Questao;
import com.br.resistenem.model.TipoSimulado;
import com.br.resistenem.repository.DificuldadesRepository;
import com.br.resistenem.repository.TipoSimuladoRepository;

@Controller
public class TipoSimuladoController {

	@Autowired
	private TipoSimuladoRepository tr;
	@Autowired
	private DificuldadesRepository dr;
	
	@RequestMapping(value="/tipoSimulado/insertTipoSimulado", method=RequestMethod.GET)
	public ModelAndView insertTipoSimulado() {
		ModelAndView mvTipoSimulado = new ModelAndView("/tipoSimulado/insertTipoSimulado");
		mvTipoSimulado.addObject("Header", true);
		return mvTipoSimulado;
	}
	
	@RequestMapping(value="/tipoSimulado/insertTipoSimulado", method=RequestMethod.POST)
	public String insertTipoSimulado(TipoSimulado TipoSimulado, RedirectAttributes attibutes) {
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
	public String updateTipoSimulado(TipoSimulado TipoSimulado, RedirectAttributes attibutes) {
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
	public ModelAndView listaTipoSimulado() {
		ModelAndView mvTipoSimulado = new ModelAndView("tipoSimulado/TipoSimulados");
		Iterable<TipoSimulado> TipoSimulados = tr.findAll();
		mvTipoSimulado.addObject("TipoSimulados", TipoSimulados);
		mvTipoSimulado.addObject("Header", true);

		return mvTipoSimulado;
	}
	
	@RequestMapping(value="/tipoSimulado/editarTipoSimulado/{id}", method=RequestMethod.GET)
	public ModelAndView editarTipoSimulado(@PathVariable("id") String id) {
		TipoSimulado TipoSimulado = tr.findAllById(id);
		ModelAndView mvTipoSimulado = new ModelAndView("tipoSimulado/editarTipoSimulado");
		mvTipoSimulado.addObject("TipoSimulado", TipoSimulado);
		mvTipoSimulado.addObject("Header", true);
		return mvTipoSimulado;
	}
	
	@RequestMapping("/tipoSimulado/excluirTipoSimulado/{id}")
	public String excluirTipoSimulado(@PathVariable("id") String id) {
		TipoSimulado TipoSimuladoNew = tr.findAllById(id);
		TipoSimuladoNew.setStatus(false);
		tr.save(TipoSimuladoNew);
		return "redirect:/tipoSimulado/TipoSimulados";
	}
	
	@RequestMapping("/tipoSimulado/publicarTipoSimulado/{id}")
	public String publicarTipoSimulado(@PathVariable("id") String id) {
		TipoSimulado TipoSimuladoNew = tr.findAllById(id);
		TipoSimuladoNew.setStatus(true);
		tr.save(TipoSimuladoNew);
		return "redirect:/tipoSimulado/TipoSimulados";
	}
	
	@RequestMapping(value="/tipoSimulado/configuraSimulado/{id}", method=RequestMethod.GET)
	public ModelAndView configuraSimulado(@PathVariable("id") String id) {
		TipoSimulado tipoSimulado = tr.findAllById(id);
		ModelAndView mvConfiguracaoSimulado = new ModelAndView("/simulado/insertConfiguracaoSimulado");
		mvConfiguracaoSimulado.addObject("TipoSimulado", tipoSimulado);
		List<Dificuldades> dificuldades = dr.findAll();
		mvConfiguracaoSimulado.addObject("Dificuldades", dificuldades);
		mvConfiguracaoSimulado.addObject("Header", true);
		return mvConfiguracaoSimulado;
	}
}
