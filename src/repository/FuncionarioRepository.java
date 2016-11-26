/**
 * 
 */
package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Funcionario;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.ui.Toast;
import totalcross.ui.dialog.MessageBox;

/**
 * @author delan
 * 
 *         Classe Reposit�rio, com o objetivo de realizar opera��es no banco de
 *         dados. O nome e a defini��o da classe, � uma padr�o, encontrado no
 *         DDD(Domain Driven Design)
 */
public class FuncionarioRepository {

	public FuncionarioRepository() {

	}

	/*
	 * M�todo para Inserir um novo funcion�rio no banco de dados, utilizando PrepareStatement.
	 * 
	 */
	public void inserirFuncionario(Funcionario funcionario) throws SQLException {
		try {

			if (funcionario.getNome().length() == 0 || funcionario.getSobrenome().length() == 0) {
				Toast.show("Por Favor, Preencher os campos Obrigat�rios", 2000);
			} else {

				String insert = "INSERT INTO tb_funcionario" + "(nome, sobrenome) VALUES" + "(?,?)";

				PreparedStatement preparedStatement = new ConnectionFactory().getConnection().prepareStatement(insert);

				preparedStatement.setString(1, funcionario.getNome());
				preparedStatement.setString(2, funcionario.getSobrenome());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				Toast.show("Novo Funcionario Inserido com Sucesso !", 2000);
			}
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}

	}

	/*
	 * M�todo para Atualizar o registro de um funcion�rio no banco de dados, utilizando tambem o
	 * PrepareStatement.
	 * 
	 */
	public void atualizaFuncionario(Funcionario funcionario) throws SQLException {
		try {

			if (funcionario.getNome().length() == 0 || funcionario.getSobrenome().length() == 0) {
				Toast.show("Por Favor, Preencher os campos Obrigat�rios", 2000);
			} else {

				String update = "UPDATE tb_funcionario SET nome = ?,sobrenome = ? " + " WHERE id = ?";
				PreparedStatement preparedStatement = new ConnectionFactory().getConnection().prepareStatement(update);

				preparedStatement.setString(1, funcionario.getNome());
				preparedStatement.setString(2, funcionario.getSobrenome());
				preparedStatement.setInt(3, funcionario.getId());

				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}

	}

	/*
	 * M�todo para excluir um funcion�rio do banco de dados, utilizando apenas o
	 * Statement.
	 * 
	 */
	public void deletarFuncionario(String id) throws SQLException {
		try {
			String delete = "delete from tb_funcionario where id = " + id;

			Statement statement = new ConnectionFactory().getConnection().createStatement();
			statement.executeUpdate(delete);
			statement.close();
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}

	}

	/*
	 * M�todo para listar todos os funcion�rios, utilizando tambem o Statement.
	 * 
	 */
	public List<Funcionario> buscarTodos() {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario funcionario;

		try {
			String query = "select * from tb_funcionario";

			Statement statement = new ConnectionFactory().getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				funcionario = new Funcionario((Integer) resultSet.getObject("id"), (String) resultSet.getObject("nome"),
						(String) resultSet.getObject("sobrenome"));

				funcionarios.add(funcionario);
			}

			statement.close();
		} catch (SQLException e) {
			MessageBox.showException(e, true);
		}

		return funcionarios;
	}

	/*
	 * M�todo busca um funcion�rio especifico pelo id, utilizando tambem apenas
	 * o Statement.
	 * 
	 */
	public Funcionario buscarFuncionarioPorId(String id) {

		Funcionario funcionario = null;

		try {
			String query = "select * from tb_funcionario where id = " + id;

			Statement statement = new ConnectionFactory().getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				funcionario = new Funcionario((Integer) resultSet.getObject("id"), (String) resultSet.getObject("nome"),
						(String) resultSet.getObject("sobrenome"));

			}

			statement.close();
		} catch (SQLException e) {
			MessageBox.showException(e, true);
		}

		return funcionario;
	}

}
