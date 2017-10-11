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
import model.NotaEntradaDAO;

public class CadastroNotaEntradaView extends JDialog {

	private static final long serialVersionUID = 967851639105823465L;
	InputListenerCadastroNotaEntrada listener;
	private JPanel contentPanel;
	private JLabel lblFuncionario;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JLabel btnPesqProduto;
	private JComboBox<Object> comboBoxFuncionario;
	private JLabel labelCodigoProduto;
	private JLabel lblQuantidade;
	private JLabel lblPreoTotalr;
	private JTextField textTotalNota;
	private JSpinner spinnerQtde;
	private JLabel lblDataEmissao;
	private JTextField txtDataEmissao;
	private JTextField textCodigo;
	private JLabel lblNumNFE;
	private JTable tableNotaEntrada;
	private JScrollPane scrollPane;
	private NotaEntradaDAO notaEntDAO = new NotaEntradaDAO();
	private JTextField textCodigoProduto;
	private JTextField textPrecoCustoUnit;
	private JTextField textFieldNomeProduto;
	private JLabel lblNomeProduto;
	private JSeparator separator;
	private JLabel lblPrecoCustoUnit;
	private JLabel lblCNPJ;
	private JTextField textFieldCNPJ;
	private JLabel lblNomeFornec;
	private JTextField textFieldNomeFornec;
	private JLabel lblValorTotalProd;
	private JTextField textFieldVTotalProd;
	private JTextField textFieldOutrosCustos;
	private JLabel lblOutrosCustos;
	private JLabel lblNewLabel;
	private JTextField textFieldChaveNFE;
	private JLabel lblAddProduto;

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
			contentPanel.add(getPesqProduto());
			contentPanel.add(getLblNomeProduto());
			contentPanel.add(getSeparator());
			contentPanel.add(getTextCodigoProduto());
			contentPanel.add(getTextPrecoCustoUnit());
			contentPanel.add(getScrollBar());
			contentPanel.add(getTextNomeProduto());
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
			contentPanel.add(getLblAddProduto());
		}
		return contentPanel;
	}

	public JTextField getTextNomeProduto() {
		if (textFieldNomeProduto == null) {
			textFieldNomeProduto = new JTextField();
			textFieldNomeProduto.setBounds(126, 213, 216, 20);
			textFieldNomeProduto.setColumns(10);
			textFieldNomeProduto.setEditable(false);
		}
		return textFieldNomeProduto;
	}

	public JLabel getPesqProduto() {
		if (btnPesqProduto == null) {
			btnPesqProduto = new JLabel();
			btnPesqProduto.setBounds(576, 210, 39, 23);
			btnPesqProduto.setToolTipText("Buscar Produto");
			btnPesqProduto.setHorizontalAlignment(SwingConstants.CENTER);
			btnPesqProduto.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return btnPesqProduto;
	}

	public JTextField getTextPrecoCustoUnit() {
		if (textPrecoCustoUnit == null) {
			textPrecoCustoUnit = new JTextField();
			textPrecoCustoUnit.setBounds(437, 213, 130, 20);
			textPrecoCustoUnit.setColumns(10);
		}
		return textPrecoCustoUnit;
	}

	public JTextField getTextCodigoProduto() {
		if (textCodigoProduto == null) {
			textCodigoProduto = new JTextField();
			textCodigoProduto.setBounds(30, 213, 86, 20);
			textCodigoProduto.setColumns(10);
			textCodigoProduto.setEditable(false);
		}
		return textCodigoProduto;
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

	public JLabel getLblNomeProduto() {
		if (lblNomeProduto == null) {
			lblNomeProduto = new JLabel("Nome do Produto");
			lblNomeProduto.setBounds(126, 188, 165, 14);
		}
		return lblNomeProduto;
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
			comboBoxFuncionario = new JComboBox<Object>();
			comboBoxFuncionario.setBounds(183, 96, 276, 20);
		}
		return comboBoxFuncionario;
	}

	public JLabel getLabelCodigoProduto() {
		if (labelCodigoProduto == null) {
			labelCodigoProduto = new JLabel("C\u00F3digo");
			labelCodigoProduto.setBounds(30, 188, 75, 14);
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

	private JTextField getTextFieldCNPJ() {
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

	private JTextField getTextFieldNomeFornec() {
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

	private JTextField getTextFieldVTotalProd() {
		if (textFieldVTotalProd == null) {
			textFieldVTotalProd = new JTextField();
			textFieldVTotalProd.setBounds(474, 96, 149, 20);
			textFieldVTotalProd.setColumns(10);
			textFieldVTotalProd.setEditable(false);
		}
		return textFieldVTotalProd;
	}

	private JTextField getTextFieldOutrosCustos() {
		if (textFieldOutrosCustos == null) {
			textFieldOutrosCustos = new JTextField();
			textFieldOutrosCustos.setBounds(474, 38, 149, 20);
			textFieldOutrosCustos.setColumns(10);
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

	private JTextField getTextFieldChaveNFE() {
		if (textFieldChaveNFE == null) {
			textFieldChaveNFE = new JTextField();
			textFieldChaveNFE.setBounds(183, 38, 276, 20);
			textFieldChaveNFE.setColumns(10);
		}
		return textFieldChaveNFE;
	}

	private JLabel getLblAddProduto() {
		if (lblAddProduto == null) {
			lblAddProduto = new JLabel("");
			lblAddProduto.setBounds(604, 210, 46, 31);
			lblAddProduto.setToolTipText("Buscar Produto");
			lblAddProduto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddProduto.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return lblAddProduto;
	}

}
