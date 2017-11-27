package view;

import java.awt.Color;
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

import control.InputListenerPrincipalAdm;

public class PrincipalAdm extends JFrame {

	private static final long serialVersionUID = 6375324782041802229L;
	private JPanel contentPane;
	private JLabel btFunc;
	private JMenuBar menuBar;
	private JMenu mnCadastros;
	private JMenuItem mntmFuncionario;
	private JMenuItem mntmProdutos;
	private JMenuItem mntmSair;
	private JMenu mnRelatorios;
	private JMenu mnSobre;
	private JPanel panel_1;
	private JLabel btProd;
	private JLabel btSair;
	private JLabel barraAtalhos;
	private JLabel setaCima;
	private JLabel setaBaixo;
	private JLabel label_1;
	private JLabel btnDesligar;
	InputListenerPrincipalAdm listener;
	private JMenuItem mntmDesligar;
	private JMenuItem mntmRelProd;

	public PrincipalAdm() {
		listener = new InputListenerPrincipalAdm(this);
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

			panel_1.add(getBtFunc());
			panel_1.add(getBtDesligar());
			panel_1.add(getBtProd());
			panel_1.add(getBtSair());
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
			mnCadastros.add(getmntmFuncionario());
			mnCadastros.add(getmntmProdutos());
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

	public JMenuItem getmntmFuncionario() {
		if (mntmFuncionario == null) {
			mntmFuncionario = new JMenuItem("Funcionário");
		}

		return mntmFuncionario;
	}

	public JMenuItem getmntmProdutos() {
		if (mntmProdutos == null) {
			mntmProdutos = new JMenuItem("Produtos");
		}

		return mntmProdutos;
	}

	public JMenuItem getmntmSair() {
		if (mntmSair == null) {
			mntmSair = new JMenuItem("Sair da sessão do Usuário");
		}

		return mntmSair;
	}

	public void initializeListeners() {
		getBtFunc().addMouseListener(listener);
		getBtProd().addMouseListener(listener);
		getBtSair().addMouseListener(listener);
		getBtDesligar().addMouseListener(listener);
		getSetaCima().addMouseListener(listener);
		getSetaBaixo().addMouseListener(listener);
		getmntmFuncionario().addMouseListener(listener);
		getmntmProdutos().addMouseListener(listener);
		getmntmSair().addMouseListener(listener);
		getmntmDesligar().addMouseListener(listener);
		getmntmRelProd().addMouseListener(listener);
		getmnSobre().addMouseListener(listener);
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 730);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Menu Administrador");
		setJMenuBar(getmenuBar());
		setContentPane(getContentPane());

		getBarraAtalhos().setVisible(false);
		getBtFunc().setVisible(false);
		getBtProd().setVisible(false);
		getBtSair().setVisible(false);
		getBtDesligar().setVisible(false);
	}

	public JLabel getBtFunc() {
		if (btFunc == null) {
			btFunc = new JLabel("");
			btFunc.setToolTipText("Cadastrar Funcion\u00E1rio");
			btFunc.setHorizontalAlignment(SwingConstants.CENTER);
			btFunc.setIcon(new ImageIcon("Interno/func.png"));
			btFunc.setBounds(227, 568, 100, 100);
		}
		return btFunc;
	}

	public JLabel getBtProd() {
		if (btProd == null) {
			btProd = new JLabel("");
			btProd.setToolTipText("Cadastrar Produto");
			btProd.setHorizontalAlignment(SwingConstants.CENTER);
			btProd.setIcon(new ImageIcon("Interno/prod.png"));
			btProd.setBounds(317, 568, 100, 100);

		}
		return btProd;
	}

	public JLabel getBtSair() {
		if (btSair == null) {
			btSair = new JLabel("");
			btSair.setToolTipText("Sair da sessão do Usuário");
			btSair.setBackground(SystemColor.menu);
			btSair.setHorizontalAlignment(SwingConstants.CENTER);
			btSair.setIcon(new ImageIcon("Interno/sair.png"));
			btSair.setBounds(407, 568, 100, 100);

		}
		return btSair;
	}

	public JLabel getBtDesligar() {
		if (btnDesligar == null) {
			btnDesligar = new JLabel("");
			btnDesligar.setToolTipText("Desligar o programa");
			btnDesligar.setBackground(SystemColor.menu);
			btnDesligar.setHorizontalAlignment(SwingConstants.CENTER);
			btnDesligar.setIcon(new ImageIcon("Interno/desligar.png"));
			btnDesligar.setBounds(497, 568, 100, 100);
		}
		return btnDesligar;
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
			setaBaixo.setBounds(665, 595, 30, 19);

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

	public JMenuItem getmntmDesligar() {
		if (mntmDesligar == null) {
			mntmDesligar = new JMenuItem("Desligar o programa");
		}
		return mntmDesligar;
	}
}
