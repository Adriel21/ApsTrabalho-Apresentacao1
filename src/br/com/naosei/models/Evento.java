package br.com.naosei.models;

import java.util.Date;

public class Evento {

	private Integer id;
	private String nome;
	private String sigla;
	private Date dataInicio;
	private Date dataFim;
	private String palavraChave;
	private String areaDeConcentracao;
	private String situacao;
	private int idAdministrador;
	private transient boolean editando;

	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public String getAreaDeConcentracao() {
		return areaDeConcentracao;
	}

	public void setAreaDeConcentracao(String areaDeConcentracao) {
		this.areaDeConcentracao = areaDeConcentracao;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", palavraChave=" + palavraChave + ", areaDeConcentracao=" + areaDeConcentracao
				+ ", idAdministrador=" + idAdministrador + "]";
	}
	
	

}
