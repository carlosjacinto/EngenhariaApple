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

import control.InputListenerEditarProduto;
import model.Produto;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class EditarProdutoView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019860783117484298L;
	private JPanel contentPanel;
	private JPanel panelFoto;
	private JButton btnPesquisarImagem;
	InputListenerEditarProduto listener;
	private Produto produto;
	private JLabel lblNome;
	private JTextField textNome;
	private JLabel lblPrecoCompra;
	private JTextField textPrecoCompra;
	private JLabel lblPrecoVenda;
	private JTextField textPrecoVenda;
	private JLabel lblDescricao;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JTextArea textDescricao;
	private JLabel lblDataCadastro;
	private JTextField txtDataDoCadastro;
	private JTextField textCodigo;
	private JLabel lblCodigo;
	private JLabel lblPercentuallucro;
	private JSpinner spinner;
	private JLabel lblFoto;

	public static void main(String[] args) {
		try {
			EditarProdutoView dialog = new EditarProdutoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarProdutoView() {
		listener = new InputListenerEditarProduto(this);
		initialize();
		initializeListeners();

	}

	public EditarProdutoView(Produto produto) {
		this.produto = produto;
		listener = new InputListenerEditarProduto(this);
		initialize();
		initializeListeners();

	}

	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 649, 520);
		setTitle("Edição de Produto");
		setContentPane(getContentPanel());

	}

	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
		getbtnPesquisarImagem().addMouseListener(listener);
		getSpinner().addChangeListener(listener);
	}

	public JButton getbtnPesquisarImagem() {
		if (btnPesquisarImagem == null) {
			btnPesquisarImagem = new JButton("Pesquisar Imagem");
			btnPesquisarImagem.setBounds(404, 371, 147, 23);
		}
		return btnPesquisarImagem;
	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			contentPanel.setLayout(null);
			contentPanel.add(getTextCodigo());
			contentPanel.add(getLblCodigo());
			contentPanel.add(getLblNome());
			contentPanel.add(getTextNome());
			contentPanel.add(getLblPrecoCompra());
			contentPanel.add(getTextPrecoCompra());
			contentPanel.add(getLblPrecoVenda());
			contentPanel.add(getTextPrecoVenda());
			contentPanel.add(getLblDescricao());
			contentPanel.add(getTextDataCadastro());
			contentPanel.add(getLblDataCadastro());
			contentPanel.add(getbtnPesquisarImagem());

			contentPanel.add(getpanelFoto());
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getTextDescricao());
			contentPanel.add(getTextDataCadastro());
			contentPanel.add(getLblDataCadastro());
			contentPanel.add(getLblCodigo());
			contentPanel.add(getTextCodigo());
			contentPanel.add(getLblPercentuallucro());
			contentPanel.add(getSpinner());

		}
		return contentPanel;
	}

	public JPanel getpanelFoto() {
		if (panelFoto == null) {
			panelFoto = new JPanel();
			panelFoto.setBounds(339, 82, 275, 281);
			panelFoto.setLayout(null);

			panelFoto.add(getLblFoto());
		}
		return panelFoto;

	}

	public JLabel getLblFoto() {
		lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(0, 0, 275, 281);
		ImageIcon i = new ImageIcon("Media/Produto/" + produto.getIdProduto() + ".png");
		System.out.println(i.getImageLoadStatus());
		if (i.getImageLoadStatus() == 4) {
			lblFoto.setIcon(new ImageIcon("Interno/default-produto.png"));
		} else {
			i.setImage(i.getImage().getScaledInstance(275, 281, 100));
			lblFoto.setIcon(i);
		}

		return lblFoto;
	}

	public JTextField getTextNome() {
		if (textNome == null) {
			textNome = new JTextField();
			textNome.setBounds(30, 95, 281, 20);
			textNome.setColumns(10);
			textNome.setText(produto.getNomeProduto());
		}
		return textNome;
	}

	public JLabel getLblNome() {
		if (lblNome == null) {
			lblNome = new JLabel("Nome");
			lblNome.setBounds(30, 70, 46, 14);
		}
		return lblNome;
	}

	public JLabel getLblPrecoCompra() {
		if (lblPrecoCompra == null) {
			lblPrecoCompra = new JLabel("Pre\u00E7o Compra (R$)");
			lblPrecoCompra.setBounds(30, 126, 221, 14);
		}
		return lblPrecoCompra;
	}

	public JTextField getTextPrecoCompra() {
		if (textPrecoCompra == null) {
			textPrecoCompra = new JTextField();
			textPrecoCompra.setEditable(false);
			textPrecoCompra.setBounds(30, 151, 281, 20);
			textPrecoCompra.setColumns(10);
			textPrecoCompra.setText(produto.getPrecoCompraProduto() + "");
		}
		return textPrecoCompra;
	}

	public JLabel getLblPrecoVenda() {
		if (lblPrecoVenda == null) {
			lblPrecoVenda = new JLabel("Pre\u00E7o Venda (R$)");
			lblPrecoVenda.setBounds(30, 238, 167, 14);
		}
		return lblPrecoVenda;
	}

	public JTextField getTextPrecoVenda() {
		if (textPrecoVenda == null) {
			textPrecoVenda = new JTextField();
			textPrecoVenda.setEditable(false);
			textPrecoVenda.setBounds(30, 263, 281, 20);
			textPrecoVenda.setColumns(10);
			textPrecoVenda.setText("" + produto.getPrecoVendaProduto());
		}
		return textPrecoVenda;
	}

	public JLabel getLblDescricao() {
		if (lblDescricao == null) {
			lblDescricao = new JLabel("Descri\u00E7\u00E3o");
			lblDescricao.setBounds(30, 294, 69, 14);
		}
		return lblDescricao;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(534, 442, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(435, 442, 89, 23);
		}
		return btnGravar;
	}

	public JTextArea getTextDescricao() {
		if (textDescricao == null) {
			textDescricao = new JTextArea();
			textDescricao.setLineWrap(true);
			textDescricao.setBorder(UIManager.getBorder("TextField.border"));
			textDescricao.setBounds(30, 319, 281, 154);
			textDescricao.setText(produto.getDescricaoProduto());
		}
		return textDescricao;
	}

	public JTextField getTextDataCadastro() {
		if (txtDataDoCadastro == null) {
			txtDataDoCadastro = new JTextField();
			txtDataDoCadastro.setBounds(339, 38, 284, 20);
			txtDataDoCadastro.setColumns(10);
			txtDataDoCadastro.setEditable(false);
			txtDataDoCadastro.setText("" + produto.getDataCadastroProduto());
		}
		return txtDataDoCadastro;
	}

	public JLabel getLblDataCadastro() {
		if (lblDataCadastro == null) {
			lblDataCadastro = new JLabel("Data do Cadastro");
			lblDataCadastro.setBounds(339, 12, 120, 14);
		}
		return lblDataCadastro;
	}

	public JTextField getTextCodigo() {
		if (textCodigo == null) {
			textCodigo = new JTextField();
			textCodigo.setBounds(30, 37, 281, 22);
			textCodigo.setEditable(false);
			textCodigo.setText("" + produto.getIdProduto());
		}
		return textCodigo;
	}

	public JLabel getLblCodigo() {
		if (lblCodigo == null) {
			lblCodigo = new JLabel("Código");
			lblCodigo.setBounds(30, 12, 277, 14);
		}
		return lblCodigo;
	}

	public JLabel getLblPercentuallucro() {
		if (lblPercentuallucro == null) {
			lblPercentuallucro = new JLabel("Percentual Lucro");
			lblPercentuallucro.setBounds(30, 182, 167, 14);
		}
		return lblPercentuallucro;
	}

	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spinner.setBounds(30, 207, 281, 20);
			spinner.setValue(new Integer(produto.getPercentualLucro()));
		}
		return spinner;
	}
}
