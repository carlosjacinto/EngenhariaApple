package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import control.InputListenerEditarPedido;
import model.Pedido;

public class EditarPedidoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7876262101294494488L;
	InputListenerEditarPedido listener;
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
	private Pedido pedido;

	public static void main(String[] args) {
		try {
			EditarPedidoView dialog = new EditarPedidoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditarPedidoView() {
		listener = new InputListenerEditarPedido(this);
		initialize();
		initializeListeners();

	}
	public EditarPedidoView(Pedido pedido) {
		this.pedido = pedido;
		listener = new InputListenerEditarPedido(this);
		initialize();
		initializeListeners();
	}
	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
	}

	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 649, 275);
		setContentPane(getContentPanel());
		setTitle("Editar de Pedido");

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

		}
		return contentPanel;
	}

	private JLabel getLblNomeCliente() {
		if (lblNomeCliente == null) {
			lblNomeCliente = new JLabel("Cliente");
			lblNomeCliente.setBounds(30, 12, 112, 14);
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
			btnCancelar.setBounds(534, 202, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(435, 202, 89, 23);
		}
		return btnGravar;
	}
	
	public JComboBox<Object> getComboBoxCliente() {
		if(comboBoxCliente == null) {
			comboBoxCliente = new JComboBox<Object>();
			comboBoxCliente.addItem("Dom");
			comboBoxCliente.setBounds(30, 37, 281, 20);
		}
		return comboBoxCliente;
	}
	
	public JComboBox<Object> getComboBoxFuncionario() {
		if (comboBoxFuncionario == null) {
			comboBoxFuncionario = new JComboBox<Object>();
			comboBoxFuncionario.addItem("Marina");
			comboBoxFuncionario.setBounds(30, 95, 281, 20);
		}
		return comboBoxFuncionario;
	}
	public JComboBox<Object> getComboBoxProduto() {
		if (comboBoxProduto == null) {
			comboBoxProduto = new JComboBox<Object>();
			comboBoxProduto.addItem("MacBook");
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
			lblQuantidade.setBounds(342, 70, 75, 14);
		}
		return lblQuantidade;
	}
	public JLabel getLblPreoTotalr() {
		if (lblPreoTotalr == null) {
			lblPreoTotalr = new JLabel("Pre\u00E7o Total (R$)");
			lblPreoTotalr.setBounds(342, 126, 130, 14);
		}
		return lblPreoTotalr;
	}
	public JTextField getTextPreco() {
		if (textPreco == null) {
			textPreco = new JTextField("R$ 10000,00");
			textPreco.setEditable(false);
			textPreco.setBounds(342, 151, 281, 20);
			textPreco.setColumns(10);
		}
		return textPreco;
	}

	public JSpinner getSpinnerQtde() {
		if (spinnerQtde == null) {
			spinnerQtde = new JSpinner();
			spinnerQtde.setBounds(342, 95, 281, 20);
		}
		return spinnerQtde;
	}
}
