package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Produto;
import model.ProdutoDAO;
import view.CadastroProdutoView;

public class InputListenerCadastroProduto implements MouseListener{
	private CadastroProdutoView cadastroProduto;
	private JFileChooser jFileChooser;
	private ImageIcon imageIcon;
	private Produto produto;
	private ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
	public InputListenerCadastroProduto(CadastroProdutoView cadastroProduto) {
		// TODO Auto-generated constructor stub
		this.cadastroProduto = cadastroProduto;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroProduto.getBtnCancelar()) {
			cadastroProduto.dispose();
		}else if ((e.getSource()) == cadastroProduto.getBtnGravar()) {
			System.out.println("Botão ok Clicado");
			capturaDadosProduto();
		}else if(e.getSource() == cadastroProduto.getbtnPesquisarImagem()){
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
	
	public void capturaDadosProduto() {
		if(!(cadastroProduto.getTextNome().equals("")
				|| cadastroProduto.getTextDescricao().equals(""))) {
			

				getProduto().setNomeProduto(cadastroProduto.getTextNome().getText());
				getProduto().setDescricaoProduto(cadastroProduto.getTextDescricao().getText());
				getProduto().setPercentualLucro(Integer.parseInt(cadastroProduto.getSpinner().getValue().toString()));
				getProduto().setDataCadastroProduto(new Date(System.currentTimeMillis()));				

				if (produtoDAO.verificaNome(produto.getNomeProduto()))
					JOptionPane.showMessageDialog(null, "Nome do produto já se encontra cadastrado em nosso sistema!", null,
							JOptionPane.ERROR_MESSAGE);
				else {
					if (!(imageIcon == null)) {

						produtoDAO.gravarProduto(produto);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Sucesso",
								JOptionPane.DEFAULT_OPTION);
						cadastroProduto.dispose();
					}

					else {
						int result = JOptionPane.showConfirmDialog(null, "Deseja Realizar o Cadastro sem Imagem?",
								"Cadastrar", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {

							produtoDAO.gravarProduto(produto);
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Sucesso",
									JOptionPane.DEFAULT_OPTION);
							cadastroProduto.dispose();
						}
					}
				}
		} else
			JOptionPane.showMessageDialog(null, "Valores em Branco!", null, JOptionPane.WARNING_MESSAGE);

	}
	
	public Produto getProduto() {
		if(produto == null) {
			produto = new Produto();
		}
		return produto;
	}
	public void getImagem() {
		getJFileChooser().showOpenDialog(null);
		if (!(getJFileChooser().getSelectedFile() == null)) {
			getProduto().setFotoProduto(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon = new ImageIcon(getJFileChooser().getSelectedFile().getAbsolutePath());
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(275, 281, 100));
			cadastroProduto.getLblFoto().setIcon(imageIcon);
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
