package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Usuario;

public class UsuarioDAO {

	
	public boolean salvar(Usuario usuario) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall("INSERT INTO `aps_database`.`usuario`(`nome`, `email`, `senha`, `instituicao`) VALUES(?, ?, ?, ?)");
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getInstituicao());
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Usuario checkLogin(String email, String senha) {
		
		Usuario usuario = new Usuario();
		
		try {
			Connection conexao = FabricaConexao.getConexao();
			PreparedStatement ps;
			ps = conexao.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
			ps.setString(1, email);
			ps.setString(2, senha);
			
			ResultSet resultSet = ps.executeQuery();
			
			resultSet.next();
			
			usuario.setId(resultSet.getInt("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setInstituicao(resultSet.getString("instituicao"));
			usuario.setTipo(resultSet.getString("tipo"));
			
			return usuario;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return usuario;
		}
		
	}
	
	public void trocaEmailTemporariamente(Usuario usuario) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		String emailTemporario = "_" + usuario.getEmail();
		
		System.out.println(emailTemporario);
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`usuario` SET `email` = ? WHERE `id` = ?");
			
			ps.setString(1, emailTemporario);
			ps.setInt(2, usuario.getId());
			ps.execute();
			FabricaConexao.fecharConexao();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean atualizarCadastro(Usuario usuario) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`usuario` SET `nome` = ?, `email` = ?, "
					+ "`senha` = ?, `instituicao` = ? WHERE `id` = ?");
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getInstituicao());
			ps.setInt(5, usuario.getId());
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