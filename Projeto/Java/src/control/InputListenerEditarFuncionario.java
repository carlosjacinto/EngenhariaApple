package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Funcionario;
import model.FuncionarioDAO;
import view.EditarFuncionarioView;

public class InputListenerEditarFuncionario implements MouseListener {
	private EditarFuncionarioView edicaoFuncionario;
	private JFileChooser jFileChooser;
	private ImageIcon imageIcon;
	private Funcionario func;
	private FuncionarioDAO funcDAO = new FuncionarioDAO();

	public InputListenerEditarFuncionario(EditarFuncionarioView edicaoFuncionario) {
		// TODO Auto-generated constructor stub
		this.edicaoFuncionario = edicaoFuncionario;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == edicaoFuncionario.getBtnCancelar()) {
			edicaoFuncionario.dispose();
		} else if ((e.getSource()) == edicaoFuncionario.getBtnGravar()) {
			System.out.println("Botão ok Clicado");
			capturarDadosFunc();
		} else if ((e.getSource()) == edicaoFuncionario.getbtnPesquisarImagem()) {
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
		if (!(edicaoFuncionario.getTextNome().getText().equals("")
				|| edicaoFuncionario.getTextSalario().getText().equals("")
				|| edicaoFuncionario.getTextComissao().getText().equals("")
				|| edicaoFuncionario.getTextCPF().getText().equals("")
				|| edicaoFuncionario.getTextTelefone().getText().equals("")
				|| edicaoFuncionario.getTextCelular().getText().equals("")
				|| edicaoFuncionario.getTextRua().getText().equals("")
				|| edicaoFuncionario.getTextComplemento().getText().equals("")
				|| edicaoFuncionario.getTextNumero().getText().equals("")
				|| edicaoFuncionario.getTextBairro().getText().equals("")
				|| edicaoFuncionario.getTextCidade().getText().equals("")
				|| edicaoFuncionario.getTextCEP().getText().equals("")
				|| edicaoFuncionario.getTextCelular().getText().equals("")
				|| edicaoFuncionario.getTextRua().getText().equals("")
				|| edicaoFuncionario.getTextDataNascimento().getText().equals(""))) {
			if (edicaoFuncionario.getTextPassword1().getText().equals(edicaoFuncionario.getTextPassword2().getText())
					&& !edicaoFuncionario.getTextPassword1().getText().equals("")) {
				if (!(imageIcon == null)) {
					getFunc().setNome(edicaoFuncionario.getTextNome().getText());
					System.out.println(getFunc().getNome());

					try {

						getFunc().setSalario(Double.parseDouble(edicaoFuncionario.getTextSalario().getText()));
						System.out.println(getFunc().getSalario());

						getFunc().setComissao(Double.parseDouble(edicaoFuncionario.getTextComissao().getText()));
						System.out.println(getFunc().getComissao());

						getFunc().setCpf(edicaoFuncionario.getTextCPF().getText());
						System.out.println(getFunc().getCpf());

						getFunc().setTelefone(Long.parseLong(edicaoFuncionario.getTextTelefone().getText()));
						System.out.println(getFunc().getTelefone());

						getFunc().setCelular(Long.parseLong(edicaoFuncionario.getTextCelular().getText()));
						System.out.println(getFunc().getCelular());
					} catch (NumberFormatException e) {
						// TODO: handle exception
						System.out.println("Valor Errado!");
					}

					getFunc().setRua(edicaoFuncionario.getTextRua().getText());
					System.out.println(getFunc().getRua());

					getFunc().setComplemento(edicaoFuncionario.getTextComplemento().getText());
					System.out.println(getFunc().getComplemento());

					getFunc().setNumero(edicaoFuncionario.getTextNumero().getText());
					System.out.println(getFunc().getNumero());

					getFunc().setBairro(edicaoFuncionario.getTextBairro().getText());
					System.out.println(getFunc().getBairro());

					getFunc().setCidade(edicaoFuncionario.getTextCidade().getText());
					System.out.println(getFunc().getCidade());

					getFunc().setCep(edicaoFuncionario.getTextCEP().getText());
					System.out.println(getFunc().getCep());

					getFunc().setDataAdmissao();
					System.out.println(getFunc().getDataAdmissao());

					// getFunc().setDataNascimento(edicaoFuncionario.getTextDataNascimento().getText());
					getFunc().setDataNascimento(getFunc().getDataAdmissao());
					System.out.println(getFunc().getDataNascimento());

					if (edicaoFuncionario.getChckbxAdministrador().isSelected())
						getFunc().setAdministrador(true);
					else
						getFunc().setAdministrador(false);

					getFunc().setSenha(edicaoFuncionario.getTextPassword1().getText());

					funcDAO.gravarFuncionario(func);

				} else {
					int result = JOptionPane.showConfirmDialog(null, "Deseja Realizar a Edição sem adicionar Imagem?",
							"Cadastrar", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						funcDAO.gravarFuncionario(func);
						// System.out.println(func.get);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Senhas não Conferem!", null, JOptionPane.ERROR_MESSAGE);
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
			edicaoFuncionario.getLblFoto().setIcon(imageIcon);
			System.out.println(getFunc().getFoto());
		}

	}

	public JFileChooser getJFileChooser() {
		if (jFileChooser == null) {
			jFileChooser = new JFileChooser();
			jFileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					return (f.getName().endsWith(".jpg") || f.getName().endsWith(".PNG")) || f.isDirectory();
				}

				public String getDescription() {
					return "Arquivo de Imagem(*.jpg, *.png)";
				}
			});
		}

		return jFileChooser;
	}

}
