package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Pedido;
import model.PedidoDAO;
import model.ProdutoDAO;
import view.CadastroPedidoView;

public class InputListenerCadastroPedido implements MouseListener {
	CadastroPedidoView cadastroPedido;
	ProdutoDAO produtoDAO = new ProdutoDAO();
	private Pedido ped;
	private PedidoDAO pedDAO = new PedidoDAO();
	String[][] dados;
	double valorTotal = 0;

	public String[][] getDados() {
		return dados;
	}

	int cont = 0;

	public Pedido getPedido() {
		if (ped == null) {
			ped = new Pedido();
		}
		return ped;
	}

	public InputListenerCadastroPedido(CadastroPedidoView cadastroPedido) {
		// TODO Auto-generated constructor stub
		this.cadastroPedido = cadastroPedido;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroPedido.getBtnCancelar()) {
			cadastroPedido.dispose();
		} else if ((e.getSource()) == cadastroPedido.getBtnGravar()) {
			System.out.println("Botão ok Clicado");
			capturarDadosPedido();
		}
		if (e.getSource() == cadastroPedido.getBtnAdd()) {
			if (!cadastroPedido.getSpinnerQtde().getValue().toString().equals("0")) {
				int qtd = Integer.parseInt(cadastroPedido.getSpinnerQtde().getValue().toString());
				String produto[] = ((String) cadastroPedido.getComboBoxProduto().getSelectedItem()).split("-");
				if (produtoDAO.RetornaProduto(Integer.parseInt(produto[0])).getQtdEstoqueProduto() >= qtd) {
					double precoVenda = produtoDAO.buscarPrecoVenda(Integer.parseInt(produto[0]));
					double precoProdutos = qtd * precoVenda;
					String[] p = { produto[0], produto[1], qtd + "", precoProdutos + "" };
					if (getDados() == null) {
						dados = new String[1][4];
						dados[0][0] = p[0];
						dados[0][1] = p[1];
						dados[0][2] = p[2];
						dados[0][3] = p[3];

					} else {
						int i = dados.length;
						String[][] aux = new String[i + 1][4];
						for (int j = 0; j < i; j++) {
							aux[j][0] = dados[j][0];
							aux[j][1] = dados[j][1];
							aux[j][2] = dados[j][2];
							aux[j][3] = dados[j][3];
						}
						aux[i][0] = p[0];
						aux[i][1] = p[1];
						aux[i][2] = p[2];
						aux[i][3] = p[3];
						dados = new String[i + 1][4];
						for (int j = 0; j <= i; j++) {
							dados[j][0] = aux[j][0];
							dados[j][1] = aux[j][1];
							dados[j][2] = aux[j][2];
							dados[j][3] = aux[j][3];
						}

					}
					String[] colunas = { "id", "Nome", "Quantidade", "Preço(R$)" };

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
					cadastroPedido.getTableFuncionario().setModel(model);
					cadastroPedido.repaint();
					cadastroPedido.revalidate();
					valorTotal += precoProdutos;
					cadastroPedido.getTextPreco().setText(valorTotal + "");
				}

			}

		}
	}

	public void capturarDadosPedido() {

		if (!(cadastroPedido.getTextPreco().getText().equals(""))) {

			String[] funcionario = cadastroPedido.getComboBoxFuncionario().getSelectedItem().toString().split("-");
			String[] cliente = cadastroPedido.getComboBoxCliente().getSelectedItem().toString().split("-");

			getPedido().setIdCliente(Integer.parseInt(cliente[0]));
			getPedido().setIdFuncionario(Integer.parseInt(funcionario[0]));

			getPedido().setPrecoPed(Float.parseFloat(cadastroPedido.getTextPreco().getText()));

			getPedido().setDataPed(new Date(System.currentTimeMillis()));
			if (dados != null && dados.length > 0) {
				int cod = pedDAO.gravarPedido(getPedido(), dados);
				if (cod > 0) {
					if (produtoDAO.atualizaProdutoVenda(dados, cod)) {
						JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						cadastroPedido.dispose();
					} else
						JOptionPane.showMessageDialog(null, "Erro ao efetuar venda!", "Erro",
								JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Erro ao efetuar venda!", "Erro", JOptionPane.ERROR_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Adicione pelo menos um produto ao pedido!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if (e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}
}
