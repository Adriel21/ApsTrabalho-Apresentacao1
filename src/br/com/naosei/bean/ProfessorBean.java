package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.ProfessorDAO;
import br.com.naosei.models.Professor;
import br.com.naosei.util.FacesUtil;

@ManagedBean
@SessionScoped
public class ProfessorBean {
	
	private Professor professor = new Professor();
	private Professor professor2 = new Professor();
	private List<Professor> professores = new ArrayList<Professor>();
	private boolean verificador;
	
	public Professor getProfessor2() {
		return professor2;
	}

	public void setProfessor2(Professor professor2) {
		this.professor2 = professor2;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public boolean isVerificador() {
		return verificador;
	}

	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}

	public void cadastrar() {
		professores.add(professor);
		this.verificador = new ProfessorDAO().salvar(professor);
		//this.msgErroCadastro = "Olá, " + usuario.getNome() + ", seu cadastro foi realizado com sucesso!";
		System.out.println(verificador);
		
		if(this.verificador)
			FacesUtil.adicionarMsgInfo("Cadastro realizado com sucesso!");
		else
			FacesUtil.adicionarMsgErro("E-mail já cadastrado.");
		professor = new Professor();
	}
	
	public void atualizarCadastro() {
		
		professor = new ProfessorDAO().checkLogin(UsuarioBean.getEmail2(), UsuarioBean.getSenha2());
		
		System.out.println(professor2.getEmail());
		
		if(professor2.getNome().equals(""))				professor2.setNome(professor.getNome());
		
		if(professor2.getEmail().equals(""))				professor2.setEmail(professor.getEmail());
		
		if(professor2.getSenha().equals(""))				professor2.setSenha(professor.getSenha());
		
		if(professor2.getInstituicao().equals(""))		professor2.setInstituicao(professor.getInstituicao());
		
		if(professor2.getTitulacao().equals(""))			professor2.setTitulacao(professor.getTitulacao());
		
		if(professor2.getAreaDePesquisa().equals(""))	professor2.setAreaDePesquisa(professor.getAreaDePesquisa());
		
		professor2.setId(professor.getId());
		
		new ProfessorDAO().trocaEmailTemporariamente(professor2);
		
		this.verificador = new ProfessorDAO().atualizarCadastro(professor2);
		
		System.out.println(professor2.getEmail());
		
		if(this.verificador)
			FacesUtil.adicionarMsgInfo("Atualização cadastral realizada com sucesso!");
		else
			FacesUtil.adicionarMsgErro("E-mail já cadastrado.");
		
		professor2 = new Professor();
		
	}
	
	public String sair() {
		professor  = new Professor();
		return "pageLogin.xhtml?faces-redirect=true";
	}
	
}
