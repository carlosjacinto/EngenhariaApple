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

import control.InputListenerPedidoView;
import model.AtualizaTabelaPedido;
import model.PedidoDAO;

public class PedidoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141345248153320486L;
	private JPanel contentPane;
	JPanel panel;
	private JTable tablePedido;
	private JTextField textBusca;
	private JLabel btnBuscarPedido;
	private JLabel btnNovoPedido;
	private JLabel btnExcluirPedido;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	InputListenerPedidoView listener;
	private AtualizaTabelaPedido aT1;
	private Thread t1;
	private PedidoDAO pedidoDAO = new PedidoDAO();
	private JLabel btnEditarPedido;
	private JLabel btnLimparBusca;

	public static void main(String[] args) {
		PedidoView frame = new PedidoView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public PedidoView() {
		listener = new InputListenerPedidoView(this);
		initialize();
		initializeListeners();
	}

	public void setTablePedido(JTable tablePedido) {
		this.tablePedido = tablePedido;
	}

	public JTable getTablePedido() {
		if (tablePedido == null) {
			String[][] notas = pedidoDAO.listaPedidoArray("");
			String[] colunas = { "Número", "Cliente", "CPF", "Total", "Funcionário", "Data do Cadastro" };

			DefaultTableModel model = new DefaultTableModel(notas, colunas) {
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
			tablePedido = new JTable(model);
			tablePedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tablePedido;
	}

	public JScrollPane getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollPane(getTablePedido());
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
		if (btnBuscarPedido == null) {
			btnBuscarPedido = new JLabel();
			btnBuscarPedido.setToolTipText("Buscar");
			btnBuscarPedido.setHorizontalAlignment(SwingConstants.CENTER);
			btnBuscarPedido.setBounds(266, 472, 23, 23);
			btnBuscarPedido.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return btnBuscarPedido;
	}

	public JLabel getbtnNovoPedido() {
		if (btnNovoPedido == null) {
			btnNovoPedido = new JLabel();
			btnNovoPedido.setToolTipText("Novo Pedido do Cliente");
			btnNovoPedido.setBounds(466, 422, 80, 80);
			btnNovoPedido.setIcon(new ImageIcon("Interno/newNota.png"));
		}
		return btnNovoPedido;
	}

	public JLabel getlblBuscarPorNome() {
		if (lblBuscarPorNome == null) {
			lblBuscarPorNome = new JLabel("Buscar por Pedido ou Nome do Funcion\u00E1rio:");
			lblBuscarPorNome.setBounds(58, 449, 258, 14);
		}
		return lblBuscarPorNome;
	}

	public void initializeListeners() {
		getBuscarButton().addMouseListener(listener);
		getbtnNovoPedido().addMouseListener(listener);
		getTablePedido().addMouseListener(listener);
		getbtnExcluirPedido().addMouseListener(listener);
		getBtnEditarPedido().addMouseListener(listener);
		getBtnLimparBusca().addMouseListener(listener);
		this.addWindowListener(listener);
	}

	public void initialize() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Pedidos");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.add(getScrollBar());
		panel.add(getBuscarButton());
		panel.add(getbtnNovoPedido());
		panel.add(getTextBusca());
		panel.add(getlblBuscarPorNome());
		panel.add(getbtnExcluirPedido());
		panel.add(getBtnEditarPedido());
		panel.add(getBtnLimparBusca());
		getT1().start();
	}

	public JLabel getbtnExcluirPedido() {
		if (btnExcluirPedido == null) {
			btnExcluirPedido = new JLabel("");
			btnExcluirPedido.setToolTipText("Excluir Pedido do Cliente");
			btnExcluirPedido.setHorizontalAlignment(SwingConstants.CENTER);
			btnExcluirPedido.setIcon(new ImageIcon("Interno/deleteNota.png"));
			btnExcluirPedido.setBounds(556, 422, 80, 80);
		}
		return btnExcluirPedido;
	}

	public Thread getT1() {
		if (t1 == null) {

			try {
				aT1 = new AtualizaTabelaPedido(this);
				t1 = new Thread(aT1);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				System.out.println(1);
			} catch (NoSuchElementException e) {
				// TODO: handle exception
				System.out.println(2);
			}
		}
		return t1;
	}

	public void setBuscaAT1(String busca) {
		aT1.setBusca(busca);
	}

	public JLabel getBtnEditarPedido() {
		if (btnEditarPedido == null) {
			btnEditarPedido = new JLabel("");
			btnEditarPedido.setToolTipText("Editar Pedido do Cliente");
			btnEditarPedido.setHorizontalAlignment(SwingConstants.CENTER);
			btnEditarPedido.setIcon(new ImageIcon("Interno/editNota.png"));
			btnEditarPedido.setBounds(646, 422, 80, 80);
		}
		return btnEditarPedido;
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
}
