/**
 * 
 */
package view;

import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.Spacer;
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
public class TelaHome extends MainWindow {

	private Button btnListar, btnNovo;

	@Override
	public void initUI() {
		super.initUI();
	}

	public TelaHome() throws ImageException, IOException {

		Settings.uiAdjustmentsBasedOnFontHeight = true;

		setBackColor(0xD3D3D3);

		Spacer sp = new Spacer(0, 0);
		add(new Label("BEM VINDO ! "), CENTER, AFTER + 50);
		add(sp, CENTER, TOP + 400, PARENTSIZE + 10, PREFERRED);

		add(btnListar = new Button(""), BEFORE, 150, PARENTSIZE + 38, 115, sp);
		btnListar.setImage(new Image("imagens/list.png"));
		btnListar.setBackColor(Color.WHITE);
		btnListar.setForeColor(Color.BLACK);

		add(new Label("Lista Funcionarios"), BEFORE, 260, PARENTSIZE + 41, 30, sp);

		add(btnNovo = new Button(""), AFTER, 150, PARENTSIZE + 38, 115, sp);
		btnNovo.setImage(new Image("imagens/add.png"));
		btnNovo.setBackColor(Color.WHITE);
		btnNovo.setForeColor(Color.BLACK);

		add(new Label("Novo Funcionário"), AFTER, 260, PARENTSIZE + 41, 30, sp);
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
