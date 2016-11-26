/**
 * 
 */
package view;

import model.Funcionario;
import repository.FuncionarioRepository;
import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sql.Statement;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.Window;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

/**
 * @author delan
 *
 */
public class TelaAddFuncionario extends Window {

	private Edit nome, sobrenome;
	private Button btnSalvar, btnCancelar, btnLimpar;
	private Connection connection;

	public TelaAddFuncionario() {
		add(new Label("Cadastre um Funcionario"), CENTER, TOP + 50);

		add(new Label("Nome: "), LEFT + 100, AFTER);
		add(nome = new Edit(), LEFT, SAME);
		nome.setRect(LEFT + 100, AFTER, FILL - 100, 25);

		add(new Label("Sobrenome: "), LEFT + 100, AFTER);
		add(sobrenome = new Edit(), LEFT, SAME);
		sobrenome.setRect(LEFT + 100, AFTER - 10, FILL - 100, 25);

		Spacer sp = new Spacer(0, 0);

		add(sp, CENTER, BOTTOM - 200, PARENTSIZE + 10, PREFERRED);

		add(btnSalvar = new Button("Salvar"), LEFT + 100, SAME, PREFERRED + 100, 25, sp);
		btnSalvar.setBackColor(Color.GREEN);
		btnSalvar.setForeColor(Color.BLACK);

		add(btnLimpar = new Button("Limpar"), CENTER, SAME, PREFERRED + 100, 25, sp);

		add(btnCancelar = new Button("Voltar"), RIGHT - 100, SAME, PREFERRED + 100, 25, sp);
		btnCancelar.setBackColor(Color.RED);
		btnCancelar.setForeColor(Color.WHITE);

		try {
			connection = DriverManager
					.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "projeto.db"));
			Statement statement = connection.createStatement();
			statement.execute(
					"create table if not exists tb_funcionario (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar,sobrenome varchar) ");
			statement.close();

		} catch (Exception e) {
			MessageBox.showException(e, true);
		}
	}

	@Override
	public void onEvent(Event event) {
		try {
			switch (event.type) {
			case ControlEvent.PRESSED:
				if (event.target == btnLimpar) {
					clear();
				} else if (event.target == btnSalvar) {

					Funcionario funcionario = new Funcionario();
					funcionario.setNome(nome.getText());
					funcionario.setSobrenome(sobrenome.getText());

					FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
					funcionarioRepository.inserirFuncionario(funcionario);
					clear();

					TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios();
					telaListaFuncionarios.popup();
				} else if (event.target == btnCancelar) {
					TelaHome telaHome = new TelaHome();
					telaHome.popup();
				}

				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
