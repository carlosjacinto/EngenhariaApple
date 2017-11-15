package view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.InputListenerPrincipalFunc;

public class PrincipalFunc extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6375324782041802229L;
	private JPanel contentPane;
	private JLabel btCliente;
	private JMenuBar menuBar;
	private JMenu mnCadastros;
	private JMenuItem mntmClientes;
	private JMenuItem mntmPedidos;
	private JMenuItem mntmNotaEntrada;
	private JMenu mnRelatorios;
	private JMenu mnSobre;
	private JPanel panel_1;
	private JLabel btPedido;
	private JLabel btNotaEntrada;
	private JLabel btSair;
	private JLabel barraAtalhos;
	private JLabel setaCima;
	private JLabel setaBaixo;
	private JLabel label_1;
	InputListenerPrincipalFunc listener;
	private JLabel btnDesligar;
	private JMenuItem mntmSair;
	private JMenuItem mntmDesligar;
	private JMenuItem mntmRelProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalFunc frame = new PrincipalFunc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalFunc() {
		listener = new InputListenerPrincipalFunc(this);
		initialize();
		initializeListeners();

	}

	public JPanel getpanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setForeground(Color.WHITE);
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(0, 0, 1360, 680);
			panel_1.setLayout(null);

			panel_1.add(getBtCliente());
			panel_1.add(getBtPedido());
			panel_1.add(getBtNotaEntrada());
			panel_1.add(getBtSair());
			panel_1.add(getBtnDesligar());
			panel_1.add(getBarraAtalhos());
			panel_1.add(getSetaBaixo());
			panel_1.add(getSetaCima());
			panel_1.add(getLabel_1());
		}

		return panel_1;
	}

	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			contentPane.setLayout(null);

			contentPane.add(getpanel_1());
		}

		return contentPane;
	}

	public JMenuBar getmenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getmnCadastros());
			menuBar.add(getmnRelatorios());
			menuBar.add(getmnSobre());
		}

		return menuBar;
	}

	public JMenu getmnCadastros() {
		if (mnCadastros == null) {
			mnCadastros = new JMenu("Cadastros");
			mnCadastros.add(getmntmCliente());
			mnCadastros.add(getmntmPedidos());
			mnCadastros.add(getmntmNotaEntrada());
			mnCadastros.add(getmntmSair());
			mnCadastros.add(getmntmDesligar());
		}

		return mnCadastros;
	}

	public JMenu getmnRelatorios() {
		if (mnRelatorios == null) {
			mnRelatorios = new JMenu("Relatórios");

			mnRelatorios.add(getmntmRelProd());
		}

		return mnRelatorios;
	}

	public JMenuItem getmntmRelProd() {
		if (mntmRelProd == null) {
			mntmRelProd = new JMenuItem("Produtos");
			mntmRelProd.setIcon(new ImageIcon("Interno/prodMin.png"));
		}

		return mntmRelProd;
	}

	public JMenu getmnSobre() {
		if (mnSobre == null) {
			mnSobre = new JMenu("Sobre");
		}

		return mnSobre;
	}

	public JMenuItem getmntmCliente() {
		if (mntmClientes == null) {
			mntmClientes = new JMenuItem("Clientes");
		}

		return mntmClientes;
	}

	public JMenuItem getmntmPedidos() {
		if (mntmPedidos == null) {
			mntmPedidos = new JMenuItem("Pedidos");
		}

		return mntmPedidos;
	}

	public JMenuItem getmntmNotaEntrada() {
		if (mntmNotaEntrada == null) {
			mntmNotaEntrada = new JMenuItem("Nota de Entrada");
		}

		return mntmNotaEntrada;
	}

	public void initializeListeners() {
		getmntmRelProd().addMouseListener(listener);
		getBtCliente().addMouseListener(listener);
		getBtPedido().addMouseListener(listener);
		getBtNotaEntrada().addMouseListener(listener);
		getBtSair().addMouseListener(listener);
		getBtnDesligar().addMouseListener(listener);
		getSetaCima().addMouseListener(listener);
		getSetaBaixo().addMouseListener(listener);
		getmntmCliente().addMouseListener(listener);
		getmntmPedidos().addMouseListener(listener);
		getmntmNotaEntrada().addMouseListener(listener);
		getmntmDesligar().addMouseListener(listener);
		getmntmSair().addMouseListener(listener);

	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 730);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Menu Funcionario");
		setJMenuBar(getmenuBar());
		setContentPane(getContentPane());

		getBarraAtalhos().setVisible(false);
		getBtCliente().setVisible(false);
		getBtPedido().setVisible(false);
		getBtNotaEntrada().setVisible(false);
		getBtSair().setVisible(false);
		getBtnDesligar().setVisible(false);
	}

	public JLabel getBtCliente() {
		if (btCliente == null) {
			btCliente = new JLabel("");
			btCliente.setToolTipText("Cadastrar Cliente");
			btCliente.setHorizontalAlignment(SwingConstants.CENTER);
			btCliente.setIcon(new ImageIcon("Interno/cliente.png"));
			btCliente.setBounds(227, 568, 100, 100);
		}
		return btCliente;
	}

	public JLabel getBtPedido() {
		if (btPedido == null) {
			btPedido = new JLabel("");
			btPedido.setToolTipText("Cadastrar Pedido");
			btPedido.setHorizontalAlignment(SwingConstants.CENTER);
			btPedido.setIcon(new ImageIcon("Interno/pedidos.png"));
			btPedido.setBounds(317, 568, 100, 100);

		}
		return btPedido;
	}

	public JLabel getBtNotaEntrada() {
		if (btNotaEntrada == null) {
			btNotaEntrada = new JLabel("");
			btNotaEntrada.setToolTipText("Nota de Entrada");
			btNotaEntrada.setBackground(SystemColor.menu);
			btNotaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
			btNotaEntrada.setIcon(new ImageIcon("Interno/nota.png"));
			btNotaEntrada.setBounds(407, 568, 100, 100);

		}
		return btNotaEntrada;
	}

	public JLabel getBtSair() {
		if (btSair == null) {
			btSair = new JLabel("");
			btSair.setToolTipText("Sair");
			btSair.setBackground(SystemColor.menu);
			btSair.setHorizontalAlignment(SwingConstants.CENTER);
			btSair.setIcon(new ImageIcon("Interno/sair.png"));
			btSair.setBounds(497, 568, 100, 100);

		}
		return btSair;
	}

	public JLabel getBarraAtalhos() {
		if (barraAtalhos == null) {
			barraAtalhos = new JLabel("");
			barraAtalhos.setIcon(new ImageIcon("Interno/barra.png"));
			barraAtalhos.setBounds(178, 605, 1000, 75);

		}
		return barraAtalhos;
	}

	public JLabel getSetaCima() {
		if (setaCima == null) {
			setaCima = new JLabel("");
			setaCima.setIcon(new ImageIcon("Interno/Seta_Para_Cima.png"));
			setaCima.setBounds(665, 660, 30, 19);

		}
		return setaCima;
	}

	public JLabel getSetaBaixo() {
		if (setaBaixo == null) {
			setaBaixo = new JLabel("");
			setaBaixo.setVisible(false);
			setaBaixo.setIcon(new ImageIcon("Interno/Seta_Para_Baixo.png"));
			setaBaixo.setBounds(702, 595, 30, 19);

		}
		return setaBaixo;
	}

	public JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon("Interno/background.png"));
			label_1.setBounds(0, 0, 1360, 680);
		}

		return label_1;
	}

	public JLabel getBtnDesligar() {
		if (btnDesligar == null) {
			btnDesligar = new JLabel("");
			btnDesligar.setToolTipText("Desligar");
			btnDesligar.setIcon(new ImageIcon("Interno/desligar.png"));
			btnDesligar.setBounds(595, 568, 100, 100);
		}
		return btnDesligar;
	}

	public JMenuItem getmntmSair() {
		if (mntmSair == null) {
			mntmSair = new JMenuItem("Sair da sessão do Usuário");
		}
		return mntmSair;
	}

	public JMenuItem getmntmDesligar() {
		if (mntmDesligar == null) {
			mntmDesligar = new JMenuItem("Desligar o programa");
		}
		return mntmDesligar;
	}

}
