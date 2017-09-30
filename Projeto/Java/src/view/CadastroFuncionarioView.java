package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import control.InputListenerCadastroFuncionario;

public class CadastroFuncionarioView extends JDialog {

	
	private static final long serialVersionUID = -6915111385220052899L;
	InputListenerCadastroFuncionario listener;
	private JPanel contentPanel;
	private JPanel panelFoto;
	private JButton btnPesquisarImagem;
	private JLabel lblNome;
	private JTextField textNome;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JTextField textSalario;
	private JLabel lblSalario;
	private JLabel lblComissao;
	private JTextField textComissao;
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
	private JLabel lblSenha;
	private JPasswordField textPassword1;
	private JLabel lblConfirmarSenha;
	private JPasswordField textPassword2;
	private JCheckBox chckbxAdministrador;
	private JLabel lblFoto;

	public static void main(String[] args) {
		try {
			CadastroFuncionarioView dialog = new CadastroFuncionarioView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CadastroFuncionarioView() {
		listener = new InputListenerCadastroFuncionario(this);
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
		setBounds(100, 100, 649, 700);
		setContentPane(getContentPanel());
		setTitle("Cadastro de Funcionários");
		setLocationRelativeTo(null);
		setResizable(false);

	}

	public JButton getbtnPesquisarImagem() {
		if (btnPesquisarImagem == null) {
			btnPesquisarImagem = new JButton("Pesquisar Imagem");
			btnPesquisarImagem.setBounds(409, 550, 147, 23);
		}
		return btnPesquisarImagem;
	}
	
	public JPanel getpanelFoto() {
		if (panelFoto == null) {
			panelFoto = new JPanel();
			panelFoto.setBounds(346, 258, 275, 281);
			panelFoto.setLayout(null);
			panelFoto.add(getLblFoto());
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

			contentPanel.add(getpanelFoto());
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getTextSalario());
			contentPanel.add(getLblSalario());
			contentPanel.add(getLblComissao());
			contentPanel.add(getTextComissao());
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
			contentPanel.add(getLblSenha());
			contentPanel.add(getTextPassword1());
			contentPanel.add(getLblConfirmarSenha());
			contentPanel.add(getTextPassword2());
			
			
			contentPanel.add(getChckbxAdministrador());
			
			
		}
		return contentPanel;
	}

	
	public JTextField getTextNome() {
		if (textNome == null) {
			textNome = new JTextField();
			textNome.setBounds(28, 61, 281, 20);
			textNome.setColumns(10);
		}
		return textNome;
	}
	
	
	public JLabel getLblNome() {
		if (lblNome == null) {
			lblNome = new JLabel("Nome");
			lblNome.setBounds(28, 36, 46, 14);
		}
		return lblNome;
	}
	
	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(532, 593, 89, 23);
		}
		return btnCancelar;
	}
	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(433, 593, 89, 23);
		}
		return btnGravar;
	}
	
	public JTextField getTextSalario() {
		if (textSalario == null) {
			textSalario = new JTextField();
			textSalario.setBounds(28, 118, 281, 20);
			textSalario.setColumns(10);
		}
		return textSalario;
	}
	public JLabel getLblSalario() {
		if (lblSalario == null) {
			lblSalario = new JLabel("Sal\u00E1rio (R$)");
			lblSalario.setBounds(28, 92, 281, 14);
		}
		return lblSalario;
	}
	
	
	public JLabel getLblComissao() {
		if (lblComissao == null) {
			lblComissao = new JLabel("Comiss\u00E3o (%)");
			lblComissao.setBounds(28, 149, 281, 14);
		}
		return lblComissao;
	}
	public JTextField getTextComissao() {
		if (textComissao == null) {
			textComissao = new JTextField();
			textComissao.setBounds(28, 174, 281, 20);
			textComissao.setColumns(10);
		}
		return textComissao;
	}
	
	public JLabel getLblCPF() {
		if (lblCPF == null) {
			lblCPF = new JLabel("CPF");
			lblCPF.setBounds(28, 208, 281, 14);
		}
		return lblCPF;
	}
	public JTextField getTextCPF() {
		if (textCPF == null) {
			textCPF = new JTextField();
			textCPF.setBounds(28, 227, 281, 20);
			textCPF.setColumns(10);
		}
		return textCPF;
	}
	
	
	
	public JLabel getLblRua() {
		if (lblRua == null) {
			lblRua = new JLabel("Rua");
			lblRua.setBounds(28, 258, 281, 14);
		}
		return lblRua;
	}
	public JTextField getTextRua() {
		if (textRua == null) {
			textRua = new JTextField();
			textRua.setBounds(28, 276, 204, 20);
			textRua.setColumns(10);
		}
		return textRua;
	}
	public JTextField getTextBairro() {
		if (textBairro == null) {
			textBairro = new JTextField();
			textBairro.setBounds(28, 329, 281, 20);
			textBairro.setColumns(10);
		}
		return textBairro;
	}
	public JTextField getTextCidade() {
		if (textCidade == null) {
			textCidade = new JTextField();
			textCidade.setBounds(28, 380, 281, 20);
			textCidade.setColumns(10);
		}
		return textCidade;
	}
	public JLabel getLblBairro() {
		if (lblBairro == null) {
			lblBairro = new JLabel("Bairro");
			lblBairro.setBounds(28, 307, 281, 14);
		}
		return lblBairro;
	}
	
	
	public JLabel getLblCidade() {
		if (lblCidade == null) {
			lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(28, 360, 281, 14);
		}
		return lblCidade;
	}
	public JLabel getLblNumero() {
		if (lblNumero == null) {
			lblNumero = new JLabel("Numero");
			lblNumero.setBounds(247, 258, 62, 14);
		}
		return lblNumero;
	}
	public JTextField getTextNumero() {
		if (textNumero == null) {
			textNumero = new JTextField();
			textNumero.setBounds(242, 276, 67, 20);
			textNumero.setColumns(10);
		}
		return textNumero;
	}
	public JLabel getLblComplemento() {
		if (lblComplemento == null) {
			lblComplemento = new JLabel("Complemento");
			lblComplemento.setBounds(28, 411, 281, 14);
		}
		return lblComplemento;
	}
	public JTextField getTextComplemento() {
		if (textComplemento == null) {
			textComplemento = new JTextField();
			textComplemento.setBounds(28, 436, 281, 20);
			textComplemento.setColumns(10);
		}
		return textComplemento;
	}
	public JLabel getLblCEP() {
		if (lblCEP == null) {
			lblCEP = new JLabel("CEP");
			lblCEP.setBounds(28, 467, 281, 14);
		}
		return lblCEP;
	}
	public JTextField getTextCEP() {
		if (textCEP == null) {
			textCEP = new JTextField();
			textCEP.setBounds(28, 483, 281, 20);
			textCEP.setColumns(10);
		}
		return textCEP;
	}
	public JLabel getLblDataNascimento() {
		if (lblDataNascimento == null) {
			lblDataNascimento = new JLabel("Data Nascimento");
			lblDataNascimento.setBounds(28, 514, 281, 14);
		}
		return lblDataNascimento;
	}
	public JTextField getTextDataNascimento() {
		if (textDataNascimento == null) {
			textDataNascimento = new JTextField();
			textDataNascimento.setBounds(28, 535, 281, 20);
			textDataNascimento.setColumns(10);
		}
		return textDataNascimento;
	}
	public JLabel getLblTelefone() {
		if (lblTelefone == null) {
			lblTelefone = new JLabel("Telefone");
			lblTelefone.setBounds(337, 36, 284, 14);
		}
		return lblTelefone;
	}
	public JTextField getTextTelefone() {
		if (textTelefone == null) {
			textTelefone = new JTextField();
			textTelefone.setBounds(337, 61, 284, 20);
			textTelefone.setColumns(10);
		}
		return textTelefone;
	}
	public JLabel getLblCelular() {
		if (lblCelular == null) {
			lblCelular = new JLabel("Celular");
			lblCelular.setBounds(337, 92, 284, 14);
		}
		return lblCelular;
	}
	
	public JTextField getTextCelular() {
		if (textCelular == null) {
			textCelular = new JTextField();
			textCelular.setBounds(337, 118, 284, 20);
			textCelular.setColumns(10);
		}
		return textCelular;
	}
	
	public JLabel getLblSenha() {
		if (lblSenha == null) {
			lblSenha = new JLabel("Senha");
			lblSenha.setBounds(337, 149, 284, 14);
		}
		return lblSenha;
	}
	
	public JTextField getTextPassword1() {
		if (textPassword1 == null) {
			textPassword1 = new JPasswordField();
			textPassword1.setBounds(337, 174, 284, 20);
		}
		return textPassword1;
	}
	
	public JLabel getLblConfirmarSenha() {
		if (lblConfirmarSenha == null) {
			lblConfirmarSenha = new JLabel("Confirmar Senha");
			lblConfirmarSenha.setBounds(337, 208, 284, 14);
			
		}
		return lblConfirmarSenha;
	}
	
	public JTextField getTextPassword2() {
		if (textPassword2 == null) {
			textPassword2 = new JPasswordField();
			textPassword2.setBounds(337, 227, 284, 20);
		}
		return textPassword2;
	}
	
	public JCheckBox getChckbxAdministrador() {
		if (chckbxAdministrador == null) {
			chckbxAdministrador = new JCheckBox("Administrador");
			chckbxAdministrador.setBounds(28, 581, 147, 23);
		}
		return chckbxAdministrador;
	}
	
	public JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBounds(0, 0, 275, 281);
			lblFoto.setIcon(new ImageIcon("Interno/default-avatar.png"));
		}
		return lblFoto;
	}
}
