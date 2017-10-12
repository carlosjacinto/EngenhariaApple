package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Produto;
import model.ProdutoDAO;
import view.EditarProdutoView;

public class InputListenerEditarProduto implements MouseListener, ChangeListener{
	private EditarProdutoView edicaoProduto;
	private Produto produto;
	private JFileChooser jFileChooser;
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private ImageIcon imageIcon;
	public InputListenerEditarProduto(EditarProdutoView edicaoProduto) {
		// TODO Auto-generated constructor stub
		this.edicaoProduto = edicaoProduto;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == edicaoProduto.getBtnCancelar()) {
			edicaoProduto.dispose();
		}else if ((e.getSource()) == edicaoProduto.getBtnGravar()) {
			System.out.println("Botão ok Clicado");
			capturaDados();
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
	
	public void capturaDados() {
		if(!(edicaoProduto.getTextCodigo().equals("") || edicaoProduto.getTextNome().equals("")
				|| edicaoProduto.getTextPrecoCompra().equals("") || edicaoProduto.getTextPrecoVenda().equals("")
				|| edicaoProduto.getTextDescricao().equals("") || edicaoProduto.getTextDataCadastro().equals("")
				)) {
			
			getProduto().setNomeProduto(edicaoProduto.getTextNome().getText());
			getProduto().setDescricaoProduto(edicaoProduto.getTextDescricao().getText());
			getProduto().setIdProduto(Integer.parseInt(edicaoProduto.getTextCodigo().getText()));
			getProduto().setDataCadastroProduto(Date.valueOf(edicaoProduto.getTextDataCadastro().getText()));
			getProduto().setPercentualLucro(Integer.parseInt(edicaoProduto.getSpinner().getValue().toString()));
			getProduto().setPrecoCompraProduto(Float.parseFloat(edicaoProduto.getTextPrecoCompra().getText()));
			getProduto().setPrecoVendaProduto(Float.parseFloat(edicaoProduto.getTextPrecoVenda().getText()));
			getProduto().setPercentualLucro(Integer.parseInt(edicaoProduto.getSpinner().getValue().toString()));
			
			if (!(imageIcon == null)) {

				produtoDAO.editarProduto(produto);
				JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				edicaoProduto.dispose();
			}

			else {
				int result = JOptionPane.showConfirmDialog(null,
						"Deseja realizar a edição sem adicionar uma imagem?", "Cadastrar",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {

					produtoDAO.editarProduto(produto);
					JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Sucesso",
							JOptionPane.DEFAULT_OPTION);
					edicaoProduto.dispose();
				}
			}
			
		}
	}
	
	public Produto getProduto() {
		if(produto == null) {
			produto = new Produto();
		}
		return produto;
	}



	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == edicaoProduto.getSpinner()) {
			edicaoProduto.getTextPrecoVenda().setText((
					(1+(Float.parseFloat(edicaoProduto.getSpinner().getValue().toString())/100))
					*Float.parseFloat(edicaoProduto.getTextPrecoCompra().getText()))+"");
		}
		
	}
	
	public void getImagem() {
		getJFileChooser().showOpenDialog(null);
		if (!(getJFileChooser().getSelectedFile() == null)) {
			getProduto().setFotoProduto(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon = new ImageIcon(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(275, 281, 100));
			edicaoProduto.getLblFoto().setIcon(imageIcon);
			System.out.println(getProduto().getFotoProduto());
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
