package br.com.naosei.models;

public class Professor extends Usuario {

	private String titulacao;
	private String areaDePesquisa;
	
	public String getTitulacao() {
		return titulacao;
	}
	
	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}
	
	public String getAreaDePesquisa() {
		return areaDePesquisa;
	}
	
	public void setAreaDePesquisa(String areaDePesquisa) {
		this.areaDePesquisa = areaDePesquisa;
	}

}
