package br.com.naosei.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.com.naosei.DAO.EventoDAO;
import br.com.naosei.models.Evento;

@SessionScoped
@ManagedBean
public class EventoBean {
	
	private Evento evento = new Evento();  
	private List<Evento> eventos = new ArrayList<Evento>();
	private String msg;
	private int idAdministrador = UsuarioBean.getId();
	
	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public void adicionar() {
		
//		try {
			System.out.println(this.idAdministrador);
			evento.setIdAdministrador(this.idAdministrador);
			eventos.add(evento);
			new EventoDAO().salvar(evento);
//			this.abrirDialogoSucesso();
			evento = new Evento();
//		}catch(Exception e){
//			this.abrirDialogoErro();
//		}
		
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
	
	private void abrirDialogoSucesso() {
		Map<String, Object> opcoes =  new HashMap<>();
		
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 100);
		
		RequestContext.getCurrentInstance().openDialog("sucesso", opcoes, null);  
	}
	
	private void abrirDialogoErro() {
		Map<String, Object> opcoes =  new HashMap<>();
		
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 100);
		
		RequestContext.getCurrentInstance().openDialog("falha", opcoes, null);  
	}
	
}



















