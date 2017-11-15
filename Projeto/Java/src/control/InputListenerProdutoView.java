package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import model.ProdutoDAO;
import view.CadastroProdutoView;
import view.EditarProdutoView;
import view.ProdutoView;

public class InputListenerProdutoView implements MouseListener, WindowListener {
	ProdutoView produtoView;
	private ProdutoDAO produtoDAO = ProdutoDAO.getInstance();

	public InputListenerProdutoView(ProdutoView produtoView) {

		this.produtoView = produtoView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		if (e.getSource() == produtoView.getBuscarButton()) {
			mudarTabela();
		}else if ((e.getSource()) == produtoView.getbtnNovoProduto()) {
			new CadastroProdutoView().setVisible(true);
			produtoView.getTextBusca().setText("");
			mudarTabela();
		}else if(e.getSource() == produtoView.buscarProdutos()) {
			
		}else if(e.getSource() == produtoView.getBtnEditarProduto()) {
			int i = produtoView.buscarProdutos().getSelectedRow();
			if(i!=-1) {
				Produto produto = produtoDAO.RetornaProduto(Integer.parseInt(
						produtoView.buscarProdutos().getValueAt(
								produtoView.buscarProdutos().getSelectedRow(), 0).toString()));
				new EditarProdutoView(produto).setVisible(true);
				produtoView.getTextBusca().setText("");
				mudarTabela();
			}else JOptionPane.showMessageDialog(null,  "Selecione um Produto!", null,JOptionPane.WARNING_MESSAGE);
		}else if(e.getSource() == produtoView.getBtnExcluirProduto()) {
			confirmarExclusao();
		}else if(e.getSource() == produtoView.getBtnLimparBusca()) {
			produtoView.getTextBusca().setText("");
			mudarTabela();
		}
	}
	
	public void mudarTabela() {
		String[][] funcs = produtoDAO.listarProdutos(produtoView.getTextBusca().getText());
		String[] colunas = {"id","Nome", "Preço de Venda(R$)", "Preço de Compra(R$)", "Quantidade"};
		
		DefaultTableModel model = new DefaultTableModel(funcs,colunas) {
			 /**
			 * 
			 */
			private static final long serialVersionUID = -7018342759131611914L;
			boolean[] canEdit = new boolean []{  
			            false, false, false, false,false,false
			        };  
			        @Override  
			        public boolean isCellEditable(int rowIndex, int columnIndex) {  
			            return canEdit [columnIndex];  
			        }
		};
		produtoView.buscarProdutos().setModel(model);
		produtoView.repaint();
		produtoView.revalidate();
		produtoView.setBuscaAT1(produtoView.getTextBusca().getText());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		if(e.getSource() == produtoView.getbtnNovoProduto()) {
			produtoView.getbtnNovoProduto().setIcon(new ImageIcon("Interno/newProd2x.png"));
		}else if(e.getSource() == produtoView.getBtnExcluirProduto()) {
			produtoView.getBtnExcluirProduto().setIcon(new ImageIcon("Interno/deleteProd2x.png"));
		}else if(e.getSource() == produtoView.getBtnEditarProduto()) {
			produtoView.getBtnEditarProduto().setIcon(new ImageIcon("Interno/editProd2x.png"));
		}else if(e.getSource() == produtoView.getBuscarButton()) {
			produtoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		}else if(e.getSource() == produtoView.getBtnLimparBusca()) {
			produtoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
	
		if(e.getSource() == produtoView.getbtnNovoProduto()) {
			produtoView.getbtnNovoProduto().setIcon(new ImageIcon("Interno/newProd.png"));
		}else if(e.getSource() == produtoView.getBtnExcluirProduto()) {
			produtoView.getBtnExcluirProduto().setIcon(new ImageIcon("Interno/deleteProd.png"));
		}else if(e.getSource() == produtoView.getBtnEditarProduto()) {
			produtoView.getBtnEditarProduto().setIcon(new ImageIcon("Interno/editProd.png"));
		}else if(e.getSource() == produtoView.getBuscarButton()) {
			produtoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		}else if(e.getSource() == produtoView.getBtnLimparBusca()) {
			produtoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	
		if(e.getSource() == produtoView.getbtnNovoProduto()) {
			produtoView.getbtnNovoProduto().setIcon(new ImageIcon("Interno/newProd.png"));
		}else if(e.getSource() == produtoView.getBtnExcluirProduto()) {
			produtoView.getBtnExcluirProduto().setIcon(new ImageIcon("Interno/deleteProd.png"));
		}else if(e.getSource() == produtoView.getBtnEditarProduto()) {
			produtoView.getBtnEditarProduto().setIcon(new ImageIcon("Interno/editProd.png"));
		}else if(e.getSource() == produtoView.getBuscarButton()) {
			produtoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		}else if(e.getSource() == produtoView.getBtnLimparBusca()) {
			produtoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
		if(e.getSource() == produtoView.getbtnNovoProduto()) {
			produtoView.getbtnNovoProduto().setIcon(new ImageIcon("Interno/newProd2x.png"));
		}else if(e.getSource() == produtoView.getBtnExcluirProduto()) {
			produtoView.getBtnExcluirProduto().setIcon(new ImageIcon("Interno/deleteProd2x.png"));
		}else if(e.getSource() == produtoView.getBtnEditarProduto()) {
			produtoView.getBtnEditarProduto().setIcon(new ImageIcon("Interno/editProd2x.png"));
		}else if(e.getSource() == produtoView.getBuscarButton()) {
			produtoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		}else if(e.getSource() == produtoView.getBtnLimparBusca()) {
			produtoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
	
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		produtoView.getT1().interrupt();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
	
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
	
		
	}
	
	public void confirmarExclusao() {
	
		int i = produtoView.buscarProdutos().getSelectedRow();
		if(i!=-1) {
			if(produtoDAO.PermitirExclusaoProduto(Integer.parseInt(produtoView.buscarProdutos().getValueAt(i, 0).toString()))) {
			
			int result = JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir "
						+produtoView.buscarProdutos().getValueAt(i, 1)+"?",
					"Excluir", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int sucesso = produtoDAO.excluirProduto(Integer.parseInt(produtoView.buscarProdutos().getValueAt(i, 0).toString()));
				
				if(sucesso == 1)JOptionPane.showMessageDialog(null, "Produto Excluido!", null,
						JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}else JOptionPane.showMessageDialog(null, "Produto possui Compra e/ou Venda!", "Não foi possivel deletar",
				JOptionPane.WARNING_MESSAGE);
		}else JOptionPane.showMessageDialog(null, "Selecione Um Produto!", null,
				JOptionPane.WARNING_MESSAGE);
	}

}
