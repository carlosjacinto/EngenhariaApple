package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.InputListenerPagamentoView;
import model.Cliente;

public class PagamentoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1875544914364472125L;
	private JPanel contentPanel;
	private JLabel lblValorr;
	private JTextField textField;
	private JButton btnPagar;
	private InputListenerPagamentoView listener;
	private Cliente c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PagamentoView dialog = new PagamentoView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagamentoView(Cliente c) {
		this.c = c;
		setModal(true);
		setBounds(100, 100, 230, 113);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		listener = new InputListenerPagamentoView(this);
		initializeListener();
		
	}
	
	public void initializeListener() {
		getBtnPagar().addMouseListener(listener);
	}

	public Cliente getCliente() {
		return c;
	}
	
	public JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.add(getTextField());
			contentPanel.add(getBtnPagar());
			contentPanel.setLayout(null);
			contentPanel.add(getLblValorr());
		}
		return contentPanel;
	}
	
	public JTextField getTextField() {
		if(textField == null) {
			textField = new JTextField();
			textField.setBounds(81, 8, 123, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	
	public JLabel getLblValorr() {
		if(lblValorr == null) {
			lblValorr = new JLabel("Valor(R$):");
			lblValorr.setBounds(10, 11, 61, 14);
		}
		return lblValorr;
	}
	public JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.setBounds(115, 39, 89, 23);
		}
		return btnPagar;
	}
}
