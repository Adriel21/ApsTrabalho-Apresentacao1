package br.com.naosei.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.ArtigoDAO;
import br.com.naosei.models.Artigo;
import br.com.naosei.util.FacesUtil;

@ManagedBean
@SessionScoped
public class ArtigoBean {
	
	private Artigo artigo = new Artigo();
	private List<Artigo> artigos;
	private List<Artigo> artigosFiltrados;
	private int idAluno = UsuarioBean.getId();
	private boolean verificador = false;
	
	public Artigo getArtigo() {
		return artigo;
	}
	
	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
	
	public List<Artigo> getArtigos() {
		return artigos;
	}
	
	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}
	
	public List<Artigo> getArtigosFiltrados() {
		return artigosFiltrados;
	}
	
	public void setArtigosFiltrados(List<Artigo> artigosFiltrados) {
		this.artigosFiltrados = artigosFiltrados;
	}
	
	public boolean isVerificador() {
		return verificador;
	}
	
	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	
	public void adicionar() {

		System.out.println(this.idAluno);
		artigo.setIdAluno(this.idAluno);
		System.out.println(artigo.toString());
		//artigos.add(artigo);
		this.verificador = new ArtigoDAO().salvar(artigo);
		if (this.verificador)
			FacesUtil.adicionarMsgInfo("Cadastro realizado com sucesso!");
		else
			FacesUtil.adicionarMsgErro("Nome ou sigla já cadastrado(s).");
		System.out.println(verificador);

		artigo = new Artigo();

	}
	
}
