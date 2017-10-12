package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Cliente;
import model.ClienteDAO;
import view.EditarClienteView;

public class InputListenerEditarCliente implements MouseListener {
	private EditarClienteView edicaoCliente;
	private JFileChooser jFileChooser;
	private ImageIcon imageIcon;
	private Cliente clie;
	private ClienteDAO clieDAO = new ClienteDAO();

	public InputListenerEditarCliente(EditarClienteView edicaoCliente) {
		// TODO Auto-generated constructor stub
		this.edicaoCliente = edicaoCliente;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == edicaoCliente.getBtnCancelar()) {
			edicaoCliente.dispose();
		} else if ((e.getSource()) == edicaoCliente.getBtnGravar()) {
			System.out.println("Botão grvar Clicado");
			capturarDadosCliente();
		} else if ((e.getSource()) == edicaoCliente.getbtnPesquisarImagem()) {
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

	public void capturarDadosCliente() {
		if (!(edicaoCliente.getTextNome().getText().equals("") || edicaoCliente.getTextCPF().getText().equals("")
				|| edicaoCliente.getTextTelefone().getText().equals("")
				|| edicaoCliente.getTextCelular().getText().equals("")
				|| edicaoCliente.getTextRua().getText().equals("") || edicaoCliente.getTextNumero().getText().equals("")
				|| edicaoCliente.getTextBairro().getText().equals("")
				|| edicaoCliente.getTextCidade().getText().equals("") || edicaoCliente.getTextCEP().getText().equals("")
				|| edicaoCliente.getTextCelular().getText().equals("")
				|| edicaoCliente.getTextRua().getText().equals("")
				|| edicaoCliente.getTextDataNascimento().getText().equals(""))) {

			getClie().setTelefone(edicaoCliente.getTextTelefone().getText());
			getClie().setCelular(edicaoCliente.getTextCelular().getText());
			getClie().setIdCliente(Integer.parseInt(edicaoCliente.getTextCodigo().getText()));
			getClie().setCpf(edicaoCliente.getTextCPF().getText());
			getClie().setNome(edicaoCliente.getTextNome().getText());
			getClie().setRua(edicaoCliente.getTextRua().getText());
			getClie().setComplemento(edicaoCliente.getTextComplemento().getText());
			getClie().setNumero(edicaoCliente.getTextNumero().getText());
			getClie().setBairro(edicaoCliente.getTextBairro().getText());
			getClie().setCidade(edicaoCliente.getTextCidade().getText());
			getClie().setCep(edicaoCliente.getTextCEP().getText());

			getClie().setDataNascimento(edicaoCliente.getTextDataNascimento().getText());

			if (!(imageIcon == null)) {

				if (clieDAO.editarCliente(clie)) {
					JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					edicaoCliente.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao editar", "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}

			else {
				int result = JOptionPane.showConfirmDialog(null, "Deseja realizar a edição sem alterar a imagem?",
						"Cadastrar", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {

					if (clieDAO.editarCliente(clie)) {
						JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						edicaoCliente.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao editar", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		} else
			JOptionPane.showMessageDialog(null, "Valores em Branco!", null, JOptionPane.WARNING_MESSAGE);

	}

	public Cliente getClie() {
		if (clie == null) {
			clie = new Cliente();
		}
		return clie;
	}

	public void getImagem() {
		getJFileChooser().showOpenDialog(null);
		if (!(getJFileChooser().getSelectedFile() == null)) {
			getClie().setFoto(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon = new ImageIcon(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(275, 281, 100));
			edicaoCliente.getLblFoto().setIcon(imageIcon);
			System.out.println(getClie().getFoto());
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
