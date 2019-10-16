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
	private Usuario usuario2 = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private String email;
	private String senha;
	private static String email2;
	private static String senha2;
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
	
	public static String getEmail2() {
		return email2;
	}

	public static void setEmail2(String email2) {
		UsuarioBean.email2 = email2;
	}

	public static String getSenha2() {
		return senha2;
	}

	public static void setSenha2(String senha2) {
		UsuarioBean.senha2 = senha2;
	}

	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
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
		
		usuario = new UsuarioDAO().checkLogin(email, senha);
		
		if (usuario.getId()>0) {
			UsuarioBean.id = usuario.getId();
			System.out.println(UsuarioBean.id);
			//this.msgLogin = "";
			System.out.println(usuario.getTipo());
			
			UsuarioBean.setEmail2(email);
			UsuarioBean.setSenha2(senha);
			
			if(usuario.getTipo().equals("Professor"))
				return "professor/pagePesquisaEventoProfessor.xhtml?faces-redirect=true";
			
			else if(usuario.getTipo().equals("Administrador"))
				return "administrador/pagePesquisaEventoAdministrador.xhtml?faces-redirect=true";
			
			else
				return "aluno/pagePesquisaEventoAluno.xhtml?faces-redirect=true";
			
		} else {
			//this.msgLogin = "E-mail ou senha incorretos!";
			FacesUtil.adicionarMsgErro("E-mail ou senha incorreto(s).");
			return "E-mail ou senha incorretos!";
		}

	}
	
	public String sair() {
		UsuarioBean.id = -1;
		System.out.println(UsuarioBean.id);
		return "pageLogin.xhtml?faces-redirect=true";
	}
	
	public void atualizarCadastro() {
		
		if(usuario2.getNome().equals(""))			usuario2.setNome(usuario.getNome());
		
		if(usuario2.getEmail().equals(""))			usuario2.setEmail(usuario.getEmail());
		else   									email = usuario2.getEmail();
		
		if(usuario2.getSenha().equals(""))			usuario2.setSenha(usuario.getSenha());
		else   									senha = usuario2.getSenha();
		
		if(usuario2.getInstituicao().equals(""))		usuario2.setInstituicao(usuario.getInstituicao());
		
		usuario2.setId(usuario.getId());
		
		new UsuarioDAO().trocaEmailTemporariamente(usuario2);
		
		this.verificador = new UsuarioDAO().atualizarCadastro(usuario2);
		
		if(this.verificador)
			FacesUtil.adicionarMsgInfo("Atualização cadastral realizada com sucesso!");
		else
			FacesUtil.adicionarMsgErro("E-mail já cadastrado.");
		
		usuario2 = new Usuario();
		usuario = new UsuarioDAO().checkLogin(email, senha);
		
	}
	
}