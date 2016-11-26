/**
 * 
 */
package view;

import modelo.Funcionario;
import repository.FuncionarioRepository;
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

/**
 * @author delano.junior
 *
 */
public class TelaEditarFuncionario extends Window {

	private Edit nome, sobrenome;
	private Button btnSalvar, btnCancelar, btnLimpar;
	String id;
	private FuncionarioRepository repository;
	private Funcionario funcionario;

	public TelaEditarFuncionario(String id) {

		this.id = id;
		gradientTitleStartColor = 0;
		gradientTitleEndColor = 0xAAAAFF;

		Settings.uiAdjustmentsBasedOnFontHeight = true;

		setBackColor(0xDDDDFF);

		add(new Label("Editando um Funcionario"), CENTER, TOP + 50);

		add(new Label("Nome: "), LEFT + 100, AFTER);
		add(nome = new Edit(), LEFT, SAME);
		nome.setRect(LEFT + 100, AFTER, FILL - 100, 25);

		add(new Label("Sobrenome: "), LEFT + 100, AFTER);
		add(sobrenome = new Edit(), LEFT, SAME);
		sobrenome.setRect(LEFT + 100, AFTER - 10, FILL - 100, 25);

		repository = new FuncionarioRepository();
		funcionario = repository.buscarFuncionarioPorId(id);

		nome.setText(funcionario.getNome());
		sobrenome.setText(funcionario.getSobrenome());

		Spacer sp = new Spacer(0, 0);

		add(sp, CENTER, BOTTOM - 200, PARENTSIZE + 10, PREFERRED);

		add(btnSalvar = new Button("SALVAR"), LEFT + 100, SAME, PREFERRED + 100, 25, sp);
		btnSalvar.setBackColor(Color.GREEN);
		btnSalvar.setForeColor(Color.BLACK);

		add(btnLimpar = new Button("LIMPAR"), CENTER, SAME, PREFERRED + 100, 25, sp);

		add(btnCancelar = new Button("VOLTAR"), RIGHT - 100, SAME, PREFERRED + 100, 25, sp);
		btnCancelar.setBackColor(Color.RED);
		btnCancelar.setForeColor(Color.WHITE);

	}

	@Override
	public void onEvent(Event event) {
		try {
			switch (event.type) {
			case ControlEvent.PRESSED:
				if (event.target == btnSalvar) {

					Integer idFuncionario = new Integer(id);
					funcionario = new Funcionario();
					funcionario.setId(idFuncionario);
					funcionario.setNome(nome.getText());
					funcionario.setSobrenome(sobrenome.getText());

					repository = new FuncionarioRepository();
					repository.atualizaFuncionario(funcionario);

					Toast.show("Funcionário Atualizado com Sucesso !", 2000);

					TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios();
					telaListaFuncionarios.popup();
				} else if (event.target == btnLimpar) {
					clear();
				} else if (event.target == btnCancelar) {
					TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios();
					telaListaFuncionarios.popup();
				}
				break;

			}
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}
	}

}
