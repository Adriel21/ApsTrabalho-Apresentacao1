package br.com.naosei.models;

public class Artigo {
	
	private int id;
	private String titulo;
	// Sei que era pra ser uma lista de autores, mas foi feito assim pela necessidade de método ágil.
	private String autores;
	private String resumo;
	private String status;
	private int idAluno;
	
	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulacao) {
		this.titulo = titulacao;
	}
	
	public String getAutores() {
		return autores;
	}
	
	public void setAutores(String autores) {
		this.autores = autores;
	}
	
	public String getResumo() {
		return resumo;
	}
	
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Artigo [id=" + id + ", titulo=" + titulo + ", autores=" + autores + ", resumo=" + resumo + ", status="
				+ status + ", idAluno=" + idAluno + "]";
	}
	
	
	
}
