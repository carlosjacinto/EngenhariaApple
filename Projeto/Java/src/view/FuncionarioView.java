package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
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

import control.AtualizaTabela;
import control.InputListenerFuncionarioView;
import model.FuncionarioDAO;
import javax.swing.ImageIcon;

public class FuncionarioView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141345248153320486L;
	private JPanel contentPane;
	JPanel panel;
	private JTable tableFuncionario;
	private JTextField textBusca;
	private JButton btnBuscarFuncionario;
	private JButton btnNovoFuncionario;
	private JLabel btnExcluirFuncionario;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	InputListenerFuncionarioView listener;
	private AtualizaTabela aT1;
	private Thread t1;
	private FuncionarioDAO funcDAO = new FuncionarioDAO();

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
	
	public JButton getBuscarButton() {
		if(btnBuscarFuncionario == null){
			btnBuscarFuncionario = new JButton("Buscar");
			btnBuscarFuncionario.setBounds(266, 472, 75, 23);
		}
		return btnBuscarFuncionario;
	}
	
	public JButton getbtnNovoFuncionario() {
		if(btnNovoFuncionario == null){
			btnNovoFuncionario = new JButton("Novo Funcionário");
			btnNovoFuncionario.setBounds(385, 449, 150, 23);
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
		getT1().start();
	}
	
	public JLabel  getbtnExcluirFuncionario(){
		if(btnExcluirFuncionario == null){
			btnExcluirFuncionario = new JLabel("");
			btnExcluirFuncionario.setToolTipText("Excluir Funcionario");
			btnExcluirFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
			btnExcluirFuncionario .setIcon(new ImageIcon("Interno/delete.png"));
			btnExcluirFuncionario.setBounds(556, 422, 93, 72);
		}
		return btnExcluirFuncionario;
	}
	
	public Thread getT1() {
		if(t1 == null) {
			aT1 = new AtualizaTabela(this);
			t1 = new Thread(aT1);
		}
		return t1;
	}
}
