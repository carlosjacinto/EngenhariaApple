package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import control.InputListenerCadastroProduto;
import javax.swing.JSpinner;

public class CadastroProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 158269313715179772L;
	private JPanel contentPanel;
	private JPanel panelFoto;
	private JButton btnPesquisarImagem;
	InputListenerCadastroProduto listener;
	private JLabel lblNome;
	private JTextField textNome;
	private JLabel lblPrecoVenda;
	private JLabel lblDescricao;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JTextArea textDescricao;
	private JLabel lblFoto;
	private JSpinner spinner;

	public static void main(String[] args) {
		try {
			CadastroProdutoView dialog = new CadastroProdutoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CadastroProdutoView() {
		listener = new InputListenerCadastroProduto(this);
		initialize();
		initializeListeners();

	}

	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 649, 500);
		setContentPane(getContentPanel());
		setTitle("Cadastro de Produto");

	}

	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
		getbtnPesquisarImagem().addMouseListener(listener);
	}

	public JButton getbtnPesquisarImagem() {
		if (btnPesquisarImagem == null) {
			btnPesquisarImagem = new JButton("Pesquisar Imagem");
			btnPesquisarImagem.setBounds(406, 361, 147, 23);
		}
		return btnPesquisarImagem;
	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			contentPanel.setLayout(null);
			contentPanel.add(getLblNome());
			contentPanel.add(getTextNome());
			contentPanel.add(getLblPrecoVenda());
			contentPanel.add(getLblDescricao());
			contentPanel.add(getbtnPesquisarImagem());

			contentPanel.add(getLblFoto());
			contentPanel.add(getpanelFoto());
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getTextDescricao());
			contentPanel.add(getSpinner());

		}
		return contentPanel;
	}

	public JPanel getpanelFoto() {
		if (panelFoto == null) {
			panelFoto = new JPanel();
			panelFoto.setBounds(348, 69, 275, 281);
		}
		return panelFoto;

	}

	public JTextField getTextNome() {
		if (textNome == null) {
			textNome = new JTextField();
			textNome.setBounds(30, 63, 281, 20);
			textNome.setColumns(10);
		}
		return textNome;
	}

	public JLabel getLblNome() {
		if (lblNome == null) {
			lblNome = new JLabel("Nome");
			lblNome.setBounds(30, 38, 46, 14);
		}
		return lblNome;
	}

	public JLabel getLblPrecoVenda() {
		if (lblPrecoVenda == null) {
			lblPrecoVenda = new JLabel("Percentual de Lucro(%)");
			lblPrecoVenda.setBounds(30, 104, 167, 14);
		}
		return lblPrecoVenda;
	}

	public JLabel getLblDescricao() {
		if (lblDescricao == null) {
			lblDescricao = new JLabel("Descri\u00E7\u00E3o");
			lblDescricao.setBounds(31, 171, 69, 14);
		}
		return lblDescricao;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(534, 411, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(434, 411, 89, 23);
		}
		return btnGravar;
	}

	public JTextArea getTextDescricao() {
		if (textDescricao == null) {
			textDescricao = new JTextArea();
			textDescricao.setLineWrap(true);
			textDescricao.setBorder(UIManager.getBorder("TextField.border"));
			textDescricao.setBounds(30, 196, 281, 154);
		}
		return textDescricao;
	}
	public JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBounds(348, 69, 275, 281);
			lblFoto.setIcon(new ImageIcon("Interno/default-produto.png"));
		}
		return lblFoto;
	}
	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setBounds(30, 129, 281, 20);
		}
		return spinner;
	}
}
