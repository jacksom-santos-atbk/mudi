package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

public class RequisicaoLoginUsuario {

	@NotBlank
	protected String nomeUsuario;
	
	@NotBlank
	protected String senha;
	protected boolean manterLogado;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean getManterLogado() {
		return manterLogado;
	}
	public void setManterLogado(boolean manterLogado) {
		this.manterLogado = manterLogado;
	}
	
}
