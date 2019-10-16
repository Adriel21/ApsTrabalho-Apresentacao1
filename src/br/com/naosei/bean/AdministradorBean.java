package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.AdministradorDAO;
import br.com.naosei.models.Administrador;
import br.com.naosei.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AdministradorBean {

	private Administrador administrador = new Administrador();
	private List<Administrador> administradores = new ArrayList<Administrador>();
	private boolean verificador;
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public boolean isVerificador() {
		return verificador;
	}

	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}

	public void cadastrar() {
		administradores.add(administrador);
		this.verificador = new AdministradorDAO().salvar(administrador);
		//this.msgErroCadastro = "Olá, " + usuario.getNome() + ", seu cadastro foi realizado com sucesso!";
		System.out.println(verificador);
		
		if(this.verificador)
			FacesUtil.adicionarMsgInfo("Cadastro realizado com sucesso!");
		else
			FacesUtil.adicionarMsgErro("E-mail já cadastrado.");
		administrador = new Administrador();
	}
	
}
