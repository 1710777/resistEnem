package com.br.resistenem.controller;

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
import com.br.resistenem.model.TipoSimulado;
import com.br.resistenem.repository.ConfiguracaoSimuladoRepository;
import com.br.resistenem.repository.DificuldadesRepository;
import com.br.resistenem.repository.TipoSimuladoRepository;

@Controller
public class ConfiguracaoSimuladoController {

	@Autowired
	private ConfiguracaoSimuladoRepository csr;
	@Autowired
	private TipoSimuladoRepository tr;
	@Autowired
	private DificuldadesRepository dr;
	

	@RequestMapping(value="/simulado/insertConfigSimulado", method=RequestMethod.POST)
	public String insertConfigSimulado(ConfiguracaoSimulado configuracaoSimulado, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(configuracaoSimulado.getDificuldades()) || "".equals(configuracaoSimulado.getTipoSimulado()) || "".equals(configuracaoSimulado.getQuantidade())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/tipoSimulado/configuraSimulado/"+configuracaoSimulado.getTipoSimulado().getId();
		}
		ConfiguracaoSimulado conf = csr.findAllByDificuldadesAndTipoSimulado(configuracaoSimulado.getDificuldades(),configuracaoSimulado.getTipoSimulado());
		if(conf != null && conf.getId() != null) {
			attibutes.addFlashAttribute("menssagem", "Ja existe um registro pra esta dificuldade e tipo de simulado!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/tipoSimulado/insertConfigSimulado/"+configuracaoSimulado.getTipoSimulado().getId();			
		} 
		TipoSimulado tipo =  tr.findTipoSimuladoById(configuracaoSimulado.getTipoSimulado().getId());
		Dificuldades dificuldade = dr.findDificuldadesById(configuracaoSimulado.getDificuldades().getId());
		configuracaoSimulado.setTipoSimulado(tipo);
		configuracaoSimulado.setDificuldades(dificuldade);
		csr.save(configuracaoSimulado);
		attibutes.addFlashAttribute("menssagem", "Configuração incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/tipoSimulado/insertConfigSimulado/"+configuracaoSimulado.getTipoSimulado().getId();	
	}
	@RequestMapping(value="/simulado/updateConfigSimulado", method=RequestMethod.POST)
	public String updateConfigSimulado(ConfiguracaoSimulado configSimulado, RedirectAttributes attibutes, HttpSession session) {

		if ("".equals(configSimulado.getDificuldades()) || "".equals(configSimulado.getTipoSimulado()) || "".equals(configSimulado.getQuantidade())) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/simulado/editarConfigSimulado/"+configSimulado.getId();
		}
		TipoSimulado tipo =  tr.findTipoSimuladoById(configSimulado.getTipoSimulado().getId());
		Dificuldades dificuldade = dr.findDificuldadesById(configSimulado.getDificuldades().getId());
		configSimulado.setTipoSimulado(tipo);
		configSimulado.setDificuldades(dificuldade);
		csr.save(configSimulado);
		attibutes.addFlashAttribute("menssagem", "Configuração atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/tipoSimulado/insertConfigSimulado/"+configSimulado.getTipoSimulado().getId();
	}
	
	@RequestMapping(value="/simulado/editarConfigSimulado/{id}", method=RequestMethod.GET)
	public ModelAndView editarConfigSimulado(@PathVariable("id") String id, HttpSession session) {
		ConfiguracaoSimulado configSimulado = csr.findAllById(id);
		ModelAndView mvConfigSimulado = new ModelAndView("simulado/editarConfiguracaoSimulado");
		mvConfigSimulado.addObject("Simulado", configSimulado);

		return mvConfigSimulado;

	}
	
	@RequestMapping("/simulado/excluirConfigSimulado/{id}")
	public String excluirConfigSimulado(@PathVariable("id") String id, RedirectAttributes attibutes, HttpSession session) {
		if ("false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		ConfiguracaoSimulado configSimulado = csr.findAllById(id);
		csr.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Configuração excluida com sucesso!");
		attibutes.addFlashAttribute("sucesso", true);

		return "redirect:/tipoSimulado/insertConfigSimulado/"+configSimulado.getTipoSimulado().getId();
	}
	
}
