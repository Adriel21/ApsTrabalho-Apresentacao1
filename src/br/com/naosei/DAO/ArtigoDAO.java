package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Artigo;

public class ArtigoDAO {

	public boolean salvar(Artigo artigo) {

		Connection conexao = FabricaConexao.getConexao();

		try {

			PreparedStatement ps = conexao
					.prepareCall("INSERT INTO `aps_database`.`artigo` (`titulo`,`autores`,`resumo`,`id_aluno`,"
							+ "`status`) VALUES (?,?,?,?,?)");
			
			ps.setString(1, artigo.getTitulo());
			ps.setString(2, artigo.getAutores());
			ps.setString(3, artigo.getResumo());
			ps.setInt(4, artigo.getIdAluno());
			ps.setString(5, "Aguardando avaliação");
			ps.execute();
			FabricaConexao.fecharConexao();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<Artigo> listar() {

		Connection conexao = FabricaConexao.getConexao();
		List<Artigo> artigos = new ArrayList<>();

		try {

			PreparedStatement ps = conexao
					.prepareCall("SELECT * FROM `aps_database`.`artigo`");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Artigo artigo = new Artigo();
				
				artigo.setId(rs.getInt("id"));
				
				artigos.add(artigo);
				
			}
			
			ps.execute();
			FabricaConexao.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return artigos;
		
	}
	
	public boolean atualizar(Artigo artigo) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`artigo` SET `nome` = ?, `sigla` = ?, `dataIni` = ?, `dataFim` = ?, "
							+ "`palavrasChave` = ?, `areaDeConcentracao` = ?, `situacao` = ? WHERE `id` = ?");
			
			ps.setString(1, artigo.getTitulo());
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean remover(Artigo artigo) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("DELETE FROM `aps_database`.`artigo` WHERE `id` = ?");
			
			ps.setInt(1, artigo.getId());
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