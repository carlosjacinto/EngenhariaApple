package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import control.InputListenerEditarPedido;
import model.Cliente;
import model.ClienteDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Pedido;
import model.PedidoDAO;
import model.ProdutoDAO;

public class EditarPedidoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7876262101294494488L;
	InputListenerEditarPedido listener;
	private JPanel contentPanel;
	private JTable tableProduto;
	private JLabel lblNomeCliente;
	private JLabel lblFuncionario;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JComboBox<Object> comboBoxCliente;
	private JComboBox<Object> comboBoxFuncionario;
	private JComboBox<Object> comboBoxProduto;
	private JLabel labelProduto;
	private JLabel lblQuantidade;
	private JLabel lblPreoTotalr;
	private JTextField textPreco;
	private JSpinner spinnerQtde;
	ClienteDAO clienteDAO = ClienteDAO.getInstance();
	FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstance();
	ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
	PedidoDAO pedidoDAO = PedidoDAO.getInstance();
	Pedido ped;
	private JLabel btnAdd;
	private JScrollPane scrollPane;
	private JLabel lblNumPed;
	private JLabel lblDataVenda;
	private JTextField textNumPed;
	private JTextField textDataVenda;
	
	public EditarPedidoView(Pedido ped) {
		this.ped = ped;
		initialize();
		preencherCampos(ped);
		listener = new InputListenerEditarPedido(this);
		initializeListeners();
	}

	public Pedido getPedido() {
		return ped;
	}
	
	public void preencherCampos(Pedido ped) {
		Cliente c = clienteDAO.RetornaCliente(ped.getIdCliente());
		Funcionario f = funcionarioDAO.RetornaFuncionario(ped.getIdFuncionario());
		getComboBoxCliente().setSelectedItem(c.getIdCliente()+"-"+c.getNome());
		getComboBoxFuncionario().setSelectedItem(f.getIdFuncionario()+"-"+f.getNome());
		
		String[] colunas = {"id","Nome", "Quantidade", "Preço(R$)"};
		
		String[][] dados = pedidoDAO.retornaProdutosPed(ped.getIdPedido());
		
		for(int i = 0; i< dados.length; i++) {
			getComboBoxProduto().removeItem(dados[i][0]+"-"+dados[i][1]);
		}
		
		DefaultTableModel model = new DefaultTableModel(dados,colunas) {
			 /**
			 * 
			 */
			private static final long serialVersionUID = -7018342759131611914L;
			boolean[] canEdit = new boolean []{  
			            false, false, false, false
			        };  
			        @Override  
			        public boolean isCellEditable(int rowIndex, int columnIndex) {  
			            return canEdit [columnIndex];  
			        }
		};
		getTableFuncionario().setModel(model);
		this.repaint();
		this.revalidate();
		
	}

	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
		getBtnAdd().addMouseListener(listener);
	}

	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 629, 385);
		setContentPane(getContentPanel());
		setTitle("Edição de Pedido");

	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			contentPanel.setLayout(null);
			contentPanel.add(getLblNomeCliente());
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getComboBoxCliente());
			contentPanel.add(getLblFuncionario());
			contentPanel.add(getComboBoxFuncionario());
			contentPanel.add(getComboBoxProduto());
			contentPanel.add(getLabelProduto());
			contentPanel.add(getLblQuantidade());
			contentPanel.add(getLblPreoTotalr());
			contentPanel.add(getTextPreco());
			contentPanel.add(getSpinnerQtde());
			contentPanel.add(getBtnAdd());
			contentPanel.add(getScrollPane());
			contentPanel.add(getLblNumPed());
			contentPanel.add(getLblDataVenda());
			contentPanel.add(getTextNumPed());
			contentPanel.add(getTextDataVenda());
			
		}
		return contentPanel;
	}

	public JTextField getTextNumPed() {
		if (textNumPed == null) {
			textNumPed = new JTextField();
			textNumPed.setEditable(false);
			textNumPed.setBounds(10, 34, 281, 20);
			textNumPed.setColumns(10);
			textNumPed.setText(""+ped.getIdPedido());
		}
		return textNumPed;
	}
	
	public JTextField getTextDataVenda() {
		if (textDataVenda == null) {
			textDataVenda = new JTextField();
			textDataVenda.setEditable(false);
			textDataVenda.setBounds(322, 34, 212, 20);
			textDataVenda.setColumns(10);
			textDataVenda.setText(""+ped.getDataPed());
		}
		return textDataVenda;
	}
	
	private JLabel getLblNomeCliente() {
		if (lblNomeCliente == null) {
			lblNomeCliente = new JLabel("Cliente");
			lblNomeCliente.setBounds(10, 65, 112, 14);
		}
		return lblNomeCliente;
	}
	
	private JLabel getLblFuncionario() {
		if (lblFuncionario == null) {
			lblFuncionario = new JLabel("Funcion\u00E1rio");
			lblFuncionario.setBounds(322, 65, 75, 14);
		}
		return lblFuncionario;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(514, 201, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(415, 201, 89, 23);
		}
		return btnGravar;
	}
	
	public JComboBox<Object> getComboBoxCliente() {
		if(comboBoxCliente == null) {
			String clientes[] = clienteDAO.buscarNomeeId();
			comboBoxCliente = new JComboBox<Object>(clientes);
			comboBoxCliente.setSelectedItem("1-prod2");
			comboBoxCliente.setEnabled(false);
			comboBoxCliente.setBounds(10, 90, 281, 20);
		}
		return comboBoxCliente;
	}
	
	public JComboBox<Object> getComboBoxFuncionario() {
		if (comboBoxFuncionario == null) {
			String funcionarios[] = funcionarioDAO.buscarNomeeId();
			comboBoxFuncionario = new JComboBox<Object>(funcionarios);
			comboBoxFuncionario.setEnabled(false);
			comboBoxFuncionario.setEditable(true);
			comboBoxFuncionario.setBounds(322, 90, 281, 20);
		}
		return comboBoxFuncionario;
	}
	public JComboBox<Object> getComboBoxProduto() {
		if (comboBoxProduto == null) {
			String produtos[] = produtoDAO.buscarNomeeId();
			comboBoxProduto = new JComboBox<Object>(produtos);
			comboBoxProduto.setBounds(10, 148, 281, 20);
		}
		return comboBoxProduto;
	}
	
	public JLabel getLabelProduto() {
		if (labelProduto == null) {
			labelProduto = new JLabel("Produto");
			labelProduto.setBounds(10, 123, 75, 14);
		}
		return labelProduto;
	}
	public JLabel getLblQuantidade() {
		if (lblQuantidade == null) {
			lblQuantidade = new JLabel("Quantidade");
			lblQuantidade.setBounds(322, 123, 75, 14);
		}
		return lblQuantidade;
	}
	public JLabel getLblPreoTotalr() {
		if (lblPreoTotalr == null) {
			lblPreoTotalr = new JLabel("Pre\u00E7o Total (R$)");
			lblPreoTotalr.setBounds(10, 179, 130, 14);
		}
		return lblPreoTotalr;
	}
	public JTextField getTextPreco() {
		if (textPreco == null) {
			textPreco = new JTextField("0.0");
			textPreco.setEditable(false);
			textPreco.setBounds(10, 204, 281, 20);
			textPreco.setColumns(10);
			System.out.println(ped.getPrecoPed());
			textPreco.setText(""+ped.getPrecoPed());
		}
		return textPreco;
	}

	public JSpinner getSpinnerQtde() {
		if (spinnerQtde == null) {
			spinnerQtde = new JSpinner();
			spinnerQtde.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spinnerQtde.setBounds(322, 148, 247, 20);
		}
		return spinnerQtde;
	}
	
	public JLabel getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JLabel("");
			btnAdd.setHorizontalAlignment(SwingConstants.CENTER);
			btnAdd.setIcon(new ImageIcon("Interno/add.png"));
			btnAdd.setBounds(580, 145, 23, 23);
		}
		return btnAdd;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTableFuncionario());
			scrollPane.setBounds(10, 235, 593, 99);
		}
		return scrollPane;
	}
	
	public JTable getTableFuncionario() {
		if(tableProduto == null){
			String[] colunas = {"id","Nome", "Quantidade", "Preço(R$)"};
			String[][] dados = null;
			
			DefaultTableModel model = new DefaultTableModel(dados,colunas) {
				 /**
				 * 
				 */
				private static final long serialVersionUID = -7018342759131611914L;
				boolean[] canEdit = new boolean []{  
				            false, false, false, false
				        };  
				        @Override  
				        public boolean isCellEditable(int rowIndex, int columnIndex) {  
				            return canEdit [columnIndex];  
				        }
			};
			tableProduto = new JTable(model);
			tableProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableProduto;
	}
	private JLabel getLblNumPed() {
		if (lblNumPed == null) {
			lblNumPed = new JLabel("N\u00FAmero do Pedido");
			lblNumPed.setBounds(10, 11, 281, 14);
		}
		return lblNumPed;
	}
	private JLabel getLblDataVenda() {
		if (lblDataVenda == null) {
			lblDataVenda = new JLabel("Data da Venda");
			lblDataVenda.setBounds(322, 11, 182, 14);
		}
		return lblDataVenda;
	}
}
