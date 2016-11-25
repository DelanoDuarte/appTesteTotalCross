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
 */
public class FuncionarioRepository {

	public FuncionarioRepository() {

	}

	public void inserirFuncionario(Funcionario funcionario) throws SQLException {
		try {

			if (funcionario.getNome().length() == 0 || funcionario.getSobrenome().length() == 0) {
				Toast.show("Por Favor, Preencher os campos Obrigat�rios", 2000);
			} else {

				String insert = "insert into tb_funcionario (nome, sobrenome) values('" + funcionario.getNome() + "','"
						+ funcionario.getSobrenome() + "')";

				Statement statement = new ConnectionFactory().getConnection().createStatement();
				statement.executeUpdate(insert);

				Toast.show("Novo Funcionario Inserido com Sucesso !", 2000);
				statement.close();
			}
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}

	}

	public void atualizaFuncionario(Funcionario funcionario) throws SQLException {
		try {
			System.out.println(funcionario.getId());
			System.out.println(funcionario.getNome());
			System.out.println(funcionario.getSobrenome());

			// String update = "update tb_funcionario set nome = '" +
			// funcionario.getNome() + "', sobrenome = '"
			// + funcionario.getSobrenome() + "' where codigo = " +
			// Integer.toString(funcionario.getId());

			String updateTableSQL = "UPDATE tb_funcionario SET nome = ?,sobrenome = ? " + " WHERE id = ?";
			PreparedStatement preparedStatement = new ConnectionFactory().getConnection()
					.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, funcionario.getNome());
			preparedStatement.setString(2, funcionario.getSobrenome());
			preparedStatement.setInt(3, funcionario.getId());
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();

			// Statement statement = new
			// ConnectionFactory().getConnection().createStatement();
			// statement.executeUpdate(update);
			// statement.close();

		} catch (Exception e) {
			MessageBox.showException(e, true);
		}

	}

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