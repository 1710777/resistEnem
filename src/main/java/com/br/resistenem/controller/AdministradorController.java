package com.br.resistenem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Administrador;
import com.br.resistenem.repository.AdministradorRepository;

@Controller
public class AdministradorController {

	@Autowired
	private AdministradorRepository admr;

	
	@RequestMapping(value="/administrador/insertAdministrador", method=RequestMethod.GET)
	public ModelAndView insertArea(HttpSession session) {
		ModelAndView mvADM = null;
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			mvADM = new ModelAndView("Administrador/Login");
		}else {
			mvADM = new ModelAndView("/administrador/insertAdministrador");
		}
		return mvADM;
	}
	
	@RequestMapping(value="/administrador/insertAdministrador", method=RequestMethod.POST)
	public String insertArea(Administrador administrador, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		if ("".equals(administrador.getNome()) || "".equals(administrador.getEmail()) || "".equals(administrador.getSenha()) || "".equals(administrador.getUsuario()) ) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/administrador/insertAdministrador";
		}
		admr.save(administrador);
		attibutes.addFlashAttribute("menssagem", "Administrador incluida com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/administrador/insertAdministrador";
	}
	
	@RequestMapping(value="/administrador/updateAdministrador", method=RequestMethod.POST)
	public String updateAdministrador(Administrador administrador, RedirectAttributes attibutes, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";

		}
		if ("".equals(administrador.getNome()) || "".equals(administrador.getEmail()) || "".equals(administrador.getSenha()) || "".equals(administrador.getUsuario()) ) {
			attibutes.addFlashAttribute("menssagem", "verifique os campos!");
			attibutes.addFlashAttribute("error", true);
			return "redirect:/administrador/editarAdministrador/"+administrador.getId();
		}
		admr.save(administrador);
		attibutes.addFlashAttribute("menssagem", "Administrador atualizada com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/administrador/editarAdministrador/"+administrador.getId();
	}
	
	@RequestMapping("/administrador/administradores")
	public ModelAndView administradores(HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvadministrador = new ModelAndView("Administrador/Login");
			return mvadministrador;
		}

		ModelAndView mvadministrador = new ModelAndView("Administrador/administrador");
		Iterable<Administrador> administradores = admr.findAll();
		mvadministrador.addObject("Administradores", administradores);
		return mvadministrador;
	}
	
	@RequestMapping(value="/administrador/editarAdministrador/{id}", method=RequestMethod.GET)
	public ModelAndView editarAdministrador(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			ModelAndView mvadministrador = new ModelAndView("Administrador/Login");
			return mvadministrador;
		}
		Administrador administrador = admr.findAllById(id);
		ModelAndView mvadministrador = new ModelAndView("administrador/editAdministrador");
		mvadministrador.addObject("Administrador", administrador);
		return mvadministrador;
	}
	
	@RequestMapping("/administrador/excluirAdministrador/{id}")
	public String excluirAdministrador(@PathVariable("id") String id, HttpSession session, RedirectAttributes attibutes) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		admr.deleteById(id);
		attibutes.addFlashAttribute("menssagem", "Administrador excluido com sucesso!");
		attibutes.addFlashAttribute("error", false);
		return "redirect:/administrador/administradores";
	}
	
	@RequestMapping("/administrador/publicarAdministrador/{id}")
	public String publicarAdministrador(@PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("isLogado") == null || "false".equals(session.getAttribute("isLogado").toString())) {
			return "redirect:/";
		}
		Administrador administradorNew = admr.findAllById(id);
		administradorNew.setStatus("false".equals(administradorNew.getStatus().toString())?true:false);
		admr.save(administradorNew);
		return "redirect:/administrador/administradores";
	}
	
	@RequestMapping("/administrador/logoff")
	public String logoff(HttpSession session) {
		session.setAttribute("ADM", null);
		session.setAttribute("isLogado", null);
		return "redirect:/";
	}

	@RequestMapping("/administrador/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mvadministrador = new ModelAndView("/home");
		return mvadministrador;
	}
	
	@RequestMapping(value="/administrador/efetuarLogin", method=RequestMethod.POST)
	public String efetuarLogin(Administrador administrador, RedirectAttributes attibutes, HttpSession session){
		if (administrador.getUsuario() == null){
			session.setAttribute("isLogado", false);
			Administrador administradorNew = admr.findByEmail(administrador.getEmail());
			if(administradorNew == null) {
				attibutes.addFlashAttribute("menssagem", "Usu??rio n??o encontrado!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (!administradorNew.getStatus()){
				attibutes.addFlashAttribute("menssagem", "Usu??rio informado desativado!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (administrador.getEmail() == null){
				attibutes.addFlashAttribute("menssagem", "Email n??o informado!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (administrador.getSenha() == null){
				attibutes.addFlashAttribute("menssagem", "Senha n??o informada!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (!administrador.getEmail().equals(administradorNew.getEmail())){
				attibutes.addFlashAttribute("menssagem", "Email informado incorreto!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (!administrador.getSenha().equals(administradorNew.getSenha())){
				attibutes.addFlashAttribute("menssagem", "Senha informada incorreta!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else {
				session.setAttribute("ADM", administradorNew);
				session.setAttribute("isLogado", true);
				attibutes.addFlashAttribute("error", false);
				return "redirect:/administrador/home";
			}
		}
		return "redirect:/";
	}
}
