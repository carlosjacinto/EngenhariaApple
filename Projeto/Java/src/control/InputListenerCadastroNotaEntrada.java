package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import model.NotaEntrada;
import model.NotaEntradaDAO;
import model.ProdutoDAO;
import view.CadastroNotaEntradaView;

public class InputListenerCadastroNotaEntrada implements MouseListener {
	CadastroNotaEntradaView cadastroNotaEntrada;
	ProdutoDAO produtoDAO = new ProdutoDAO();
	private NotaEntrada nota;
	private NotaEntradaDAO notaDAO = new NotaEntradaDAO();
	String[][] dados;
	double valorTotal = 0;

	public InputListenerCadastroNotaEntrada(CadastroNotaEntradaView cadastroNotaEntrada) {
		// TODO Auto-generated constructor stub
		this.cadastroNotaEntrada = cadastroNotaEntrada;
	}

	public String[][] getDados() {
		return dados;
	}

	int cont = 0;

	public NotaEntrada getNotaEntrada() {
		if (nota == null) {
			nota = new NotaEntrada();
		}
		return nota;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getBtnCancelar()) {
			cadastroNotaEntrada.dispose();
		} else if ((e.getSource()) == cadastroNotaEntrada.getBtnGravar()) {
			System.out.println("Bot�o ok Clicado");
		} else if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			if (!cadastroNotaEntrada.getSpinnerQtde().getValue().toString().equals("0")) {
				int qtd = Integer.parseInt(cadastroNotaEntrada.getSpinnerQtde().getValue().toString());
				String produto[] = ((String) cadastroNotaEntrada.getComboBoxProduto().getSelectedItem()).split("-");
				if (produtoDAO.RetornaProduto(Integer.parseInt(produto[0])).getQtdEstoqueProduto() >= qtd) {
					double precoCompra = Integer.parseInt(cadastroNotaEntrada.getTextPrecoCustoUnit().getText());
					double precoProdutos = qtd * precoCompra;
					String[] p = { produto[0], produto[1], precoCompra + "", qtd + "", precoProdutos + "" };
					if (getDados() == null) {
						dados = new String[1][5];
						dados[0][0] = p[0];
						dados[0][1] = p[1];
						dados[0][2] = p[2];
						dados[0][3] = p[3];
						dados[0][3] = p[4];

					} else {
						int i = dados.length;
						String[][] aux = new String[i + 1][5];
						for (int j = 0; j < i; j++) {
							aux[j][0] = dados[j][0];
							aux[j][1] = dados[j][1];
							aux[j][2] = dados[j][2];
							aux[j][3] = dados[j][3];
							aux[j][4] = dados[j][4];
						}
						aux[i][0] = p[0];
						aux[i][1] = p[1];
						aux[i][2] = p[2];
						aux[i][3] = p[3];
						aux[i][4] = p[4];
						dados = new String[i + 1][5];
						for (int j = 0; j <= i; j++) {
							dados[j][0] = aux[j][0];
							dados[j][1] = aux[j][1];
							dados[j][2] = aux[j][2];
							dados[j][3] = aux[j][3];
							dados[j][4] = aux[j][4];
						}
						String[] colunas = { "C�digo", "Nome", "Pre�o Unit", "Quantidade", "Pre�o Total" };
						DefaultTableModel model = new DefaultTableModel(dados, colunas) {
							/**
							* 
							*/
							private static final long serialVersionUID = -7018342759131611914L;
							boolean[] canEdit = new boolean[] { false, false, false, false };

							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return canEdit[columnIndex];
							}
						};
						cadastroNotaEntrada.getTableFuncionario().setModel(model);
						cadastroNotaEntrada.repaint();
						cadastroNotaEntrada.revalidate();
						valorTotal += precoProdutos;
						cadastroNotaEntrada.getTextFieldVTotalProd().setText(valorTotal + "");
					}

				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}
}