package br.com.naosei.DAO;

import java.sql.*;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Evento;

public class EventoDAO {
	
	public void salvar(Evento evento) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall("INSERT INTO `aps_database`.`evento`(`nome`,`sigla`,`dataIni`,`dataFim`,"
					                                 + "`palavrasChave`,`areaDeConcentracao`,`id_administrador`) VALUES (?,?,?,?,?,?,?)");
			
			ps.setString(1, evento.getNome());
			ps.setString(2, evento.getSigla());
			ps.setDate(3, new Date(evento.getDataInicio().getTime()));
			ps.setDate(4, new Date(evento.getDataFim().getTime()));
			ps.setString(5, evento.getPalavraChave());
			ps.setString(6, evento.getAreaDeConcentracao());
			ps.setInt(7, evento.getIdAdministrador());
			System.out.println(evento.getIdAdministrador());
			ps.execute();
			FabricaConexao.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
