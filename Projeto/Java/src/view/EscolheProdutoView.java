package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;

public class EscolheProdutoView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EscolheProdutoView dialog = new EscolheProdutoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EscolheProdutoView() {
		setBounds(100, 100, 636, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 394, 620, 51);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JLabel lblBuscarPorNome = new JLabel("Buscar por nome ou c\u00F3digo:");
				lblBuscarPorNome.setBounds(10, 22, 205, 14);
				buttonPane.add(lblBuscarPorNome);
			}
			{
				textField = new JTextField();
				textField.setBounds(179, 19, 162, 20);
				textField.setColumns(10);
				buttonPane.add(textField);
			}
			{
				JButton okButton = new JButton("Confirmar Sele\u00E7\u00E3o");
				okButton.setBounds(351, 18, 147, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBounds(508, 18, 102, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 0, 620, 393);
		contentPanel.add(scrollPane);
	}
}
