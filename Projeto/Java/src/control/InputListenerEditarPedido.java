package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.ContaDAO;
import model.Pedido;
import model.PedidoDAO;
import model.ProdutoDAO;
import view.EditarPedidoView;

public class InputListenerEditarPedido implements MouseListener {
	EditarPedidoView editarPedido;
	ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
	Pedido ped;
	private ContaDAO contaDAO = ContaDAO.getInstance();
	private PedidoDAO pedDAO = PedidoDAO.getInstance();
	String[][] dados;

	double valorTotal=0;

	public String[][] getDados() {
		return dados;
	}

	int cont = 0;

	public Pedido getPedido2() {
		if (ped == null) {
			ped = editarPedido.getPedido();
		}
		return ped;
	}

	public InputListenerEditarPedido(EditarPedidoView editarPedido) {
		// TODO Auto-generated constructor stub
		this.editarPedido = editarPedido;
		dados = pedDAO.retornaProdutosPed(getPedido2().getIdPedido());
		valorTotal = Double.parseDouble(editarPedido.getTextPreco().getText());
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == editarPedido.getBtnCancelar()) {
			editarPedido.dispose();
		} else if ((e.getSource()) == editarPedido.getBtnGravar()) {
			capturarDadosPedido();
		}
		if (e.getSource() == editarPedido.getBtnAdd()) {
			if (!editarPedido.getSpinnerQtde().getValue().toString().equals("0")) {
				int qtd = Integer.parseInt(editarPedido.getSpinnerQtde().getValue().toString());
				String produto[] = ((String) editarPedido.getComboBoxProduto().getSelectedItem()).split("-");
				if (produtoDAO.RetornaProduto(Integer.parseInt(produto[0])).getQtdEstoqueProduto() >= qtd) {
					editarPedido.getComboBoxProduto().removeItemAt(editarPedido.getComboBoxProduto().getSelectedIndex());
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
					editarPedido.getTableFuncionario().setModel(model);
					editarPedido.repaint();
					editarPedido.revalidate();
					valorTotal += precoProdutos;
					editarPedido.getTextPreco().setText(valorTotal + "");
				}else {
					JOptionPane.showMessageDialog(null, "Quantidade maior que o número deste produto no estoque!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}

	public void capturarDadosPedido() {

		if (!(editarPedido.getTextPreco().getText().equals(""))) {

			getPedido2().setPrecoPed(Float.parseFloat(editarPedido.getTextPreco().getText()));

			if (dados != null && dados.length > 0) {
				float vAntigo = pedDAO.editarPedido(getPedido2(), dados);
				if (vAntigo > 0) {
					if (produtoDAO.atualizaProdutoVenda(dados, getPedido2().getIdPedido())) {
						contaDAO.atualizaValorConta(getPedido2().getPrecoPed()-vAntigo, getPedido2().getIdCliente());
						JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						editarPedido.dispose();
					} else
						JOptionPane.showMessageDialog(null, "Erro ao efetuar atualização da venda!", "Erro",
								JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Erro ao efetuar atualização da venda!", "Erro", JOptionPane.ERROR_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Adicione pelo menos um produto ao pedido!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if (e.getSource() == editarPedido.getBtnAdd()) {
			editarPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource() == editarPedido.getBtnAdd()) {
			editarPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource() == editarPedido.getBtnAdd()) {
			editarPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == editarPedido.getBtnAdd()) {
			editarPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}
}