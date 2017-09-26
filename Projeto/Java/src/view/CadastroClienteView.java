package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import control.InputListenerCadastroCliente;

public class CadastroClienteView extends JDialog {

	private static final long serialVersionUID = -6915111385220052899L;
	InputListenerCadastroCliente listener;
	private JPanel contentPanel;
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
	private JTextField textDataNascimento;
	private JLabel lblTelefone;
	private JTextField textTelefone;
	private JLabel lblCelular;
	private JTextField textCelular;
	private JLabel lblFoto;
	

	public static void main(String[] args) {
		try {
			CadastroClienteView dialog = new CadastroClienteView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CadastroClienteView() {
		listener = new InputListenerCadastroCliente(this);
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

		}
		return contentPanel;
	}

	public JTextField getTextNome() {
		if (textNome == null) {
			textNome = new JTextField();
			textNome.setBounds(30, 95, 281, 20);
			textNome.setColumns(10);
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
		}
		return textRua;
	}

	public JTextField getTextBairro() {
		if (textBairro == null) {
			textBairro = new JTextField();
			textBairro.setBounds(30, 248, 281, 20);
			textBairro.setColumns(10);
		}
		return textBairro;
	}

	public JTextField getTextCidade() {
		if (textCidade == null) {
			textCidade = new JTextField();
			textCidade.setBounds(30, 299, 281, 20);
			textCidade.setColumns(10);
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

	public JTextField getTextDataNascimento() {
		if (textDataNascimento == null) {
			textDataNascimento = new JTextField();
			textDataNascimento.setBounds(30, 454, 281, 20);
			textDataNascimento.setColumns(10);
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
		}
		return textCelular;
	}

	public JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBounds(348, 195, 275, 281);
			lblFoto.setIcon(new ImageIcon("Interno/default-avatar.png"));
		}
		return lblFoto;
	}


}
