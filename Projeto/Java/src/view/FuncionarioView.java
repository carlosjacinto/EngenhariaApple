package view;

import java.awt.BorderLayout;
import java.util.NoSuchElementException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.InputListenerFuncionarioView;
import model.AtualizaTabelaFuncionario;
import model.FuncionarioDAO;
import javax.swing.ListSelectionModel;

public class FuncionarioView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141345248153320486L;
	private JPanel contentPane;
	JPanel panel;
	private JTable tableFuncionario;
	private JTextField textBusca;
	private JLabel btnBuscarFuncionario;
	private JLabel btnNovoFuncionario;
	private JLabel btnExcluirFuncionario;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	InputListenerFuncionarioView listener;
	private AtualizaTabelaFuncionario aT1;
	private Thread t1;
	private FuncionarioDAO funcDAO = new FuncionarioDAO();
	private JLabel btnEditarFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FuncionarioView frame = new FuncionarioView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public FuncionarioView() {
		listener = new InputListenerFuncionarioView(this);
		initialize();
		initializeListeners();
	}
		
	public void setTableFuncionario(JTable tableFuncionario) {
		this.tableFuncionario = tableFuncionario;
	}

	public JTable getTableFuncionario() {
		if(tableFuncionario == null){
			String[][] funcs = funcDAO.listaFuncionarioArray("");
			String[] colunas = {"id","Nome", "CPF", "Endereço", "Telefone","Nascimento"};
			
			DefaultTableModel model = new DefaultTableModel(funcs,colunas) {
				 /**
				 * 
				 */
				private static final long serialVersionUID = -7018342759131611914L;
				boolean[] canEdit = new boolean []{  
				            false, false, false, false,false,false
				        };  
				        @Override  
				        public boolean isCellEditable(int rowIndex, int columnIndex) {  
				            return canEdit [columnIndex];  
				        }
			};
			tableFuncionario = new JTable(model);
			tableFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableFuncionario;
	}
	
	public JScrollPane getScrollBar() {
		if(scrollBar == null){
			scrollBar = new JScrollPane(getTableFuncionario());
			scrollBar.setBounds(58, 52, 668, 359);
		}
		return scrollBar;
	}
	
	public JTextField getTextBusca() {
		if(textBusca == null){
			textBusca = new JTextField();
			textBusca.setColumns(10);
			textBusca.setBounds(58, 473, 198, 23);
		}
		return textBusca;
	}
	
	public JLabel getBuscarButton() {
		if(btnBuscarFuncionario == null){
			btnBuscarFuncionario = new JLabel();
			btnBuscarFuncionario.setToolTipText("Buscar");
			btnBuscarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
			btnBuscarFuncionario.setBounds(266, 472, 23, 23);
			btnBuscarFuncionario.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return btnBuscarFuncionario;
	}
	
	public JLabel getbtnNovoFuncionario() {
		if(btnNovoFuncionario == null){
			btnNovoFuncionario = new JLabel();
			btnNovoFuncionario.setToolTipText("Novo Funcion\u00E1rio");
			btnNovoFuncionario.setBounds(466, 422, 80, 80);
			btnNovoFuncionario.setIcon(new ImageIcon("Interno/new.png"));
		}
		return btnNovoFuncionario;
	}
	
	public JLabel getlblBuscarPorNome() {
		if(lblBuscarPorNome == null){
			lblBuscarPorNome = new JLabel("Buscar por Nome ou CPF:");
			lblBuscarPorNome.setBounds(58, 449, 185, 14);
		}
		return lblBuscarPorNome;
	}
	
	public void initializeListeners() {
		getBuscarButton().addMouseListener(listener);
		getbtnNovoFuncionario().addMouseListener(listener);
		getTableFuncionario().addMouseListener(listener);
		getbtnExcluirFuncionario().addMouseListener(listener);
		getBtnEditarFuncionario().addMouseListener(listener);
		this.addWindowListener(listener);
	}
	
	public void initialize(){
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Funcionário");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.add(getScrollBar());		
		panel.add(getBuscarButton());		
		panel.add(getbtnNovoFuncionario());	
		panel.add(getTextBusca());		
		panel.add(getlblBuscarPorNome());
		panel.add(getbtnExcluirFuncionario());
		panel.add(getBtnEditarFuncionario());
		getT1().start();
	}
	
	public JLabel  getbtnExcluirFuncionario(){
		if(btnExcluirFuncionario == null){
			btnExcluirFuncionario = new JLabel("");
			btnExcluirFuncionario.setToolTipText("Excluir Funcionario");
			btnExcluirFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
			btnExcluirFuncionario .setIcon(new ImageIcon("Interno/delete.png"));
			btnExcluirFuncionario.setBounds(556, 422, 80, 80);
		}
		return btnExcluirFuncionario;
	}
	
	public Thread getT1() {
		if(t1 == null) {
			
			try {
				aT1 = new AtualizaTabelaFuncionario(this);
				t1 = new Thread(aT1);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				System.out.println(1);
			}catch (NoSuchElementException e) {
				// TODO: handle exception
				System.out.println(2);
			}
		}
		return t1;
	}
	
	public void setBuscaAT1(String busca) {
		aT1.setBusca(busca);
	}
	
	public JLabel getBtnEditarFuncionario() {
		if (btnEditarFuncionario == null) {
			btnEditarFuncionario = new JLabel("");
			btnEditarFuncionario.setToolTipText("Editar Funcionario");
			btnEditarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
			btnEditarFuncionario.setIcon(new ImageIcon("Interno/edit.png"));
			btnEditarFuncionario.setBounds(646, 422, 80, 80);
		}
		return btnEditarFuncionario;
	}
}
