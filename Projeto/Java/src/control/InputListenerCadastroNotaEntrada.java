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
			System.out.println("Botão ok Clicado");

			capturarDadosNotaEntrada();
		} else if (e.getSource() == cadastroNotaEntrada.getBtnAddProduto()) {

			if (!cadastroNotaEntrada.getSpinnerQtde().getValue().toString().equals("0")) {
				int qtd = Integer.parseInt(cadastroNotaEntrada.getSpinnerQtde().getValue().toString());
				String produto[] = ((String) cadastroNotaEntrada.getComboBoxProduto().getSelectedItem()).split("-");
				cadastroNotaEntrada.getComboBoxProduto().removeItemAt(cadastroNotaEntrada.getComboBoxProduto().getSelectedIndex());
				double precoCompra = Integer.parseInt(cadastroNotaEntrada.getTextPrecoCustoUnit().getText());
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
				cadastroNotaEntrada.getTableNotaEntrada().setModel(model);
				cadastroNotaEntrada.repaint();
				cadastroNotaEntrada.revalidate();
				valorTotal += precoProdutos;
				cadastroNotaEntrada.getTextFieldVTotalProd().setText(valorTotal + "");
				cadastroNotaEntrada.getTextTotalNota().setText(
						(valorTotal + Float.parseFloat((cadastroNotaEntrada.getTextFieldOutrosCustos().getText())))
								+ "");

			}
		}
	}

	public void capturarDadosNotaEntrada() {
		try {
			cadastroNotaEntrada.getTextTotalNota()
					.setText((Float.parseFloat(cadastroNotaEntrada.getTextFieldVTotalProd().getText())
							+ Float.parseFloat((cadastroNotaEntrada.getTextFieldOutrosCustos().getText()))) + "");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor Inválido! (Campo: Outros)", null, JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!(cadastroNotaEntrada.getTextCodigo().getText().equals("")
				|| cadastroNotaEntrada.getTextFieldVTotalProd().getText().equals("")
				|| cadastroNotaEntrada.getTextFieldOutrosCustos().getText().equals("")
				|| cadastroNotaEntrada.getTextFieldCNPJ().getText().equals("")
				|| cadastroNotaEntrada.getTextFieldNomeFornec().getText().equals("")
				|| cadastroNotaEntrada.getTextDataEmissao().getText().equals(""))) {

			try {
				getNotaEntrada().setTotal(Float.parseFloat((cadastroNotaEntrada.getTextFieldVTotalProd().getText())));
				getNotaEntrada()
						.setOutros(Float.parseFloat((cadastroNotaEntrada.getTextFieldOutrosCustos().getText())));
				getNotaEntrada().setNumeroNota(Integer.parseInt(cadastroNotaEntrada.getTextCodigo().getText()));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor Inválido! (Campo: Numero da Nota)", null,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String[] funcionario = cadastroNotaEntrada.getComboBoxFuncionario().getSelectedItem().toString().split("-");
			getNotaEntrada().setIdFuncionario(Integer.parseInt(funcionario[0]));

			getNotaEntrada().setDataEntrada(new Date(System.currentTimeMillis()));
			getNotaEntrada().setChaveAcesso(cadastroNotaEntrada.getTextFieldChaveNFE().getText());
			getNotaEntrada().setCnpj(cadastroNotaEntrada.getTextFieldCNPJ().getText());
			getNotaEntrada().setFornecedor(cadastroNotaEntrada.getTextFieldNomeFornec().getText());
			getNotaEntrada().setDataCompra(cadastroNotaEntrada.getTextDataEmissao().getText());

			int tam = cadastroNotaEntrada.getTextFieldChaveNFE().getText().length();
			System.out.println(tam);
			if (!(tam == 44 || tam == 0)) {
				JOptionPane.showMessageDialog(null,
						"Chave de Acesso deve possuir 44 digitos. Deixe em branco ou digite-a corretamente.",
						"Erro ao preencher campo", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (notaDAO.VerificaCompra(getNotaEntrada()) >= 0) {
				JOptionPane.showMessageDialog(null, "Essa nota já consta em nossa base de dados",
						"Erro ao preencher campo", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (Integer.parseInt(cadastroNotaEntrada.getTextCodigo().getText()) < 0) {
				JOptionPane.showMessageDialog(null, "Número da NFE está incorreto", "Erro ao preencher campo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String s =cadastroNotaEntrada.getTextDataEmissao().getText();
			if ((Integer.parseInt(s.substring(0, 2))>31) 
					|| (Integer.parseInt(s.substring(3,5))>12) 
					|| (Integer.parseInt(s.substring(0, 2))<1) 
					||(Integer.parseInt(s.substring(3,5))<1)) {
				JOptionPane.showMessageDialog(null,
						"Valor(es) de data invalido(s).",
						"Erro ao preencher campo", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (dados != null && dados.length > 0) {
				int cod = notaDAO.gravarCompra(getNotaEntrada(), dados);
				if (cod >0) {
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

					int result = JOptionPane.showConfirmDialog(null,
							"Deseja atualizar o preço de custo e venda das mercadorias?", "Atualizar preços",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						if (produtoDAO.atualizaProdutoCompra(dados, true, cod )) {
							JOptionPane.showMessageDialog(null, "Estoque e Preços atualizados com sucesso!", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "Erro ao atualizar", "Erro", JOptionPane.ERROR_MESSAGE);
					}else {
						if (produtoDAO.atualizaProdutoCompra(dados, false, cod)) {
							JOptionPane.showMessageDialog(null, "Estoque atualizado com sucesso!", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
						}else JOptionPane.showMessageDialog(null, "Erro ao atualizar", "Erro", JOptionPane.ERROR_MESSAGE);
							
					}
					cadastroNotaEntrada.dispose();

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

		if (e.getSource() == cadastroNotaEntrada.getBtnAddProduto()) {
			cadastroNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource() == cadastroNotaEntrada.getBtnAddProduto()) {
			cadastroNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource() == cadastroNotaEntrada.getBtnAddProduto()) {
			cadastroNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getBtnAddProduto()) {
			cadastroNotaEntrada.getBtnAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}
}
