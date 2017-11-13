package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PagamentoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1875544914364472125L;
	private JPanel contentPanel;
	private JLabel lblValorr;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PagamentoView dialog = new PagamentoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagamentoView() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		contentPanel.setLayout(null);
		getContentPanel().setLayout(null);

		contentPanel.add(getLblValorr());
	}
	
	public JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			textField = new JTextField();
			textField.setBounds(65, 8, 123, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		return contentPanel;
	}
	public JLabel getLblValorr() {
		if(lblValorr == null) {
			lblValorr = new JLabel("Valor(R$):");
			lblValorr.setBounds(10, 11, 61, 14);
		}
		return lblValorr;
	}
	
	
}
