package com.br.resistenem.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.resistenem.model.Administrador;
import com.br.resistenem.model.User;
import com.br.resistenem.repository.AdministradorRepository;
import com.br.resistenem.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository us;
	@Autowired
	private AdministradorRepository adm;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String User() {
		return "/login/user";
		
	}
	/*
	    @RequestMapping(value="/user", method=RequestMethod.POST)
	    public String efetuarLogin(Model model, Administrador admParam) {
	    	Administrador adm=this.efetuarLogin(admParam.getEmail(),Administrador.getSenha());
	    	if(adm != null) {
	    		return "redirect:/area/areas";
	    	}
	    	return "login/index";
	    }
	
	private Administrador efetuarLogin(String email, String senha) {
			// TODO Auto-generated method stub
			return null;
		}*/

		@RequestMapping(value="/efetuarLogin", method=RequestMethod.POST)
		public String efetuarLogin(Model model, Administrador AdministradorParam, RedirectAttributes attibutes, 
				Object AdministradorAutenticado, Administrador entAdministradorAutenticado) throws ServletException {
			Administrador email = new Administrador();
			if (email == null || Administrador.getSenha() == null){
				throw new ServletException("Nome ou senha inválidos");
			}
				//compara usuario digitado com o banco		
			if(entAdministradorAutenticado == null) {
				throw new ServletException("Usuário não encontrado");
			}
			
			//compara a senha digitada na tela com o banco de dados
			if(!Administrador.getSenha().equals(Administrador.getSenha())) {
				throw new ServletException("Senha inválida");
			}else {
				return "/Administrador/administradores";
			}
			
				
		
						}
	
	@RequestMapping("/index")
	public ModelAndView listaUser() {
		ModelAndView mvUser = new ModelAndView("user");
		Iterable<User> user = us.findAll();
		mvUser.addObject("User", user);
		return mvUser;
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ModelAndView editarUser(@PathVariable("id") String id) {
		User user = us.findAllById(id);
		ModelAndView mvUser = new ModelAndView("user");
		mvUser.addObject("User", user);
		return mvUser;
	}
	
}
