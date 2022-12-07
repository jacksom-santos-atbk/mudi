package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/home")
	public String home(HttpServletRequest request, Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		
		request.setAttribute("pedidos", pedidos);		
		return "usuario/home";
	}
	
	@GetMapping("/home/{status}")
	public String porStatus(@PathVariable("status") String status, 
			HttpServletRequest request, Principal principal) {
		
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUsername(
				StatusPedido.valueOf(status.toUpperCase()),
				principal.getName());
		
		request.setAttribute("pedidos", pedidos);	
		request.setAttribute("status", status);
		return "usuario/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
	
}
