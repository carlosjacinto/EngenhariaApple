package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Funcionario;
import model.FuncionarioDAO;
import model.Validacao;
import view.CadastroFuncionarioView;

public class InputListenerCadastroFuncionario implements MouseListener {
	private CadastroFuncionarioView cadastroFuncionario;
	private JFileChooser jFileChooser;
	private ImageIcon imageIcon;
	private Funcionario func;
	private FuncionarioDAO funcDAO = new FuncionarioDAO();
	private Validacao valida = new Validacao();

	public InputListenerCadastroFuncionario(CadastroFuncionarioView cadastroFuncionario) {
		// TODO Auto-generated constructor stub
		this.cadastroFuncionario = cadastroFuncionario;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroFuncionario.getBtnCancelar()) {
			cadastroFuncionario.dispose();
		} else if ((e.getSource()) == cadastroFuncionario.getBtnGravar()) {
			System.out.println("Bot�o ok Clicado");
			capturarDadosFunc();
		} else if ((e.getSource()) == cadastroFuncionario.getbtnPesquisarImagem()) {
			getImagem();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub

	}

	public void capturarDadosFunc() {
		// TODO Auto-generated method stub
		if (!(cadastroFuncionario.getTextNome().getText().equals("")
				|| cadastroFuncionario.getTextSalario().getText().equals("")
				|| cadastroFuncionario.getTextComissao().getText().equals("")
				|| cadastroFuncionario.getTextCPF().getText().equals("")
				|| cadastroFuncionario.getTextTelefone().getText().equals("")
				|| cadastroFuncionario.getTextCelular().getText().equals("")
				|| cadastroFuncionario.getTextRua().getText().equals("")
				|| cadastroFuncionario.getTextNumero().getText().equals("")
				|| cadastroFuncionario.getTextBairro().getText().equals("")
				|| cadastroFuncionario.getTextCidade().getText().equals("")
				|| cadastroFuncionario.getTextCEP().getText().equals("")
				|| cadastroFuncionario.getTextCelular().getText().equals("")
				|| cadastroFuncionario.getTextRua().getText().equals("")
				|| cadastroFuncionario.getTextDataNascimento().getText().equals(""))) {
			if (cadastroFuncionario.getTextPassword1().getText()
					.equals(cadastroFuncionario.getTextPassword2().getText())
					&& !cadastroFuncionario.getTextPassword1().getText().equals("")) {

				try {
					getFunc().setSalario(Double.parseDouble(cadastroFuncionario.getTextSalario().getText()));
					getFunc().setComissao(Double.parseDouble(cadastroFuncionario.getTextComissao().getText()));
					getFunc().setTelefone(Long.parseLong(cadastroFuncionario.getTextTelefone().getText()));
					getFunc().setCelular(Long.parseLong(cadastroFuncionario.getTextCelular().getText()));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Valor Inv�lido!", null,
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				getFunc().setCpf(cadastroFuncionario.getTextCPF().getText());
				getFunc().setNome(cadastroFuncionario.getTextNome().getText());
				getFunc().setRua(cadastroFuncionario.getTextRua().getText());
				getFunc().setComplemento(cadastroFuncionario.getTextComplemento().getText());
				getFunc().setNumero(cadastroFuncionario.getTextNumero().getText());
				getFunc().setBairro(cadastroFuncionario.getTextBairro().getText());
				getFunc().setCidade(cadastroFuncionario.getTextCidade().getText());
				getFunc().setCep(cadastroFuncionario.getTextCEP().getText());
				getFunc().setDataAdmissao(new Date(System.currentTimeMillis()));
				getFunc().setSenha(cadastroFuncionario.getTextPassword1().getText());

				
		        getFunc().setDataNascimento(cadastroFuncionario.getTextDataNascimento().getText());
		       
				
				if (cadastroFuncionario.getChckbxAdministrador().isSelected())
					getFunc().setAdministrador(true);
				else
					getFunc().setAdministrador(false);

				if (funcDAO.verificaCPF(func.getCpf()))
					JOptionPane.showMessageDialog(null, "CPF j� se encontra cadastrado em nosso sistema!", null,
							JOptionPane.ERROR_MESSAGE);
				else if (!valida.isCPF(func.getCpf()))
					JOptionPane.showMessageDialog(null, "CPF inv�lido!", null, JOptionPane.ERROR_MESSAGE);
				else {
					if (!(imageIcon == null)) {

						funcDAO.gravarFuncionario(func);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						cadastroFuncionario.dispose();
					}

					else {
						int result = JOptionPane.showConfirmDialog(null, "Deseja Realizar o Cadastro sem Imagem?",
								"Cadastrar", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {

							funcDAO.gravarFuncionario(func);
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Sucesso",
									JOptionPane.DEFAULT_OPTION);
							cadastroFuncionario.dispose();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Senhas n�o Conferem!", null, JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Valores em Branco!", null, JOptionPane.WARNING_MESSAGE);

	}

	public Funcionario getFunc() {
		if (func == null) {
			func = new Funcionario();
		}
		return func;
	}

	public void getImagem() {
		getJFileChooser().showOpenDialog(null);
		if (!(getJFileChooser().getSelectedFile() == null)) {
			getFunc().setFoto(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon = new ImageIcon(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(275, 281, 100));
			cadastroFuncionario.getLblFoto().setIcon(imageIcon);
			System.out.println(getFunc().getFoto());
		}

	}

	public JFileChooser getJFileChooser() {
		if (jFileChooser == null) {
			jFileChooser = new JFileChooser();
			jFileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					return (f.getName().endsWith(".jpg") ||f.getName().endsWith(".JPG") || f.getName().endsWith(".png"))|| f.getName().endsWith(".PNG")|| f.isDirectory();
				}

				public String getDescription() {
					return "Arquivo de Imagem(*.jpg, *.png)";
				}
			});
		}

		return jFileChooser;
	}

}