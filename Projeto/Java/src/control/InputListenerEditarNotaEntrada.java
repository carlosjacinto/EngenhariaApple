package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.NotaEntrada;
import model.NotaEntradaDAO;
import model.ProdutoDAO;
import view.CadastroNotaEntradaView;
import view.EditarNotaEntradaView;

public class InputListenerEditarNotaEntrada implements MouseListener {
	EditarNotaEntradaView edicaoNotaEntrada;
	ProdutoDAO produtoDAO = new ProdutoDAO();
	private NotaEntrada nota;
	private NotaEntradaDAO notaDAO = new NotaEntradaDAO();
	String[][] dados;
	double valorTotal = 0;

	public InputListenerEditarNotaEntrada(EditarNotaEntradaView edicaoNotaEntrada) {
		// TODO Auto-generated constructor stub
		this.edicaoNotaEntrada = edicaoNotaEntrada;
		dados = notaDAO.retornaProdutosNota(getNotaEntrada().getIdCompra());
		valorTotal = Double.parseDouble(edicaoNotaEntrada.getTextTotalNota().getText());
	}

	public String[][] getDados() {
		return dados;
	}

	int cont = 0;

	public NotaEntrada getNotaEntrada() {
		if (nota == null) {
			nota = edicaoNotaEntrada.getNota();
		}
		return nota;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == edicaoNotaEntrada.getBtnCancelar()) {
			edicaoNotaEntrada.dispose();
		} else if ((e.getSource()) == edicaoNotaEntrada.getBtnGravar()) {
			capturarDadosNota();
		} else if (e.getSource() == edicaoNotaEntrada.getBtnAddProduto()) {

			if (!edicaoNotaEntrada.getSpinnerQtde().getValue().toString().equals("0")) {
				int qtd = Integer.parseInt(edicaoNotaEntrada.getSpinnerQtde().getValue().toString());
				String produto[] = ((String) edicaoNotaEntrada.getComboBoxProduto().getSelectedItem()).split("-");
				edicaoNotaEntrada.getComboBoxProduto()
						.removeItemAt(edicaoNotaEntrada.getComboBoxProduto().getSelectedIndex());
				double precoCompra = Integer.parseInt(edicaoNotaEntrada.getTextPrecoCustoUnit().getText());
				System.out.println("preco unit" + precoCompra);
				double precoProdutos = qtd * precoCompra;
				String[] p = { produto[0], produto[1], precoCompra + "", qtd + "", precoProdutos + "" };
				if (getDados() == null) {
					dados = new String[1][5];
					dados[0][0] = p[0];
					dados[0][1] = p[1];
					dados[0][2] = p[2];
					dados[0][3] = p[3];
					dados[0][4] = p[4];

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

				}
				String[] colunas = { "Código", "Nome", "Preço Unit", "Quantidade", "Preço Total" };
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
				edicaoNotaEntrada.getTableNotaEntrada().setModel(model);
				edicaoNotaEntrada.repaint();
				edicaoNotaEntrada.revalidate();
				valorTotal += precoProdutos;
				edicaoNotaEntrada.getTextFieldVTotalProd().setText(valorTotal + "");
				edicaoNotaEntrada.getTextTotalNota().setText(
						(valorTotal + Float.parseFloat((edicaoNotaEntrada.getTextFieldOutrosCustos().getText()))) + "");

			}
		}
	}

	public void capturarDadosNota() {
		try {
			edicaoNotaEntrada.getTextTotalNota()
					.setText((Float.parseFloat(edicaoNotaEntrada.getTextFieldVTotalProd().getText())
							+ Float.parseFloat((edicaoNotaEntrada.getTextFieldOutrosCustos().getText()))) + "");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor Inválido! (Campo: Outros)", null, JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!(edicaoNotaEntrada.getTextFieldVTotalProd().getText().equals("")
				|| edicaoNotaEntrada.getTextFieldOutrosCustos().getText().equals("")
				|| edicaoNotaEntrada.getTextFieldNomeFornec().getText().equals("")
				|| edicaoNotaEntrada.getTextDataEmissao().getText().equals(""))) {

			try {
				getNotaEntrada().setTotal(Float.parseFloat((edicaoNotaEntrada.getTextTotalNota().getText())));
				getNotaEntrada().setOutros(Float.parseFloat((edicaoNotaEntrada.getTextFieldOutrosCustos().getText())));
				getNotaEntrada().setNumeroNota(Integer.parseInt(edicaoNotaEntrada.getTextNumeroNFE().getText()));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor Inválido! (Campo: Numero da Nota)", null,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String[] funcionario = edicaoNotaEntrada.getComboBoxFuncionario().getSelectedItem().toString().split("-");
			getNotaEntrada().setIdFuncionario(Integer.parseInt(funcionario[0]));

			getNotaEntrada().setDataEntrada(new Date(System.currentTimeMillis()));
			getNotaEntrada().setChaveAcesso(edicaoNotaEntrada.getTextFieldChaveNFE().getText());
			getNotaEntrada().setCnpj(edicaoNotaEntrada.getTextFieldCNPJ().getText());
			getNotaEntrada().setFornecedor(edicaoNotaEntrada.getTextFieldNomeFornec().getText());
			getNotaEntrada().setDataCompra(edicaoNotaEntrada.getTextDataEmissao().getText());
			getNotaEntrada().setIdCompra(Integer.parseInt(edicaoNotaEntrada.getTextNumeroNFE().getText()));
			int tam = edicaoNotaEntrada.getTextFieldChaveNFE().getText().length();
			if (!(tam == 44 || tam == 0)) {
				JOptionPane.showMessageDialog(null,
						"Chave de Acesso deve possuir 44 digitos. Deixe em branco ou digite-a corretamente.",
						"Erro ao preencher campo", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String s = edicaoNotaEntrada.getTextDataEmissao().getText();
			if ((Integer.parseInt(s.substring(0, 2)) > 31) || (Integer.parseInt(s.substring(3, 5)) > 12)
					|| (Integer.parseInt(s.substring(0, 2)) < 1) || (Integer.parseInt(s.substring(3, 5)) < 1)) {
				JOptionPane.showMessageDialog(null, "Valor(es) de data invalido(s).", "Erro ao preencher campo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (dados != null && dados.length > 0) {
				float vAntigo = notaDAO.editarCompra(getNotaEntrada(), dados);
				if (vAntigo > 0) {
					JOptionPane.showMessageDialog(null, "Editado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

					int result = JOptionPane.showConfirmDialog(null,
							"Deseja atualizar o preço de custo e venda das mercadorias?", "Atualizar preços",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						if (produtoDAO.atualizaProdutoCompra(dados, true, getNotaEntrada().getIdCompra())) {
							JOptionPane.showMessageDialog(null, "Estoque e Preços atualizados com sucesso!", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "Erro ao atualizar", "Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						if (produtoDAO.atualizaProdutoCompra(dados, false, getNotaEntrada().getIdCompra())) {
							JOptionPane.showMessageDialog(null, "Estoque atualizado com sucesso!", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "Erro ao atualizar", "Erro", JOptionPane.ERROR_MESSAGE);

					}
					edicaoNotaEntrada.dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Adicione pelo menos um produto a nota!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Valores em Branco!", null, JOptionPane.WARNING_MESSAGE);

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if (e.getSource() == edicaoNotaEntrada.getBtnAddProduto()) {
			edicaoNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource() == edicaoNotaEntrada.getBtnAddProduto()) {
			edicaoNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource() == edicaoNotaEntrada.getBtnAddProduto()) {
			edicaoNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == edicaoNotaEntrada.getBtnAddProduto()) {
			edicaoNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}
}
