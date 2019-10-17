package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Evento;

public class EventoDAO {

	public boolean salvar(Evento evento) {

		Connection conexao = FabricaConexao.getConexao();

		try {

			PreparedStatement ps = conexao
					.prepareCall("INSERT INTO `aps_database`.`evento`(`nome`,`sigla`,`dataIni`,`dataFim`,"
							+ "`palavrasChave`,`areaDeConcentracao`,`id_administrador`, `situacao`) VALUES (?,?,?,?,?,?,?,?)");

			ps.setString(1, evento.getNome());
			ps.setString(2, evento.getSigla());
			ps.setDate(3, new Date(evento.getDataInicio().getTime()));
			ps.setDate(4, new Date(evento.getDataFim().getTime()));
			ps.setString(5, evento.getPalavraChave());
			ps.setString(6, evento.getAreaDeConcentracao());
			ps.setInt(7, evento.getIdAdministrador());
			ps.setString(8, evento.getSituacao());
			System.out.println(evento.getIdAdministrador());
			ps.execute();
			FabricaConexao.fecharConexao();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<Evento> listar() {

		Connection conexao = FabricaConexao.getConexao();
		List<Evento> eventos = new ArrayList<>();

		try {

			PreparedStatement ps = conexao
					.prepareCall("SELECT * FROM `aps_database`.`evento`");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Evento evento = new Evento();
				
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setSigla(rs.getString("sigla"));
				evento.setDataInicio(rs.getDate("dataIni"));
				evento.setDataFim(rs.getDate("dataFim"));
				evento.setPalavraChave(rs.getString("palavrasChave"));
				evento.setAreaDeConcentracao(rs.getString("areaDeConcentracao"));
				evento.setSituacao(rs.getString("situacao"));
				
				eventos.add(evento);
				
			}
			
			ps.execute();
			FabricaConexao.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return eventos;
		
	}
	
	public void trocaSiglaTemporariamente(Evento evento) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		String siglaTemporaria = evento.getSigla() + "1";
		
		System.out.println(siglaTemporaria);
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`evento` SET `sigla` = ? WHERE `id` = ?");
			
			ps.setString(1, siglaTemporaria);
			ps.setInt(2, evento.getId());
			ps.execute();
			FabricaConexao.fecharConexao();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean atualizar(Evento evento) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`evento` SET `nome` = ?, `sigla` = ?, `dataIni` = ?, `dataFim` = ?, "
							+ "`palavrasChave` = ?, `areaDeConcentracao` = ?, `situacao` = ? WHERE `id` = ?");
			
			ps.setString(1, evento.getNome());
			ps.setString(2, evento.getSigla());
			ps.setDate(3, new Date(evento.getDataInicio().getTime()));
			ps.setDate(4, new Date(evento.getDataFim().getTime()));
			ps.setString(5, evento.getPalavraChave());
			ps.setString(6, evento.getAreaDeConcentracao());
			ps.setString(7, evento.getSituacao());
			ps.setInt(8, evento.getId());
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean remover(Evento evento) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("DELETE FROM `aps_database`.`evento` WHERE `id` = ?");
			
			ps.setInt(1, evento.getId());
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
