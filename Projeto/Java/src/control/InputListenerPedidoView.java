package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Pedido;
import model.PedidoDAO;
import view.CadastroPedidoView;
import view.EditarPedidoView;
import view.PedidoView;

public class InputListenerPedidoView implements MouseListener, WindowListener {
	PedidoView pedidoView;
	
	private PedidoDAO pedioDAO = new PedidoDAO();

	public InputListenerPedidoView(PedidoView pedidoView) {

		this.pedidoView = pedidoView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == pedidoView.getBuscarButton()) {
			mudarTabela();
		} else if ((e.getSource()) == pedidoView.getbtnNovoPedido()) {
			CadastroPedidoView cadastroPedidoView;
			cadastroPedidoView = new CadastroPedidoView();
			cadastroPedidoView.setVisible(true);
			pedidoView.getTextBusca().setText("");
			mudarTabela();
		} else if (e.getSource() == pedidoView.getTablePedido()) {

		} else if (e.getSource() == pedidoView.getbtnExcluirPedido()) {
			confirmarExclusao();
		} else if (e.getSource() == pedidoView.getBtnEditarPedido()) {
			if (pedidoView.getTablePedido().getSelectedRow() != -1) {
				String id = (pedidoView.getTablePedido().getModel()
						.getValueAt(pedidoView.getTablePedido().getSelectedRow(), 0).toString());
				Pedido pedido = pedioDAO.RetornaPedido(Integer.parseInt(id));
				new EditarPedidoView(pedido).setVisible(true);
				pedidoView.getTextBusca().setText("");
				mudarTabela();
			} else
				JOptionPane.showMessageDialog(null, "Selecione Uma Nota de Entrada!", null, JOptionPane.WARNING_MESSAGE);
		}else if(e.getSource() == pedidoView.getBtnLimparBusca()) {
			pedidoView.getTextBusca().setText("");
			mudarTabela();
		}else if(e.getSource() == pedidoView.getbtnExcluirPedido()) {
			confirmarExclusao();
		}
	}
	
	

	public void mudarTabela() {
		String[][] pedidos = pedioDAO.listaPedidoArray(pedidoView.getTextBusca().getText());
		String[] colunas = { "Número", "Nome", "CPF", "Total", "Funcionário", "Data do Cadastro" };

		DefaultTableModel model = new DefaultTableModel(pedidos, colunas) {
			/**
			* 
			*/
			private static final long serialVersionUID = -7018342759131611914L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		pedidoView.getTablePedido().setModel(model);
		pedidoView.repaint();
		pedidoView.revalidate();
		pedidoView.setBuscaAT1(pedidoView.getTextBusca().getText());
	}

	public void confirmarExclusao() {
		
		int i = pedidoView.getTablePedido().getSelectedRow();
		if (i != -1) {
			int result = JOptionPane.showConfirmDialog(null,
					"Tem certeza que quer excluir " + pedidoView.getTablePedido().getValueAt(i, 1) + "?",
					"Excluir", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				int sucesso = pedioDAO.excluirPedido(
						Integer.parseInt(pedidoView.getTablePedido().getValueAt(i, 0).toString()));

				if (sucesso == 1)
					JOptionPane.showMessageDialog(null, "Pedido Excluido!", null,
							JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
							JOptionPane.INFORMATION_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Selecione Um Pedido!", null, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() == pedidoView.getbtnExcluirPedido()) {
			pedidoView.getbtnExcluirPedido().setIcon(new ImageIcon("Interno/deleteNota2x.png"));
		} else if (e.getSource() == pedidoView.getbtnNovoPedido()) {
			pedidoView.getbtnNovoPedido().setIcon(new ImageIcon("Interno/newNota2x.png"));
		} else if (e.getSource() == pedidoView.getBuscarButton()) {
			pedidoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		} else if (e.getSource() == pedidoView.getBtnEditarPedido()) {
			pedidoView.getBtnEditarPedido().setIcon(new ImageIcon("Interno/editNota2x.png"));
		}else if(e.getSource() == pedidoView.getBtnLimparBusca()) {
			pedidoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == pedidoView.getbtnExcluirPedido()) {
			pedidoView.getbtnExcluirPedido().setIcon(new ImageIcon("Interno/deleteNota.png"));
		} else if (e.getSource() == pedidoView.getbtnNovoPedido()) {
			pedidoView.getbtnNovoPedido().setIcon(new ImageIcon("Interno/newNota.png"));
		} else if (e.getSource() == pedidoView.getBuscarButton()) {
			pedidoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == pedidoView.getBtnEditarPedido()) {
			pedidoView.getBtnEditarPedido().setIcon(new ImageIcon("Interno/editNota.png"));
		}else if(e.getSource() == pedidoView.getBtnLimparBusca()) {
			pedidoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (e.getSource() == pedidoView.getbtnExcluirPedido()) {
			pedidoView.getbtnExcluirPedido().setIcon(new ImageIcon("Interno/deleteNota.png"));
		} else if (e.getSource() == pedidoView.getbtnNovoPedido()) {
			pedidoView.getbtnNovoPedido().setIcon(new ImageIcon("Interno/newNota.png"));
		} else if (e.getSource() == pedidoView.getBuscarButton()) {
			pedidoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == pedidoView.getBtnEditarPedido()) {
			pedidoView.getBtnEditarPedido().setIcon(new ImageIcon("Interno/editNota.png"));
		}else if(e.getSource() == pedidoView.getBtnLimparBusca()) {
			pedidoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (e.getSource() == pedidoView.getbtnExcluirPedido()) {
			pedidoView.getbtnExcluirPedido().setIcon(new ImageIcon("Interno/deleteNota2x.png"));
		} else if (e.getSource() == pedidoView.getbtnNovoPedido()) {
			pedidoView.getbtnNovoPedido().setIcon(new ImageIcon("Interno/newNota2x.png"));
		} else if (e.getSource() == pedidoView.getBuscarButton()) {
			pedidoView.getBuscarButton().setIcon(new ImageIcon("Interno/search-iconNota2x.png"));
		} else if (e.getSource() == pedidoView.getBtnEditarPedido()) {
			pedidoView.getBtnEditarPedido().setIcon(new ImageIcon("Interno/editNota2x.png"));
		}else if(e.getSource() == pedidoView.getBtnLimparBusca()) {
			pedidoView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
		

	}

	@Override
	public void windowClosed(WindowEvent e) {
		

	}

	@Override
	public void windowClosing(WindowEvent e) {
		pedidoView.getT1().interrupt();
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

}
