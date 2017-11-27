package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.InputListenerLoginView;

import java.awt.Color;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4036120664241935676L;
	
	private JPanel contentPane;
	private JLabel lblCpf;
	private JTextField textCpf;
	private JLabel lblSenha;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnCancelar;
	private JLabel lblLogo;
	private JLabel lblFundo;
	InputListenerLoginView listener;

	public LoginView() {
		listener = new InputListenerLoginView(this);
		initialize();
		initializeListeners();
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 321);
		setLocationRelativeTo(null);
		setContentPane(getContentPane());
	}

	public void initializeListeners() {
		getBtnLogin().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
	}	
	

	public JPanel getContentPane() {
		if(contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			contentPane.add(getLblCpf());
			contentPane.add(getTextCpf());
			contentPane.add(getLblSenha());
			contentPane.add(getPasswordField());
			contentPane.add(getBtnLogin());
			contentPane.add(getBtnCancelar());
			contentPane.add(getLblLogo());
			contentPane.add(getLblFundo());
		}
		return contentPane;
	}
	
	public JLabel getLblCpf() {
		if(lblCpf == null) {
			lblCpf = new JLabel("CPF");
			lblCpf.setForeground(Color.WHITE);
			lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
			lblCpf.setBounds(10, 47, 614, 14);
		}
		return lblCpf;
	}
	
	public JTextField getTextCpf() {
		if(textCpf == null) {
			textCpf = new JTextField();
			textCpf.setHorizontalAlignment(SwingConstants.CENTER);
			textCpf.setBounds(197, 72, 243, 20);
			textCpf.setColumns(10);
		}
		return textCpf;
	}
	
	
	public JLabel getLblSenha() {
		if (lblSenha == null) {
			lblSenha = new JLabel("Senha");
			lblSenha.setForeground(Color.WHITE);
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			lblSenha.setBounds(10, 103, 614, 14);
		}
		return lblSenha;
	}
	public JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setHorizontalAlignment(SwingConstants.CENTER);
			passwordField.setBounds(197, 128, 243, 20);
		}
		return passwordField;
	}
	public JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Login");
			btnLogin.setBounds(255, 175, 127, 23);
		}
		return btnLogin;
	}
	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(274, 231, 89, 23);
		}
		return btnCancelar;
	}
	public JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon("Interno/logo.png"));
			lblLogo.setBounds(80, 40, 100, 112);
		}
		return lblLogo;
	}
	public JLabel getLblFundo() {
		if (lblFundo == null) {
			lblFundo = new JLabel("");
			lblFundo.setIcon(new ImageIcon("Interno/fundoLogin.jpg"));
			lblFundo.setBounds(0, 0, 650, 321);
		}
		return lblFundo;
	}
}
