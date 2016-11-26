/**
 * 
 */
package view;

import model.Funcionario;
import repository.FuncionarioRepository;
import totalcross.io.IOException;
import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sql.Statement;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.Toast;
import totalcross.ui.Window;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

/**
 * @author delan
 *
 */
public class TelaAddFuncionario extends Window {

	private Edit nome, sobrenome, salario, cargo;
	private Button btnSalvar, btnCancelar, btnLimpar;
	private Connection connection;

	public TelaAddFuncionario() throws ImageException, IOException {

		setBackColor(Color.WHITE);

		add(new Label("Cadastre um Funcionario"), CENTER, TOP + 50);

		add(new Label("Nome: *"), LEFT + 100, AFTER);
		add(nome = new Edit(), LEFT, SAME);
		nome.setRect(LEFT + 100, AFTER, FILL - 100, 25);

		add(new Label("Sobrenome: *"), LEFT + 100, AFTER);
		add(sobrenome = new Edit(), LEFT, SAME);
		sobrenome.setRect(LEFT + 100, AFTER - 10, FILL - 100, 25);

		add(new Label("Salario(R$): "), LEFT + 100, AFTER);
		add(salario = new Edit(), LEFT, SAME);
		salario.setRect(LEFT + 100, AFTER - 10, FILL - 100, 25);

		add(new Label("Cargo: *"), LEFT + 100, AFTER);
		add(cargo = new Edit(), LEFT, SAME);
		cargo.setRect(LEFT + 100, AFTER - 10, FILL - 100, 25);

		Spacer sp = new Spacer(0, 0);

		add(sp, CENTER, BOTTOM - 200, PARENTSIZE + 10, PREFERRED);

		add(btnSalvar = new Button("Salvar", new Image("imagens/add-icon.png"), RIGHT, 0), LEFT + 100, SAME,
				PREFERRED + 100, 25, sp);
		btnSalvar.setBackColor(Color.GREEN);
		btnSalvar.setForeColor(Color.BLACK);

		add(btnLimpar = new Button("Limpar", new Image("imagens/reset-icon.png"), RIGHT, 0), CENTER, SAME,
				PREFERRED + 100, 25, sp);

		add(btnCancelar = new Button("Voltar", new Image("imagens/back-icon.png"), RIGHT, 0), RIGHT - 100, SAME,
				PREFERRED + 100, 25, sp);
		btnCancelar.setBackColor(Color.RED);
		btnCancelar.setForeColor(Color.WHITE);

		try {
			connection = DriverManager
					.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "projeto.db"));
			Statement statement = connection.createStatement();
			statement.execute(
					"create table if not exists tb_funcionario (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar,sobrenome varchar,salario double,cargo varchar)");
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

					Double salarioTemp = new Double(salario.getText());

					Funcionario funcionario = new Funcionario();
					funcionario.setNome(nome.getText());
					funcionario.setSobrenome(sobrenome.getText());
					funcionario.setSalario(salarioTemp);
					funcionario.setCargo(cargo.getText());

					if (funcionario.getNome().length() == 0 || funcionario.getSobrenome().length() == 0
							|| funcionario.getCargo().length() == 0) {
						Toast.show("Por Favor, Preencher os campos Obrigatórios", 2000);
					} else {
						FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
						funcionarioRepository.inserirFuncionario(funcionario);
						clear();

						TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios();
						telaListaFuncionarios.popup();
					}
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
