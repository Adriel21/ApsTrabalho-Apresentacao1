package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.UsuarioDAO;
import br.com.naosei.models.Usuario;
import br.com.naosei.util.FacesUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
//	private String msgCadastro;
//	private String msgLogin;
	private String email;
	private String senha;
	private static int id;
	private boolean verificador;
	
	public boolean isVerificador() {
		return verificador;
	}

	public void setverificador(boolean verificador) {
		this.verificador = verificador;
	}

	public static int getId() {
		return id;
	}

	public void setId(int id) {
		UsuarioBean.id = id;
	}

//	public String getMsgCadastro() {
//		return msgCadastro;
//	}
//
//	public void setMsgCadastro(String msgCadastro) {
//		this.msgCadastro = msgCadastro;
//	}
//
//	public String getMsgLogin() {
//		return msgLogin;
//	}
//
//	public void setMsgLogin(String msgLogin) {
//		this.msgLogin = msgLogin;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void cadastrar() {
		usuarios.add(usuario);
		this.verificador = new UsuarioDAO().salvar(usuario);
		//this.msgErroCadastro = "Olá, " + usuario.getNome() + ", seu cadastro foi realizado com sucesso!";
		System.out.println(verificador);
		
		if(this.verificador)
			FacesUtil.adicionarMsgInfo("Cadastro realizado com sucesso!");
		else
			FacesUtil.adicionarMsgErro("E-mail já cadastrado.");
		usuario = new Usuario();
	}
	
	public String logar() {

		usuario.setId(new UsuarioDAO().checkLogin(email, senha));
//		System.out.println(usuario.getId());

		if (usuario.getId()>0) {
			UsuarioBean.id = usuario.getId();
			System.out.println(UsuarioBean.id);
			//this.msgLogin = "";
			return "pagePesquisaEvento.xhtml?faces-redirect=true";
		} else {
			//this.msgLogin = "E-mail ou senha incorretos!";
			FacesUtil.adicionarMsgErro("Usuário ou senha incorreto(s).");
			return "E-mail ou senha incorretos!";
		}

	}
	
	public String sair() {
		UsuarioBean.id = -1;
		System.out.println(UsuarioBean.id);
		return "pageLogin.xhtml?faces-redirect=true";
	}
	
}