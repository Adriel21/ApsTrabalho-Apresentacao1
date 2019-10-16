package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Professor;

public class ProfessorDAO {

	
	public boolean salvar(Professor professor) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall("INSERT INTO `aps_database`.`usuario`(`nome`, `email`, `senha`, `instituicao`, `titulacao`, `areaDePesquisa`, `tipo`) VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getEmail());
			ps.setString(3, professor.getSenha());
			ps.setString(4, professor.getInstituicao());
			ps.setString(5, professor.getTitulacao());
			System.out.println(professor.getTitulacao() + "12");
			ps.setString(6, professor.getAreaDePesquisa());
			System.out.println(professor.getAreaDePesquisa() + "123");
			ps.setString(7, "Professor");
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Professor checkLogin(String email, String senha) {
		
		Professor professor = new Professor();
		
		try {
			Connection conexao = FabricaConexao.getConexao();
			PreparedStatement ps;
			ps = conexao.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
			ps.setString(1, email);
			ps.setString(2, senha);
			
			ResultSet resultSet = ps.executeQuery();
			
			resultSet.next();
			
			professor.setId(resultSet.getInt("id"));
			professor.setNome(resultSet.getString("nome"));
			professor.setEmail(resultSet.getString("email"));
			professor.setSenha(resultSet.getString("senha"));
			professor.setInstituicao(resultSet.getString("instituicao"));
			professor.setTitulacao(resultSet.getString("titulacao"));
			professor.setAreaDePesquisa(resultSet.getString("areaDePesquisa"));
			
			return professor;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return professor;
		}
		
	}
	
	public void trocaEmailTemporariamente(Professor professor) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		String emailTemporario = professor.getEmail() + "2";
		
		System.out.println(emailTemporario);
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`usuario` SET `email` = ? WHERE `id` = ?");
			
			ps.setString(1, emailTemporario);
			ps.setInt(2, professor.getId());
			ps.execute();
			FabricaConexao.fecharConexao();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean atualizarCadastro(Professor professor) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`usuario` SET `nome` = ?, `email` = ?, "
					+ "`senha` = ?, `instituicao` = ?, `titulacao` = ?, `areaDePesquisa` = ? WHERE `id` = ?");
			
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getEmail());
			ps.setString(3, professor.getSenha());
			ps.setString(4, professor.getInstituicao());
			ps.setString(5, professor.getTitulacao());
			ps.setString(6, professor.getAreaDePesquisa());
			ps.setInt(7, professor.getId());
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