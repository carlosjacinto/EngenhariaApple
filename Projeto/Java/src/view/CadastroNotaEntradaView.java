package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import control.InputListenerCadastroNotaEntrada;
import model.NotaEntradaDAO;
import java.awt.Component;

public class CadastroNotaEntradaView extends JDialog {

	private static final long serialVersionUID = 967851639105823465L;
	InputListenerCadastroNotaEntrada listener;
	private JPanel contentPanel;
	private JLabel lblNomeCliente;
	private JLabel lblFuncionario;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JComboBox<Object> comboBoxCliente;
	private JComboBox<Object> comboBoxFuncionario;
	private JComboBox<Object> comboBoxProduto;
	private JLabel labelProduto;
	private JLabel lblQuantidade;
	private JLabel lblPreoTotalr;
	private JTextField textPreco;
	private JSpinner spinnerQtde;
	private JLabel lblDataEmissao;
	private JTextField txtDataEmissao;
	private JTextField textCodigo;
	private JLabel lblCodigo;
	private JTable tableNotaEntrada;
	private JTextField textBusca;
	private JScrollPane scrollBar;
	private NotaEntradaDAO notaEntDAO = new NotaEntradaDAO();

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
		setBounds(100, 100, 649, 491);
		setContentPane(getContentPanel());
		setTitle("Cadastro de Nota de Entrada");

	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			contentPanel.setLayout(null);
			contentPanel.add(getLblNomeCliente());
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getComboBoxCliente());
			contentPanel.add(getLblFuncionario());
			contentPanel.add(getComboBoxFuncionario());
			contentPanel.add(getComboBoxProduto());
			contentPanel.add(getLabelProduto());
			contentPanel.add(getLblQuantidade());
			contentPanel.add(getLblPreoTotalr());
			contentPanel.add(getTextPreco());
			contentPanel.add(getSpinnerQtde());
			contentPanel.add(getLblCodigo());
			contentPanel.add(getTextCodigo());
			contentPanel.add(getTextDataEmissao());
			contentPanel.add(getLblDataEmissao());
			
			JScrollPane scrollPane = new JScrollPane((Component) null);
			scrollPane.setBounds(30, 252, 668, 189);
			contentPanel.add(scrollPane);

		}
		return contentPanel;
	}
	
	public JScrollPane getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollPane(getTableNotaEntrada());
			scrollBar.setBounds(58, 52, 668, 359);
		}
		return scrollBar;
	}
	
	public JTable getTableNotaEntrada() {
		if (tableNotaEntrada == null) {
			String[][] notas = notaEntDAO.listaNotaEntradaArray("");
			String[] colunas = { "Código", "Nome", "Preço Unit", "Quantidade", "Preço Total"};

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

	private JLabel getLblNomeCliente() {
		if (lblNomeCliente == null) {
			lblNomeCliente = new JLabel("Cliente");
			lblNomeCliente.setBounds(30, 126, 112, 14);
		}
		return lblNomeCliente;
	}

	private JLabel getLblFuncionario() {
		if (lblFuncionario == null) {
			lblFuncionario = new JLabel("Funcion\u00E1rio");
			lblFuncionario.setBounds(30, 70, 75, 14);
		}
		return lblFuncionario;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(485, 215, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(386, 215, 89, 23);
		}
		return btnGravar;
	}

	public JComboBox<Object> getComboBoxCliente() {
		if (comboBoxCliente == null) {
			comboBoxCliente = new JComboBox<Object>();
			comboBoxCliente.setBounds(30, 151, 281, 20);
		}
		return comboBoxCliente;
	}

	public JComboBox<Object> getComboBoxFuncionario() {
		if (comboBoxFuncionario == null) {
			comboBoxFuncionario = new JComboBox<Object>();
			comboBoxFuncionario.setBounds(30, 95, 281, 20);
		}
		return comboBoxFuncionario;
	}

	public JComboBox<Object> getComboBoxProduto() {
		if (comboBoxProduto == null) {
			comboBoxProduto = new JComboBox<Object>();
			comboBoxProduto.setBounds(342, 37, 281, 20);
		}
		return comboBoxProduto;
	}

	public JLabel getLabelProduto() {
		if (labelProduto == null) {
			labelProduto = new JLabel("Produto");
			labelProduto.setBounds(342, 12, 75, 14);
		}
		return labelProduto;
	}

	public JLabel getLblQuantidade() {
		if (lblQuantidade == null) {
			lblQuantidade = new JLabel("Quantidade");
			lblQuantidade.setBounds(342, 74, 75, 14);
		}
		return lblQuantidade;
	}

	public JLabel getLblPreoTotalr() {
		if (lblPreoTotalr == null) {
			lblPreoTotalr = new JLabel("Pre\u00E7o Total (R$)");
			lblPreoTotalr.setBounds(342, 130, 130, 14);
		}
		return lblPreoTotalr;
	}

	public JTextField getTextPreco() {
		if (textPreco == null) {
			textPreco = new JTextField();
			textPreco.setEditable(true);
			textPreco.setBounds(342, 155, 281, 20);
			textPreco.setColumns(10);
		}
		return textPreco;
	}

	public JSpinner getSpinnerQtde() {
		if (spinnerQtde == null) {
			spinnerQtde = new JSpinner();
			spinnerQtde.setBounds(342, 99, 281, 20);
		}
		return spinnerQtde;
	}

	public JTextField getTextCodigo() {
		if (textCodigo == null) {
			textCodigo = new JTextField();
			textCodigo.setBounds(30, 37, 281, 22);
			textCodigo.setEditable(true);
		}
		return textCodigo;
	}

	public JLabel getLblCodigo() {
		if (lblCodigo == null) {
			lblCodigo = new JLabel("Código");
			lblCodigo.setBounds(30, 12, 277, 14);
		}
		return lblCodigo;
	}

	public JTextField getTextDataEmissao() {
		if (txtDataEmissao == null) {
			txtDataEmissao = new JTextField();
			txtDataEmissao.setBounds(30, 215, 284, 20);
			txtDataEmissao.setColumns(10);
			txtDataEmissao.setEditable(true);
		}
		return txtDataEmissao;
	}

	public JLabel getLblDataEmissao() {
		if (lblDataEmissao == null) {
			lblDataEmissao = new JLabel("Data de Emissao da NFe");
			lblDataEmissao.setBounds(30, 190, 120, 14);
		}
		return lblDataEmissao;
	}
}
