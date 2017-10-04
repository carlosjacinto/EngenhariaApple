package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.InputListenerPrincipal;
import model.Contador;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110345484380030496L;
	private JPanel contentPane;
	private JLabel lblFundo;
	private JLabel lblLogo;
	private JButton btnIniciar;
	private Contador c1;
	private Thread t1;
	private InputListenerPrincipal listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Principal().setVisible(true);
		
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		initialize();
		initializeListeners();
		
	}
	
	public void initializeListeners() {
		getBtnIniciar().addMouseListener(getListener());
		this.addWindowListener(getListener());
	}
	public InputListenerPrincipal getListener() {
		if(listener == null) {
			listener = new InputListenerPrincipal(this);
		}
		return listener;
	}

	public void initialize() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1244, 700);
		setLocationRelativeTo(null);
		setContentPane(getContentPane());
		setTitle("Início");
		getT1().start();
	}
	
	public JPanel getContentPane() {
		if(contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			contentPane.setLayout(null);
			contentPane.add(getBtnIniciar());
			contentPane.add(getLblLogo());
			contentPane.add(getLblFundo());
		}
		return contentPane;
	}
	
	public JButton getBtnIniciar() {
		if(btnIniciar == null) {
			btnIniciar = new JButton("Iniciar");
			btnIniciar.setBounds(1129, 627, 89, 23);
		}
		return btnIniciar;
	}

	public JLabel getLblFundo() {
		if(lblFundo == null) {
			lblFundo = new JLabel("");
			lblFundo.setIcon(new ImageIcon("Interno/1.jpg"));
			lblFundo.setBounds(0, 0, 1244, 700);
		}
		return lblFundo;
	}
	
	public JLabel getLblLogo() {
		if(lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon("Interno/logo2.png"));
			lblLogo.setBounds(472, 200, 300, 300);
		}
		return lblLogo;
	}
	
	public Thread getT1() {
		if(t1 == null) {
			c1 = new Contador(this);
			t1 = new Thread(c1);
		}
		return t1;
	}
}
