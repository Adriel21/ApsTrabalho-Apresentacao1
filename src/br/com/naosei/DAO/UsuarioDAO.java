package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Usuario;

public class UsuarioDAO {

	
	public void salvar(Usuario usuario) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall("INSERT INTO `aps_database`.`administrador`(`nome`, `email`, `senha`, `instituicao`) VALUES(?, ?, ?, ?)");
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getInstituicao());
			ps.execute();
			FabricaConexao.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int checkLogin(String email, String senha) {
		
		try {
			Connection conexao = FabricaConexao.getConexao();
			PreparedStatement ps;
			ps = conexao.prepareStatement("SELECT * FROM administrador WHERE email = ? AND senha = ?");
			ps.setString(1, email);
			ps.setString(2, senha);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next())				return resultSet.getInt(1);
			else 								return 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}
}