package view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import control.InputListenerProdutoView;
import model.AtualizaTabelaProduto;
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
	private JLabel btnBuscarProduto;
	private JLabel btnNovoProduto;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	private AtualizaTabelaProduto aT1;
	private Thread t1;
	private ProdutoDAO  produtoDAO = new ProdutoDAO();  
	InputListenerProdutoView listener;
	private JLabel btnExcluirProduto;
	private JLabel btnEditarProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		ProdutoView frame = new ProdutoView();
		frame.setVisible(true);
				
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
	
	public JLabel getBuscarButton() {
		if(btnBuscarProduto == null){
			btnBuscarProduto = new JLabel();
			btnBuscarProduto.setHorizontalAlignment(SwingConstants.CENTER);
			btnBuscarProduto.setBounds(266, 472, 23, 23);
			btnBuscarProduto.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return btnBuscarProduto;
	}
	
	public JLabel getbtnNovoProduto() {
		if(btnNovoProduto == null){
			btnNovoProduto = new JLabel("");
			btnNovoProduto.setHorizontalAlignment(SwingConstants.CENTER);
			btnNovoProduto.setIcon(new ImageIcon("Interno/newProd.png"));
			btnNovoProduto.setBounds(466, 422, 80, 80);
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
			panel.add(getBtnExcluirProduto());
			panel.add(getBtnEditarProduto());
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
		getBtnExcluirProduto().addMouseListener(listener);
		getBtnEditarProduto().addMouseListener(listener);
		this.addWindowListener(listener);
	}
	
	public void initialize(){
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Produto");
		
		setContentPane(getContentPane());
		getT1().start();
		
	}
	public Thread getT1() {
		if(t1 == null) {
			
			try {
				aT1 = new AtualizaTabelaProduto(this);
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
	public JLabel getBtnExcluirProduto() {
		if (btnExcluirProduto == null) {
			btnExcluirProduto = new JLabel("");
			btnExcluirProduto.setHorizontalAlignment(SwingConstants.CENTER);
			btnExcluirProduto.setBounds(556, 422, 80, 80);
			btnExcluirProduto.setIcon(new ImageIcon("Interno/deleteProd.png"));
		}
		return btnExcluirProduto;
	}
	public JLabel getBtnEditarProduto() {
		if (btnEditarProduto == null) {
			btnEditarProduto = new JLabel("");
			btnEditarProduto.setHorizontalAlignment(SwingConstants.CENTER);
			btnEditarProduto.setIcon(new ImageIcon("Interno/editProd.png"));
			btnEditarProduto.setBounds(646, 422, 80, 80);
		}
		return btnEditarProduto;
	}
}
