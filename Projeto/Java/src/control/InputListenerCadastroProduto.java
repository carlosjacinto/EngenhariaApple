package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Produto;
import model.ProdutoDAO;
import view.CadastroProdutoView;

public class InputListenerCadastroProduto implements MouseListener{
	private CadastroProdutoView cadastroProduto;
	private ImageIcon imageIcon;
	private Produto produto;
	private ProdutoDAO produtoDAO;
	public InputListenerCadastroProduto(CadastroProdutoView cadastroProduto) {
		// TODO Auto-generated constructor stub
		this.cadastroProduto = cadastroProduto;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroProduto.getBtnCancelar()) {
			cadastroProduto.dispose();
		}else if ((e.getSource()) == cadastroProduto.getBtnGravar()) {
			System.out.println("Bot�o ok Clicado");
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
		if(!(cadastroProduto.getTextNome().equals("")
				|| cadastroProduto.getTextPrecoVenda().equals("")
				|| cadastroProduto.getTextDescricao().equals(""))) {
			
				try {
					getProduto().setPrecoVendaProduto(Float.parseFloat(cadastroProduto.getTextPrecoVenda().getText()));
				} catch (NumberFormatException e) {
					System.out.println("Valor Errado!");
				}

				getProduto().setNomeProduto(cadastroProduto.getTextNome().getText());
				getProduto().setDescricaoProduto(cadastroProduto.getTextDescricao().getText());
				
				getProduto().setDataCadastroProduto(new Date(System.currentTimeMillis()));				

				if (produtoDAO.verificaNome(produto.getNomeProduto()))
					JOptionPane.showMessageDialog(null, "Nome do produto j� se encontra cadastrado em nosso sistema!", null,
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
}
