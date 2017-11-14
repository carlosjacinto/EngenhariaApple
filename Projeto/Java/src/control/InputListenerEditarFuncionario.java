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
			System.out.println("Botão grvar Clicado");
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
		if (!(edicaoFuncionario.getTextNome().getText().equals("")
				|| edicaoFuncionario.getTextSalario().getText().equals("")
				|| edicaoFuncionario.getTextComissao().getText().equals("")
				|| edicaoFuncionario.getTextTelefone().getText().equals("")
				|| edicaoFuncionario.getTextCelular().getText().equals("")
				|| edicaoFuncionario.getTextRua().getText().equals("")
				|| edicaoFuncionario.getTextNumero().getText().equals("")
				|| edicaoFuncionario.getTextBairro().getText().equals("")
				|| edicaoFuncionario.getTextCidade().getText().equals("")
				|| edicaoFuncionario.getTextCEP().getText().equals("")
				|| edicaoFuncionario.getTextDataNascimento().getText().equals(""))) {

			if (edicaoFuncionario.getTextPassword1().getText().equals(edicaoFuncionario.getTextPassword2().getText())
					&& !edicaoFuncionario.getTextPassword1().getText().equals("")) {

				try {
					getFunc().setSalario(Double.parseDouble(edicaoFuncionario.getTextSalario().getText()));
					getFunc().setComissao(Double.parseDouble(edicaoFuncionario.getTextComissao().getText()));
					} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Valor Inválido! (Salário ou Comissão)", null, JOptionPane.ERROR_MESSAGE);
					return;
				}
				getFunc().setIdFuncionario(Integer.parseInt(edicaoFuncionario.getTextCodigo().getText()));
				getFunc().setCpf(edicaoFuncionario.getTextCPF().getText());
				getFunc().setNome(edicaoFuncionario.getTextNome().getText());
				getFunc().setRua(edicaoFuncionario.getTextRua().getText());
				getFunc().setComplemento(edicaoFuncionario.getTextComplemento().getText());
				getFunc().setNumero(edicaoFuncionario.getTextNumero().getText());
				getFunc().setBairro(edicaoFuncionario.getTextBairro().getText());
				getFunc().setCidade(edicaoFuncionario.getTextCidade().getText());
				getFunc().setCep(edicaoFuncionario.getTextCEP().getText());
				getFunc().setSenha(edicaoFuncionario.getTextPassword1().getText());
				getFunc().setTelefone(edicaoFuncionario.getTextTelefone().getText());
				getFunc().setCelular(edicaoFuncionario.getTextCelular().getText());
			

				getFunc().setDataNascimento(edicaoFuncionario.getTextDataNascimento().getText());

				String s =edicaoFuncionario.getTextDataNascimento().getText();
				if ((Integer.parseInt(s.substring(0, 2))>31) 
						|| (Integer.parseInt(s.substring(3,5))>12) 
						|| (Integer.parseInt(s.substring(0, 2))<1) 
						||(Integer.parseInt(s.substring(3,5))<1)) {
					JOptionPane.showMessageDialog(null,
							"Valor(es) de data invalido(s).",
							"Erro ao preencher campo", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				if (edicaoFuncionario.getChckbxAdministrador().isSelected())
					getFunc().setAdministrador(true);
				else
					getFunc().setAdministrador(false);

				if (!(imageIcon == null)) {

					if (funcDAO.editarFuncionario(func)) {
						JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						edicaoFuncionario.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao editar", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}

				else {
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja realizar a edição sem alterar a imagem?", "Editar",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {

						if (funcDAO.editarFuncionario(func)) {
							JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
							edicaoFuncionario.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao editar", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			} else
				JOptionPane.showMessageDialog(null, "Senhas não Conferem!", null, JOptionPane.ERROR_MESSAGE);

		}
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
					return (f.getName().endsWith(".jpg") || f.getName().endsWith(".PNG"))
							|| f.getName().endsWith(".png") || f.getName().endsWith(".JPG") || f.isDirectory();
				}

				public String getDescription() {
					return "Arquivo de Imagem(*.jpg, *.png)";
				}
			});
		}

		return jFileChooser;
	}

}
