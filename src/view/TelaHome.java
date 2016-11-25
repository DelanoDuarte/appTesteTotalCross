/**
 * 
 */
package view;

import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

/**
 * @author delan
 *
 */
public class TelaHome extends MainWindow {

	private Button btnListar, btnNovo;

	@Override
	public void initUI() {
		super.initUI();
	}

	public TelaHome() {

		gradientTitleStartColor = 0;
		gradientTitleEndColor = 0xAAAAFF;

		Settings.uiAdjustmentsBasedOnFontHeight = true;

		setBackColor(0xDDDDFF);

		Spacer sp = new Spacer(0, 0);
		add(new Label("TELA INICIAL "), CENTER, AFTER + 50);
		add(sp, CENTER, TOP + 400, PARENTSIZE + 10, PREFERRED);

		add(btnListar = new Button("Listar Funcionarios "), BEFORE, 150, PARENTSIZE + 40, 30, sp);
		btnListar.setBackColor(Color.BRIGHT);
		btnListar.setForeColor(Color.BLACK);

		add(btnNovo = new Button("Novo Funcionario "), AFTER, 150, PARENTSIZE + 40, 30, sp);
		btnNovo.setBackColor(Color.BRIGHT);
		btnNovo.setForeColor(Color.BLACK);
	}

	@Override
	public void onEvent(Event event) {
		try {
			switch (event.type) {
			case ControlEvent.PRESSED:
				if (event.target == btnListar) {
					TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios();
					telaListaFuncionarios.popup();
				} else if (event.target == btnNovo) {
					TelaAddFuncionario telaAddFuncionario = new TelaAddFuncionario();
					telaAddFuncionario.popup();
				}
				break;

			}
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}
	}
}
