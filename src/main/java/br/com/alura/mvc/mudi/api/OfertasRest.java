package br.com.alura.mvc.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/oferta")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		Oferta novaOferta = null;
		
		try {
			Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
			
			if(!pedidoBuscado.isPresent()) {
				return null;
			}

			novaOferta = requisicao.toOferta();
			
			Pedido pedido = pedidoBuscado.get();
			
			novaOferta.setPedido(pedido);
			
			pedido.getOfertas().add(novaOferta);
			
			pedidoRepository.save(pedido);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return novaOferta;
	}
	
}
