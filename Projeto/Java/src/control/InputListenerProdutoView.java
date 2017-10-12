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
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public InputListenerProdutoView(ProdutoView produtoView) {
		// TODO Auto-generated constructor stub
		this.produtoView = produtoView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == produtoView.getBuscarButton()) {
			mudarTabela();
		}else if ((e.getSource()) == produtoView.getbtnNovoProduto()) {
			new CadastroProdutoView().setVisible(true);
		}else if(e.getSource() == produtoView.getTableProduto()) {
			
		}else if(e.getSource() == produtoView.getBtnEditarProduto()) {
			int i = produtoView.getTableProduto().getSelectedRow();
			if(i!=-1) {
				Produto produto = produtoDAO.RetornaProduto(Integer.parseInt(
						produtoView.getTableProduto().getValueAt(
								produtoView.getTableProduto().getSelectedRow(), 0).toString()));
				new EditarProdutoView(produto).setVisible(true);
			}else JOptionPane.showMessageDialog(null,  "Selecione um Produto!", null,JOptionPane.WARNING_MESSAGE);
		}else if(e.getSource() == produtoView.getBtnExcluirProduto()) {
			confirmarExclusao();
		}else if(e.getSource() == produtoView.getBtnLimparBusca()) {
			produtoView.getTextBusca().setText("");
			mudarTabela();
		}
	}
	
	public void mudarTabela() {
		String[][] funcs = produtoDAO.listaProdutoArray(produtoView.getTextBusca().getText());
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
		produtoView.getTableProduto().setModel(model);
		produtoView.repaint();
		produtoView.revalidate();
		produtoView.setBuscaAT1(produtoView.getTextBusca().getText());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		produtoView.getT1().interrupt();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void confirmarExclusao() {
		// TODO Auto-generated method stub
		int i = produtoView.getTableProduto().getSelectedRow();
		if(i!=-1) {
			int result = JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir "
						+produtoView.getTableProduto().getValueAt(i, 1)+"?",
					"Excluir", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int sucesso = produtoDAO.excluirProduto(Integer.parseInt(produtoView.getTableProduto().getValueAt(i, 0).toString()));
				
				if(sucesso == 1)JOptionPane.showMessageDialog(null, "Produto Excluido!", null,
						JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}else JOptionPane.showMessageDialog(null, "Selecione Um Produto!", null,
				JOptionPane.WARNING_MESSAGE);
	}

}
