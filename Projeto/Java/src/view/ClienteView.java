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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.InputListenerClienteView;
import model.AtualizaTabelaCliente;
import model.ClienteDAO;

public class ClienteView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141345248153320486L;
	private JPanel contentPane;
	JPanel panel;
	private JTable tableCliente;
	private JTextField textBusca;
	private JLabel btnBuscarCliente;
	private JLabel btnNovoCliente;
	private JLabel btnExcluirCliente;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	InputListenerClienteView listener;
	private AtualizaTabelaCliente aT1;
	private Thread t1;
	private ClienteDAO clieDAO = ClienteDAO.getInstance();
	private JLabel btnEditarCliente;
	private JLabel btnLimparBusca;
	private JLabel btnVisualizarConta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClienteView frame = new ClienteView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ClienteView() {
		listener = new InputListenerClienteView(this);
		initialize();
		initializeListeners();
	}

	public void setTableCliente(JTable tableCliente) {
		this.tableCliente = tableCliente;
	}

	public JTable buscarClientes() {
		if (tableCliente == null) {
			String[][] clies = clieDAO.listarClientes("");
			String[] colunas = { "id", "Nome", "CPF", "Endereço", "Telefone", "Nascimento" };

			DefaultTableModel model = new DefaultTableModel(clies, colunas) {
				/**
				* 
				*/
				private static final long serialVersionUID = -7018342759131611914L;
				boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			tableCliente = new JTable(model);
			tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		
		return tableCliente;
		
	}

	public JScrollPane getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollPane(buscarClientes());
			scrollBar.setBounds(58, 52, 668, 359);
		}
		return scrollBar;
	}

	public JTextField getTextBusca() {
		if (textBusca == null) {
			textBusca = new JTextField();
			textBusca.setColumns(10);
			textBusca.setBounds(58, 473, 198, 23);
		}
		return textBusca;
	}

	public JLabel getBuscarButton() {
		if (btnBuscarCliente == null) {
			btnBuscarCliente = new JLabel();
			btnBuscarCliente.setToolTipText("Buscar");
			btnBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
			btnBuscarCliente.setBounds(266, 472, 23, 23);
			btnBuscarCliente.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return btnBuscarCliente;
	}

	public JLabel getbtnNovoCliente() {
		if (btnNovoCliente == null) {
			btnNovoCliente = new JLabel();
			btnNovoCliente.setToolTipText("Novo Cliente");
			btnNovoCliente.setBounds(376, 422, 80, 80);
			btnNovoCliente.setIcon(new ImageIcon("Interno/new.png"));
		}
		return btnNovoCliente;
	}

	public JLabel getlblBuscarPorNome() {
		if (lblBuscarPorNome == null) {
			lblBuscarPorNome = new JLabel("Buscar por Nome ou CPF:");
			lblBuscarPorNome.setBounds(58, 449, 185, 14);
		}
		return lblBuscarPorNome;
	}

	public void initializeListeners() {
		getBuscarButton().addMouseListener(listener);
		getbtnNovoCliente().addMouseListener(listener);
		buscarClientes().addMouseListener(listener);
		getbtnExcluirCliente().addMouseListener(listener);
		getBtnEditarCliente().addMouseListener(listener);
		getBtnLimparBusca().addMouseListener(listener);
		getBtnVisualizarConta().addMouseListener(listener);
		this.addWindowListener(listener);
		//TODO: this.addWindowListener(listener);
	}

	public void initialize() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Cliente");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.add(getScrollBar());
		panel.add(getBuscarButton());
		panel.add(getBtnLimparBusca());
		panel.add(getbtnNovoCliente());
		panel.add(getTextBusca());
		panel.add(getlblBuscarPorNome());
		panel.add(getbtnExcluirCliente());
		panel.add(getBtnEditarCliente());
		panel.add(getBtnVisualizarConta());
		getT1().start();
	}

	public JLabel getbtnExcluirCliente() {
		if (btnExcluirCliente == null) {
			btnExcluirCliente = new JLabel("");
			btnExcluirCliente.setToolTipText("Excluir Cliente");
			btnExcluirCliente.setHorizontalAlignment(SwingConstants.CENTER);
			btnExcluirCliente.setIcon(new ImageIcon("Interno/delete.png"));
			btnExcluirCliente.setBounds(466, 422, 80, 80);
		}
		return btnExcluirCliente;
	}

	public Thread getT1() {
		if (t1 == null) {

			try {
				aT1 = new AtualizaTabelaCliente(this);
				t1 = new Thread(aT1);
			} catch (ArrayIndexOutOfBoundsException e) {

				System.out.println(1);
			} catch (NoSuchElementException e) {

				System.out.println(2);
			}
		}
		return t1;
	}

	public void setBuscaAT1(String busca) {
		aT1.setBusca(busca);
	}

	public JLabel getBtnEditarCliente() {
		if (btnEditarCliente == null) {
			btnEditarCliente = new JLabel("");
			btnEditarCliente.setToolTipText("Editar Cliente");
			btnEditarCliente.setHorizontalAlignment(SwingConstants.CENTER);
			btnEditarCliente.setIcon(new ImageIcon("Interno/edit.png"));
			btnEditarCliente.setBounds(556, 422, 80, 80);
		}
		return btnEditarCliente;
	}
	
	public JLabel getBtnLimparBusca() {
		if (btnLimparBusca == null) {
			btnLimparBusca = new JLabel();
			btnLimparBusca.setToolTipText("Limpar Busca");
			btnLimparBusca.setHorizontalAlignment(SwingConstants.CENTER);
			btnLimparBusca.setBounds(299, 472, 23, 23);
			btnLimparBusca.setIcon(new ImageIcon("Interno/clean-search.png"));
		}
		return btnLimparBusca;
	}
	public JLabel getBtnVisualizarConta() {
		if (btnVisualizarConta == null) {
			btnVisualizarConta = new JLabel("");
			btnVisualizarConta.setToolTipText("Visualizar Conta");
			btnVisualizarConta.setHorizontalAlignment(SwingConstants.CENTER);
			btnVisualizarConta.setBounds(646, 422, 80, 80);
			btnVisualizarConta.setIcon(new ImageIcon("Interno/conta.png"));
		}
		return btnVisualizarConta;
	}
}
