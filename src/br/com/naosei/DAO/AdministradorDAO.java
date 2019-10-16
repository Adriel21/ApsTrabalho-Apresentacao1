package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Administrador;

public class AdministradorDAO {

	
	public boolean salvar(Administrador administrador) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall("INSERT INTO `aps_database`.`usuario`(`nome`, `email`, `senha`, `instituicao`, `tipo`) VALUES(?, ?, ?, ?, ?)");
			
			ps.setString(1, administrador.getNome());
			ps.setString(2, administrador.getEmail());
			ps.setString(3, administrador.getSenha());
			ps.setString(4, administrador.getInstituicao());
			ps.setString(5, "Administrador");
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean atualizarCadastro(Administrador administrador) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`usuario` SET `nome` = ?, `email` = ?, "
					+ "`senha` = ?, `instituicao` = ? WHERE `id` = ?");
			
			ps.setString(1, administrador.getNome());
			ps.setString(2, administrador.getEmail());
			ps.setString(3, administrador.getSenha());
			ps.setString(4, administrador.getInstituicao());
			ps.setInt(5, administrador.getId());
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