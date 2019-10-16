package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.AlunoDAO;
import br.com.naosei.models.Aluno;
import br.com.naosei.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AlunoBean {
	
	private Aluno aluno = new Aluno();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private boolean verificador;
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public boolean isVerificador() {
		return verificador;
	}

	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}

	public void cadastrar() {
		alunos.add(aluno);
		this.verificador = new AlunoDAO().salvar(aluno);
		//this.msgErroCadastro = "Olá, " + usuario.getNome() + ", seu cadastro foi realizado com sucesso!";
		System.out.println(verificador);
		
		if(this.verificador)
			FacesUtil.adicionarMsgInfo("Cadastro realizado com sucesso!");
		else
			FacesUtil.adicionarMsgErro("E-mail já cadastrado.");
		aluno = new Aluno();
	}

}
