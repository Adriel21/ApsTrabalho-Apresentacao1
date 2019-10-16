package br.com.naosei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.naosei.factory.FabricaConexao;
import br.com.naosei.models.Aluno;

public class AlunoDAO {

	
	public boolean salvar(Aluno aluno) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall("INSERT INTO `aps_database`.`usuario`(`nome`, `email`, `senha`, `instituicao`, `tipo`) VALUES(?, ?, ?, ?, ?)");
			
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
			ps.setString(3, aluno.getSenha());
			ps.setString(4, aluno.getInstituicao());
			ps.setString(5, "Aluno");
			ps.execute();
			FabricaConexao.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean atualizarCadastro(Aluno aluno) {
		
		Connection conexao = FabricaConexao.getConexao();
		
		try {
			
			PreparedStatement ps = conexao.prepareCall
					("UPDATE `aps_database`.`usuario` SET `nome` = ?, `email` = ?, "
					+ "`senha` = ?, `instituicao` = ? WHERE `id` = ?");
			
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
			ps.setString(3, aluno.getSenha());
			ps.setString(4, aluno.getInstituicao());
			ps.setInt(5, aluno.getId());
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