/**
 * 
 */
package view;

import model.Funcionario;
import repository.FuncionarioRepository;
import totalcross.io.IOException;
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
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

/**
 * @author delano.junior
 *
 */
public class TelaVisualizarFuncionario extends Window {

	private Edit nomeCompleto, salario, cargo;
	private Button btnCancelar;
	String id;
	private FuncionarioRepository repository;
	private Funcionario funcionario;

	public TelaVisualizarFuncionario(String id) throws ImageException, IOException {

		this.id = id;
		gradientTitleStartColor = 0;
		gradientTitleEndColor = 0xAAAAFF;

		Settings.uiAdjustmentsBasedOnFontHeight = true;

		setBackColor(0xDDDDFF);

		add(new Label("Visualizando um Funcionario"), CENTER, TOP + 50);

		add(new Label("Nome Completo: "), LEFT + 100, AFTER);
		add(nomeCompleto = new Edit(), LEFT, SAME);
		nomeCompleto.setRect(LEFT + 100, AFTER, FILL - 100, 25);

		add(new Label("Salario: "), LEFT + 100, AFTER);
		add(salario = new Edit(), LEFT, SAME);
		salario.setRect(LEFT + 100, AFTER, FILL - 100, 25);

		add(new Label("Cargo: "), LEFT + 100, AFTER);
		add(cargo = new Edit(), LEFT, SAME);
		cargo.setRect(LEFT + 100, AFTER, FILL - 100, 25);

		repository = new FuncionarioRepository();
		funcionario = repository.buscarFuncionarioPorId(id);

		nomeCompleto.setText(funcionario.getNome() + " " + funcionario.getSobrenome());
		nomeCompleto.setEditable(false);

		String salarioTemp = String.valueOf(funcionario.getSalario());
		salario.setText("R$" + " " + salarioTemp);
		salario.setEditable(false);

		cargo.setText(funcionario.getCargo());
		cargo.setEditable(false);

		Spacer sp = new Spacer(0, 0);

		add(sp, CENTER, BOTTOM - 200, PARENTSIZE + 10, PREFERRED);

		add(btnCancelar = new Button("VOLTAR", new Image("imagens/back-icon.png"), RIGHT, 0), RIGHT - 100, SAME,
				PREFERRED + 100, 25, sp);
		btnCancelar.setBackColor(Color.RED);
		btnCancelar.setForeColor(Color.WHITE);

	}

	@Override
	public void onEvent(Event event) {
		try {
			switch (event.type) {
			case ControlEvent.PRESSED:
				if (event.target == btnCancelar) {
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
