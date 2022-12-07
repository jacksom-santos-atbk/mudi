package br.com.alura.mvc.mudi.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class RequisicaoNovaOferta {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	private Long pedidoId;
	
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotNull
	private String valor;
	
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String dataDaEntrega;
	private String comentario;
	
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDataDaEntrega() {
		return dataDaEntrega;
	}
	public void setDataDaEntrega(String dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Oferta toOferta() throws Exception {
		Oferta oferta = new Oferta();
		
		oferta.setComentario(this.comentario);
		oferta.setDataDaEntrega(formatter.parse(this.dataDaEntrega));
		oferta.setValor(new BigDecimal(this.valor));
		
		return oferta;
	}
	
	
	
}
