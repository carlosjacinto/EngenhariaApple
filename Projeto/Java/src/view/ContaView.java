package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.InputListenerContaView;
import model.Cliente;
import model.ContaDAO;

public class ContaView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5454329058636231814L;
	private JPanel contentPanel;
	private JTextField nomeClienteTextField;
	private JLabel lblCliente;
	private JLabel lblValorAPagarr;
	private JTextField valorTextField;
	private JButton btnRealizarPagamento;
	private Cliente c;
	private ContaDAO contaDAO = new ContaDAO();
	private InputListenerContaView listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ContaView dialog = new ContaView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ContaView(Cliente c) {
		this.c = c;
		listener = new InputListenerContaView(this);
		initialize();
		initializeListener();
		
	}
	
	public void initialize() {
		setModal(true);
		setBounds(100, 100, 381, 129);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
	}
	
	public void initializeListener() {
		getBtnRealizarPagamento().addMouseListener(listener);
	}
	
	public JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(null);
			contentPanel.add(getNomeClienteTextField());
			contentPanel.add(getLblCliente());
			contentPanel.add(getLblValorAPagarr());
			contentPanel.add(mostrarDebitoConta());
			contentPanel.add(getBtnRealizarPagamento());
		}
		return contentPanel;
	}
	
	public JTextField getNomeClienteTextField() {
		if(nomeClienteTextField == null) {
			nomeClienteTextField = new JTextField();
			nomeClienteTextField.setEditable(false);
			nomeClienteTextField.setBounds(10, 29, 196, 20);
			nomeClienteTextField.setColumns(10);
			nomeClienteTextField.setText(c.getNome());
		}
		return nomeClienteTextField;
	}
	
	public JLabel getLblCliente() {
		if(lblCliente == null) {
			lblCliente = new JLabel("Cliente:");
			lblCliente.setBounds(10, 11, 46, 14);
		}
		return lblCliente;
	}
	
	private JLabel getLblValorAPagarr() {
		if (lblValorAPagarr == null) {
			lblValorAPagarr = new JLabel("Valor a Pagar(R$)");
			lblValorAPagarr.setBounds(225, 11, 106, 14);
		}
		return lblValorAPagarr;
	}
	public JTextField mostrarDebitoConta() {
		if (valorTextField == null) {
			valorTextField = new JTextField();
			valorTextField.setEditable(false);
			valorTextField.setBounds(223, 29, 132, 20);
			valorTextField.setColumns(10);
			valorTextField.setText(contaDAO.retornaAReceber(c.getIdCliente())+"");
		}
		return valorTextField;
	}
	
	public Cliente getCliente() {
		return c;
	}
	
	public JButton getBtnRealizarPagamento() {
		if(btnRealizarPagamento == null) {
			btnRealizarPagamento = new JButton("Realizar Pagamento");
			btnRealizarPagamento.setBounds(99, 56, 155, 23);
		}
		return btnRealizarPagamento;
	}
}
