package com.br.resistenem.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String insertArea() {
		return "/administrador/insertAdministrador";
	}
	
	@RequestMapping(value="/administrador/insertAdministrador", method=RequestMethod.POST)
	public String insertArea(Administrador administrador, RedirectAttributes attibutes) {
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
	public String updateAdministrador(Administrador administrador, RedirectAttributes attibutes) {
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
	public ModelAndView administradores() {
		ModelAndView mvadministrador = new ModelAndView("Administrador/administrador");
		Iterable<Administrador> administradores = admr.findAll();
		mvadministrador.addObject("Administradores", administradores);
		mvadministrador.addObject("Header", true);
		return mvadministrador;
	}
	
	@RequestMapping(value="/administrador/editarAdministrador/{id}", method=RequestMethod.GET)
	public ModelAndView editarAdministrador(@PathVariable("id") String id) {
		Administrador administrador = admr.findAllById(id);
		ModelAndView mvadministrador = new ModelAndView("administrador/editAdministrador");
		mvadministrador.addObject("Administrador", administrador);
		return mvadministrador;
	}
	
	@RequestMapping("/administrador/excluirAdministrador/{id}")
	public String excluirAdministrador(@PathVariable("id") String id) {
		Administrador administradorNew = admr.findAllById(id);
		administradorNew.setStatus(false);
		admr.save(administradorNew);
		return "redirect:/administrador/administradores";
	}
	
	@RequestMapping("/administrador/publicarAdministrador/{id}")
	public String publicarAdministrador(@PathVariable("id") String id) {
		Administrador administradorNew = admr.findAllById(id);
		administradorNew.setStatus(true);
		admr.save(administradorNew);
		return "redirect:/administrador/administradores";
	}
	
	@RequestMapping(value="/efetuarLogin", method=RequestMethod.POST)
	public String efetuarLogin(Administrador administrador, RedirectAttributes attibutes){
		if (administrador.getUsuario() == null){
			Administrador administradorNew = admr.findByEmail(administrador.getEmail());
			if(administradorNew == null) {
				attibutes.addFlashAttribute("menssagem", "Usuário não encontrado!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (administrador.getEmail() == null){
				attibutes.addFlashAttribute("menssagem", "Email não informado!");
				attibutes.addFlashAttribute("error", true);
				return "redirect:/";
			}else if (administrador.getSenha() == null){
				attibutes.addFlashAttribute("menssagem", "Senha não informada!");
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
				attibutes.addFlashAttribute("error", false);
				return "redirect:/administrador/administradores";
			}
		}
		return "redirect:/";
	}
}
