package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.InputListenerProdutoView;
import model.ProdutoDAO;

public class ProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141345248153320486L;
	private JPanel contentPane;
	private JPanel panel;
	private JTable tableProduto;
	private JTextField textBusca;
	private JButton btnBuscarProduto;
	private JButton btnNovoProduto;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	private ProdutoDAO  produtoDAO = new ProdutoDAO();  
	InputListenerProdutoView listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoView frame = new ProdutoView();
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
	public ProdutoView() {
		listener = new InputListenerProdutoView(this);
		initialize();
		initializeListeners();
	}
	
	public JTable getTableProduto() {
		if(tableProduto == null){
			String[] colunas = {"id","Nome", "Preço de Venda(R$)", "Preço de Compra(R$)", "Quantidade"};
			String[][] dados = produtoDAO.listaProdutoArray("");
			tableProduto = new JTable(new DefaultTableModel(dados,colunas) {
				 /**
				 * 
				 */
				private static final long serialVersionUID = -7018342759131611914L;
				boolean[] canEdit = new boolean []{  
				            false, false, false, false,false
				        };  
				        @Override  
				        public boolean isCellEditable(int rowIndex, int columnIndex) {  
				            return canEdit [columnIndex];  
				        }
			});
		}
		return tableProduto;
	}
	
	public JScrollPane getScrollBar() {
		if(scrollBar == null){
			scrollBar = new JScrollPane(getTableProduto());
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
		if(btnBuscarProduto == null){
			btnBuscarProduto = new JButton("Buscar");
			btnBuscarProduto.setBounds(266, 472, 75, 23);
		}
		return btnBuscarProduto;
	}
	
	public JButton getbtnNovoProduto() {
		if(btnNovoProduto == null){
			btnNovoProduto = new JButton("Novo Produto");
			btnNovoProduto.setBounds(469, 472, 150, 23);
		}
		return btnNovoProduto;
	}
	
	public JLabel getlblBuscarPorNome() {
		if(lblBuscarPorNome == null){
			lblBuscarPorNome = new JLabel("Buscar por Nome:");
			lblBuscarPorNome.setBounds(58, 449, 185, 14);
		}
		return lblBuscarPorNome;
	}
	
	public JPanel getPanel() {
		if(panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(null);
			panel.add(getScrollBar());		
			panel.add(getBuscarButton());		
			panel.add(getbtnNovoProduto());	
			panel.add(getTextBusca());		
			panel.add(getlblBuscarPorNome());
		}
		return panel;
	}
	
	public JPanel getContentPane() {
		if(contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			contentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return contentPane;
	}
	
	public void initializeListeners() {
		getBuscarButton().addMouseListener(listener);
		getbtnNovoProduto().addMouseListener(listener);
		getTableProduto().addMouseListener(listener);
	}
	
	public void initialize(){
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Produto");
		
		setContentPane(getContentPane());
		
		
	}
}
