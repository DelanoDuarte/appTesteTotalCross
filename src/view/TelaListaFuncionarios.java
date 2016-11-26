/**
 * 
 */
package view;

import java.sql.SQLException;
import java.util.List;

import model.Funcionario;
import repository.FuncionarioRepository;
import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.Spacer;
import totalcross.ui.Toast;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Rect;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

/**
 * @author delano.junior
 *
 */
public class TelaListaFuncionarios extends Window {

	private ListBox listBox;
	private FuncionarioRepository repository;
	private Button btnEditar, btnExcluir, btnSair, btnVisualizar;
	private Grid grid;

	public TelaListaFuncionarios() throws ImageException, IOException {

		setBackColor(Color.WHITE);

		Settings.uiAdjustmentsBasedOnFontHeight = true;

		setBackColor(0xDDDDFF);

		add(new Label("Funcionarios Cadastrados"), CENTER, TOP + 50);

		repository = new FuncionarioRepository();

		List<Funcionario> funcionarios = repository.buscarTodos();

		Rect r = getClientRect();

		String[] gridCaptions = { " ID ", " Nome ", " Sobrenome " };
		int gridWidths[] = { -5, -35, -30 };
		int gridAligns[] = { LEFT, LEFT, LEFT };
		grid = new Grid(gridCaptions, gridWidths, gridAligns, false);
		add(grid, LEFT + 5, TOP + 5, r.width, r.height / 2 + r.height / 3);
		grid.secondStripeColor = Color.getRGB(235, 235, 235);

		String[][] lista = new String[100][3];
		int i, j;
		i = j = 0;

		for (Funcionario funcionario : funcionarios) {
			lista[i][j] = Integer.toString(funcionario.getId());
			j++;
			lista[i][j] = funcionario.getNome();
			j++;
			lista[i][j] = funcionario.getSobrenome();
			j = 0;
			i++;
		}
		grid.setItems(lista);

		Spacer sp = new Spacer(0, 0), sp2 = new Spacer(0, 0);

		add(sp, CENTER, BOTTOM - 200, PARENTSIZE + 10, PREFERRED);
		add(sp2, CENTER, BOTTOM - 400, PARENTSIZE + 10, PREFERRED);

		add(btnExcluir = new Button("Excluir", new Image("imagens/delete-icon.png"), RIGHT, 0), LEFT - 100, SAME,
				PREFERRED + 100, 25, sp2);
		btnExcluir.setBackColor(Color.RED);
		btnExcluir.setForeColor(Color.WHITE);

		add(btnEditar = new Button("Editar", new Image("imagens/edit-icon.png"), RIGHT, 0), CENTER, SAME,
				PREFERRED + 100, 25, sp2);
		btnEditar.setBackColor(Color.YELLOW);
		btnEditar.setForeColor(Color.BLACK);

		add(btnVisualizar = new Button("Ver", new Image("imagens/search-icon.png"), RIGHT, 0), RIGHT - 100, SAME,
				PREFERRED + 100, 25, sp2);
		btnVisualizar.setBackColor(Color.GREEN);
		btnVisualizar.setForeColor(Color.BLACK);

		add(btnSair = new Button("Voltar", new Image("imagens/back-icon.png"), RIGHT, 0), LEFT, SAME, PREFERRED + 100,
				25, sp);
		btnSair.setBackColor(Color.RED);
		btnSair.setForeColor(Color.WHITE);
	}

	@Override
	public void onEvent(Event event) {
		try {
			switch (event.type) {
			case ControlEvent.PRESSED:
				if (event.target == btnExcluir) {
					if (grid.getSelectedItem() == null) {
						Toast.show("Selecione algum Funcionário para Excluir", 2000);
					} else {
						try {
							String id[] = (String[]) grid.getSelectedItem();
							repository = new FuncionarioRepository();
							repository.deletarFuncionario(id[0]);
							Toast.show("Funcionario Deletado com Sucesso", 2000);

							TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios();
							telaListaFuncionarios.popup();
						} catch (SQLException e) {
							// MessageBox.showException(e, true);
						}
					}
				} else if (event.target == btnEditar) {
					if (grid.getSelectedItem() == null) {
						Toast.show("Escolha um Funcionário para Editar", 2000);
					} else {
						try {
							String id[] = (String[]) grid.getSelectedItem();
							TelaEditarFuncionario telaEditarFuncionario = new TelaEditarFuncionario(id[0]);
							telaEditarFuncionario.popup();

						} catch (Exception e) {
							// MessageBox.showException(e, true);
						}

					}
				} else if (event.target == btnVisualizar) {
					if (grid.getSelectedItem() == null) {
						Toast.show("Escolha um Funcionário para Visualizar", 2000);
					} else {
						try {
							String id[] = (String[]) grid.getSelectedItem();
							TelaVisualizarFuncionario telaVisualizarFuncionario = new TelaVisualizarFuncionario(id[0]);
							telaVisualizarFuncionario.popup();

						} catch (Exception e) {
							// MessageBox.showException(e, true);
						}

					}
				}

				else if (event.target == btnSair) {
					TelaHome home = new TelaHome();
					home.popup();
				}

				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.onEvent(event);
	}

}
