package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.naosei.DAO.EventoDAO;
import br.com.naosei.models.Evento;
import br.com.naosei.util.FacesUtil;

@ManagedBean
@SessionScoped
public class EventoBean {

	private Evento evento = new Evento();
	private List<Evento> eventos = new ArrayList<Evento>();
	private List<Evento> eventosFiltrados = new ArrayList<Evento>();
//	private String msg;
	private int idAdministrador = UsuarioBean.getId();
	private boolean verificador = false;

	public boolean isVerificador() {
		return verificador;
	}

	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}

	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public ArrayList<Evento> getEventos() {
		return (ArrayList<Evento>) eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Evento> getEventosFiltrados() {
		return eventosFiltrados;
	}

	public void setEventosFiltrados(List<Evento> eventosFiltrados) {
		this.eventosFiltrados = eventosFiltrados;
	}

	public void adicionar() {

		System.out.println(this.idAdministrador);
		evento.setIdAdministrador(this.idAdministrador);
		eventos.add(evento);
		this.verificador = new EventoDAO().salvar(evento);
		if (this.verificador)
			FacesUtil.adicionarMsgInfo("Cadastro realizado com sucesso!");
//		this.msg = "Evento foi adicionado com sucesso!";
		else
			FacesUtil.adicionarMsgErro("Nome ou sigla já cadastrado(s).");
//		this.msg = "Falha ao adicionar o evento. Nome ou sigla já existente(s).";
		System.out.println(verificador);

		evento = new Evento();

	}
	
	public void carregarPesquisa() {
		
		this.eventos = new ArrayList<Evento>();
		
		try {
			
			EventoDAO eventoDao = new EventoDAO();
			for(int i = (eventoDao.listar().size()-5); i<(eventoDao.listar().size()); i++){
				eventos.add(eventoDao.listar().get(i));
			}
			
		}catch(RuntimeException ex) {
			
			FacesUtil.adicionarMsgErro("Erro ao listar os eventos: " + ex.getMessage());
			
		}
		
	}

//	private void abrirDialogoSucesso() {
//		Map<String, Object> opcoes =  new HashMap<>();
//		
//		opcoes.put("modal", true);
//		opcoes.put("resizable", false);
//		opcoes.put("contentHeight", 100);
//		
//		RequestContext.getCurrentInstance().openDialog("sucesso", opcoes, null);  
//	}
////	
//	private void abrirDialogoErro() {
//		Map<String, Object> opcoes =  new HashMap<>();
//		
//		opcoes.put("modal", true);
//		opcoes.put("resizable", false);
//		opcoes.put("contentHeight", 100);
//		
//		RequestContext.getCurrentInstance().openDialog("falha", opcoes, null);  
//	}
//	
//	public void abrirDialogoDeAdicionarEvento() {
//		Map<String, Object> opcoes =  new HashMap<>();
//		
//		opcoes.put("modal", true);
//		opcoes.put("resizable", false);
//		opcoes.put("contentHeight", 400);
//		
//		RequestContext.getCurrentInstance().openDialog("cadastroEventos", opcoes, null);  
//	}

}
