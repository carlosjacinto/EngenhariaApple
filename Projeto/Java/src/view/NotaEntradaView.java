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

import control.InputListenerNotaEntradaView;
import model.AtualizaTabelaNotaEntrada;
import model.NotaEntradaDAO;

public class NotaEntradaView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141345248153320486L;
	private JPanel contentPane;
	JPanel panel;
	private JTable tableNotaEntrada;
	private JTextField textBusca;
	private JLabel btnBuscarNotaEntrada;
	private JLabel btnNovoNotaEntrada;
	private JLabel btnExcluirNotaEntrada;
	private JLabel lblBuscarPorNome;
	private JScrollPane scrollBar;
	InputListenerNotaEntradaView listener;
	private AtualizaTabelaNotaEntrada aT1;
	private Thread t1;
	private NotaEntradaDAO notaEntDAO = new NotaEntradaDAO();
	private JLabel btnEditarNotaEntrada;
	private JLabel btnLimparBusca;

	public static void main(String[] args) {
		NotaEntradaView frame = new NotaEntradaView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public NotaEntradaView() {
		listener = new InputListenerNotaEntradaView(this);
		initialize();
		initializeListeners();
	}

	public void setTableNotaEntrada(JTable tableNotaEntrada) {
		this.tableNotaEntrada = tableNotaEntrada;
	}

	public JTable getTableNotaEntrada() {
		if (tableNotaEntrada == null) {
			String[][] notas = notaEntDAO.listaNotaEntradaArray("");
			String[] colunas = { "Número", "Nome", "CNPJ", "Total", "Funcionário", "Data do Cadastro" };

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
			tableNotaEntrada = new JTable(model);
			tableNotaEntrada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableNotaEntrada;
	}

	public JScrollPane getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollPane(getTableNotaEntrada());
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
		if (btnBuscarNotaEntrada == null) {
			btnBuscarNotaEntrada = new JLabel();
			btnBuscarNotaEntrada.setToolTipText("Buscar");
			btnBuscarNotaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
			btnBuscarNotaEntrada.setBounds(266, 472, 23, 23);
			btnBuscarNotaEntrada.setIcon(new ImageIcon("Interno/search-icon.png"));
		}
		return btnBuscarNotaEntrada;
	}

	public JLabel getbtnNovoNotaEntrada() {
		if (btnNovoNotaEntrada == null) {
			btnNovoNotaEntrada = new JLabel();
			btnNovoNotaEntrada.setToolTipText("Nova Nota de Entrada");
			btnNovoNotaEntrada.setBounds(466, 422, 80, 80);
			btnNovoNotaEntrada.setIcon(new ImageIcon("Interno/newNota.png"));
		}
		return btnNovoNotaEntrada;
	}

	public JLabel getlblBuscarPorNome() {
		if (lblBuscarPorNome == null) {
			lblBuscarPorNome = new JLabel("Buscar por NFE ou Nome do Funcion\u00E1rio:");
			lblBuscarPorNome.setBounds(58, 449, 258, 14);
		}
		return lblBuscarPorNome;
	}

	public void initializeListeners() {
		getBuscarButton().addMouseListener(listener);
		getbtnNovoNotaEntrada().addMouseListener(listener);
		getTableNotaEntrada().addMouseListener(listener);
		getbtnExcluirNotaEntrada().addMouseListener(listener);
		getBtnEditarNotaEntrada().addMouseListener(listener);
		getBtnLimparBusca().addMouseListener(listener);
		this.addWindowListener(listener);
	}

	public void initialize() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Nota de Entrada");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.add(getScrollBar());
		panel.add(getBuscarButton());
		panel.add(getbtnNovoNotaEntrada());
		panel.add(getTextBusca());
		panel.add(getlblBuscarPorNome());
		panel.add(getbtnExcluirNotaEntrada());
		panel.add(getBtnEditarNotaEntrada());
		panel.add(getBtnLimparBusca());
		getT1().start();
	}

	public JLabel getbtnExcluirNotaEntrada() {
		if (btnExcluirNotaEntrada == null) {
			btnExcluirNotaEntrada = new JLabel("");
			btnExcluirNotaEntrada.setToolTipText("Excluir Nota de Entrada");
			btnExcluirNotaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
			btnExcluirNotaEntrada.setIcon(new ImageIcon("Interno/deleteNota.png"));
			btnExcluirNotaEntrada.setBounds(556, 422, 80, 80);
		}
		return btnExcluirNotaEntrada;
	}

	public Thread getT1() {
		if (t1 == null) {

			try {
				aT1 = new AtualizaTabelaNotaEntrada(this);
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

	public JLabel getBtnEditarNotaEntrada() {
		if (btnEditarNotaEntrada == null) {
			btnEditarNotaEntrada = new JLabel("");
			btnEditarNotaEntrada.setToolTipText("Editar Nota de Entrada");
			btnEditarNotaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
			btnEditarNotaEntrada.setIcon(new ImageIcon("Interno/editNota.png"));
			btnEditarNotaEntrada.setBounds(646, 422, 80, 80);
		}
		return btnEditarNotaEntrada;
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
