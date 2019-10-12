package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.UsuarioDAO;
import br.com.naosei.models.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private String msgErroCadastro;
	private String msgErroLogin;
	private String email;
	private String senha;
	private static int id;
	
	public static int getId() {
		return id;
	}

	public void setId(int id) {
		UsuarioBean.id = id;
	}

	public String getMsgErroCadastro() {
		return msgErroCadastro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErroCadastro = msgErro;
	}

	public String getMsgErroLogin() {
		return msgErroLogin;
	}

	public void setMsgErroLogin(String msgErroLogin) {
		this.msgErroLogin = msgErroLogin;
	}

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
	
	public String cadastrar() {
		usuarios.add(usuario);
		new UsuarioDAO().salvar(usuario);
		//this.msgErroCadastro = "Olá, " + usuario.getNome() + ", seu cadastro foi realizado com sucesso!";
		usuario = new Usuario();
		return "telaLogin";
	}
	
	public String logar() {

		usuario.setId(new UsuarioDAO().checkLogin(email, senha));
		System.out.println(usuario.getId());

		if (usuario.getId()>0) {
			UsuarioBean.id = usuario.getId();
			
			return "pagePesquisaEvento";
		} else {
			this.msgErroLogin = "E-mail ou senha incorretos!";
			return "E-mail ou senha incorretos!";
		}

	}
	
}