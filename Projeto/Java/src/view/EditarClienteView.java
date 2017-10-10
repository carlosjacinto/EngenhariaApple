package view;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import control.InputListenerEditarCliente;
import model.Cliente;

public class EditarClienteView extends JDialog {

	private static final long serialVersionUID = -5302940356165709734L;
	private JPanel contentPanel;
	InputListenerEditarCliente listener;
	private JPanel panelFoto;
	private JButton btnPesquisarImagem;
	private JLabel lblNome;
	private JTextField textNome;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JLabel lblCPF;
	private JTextField textCPF;
	private JLabel lblRua;
	private JTextField textRua;
	private JTextField textBairro;
	private JTextField textCidade;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblNumero;
	private JTextField textNumero;
	private JLabel lblComplemento;
	private JTextField textComplemento;
	private JLabel lblCEP;
	private JTextField textCEP;
	private JLabel lblDataNascimento;
	private JFormattedTextField textDataNascimento;
	private JLabel lblTelefone;
	private JTextField textTelefone;
	private JLabel lblCelular;
	private JTextField textCelular;
	private JLabel lblFoto;
	private JLabel lblDataCadastro;
	private JTextField txtDataDoCadastro;
	private JTextField textCodigo;
	private JLabel lblCodigo;
	Cliente clie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarClienteView dialog = new EditarClienteView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditarClienteView(Cliente clie) {
		this.clie = clie;
		listener = new InputListenerEditarCliente(this);
		initialize();
		initializeListeners();
	}
	
	public EditarClienteView() {
		listener = new InputListenerEditarCliente(this);
		initialize();
		initializeListeners();

	}

	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
		getbtnPesquisarImagem().addMouseListener(listener);
	}

	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 649, 590);
		setContentPane(getContentPanel());
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Edição de Cliente");
	}

	public JButton getbtnPesquisarImagem() {
		if (btnPesquisarImagem == null) {
			btnPesquisarImagem = new JButton("Pesquisar Imagem");
			btnPesquisarImagem.setBounds(411, 487, 147, 23);
		}
		return btnPesquisarImagem;
	}

	public JPanel getpanelFoto() {
		if (panelFoto == null) {
			panelFoto = new JPanel();
			panelFoto.setBounds(348, 195, 275, 281);
			panelFoto.setLayout(null);
		}
		return panelFoto;

	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			contentPanel.setLayout(null);
			contentPanel.add(getLblNome());
			contentPanel.add(getTextNome());
			contentPanel.add(getbtnPesquisarImagem());

			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getLblCPF());
			contentPanel.add(getTextCPF());
			contentPanel.add(getLblRua());
			contentPanel.add(getTextRua());
			contentPanel.add(getTextBairro());
			contentPanel.add(getTextCidade());
			contentPanel.add(getLblBairro());
			contentPanel.add(getLblCidade());
			contentPanel.add(getLblNumero());
			contentPanel.add(getTextNumero());
			contentPanel.add(getLblComplemento());
			contentPanel.add(getTextComplemento());
			contentPanel.add(getLblCEP());
			contentPanel.add(getTextCEP());
			contentPanel.add(getLblDataNascimento());
			contentPanel.add(getTextDataNascimento());
			contentPanel.add(getLblTelefone());
			contentPanel.add(getTextTelefone());
			contentPanel.add(getLblCelular());
			contentPanel.add(getTextCelular());
			contentPanel.add(getLblFoto());
			contentPanel.add(getpanelFoto());
			contentPanel.add(getTextDataCadastro());
			contentPanel.add(getLblDataCadastro());
			contentPanel.add(getLblCodigo());
			contentPanel.add(getTextCodigo());

		}
		return contentPanel;
	}

	public JTextField getTextNome() {
		if (textNome == null) {
			textNome = new JTextField();
			textNome.setBounds(30, 95, 281, 20);
			textNome.setColumns(10);
			textNome.setText(clie.getNome());
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

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(534, 530, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(435, 530, 89, 23);
		}
		return btnGravar;
	}

	public JLabel getLblCPF() {
		if (lblCPF == null) {
			lblCPF = new JLabel("CPF");
			lblCPF.setBounds(30, 126, 281, 14);
		}
		return lblCPF;
	}

	public JTextField getTextCPF() {
		if (textCPF == null) {
			textCPF = new JTextField();
			textCPF.setBounds(30, 146, 281, 20);
			textCPF.setColumns(10);
			textCPF.setEditable(false);
			textCPF.setText(clie.getCpf());
		}
		return textCPF;
	}

	public JLabel getLblRua() {
		if (lblRua == null) {
			lblRua = new JLabel("Rua");
			lblRua.setBounds(30, 177, 281, 14);
		}
		return lblRua;
	}

	public JTextField getTextRua() {
		if (textRua == null) {
			textRua = new JTextField();
			textRua.setBounds(30, 195, 204, 20);
			textRua.setColumns(10);
			textRua.setText(clie.getRua());
		}
		return textRua;
	}

	public JTextField getTextBairro() {
		if (textBairro == null) {
			textBairro = new JTextField();
			textBairro.setBounds(30, 248, 281, 20);
			textBairro.setColumns(10);
			textBairro.setText(clie.getBairro());
		}
		return textBairro;
	}

	public JTextField getTextCidade() {
		if (textCidade == null) {
			textCidade = new JTextField();
			textCidade.setBounds(30, 299, 281, 20);
			textCidade.setColumns(10);
			textCidade.setText(clie.getCidade());
		}
		return textCidade;
	}

	public JLabel getLblBairro() {
		if (lblBairro == null) {
			lblBairro = new JLabel("Bairro");
			lblBairro.setBounds(30, 226, 281, 14);
		}
		return lblBairro;
	}

	public JLabel getLblCidade() {
		if (lblCidade == null) {
			lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(30, 279, 281, 14);
		}
		return lblCidade;
	}

	public JLabel getLblNumero() {
		if (lblNumero == null) {
			lblNumero = new JLabel("Numero");
			lblNumero.setBounds(244, 177, 62, 14);
		}
		return lblNumero;
	}

	public JTextField getTextNumero() {
		if (textNumero == null) {
			textNumero = new JTextField();
			textNumero.setBounds(244, 195, 67, 20);
			textNumero.setColumns(10);
			textNumero.setText(clie.getNumero());
		}
		return textNumero;
	}

	public JLabel getLblComplemento() {
		if (lblComplemento == null) {
			lblComplemento = new JLabel("Complemento");
			lblComplemento.setBounds(30, 330, 281, 14);
		}
		return lblComplemento;
	}

	public JTextField getTextComplemento() {
		if (textComplemento == null) {
			textComplemento = new JTextField();
			textComplemento.setBounds(30, 355, 281, 20);
			textComplemento.setColumns(10);
			textComplemento.setText(clie.getComplemento());
		}
		return textComplemento;
	}

	public JLabel getLblCEP() {
		if (lblCEP == null) {
			lblCEP = new JLabel("CEP");
			lblCEP.setBounds(30, 386, 281, 14);
		}
		return lblCEP;
	}

	public JTextField getTextCEP() {
		if (textCEP == null) {
			textCEP = new JTextField();
			textCEP.setBounds(30, 402, 281, 20);
			textCEP.setColumns(10);
			textCEP.setText(clie.getCep());
		}
		return textCEP;
	}

	public JLabel getLblDataNascimento() {
		if (lblDataNascimento == null) {
			lblDataNascimento = new JLabel("Data Nascimento");
			lblDataNascimento.setBounds(30, 433, 281, 14);
		}
		return lblDataNascimento;
	}

	public JFormattedTextField getTextDataNascimento() {
		if (textDataNascimento == null) {
			try {
				textDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			textDataNascimento.setValue(null);
			textDataNascimento.setColumns(6);
			textDataNascimento.setBounds(30, 454, 281, 20);
			textDataNascimento.setColumns(10);
			textDataNascimento.setText(clie.getDataNascimento());
		}
		return textDataNascimento;
	}

	public JLabel getLblTelefone() {
		if (lblTelefone == null) {
			lblTelefone = new JLabel("Telefone");
			lblTelefone.setBounds(339, 70, 284, 14);
		}
		return lblTelefone;
	}

	public JTextField getTextTelefone() {
		if (textTelefone == null) {
			textTelefone = new JTextField();
			textTelefone.setBounds(339, 95, 284, 20);
			textTelefone.setColumns(10);
			textTelefone.setText(clie.getTelefone()+"");
		}
		return textTelefone;
	}

	public JLabel getLblCelular() {
		if (lblCelular == null) {
			lblCelular = new JLabel("Celular");
			lblCelular.setBounds(339, 126, 284, 14);
		}
		return lblCelular;
	}

	public JTextField getTextCelular() {
		if (textCelular == null) {
			textCelular = new JTextField();
			textCelular.setBounds(339, 152, 284, 20);
			textCelular.setColumns(10);
			textCelular.setText(clie.getCelular()+"");
		}
		return textCelular;
	}

	public JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBounds(0, 0, 275, 281);
			
			ImageIcon i = new ImageIcon("Media/Cliente/" + clie.getIdCliente() + ".png");
			if (i.getImage()==null)
				lblFoto.setIcon(new ImageIcon("Interno/default-avatar.png"));
			else {
				i.setImage(i.getImage().getScaledInstance(275, 281, 100));
				lblFoto.setIcon(i);
			}
		}
		return lblFoto;
	}

	public JTextField getTextDataCadastro() {
		if (txtDataDoCadastro == null) {
			txtDataDoCadastro = new JTextField();
			txtDataDoCadastro.setBounds(339, 38, 284, 20);
			txtDataDoCadastro.setColumns(10);
			txtDataDoCadastro.setEditable(false);
			txtDataDoCadastro.setText(clie.getDataCadastro()+"");
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
			textCodigo.setText("" + clie.getIdCliente());
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
}
