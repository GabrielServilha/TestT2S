package com.projeto.porto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.porto.models.Cliente;
import com.projeto.porto.models.Conteiners;
import com.projeto.porto.repository.ClienteRepository;
import com.projeto.porto.repository.ConteinerRepository;

@Controller
public class CadastroClienteController {

	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private ConteinerRepository cor;
		
	@RequestMapping(value="/cadastrocliente", method=RequestMethod.GET)
	public String form() {
		return "cadastrocliente";
	}
	
	@RequestMapping(value="/cadastrocliente", method=RequestMethod.POST)
	public String form(Cliente cliente) {
		cr.save(cliente);
		
		return "redirect:/cadastrocliente";
	}
	
	@RequestMapping("/listaCliente")
	public ModelAndView listaCliente() {
		ModelAndView mv = new ModelAndView("conteiner/listaCliente");
		Iterable<Cliente> clientes = cr.findAll();
				mv.addObject("clientes", clientes);
				return mv;
	}
	
	@RequestMapping(value="/{rg}", method=RequestMethod.GET)
	public ModelAndView cadastrarConteiner(@PathVariable("rg") long rg) {
		Cliente cliente = cr.findByRg(rg);
		ModelAndView mv = new ModelAndView("conteiner/cadastrarConteiner");
		mv.addObject("cliente", cliente);
		
		Iterable<Conteiners> conteiners = cor.findByCliente(cliente);
		mv.addObject("conteiners", conteiners);
		
		return mv;
	}
	

	@RequestMapping(value="/{rg}", method=RequestMethod.POST)
	public String cadastrarConteiner(@PathVariable("rg") long rg, Conteiners conteiners) {
		Cliente cliente = cr.findByRg(rg);
		conteiners.setCliente(cliente);
		cor.save(conteiners);
		return "redirect:/{rg}";
	}
	
	@RequestMapping(value="/detalhesConteiner/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesConteiner(@PathVariable("codigo") String codigo) {
		Conteiners conteiners = cor.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("conteiner/detalhesConteiner");
		mv.addObject("conteiners", conteiners);
		return mv;
	}
	@RequestMapping("/deletarConteiner/{codigo}")
	public String deletarConteiner(@PathVariable("codigo") String codigo){
		Conteiners conteiners = cor.findByCodigo(codigo);
		cor.delete(conteiners);
		
		Cliente cliente = conteiners.getCliente();
		long rglong = cliente.getRg();
		String rg = "" + rglong;
		return "redirect:/" + rg;
	}
	
	@RequestMapping("/deletarCliente/{rg}")
	public String deletarCliente(@PathVariable("rg") long rg){
		Cliente cliente = cr.findByRg(rg);
		cr.delete(cliente);
		return "redirect:/listaCliente";
	}
}