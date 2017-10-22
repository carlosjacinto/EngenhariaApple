package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import control.InputListenerCadastroNotaEntrada;
import model.FuncionarioDAO;
import model.NotaEntradaDAO;
import model.ProdutoDAO;

public class CadastroNotaEntradaView extends JDialog {

	private static final long serialVersionUID = 967851639105823465L;
	InputListenerCadastroNotaEntrada listener;
	private JTable tableProduto;
	private JPanel contentPanel;
	private JLabel lblFuncionario;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JComboBox<Object> comboBoxFuncionario;
	private JLabel labelCodigoProduto;
	private JLabel lblQuantidade;
	private JLabel lblPreoTotalr;
	public JTextField textTotalNota;
	private JSpinner spinnerQtde;
	private JLabel lblDataEmissao;
	public JTextField txtDataEmissao;
	public JTextField textCodigo;
	private JLabel lblNumNFE;
	private JTable tableNotaEntrada;
	private JScrollPane scrollPane;
	private NotaEntradaDAO notaEntDAO = new NotaEntradaDAO();
	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	ProdutoDAO produtoDAO = new ProdutoDAO();
	public JTextField textPrecoCustoUnit;
	private JSeparator separator;
	private JLabel lblPrecoCustoUnit;
	private JLabel lblCNPJ;
	public JTextField textFieldCNPJ;
	private JLabel lblNomeFornec;
	public JTextField textFieldNomeFornec;
	private JLabel lblValorTotalProd;
	public JTextField textFieldVTotalProd;
	public JTextField textFieldOutrosCustos;
	private JLabel lblOutrosCustos;
	private JLabel lblNewLabel;
	public JTextField textFieldChaveNFE;
	private JLabel lblAddProduto;
	private JComboBox comboBoxProduto;

	public static void main(String[] args) {
		try {
			EditarNotaEntradaView dialog = new EditarNotaEntradaView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CadastroNotaEntradaView() {
		listener = new InputListenerCadastroNotaEntrada(this);
		initialize();
		initializeListeners();

	}

	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
		getBtnAddProduto().addMouseListener(listener);
	}

	public JTable getTableFuncionario() {
		if (tableProduto == null) {
			String[] colunas = { "id", "Nome", "Quantidade", "Preço(R$)" };
			String[][] dados = null;

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
			tableProduto = new JTable(model);
			tableProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableProduto;
	}

	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 666, 528);
		setContentPane(getContentPanel());
		setTitle("Cadastro de Nota de Entrada");

	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.setLayout(null);
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getLblFuncionario());
			contentPanel.add(getComboBoxFuncionario());
			contentPanel.add(getLabelCodigoProduto());
			contentPanel.add(getLblQuantidade());
			contentPanel.add(getLblPreoTotalr());
			contentPanel.add(getTextTotalNota());
			contentPanel.add(getSpinnerQtde());
			contentPanel.add(getLblNumNFE());
			contentPanel.add(getTextCodigo());
			contentPanel.add(getTextDataEmissao());
			contentPanel.add(getLblDataEmissao());
			contentPanel.add(getLblPrecoCustoUnit());
			contentPanel.add(getSeparator());
			contentPanel.add(getTextPrecoCustoUnit());
			contentPanel.add(getScrollBar());
			contentPanel.add(getLblCNPJ());
			contentPanel.add(getTextFieldCNPJ());
			contentPanel.add(getLblNomeFornec());
			contentPanel.add(getTextFieldNomeFornec());
			contentPanel.add(getLblValorTotalProd());
			contentPanel.add(getTextFieldVTotalProd());
			contentPanel.add(getTextFieldOutrosCustos());
			contentPanel.add(getLblOutrosCustos());
			contentPanel.add(getLblNewLabel());
			contentPanel.add(getTextFieldChaveNFE());
			contentPanel.add(getBtnAddProduto());
			contentPanel.add(getComboBoxProduto());

		}
		return contentPanel;
	}

	public JTextField getTextPrecoCustoUnit() {
		if (textPrecoCustoUnit == null) {
			textPrecoCustoUnit = new JTextField();
			textPrecoCustoUnit.setBounds(437, 213, 130, 20);
			textPrecoCustoUnit.setColumns(10);
		}
		return textPrecoCustoUnit;
	}

	public JLabel getLblPrecoCustoUnit() {
		if (lblPrecoCustoUnit == null) {
			lblPrecoCustoUnit = new JLabel("Preço de Custo Unitário (R$)");
			lblPrecoCustoUnit.setBounds(439, 188, 176, 14);
		}
		return lblPrecoCustoUnit;
	}

	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(30, 175, 593, 2);
		}
		return separator;
	}

	public JScrollPane getScrollBar() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTableNotaEntrada());
			scrollPane.setBounds(30, 252, 593, 189);
		}
		return scrollPane;
	}

	public JTable getTableNotaEntrada() {
		if (tableNotaEntrada == null) {
			String[][] notas = null; // = notaEntDAO.listaNotaEntradaArray("");
			String[] colunas = { "Código", "Nome", "Preço Unit", "Quantidade", "Preço Total" };

			DefaultTableModel model = new DefaultTableModel(notas, colunas) {
				/**
				* 
				*/
				private static final long serialVersionUID = -7018342759131611914L;
				boolean[] canEdit = new boolean[] { false, false, false, false, false };

				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			tableNotaEntrada = new JTable(model);
			tableNotaEntrada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableNotaEntrada;
	}

	private JLabel getLblFuncionario() {
		if (lblFuncionario == null) {
			lblFuncionario = new JLabel("Funcion\u00E1rio");
			lblFuncionario.setBounds(183, 71, 75, 14);
		}
		return lblFuncionario;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(534, 452, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(426, 452, 89, 23);
		}
		return btnGravar;
	}

	public JComboBox<Object> getComboBoxFuncionario() {
		if (comboBoxFuncionario == null) {
			String funcionarios[] = funcionarioDAO.buscarNomeeId();
			comboBoxFuncionario = new JComboBox<Object>(funcionarios);
			comboBoxFuncionario.setBounds(183, 96, 281, 20);
		}
		return comboBoxFuncionario;
	}

	public JLabel getLabelCodigoProduto() {
		if (labelCodigoProduto == null) {
			labelCodigoProduto = new JLabel("C\u00F3digo - Nome do Produto");
			labelCodigoProduto.setBounds(30, 188, 312, 14);
		}
		return labelCodigoProduto;
	}

	public JLabel getLblQuantidade() {
		if (lblQuantidade == null) {
			lblQuantidade = new JLabel("Quantidade");
			lblQuantidade.setBounds(352, 188, 89, 14);
		}
		return lblQuantidade;
	}

	public JLabel getLblPreoTotalr() {
		if (lblPreoTotalr == null) {
			lblPreoTotalr = new JLabel("Pre\u00E7o Total (R$)");
			lblPreoTotalr.setBounds(474, 127, 141, 14);
		}
		return lblPreoTotalr;
	}

	public JTextField getTextTotalNota() {
		if (textTotalNota == null) {
			textTotalNota = new JTextField();
			textTotalNota.setEditable(true);
			textTotalNota.setBounds(474, 144, 152, 20);
			//textTotalNota.setText((double)(getTextFieldOutrosCustos()+getTextFieldVTotalProd())+"");
			textTotalNota.setColumns(10);
			textTotalNota.setEditable(false);
		}
		return textTotalNota;
	}

	public JSpinner getSpinnerQtde() {
		if (spinnerQtde == null) {
			spinnerQtde = new JSpinner();
			spinnerQtde.setBounds(352, 213, 75, 20);
		}
		return spinnerQtde;
	}

	public JTextField getTextCodigo() {
		if (textCodigo == null) {
			textCodigo = new JTextField();
			textCodigo.setBounds(30, 37, 141, 22);
			textCodigo.setEditable(true);
		}
		return textCodigo;
	}

	public JLabel getLblNumNFE() {
		if (lblNumNFE == null) {
			lblNumNFE = new JLabel("N\u00FAmero da NFe");
			lblNumNFE.setBounds(30, 12, 141, 14);
		}
		return lblNumNFE;
	}

	public JTextField getTextDataEmissao() {
		if (txtDataEmissao == null) {
			txtDataEmissao = new JTextField();
			txtDataEmissao.setBounds(310, 144, 149, 20);
			txtDataEmissao.setColumns(10);
			txtDataEmissao.setEditable(true);
		}
		return txtDataEmissao;
	}

	public JLabel getLblDataEmissao() {
		if (lblDataEmissao == null) {
			lblDataEmissao = new JLabel("Data de Emissao da NFe");
			lblDataEmissao.setBounds(313, 127, 165, 14);
		}
		return lblDataEmissao;
	}

	private JLabel getLblCNPJ() {
		if (lblCNPJ == null) {
			lblCNPJ = new JLabel("CNPJ Fornecedor");
			lblCNPJ.setBounds(30, 70, 141, 14);
		}
		return lblCNPJ;
	}

	public JTextField getTextFieldCNPJ() {
		if (textFieldCNPJ == null) {
			textFieldCNPJ = new JTextField();
			textFieldCNPJ.setBounds(30, 95, 141, 20);
			textFieldCNPJ.setColumns(10);
		}
		return textFieldCNPJ;
	}

	private JLabel getLblNomeFornec() {
		if (lblNomeFornec == null) {
			lblNomeFornec = new JLabel("Nome do Fornecedor");
			lblNomeFornec.setBounds(30, 127, 141, 14);
		}
		return lblNomeFornec;
	}

	public JTextField getTextFieldNomeFornec() {
		if (textFieldNomeFornec == null) {
			textFieldNomeFornec = new JTextField();
			textFieldNomeFornec.setBounds(30, 144, 261, 20);
			textFieldNomeFornec.setColumns(10);
		}
		return textFieldNomeFornec;
	}

	private JLabel getLblValorTotalProd() {
		if (lblValorTotalProd == null) {
			lblValorTotalProd = new JLabel("Valor Total dos Produtos (R$)");
			lblValorTotalProd.setBounds(474, 70, 166, 14);
		}
		return lblValorTotalProd;
	}

	public JTextField getTextFieldVTotalProd() {
		if (textFieldVTotalProd == null) {
			textFieldVTotalProd = new JTextField();
			textFieldVTotalProd.setBounds(474, 96, 149, 20);
			textFieldVTotalProd.setText("0");
			textFieldVTotalProd.setColumns(10);
			textFieldVTotalProd.setEditable(false);
		}
		return textFieldVTotalProd;
	}

	public JTextField getTextFieldOutrosCustos() {
		if (textFieldOutrosCustos == null) {
			textFieldOutrosCustos = new JTextField();
			textFieldOutrosCustos.setBounds(474, 38, 149, 20);
			textFieldOutrosCustos.setColumns(10);
			textFieldOutrosCustos.setText("0");
			textFieldOutrosCustos.setEditable(true);
		}
		return textFieldOutrosCustos;
	}

	private JLabel getLblOutrosCustos() {
		if (lblOutrosCustos == null) {
			lblOutrosCustos = new JLabel("Valor Total Outros Custos (R$)");
			lblOutrosCustos.setBounds(474, 12, 176, 14);
		}
		return lblOutrosCustos;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Chave de Acesso da NFe");
			lblNewLabel.setBounds(183, 12, 231, 14);
		}
		return lblNewLabel;
	}

	public JTextField getTextFieldChaveNFE() {
		if (textFieldChaveNFE == null) {
			textFieldChaveNFE = new JTextField();
			textFieldChaveNFE.setBounds(183, 38, 276, 20);
			textFieldChaveNFE.setColumns(10);
		}
		return textFieldChaveNFE;
	}

	public JLabel getBtnAddProduto() {
		if (lblAddProduto == null) {
			lblAddProduto = new JLabel("");
			lblAddProduto.setBounds(577, 202, 63, 39);
			lblAddProduto.setToolTipText("Adicionar Produto a Lista");
			lblAddProduto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddProduto.setIcon(new ImageIcon("Interno/add.png"));
		}
		return lblAddProduto;
	}

	public JComboBox<Object> getComboBoxProduto() {
		if (comboBoxProduto == null) {
			String produtos[] = produtoDAO.buscarNomeeId();
			comboBoxProduto = new JComboBox<Object>(produtos);
			comboBoxProduto.setBounds(30, 213, 312, 20);
		}
		return comboBoxProduto;
	}
}
