package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.ClienteDAO;
import model.ContaDAO;
import view.CadastroClienteView;
import view.ClienteView;
import view.ContaView;
import view.EditarClienteView;

public class InputListenerClienteView implements MouseListener, WindowListener {
	ClienteView clienteView;
	private ClienteDAO clieDAO = new ClienteDAO();
	private ContaDAO contaDAO = new ContaDAO();

	public InputListenerClienteView(ClienteView clienteView) {
		this.clienteView = clienteView;
	}


	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == clienteView.getBuscarButton()) {
			mudarTabela();
			//EditarClienteView edicaoClienteView = new EditarClienteView();
			//edicaoClienteView.setVisible(true);
		} else if ((e.getSource()) == clienteView.getbtnNovoCliente()) {
			CadastroClienteView cadastroClienteView;
			cadastroClienteView = new CadastroClienteView();
			cadastroClienteView.setVisible(true);
			clienteView.getTextBusca().setText("");
			mudarTabela();
		} else if (e.getSource() == clienteView.buscarClientes()) {
			//String id = (clienteView.getTableCliente().getModel().getValueAt(clienteView.getTableCliente().getSelectedRow(), 0).toString());
			//Cliente clie = clieDAO.RetornaCliente(Integer.parseInt(id));
			//new EditarClienteView(clie).setVisible(true);
		}else if(e.getSource() == clienteView.getbtnExcluirCliente()) {
			confirmarExclusao();
		}else if(e.getSource() == clienteView.getBtnEditarCliente()) {
			if(clienteView.buscarClientes().getSelectedRow()!=-1) {
				String id = (clienteView.buscarClientes().getModel().getValueAt(clienteView.buscarClientes().getSelectedRow(), 0).toString());
				Cliente clie = clieDAO.RetornaCliente(Integer.parseInt(id));
				new EditarClienteView(clie).setVisible(true);
				clienteView.getTextBusca().setText("");
				mudarTabela();
			}else JOptionPane.showMessageDialog(null, "Selecione Um Cliente!", null,
					JOptionPane.WARNING_MESSAGE);
		}else if(e.getSource() == clienteView.getBtnLimparBusca()) {
			clienteView.getTextBusca().setText("");
			mudarTabela();
		}else if(e.getSource() == clienteView.getBtnVisualizarConta()) {
			clienteView.getBtnVisualizarConta().setIcon(new ImageIcon("Interno/conta.png"));
			if(clienteView.buscarClientes().getSelectedRow()!=-1) {
				String id = (clienteView.buscarClientes().getModel().getValueAt(clienteView.buscarClientes().getSelectedRow(), 0).toString());
				Cliente clie = clieDAO.RetornaCliente(Integer.parseInt(id));
				ContaView contaView;
				contaView = new ContaView(clie);
				contaView.setVisible(true);
			}else JOptionPane.showMessageDialog(null, "Selecione Um Cliente!", null,
					JOptionPane.WARNING_MESSAGE);
			
			
			
		}
	}
	
	public void mudarTabela() {
		String[][] clies = clieDAO.listarClientes(clienteView.getTextBusca().getText());
		String[] colunas = {"id","Nome", "CPF", "Endereço", "Telefone","Nascimento"};
		
		DefaultTableModel model = new DefaultTableModel(clies,colunas) {
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
		clienteView.buscarClientes().setModel(model);
		clienteView.repaint();
		clienteView.revalidate();
		clienteView.setBuscaAT1(clienteView.getTextBusca().getText());
	}

	public void confirmarExclusao() {
		
		int i = clienteView.buscarClientes().getSelectedRow();
		if(i!=-1) {
			if(clieDAO.PermitirExclusaoCliente(Integer.parseInt(clienteView.buscarClientes().getValueAt(i, 0).toString()))){
				int result = JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir "
							+clienteView.buscarClientes().getValueAt(i, 1)+"?",
						"Excluir", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					int sucesso = contaDAO.excluirConta(Integer.parseInt(clienteView.buscarClientes().getValueAt(i, 0).toString()));
					if(sucesso == 1) {
					sucesso = clieDAO.excluirCliente(Integer.parseInt(clienteView.buscarClientes().getValueAt(i, 0).toString()));
					
					if(sucesso == 1)JOptionPane.showMessageDialog(null, "Cliente Excluido!", null,
							JOptionPane.INFORMATION_MESSAGE);
					else JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
							JOptionPane.INFORMATION_MESSAGE);
					}else JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}else JOptionPane.showMessageDialog(null, "Cliente possui pedido!", "Não foi possivel deletar",
					JOptionPane.WARNING_MESSAGE);
		}else JOptionPane.showMessageDialog(null, "Selecione Um Cliente!", null,
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == clienteView.getbtnExcluirCliente()) {
			clienteView.getbtnExcluirCliente().setIcon(new ImageIcon("Interno/delete2x.png"));
		}else if(e.getSource() == clienteView.getbtnNovoCliente()) {
			clienteView.getbtnNovoCliente().setIcon(new ImageIcon("Interno/new2x.png"));
		}else if(e.getSource() == clienteView.getBuscarButton()) {
			clienteView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		}else if(e.getSource() == clienteView.getBtnEditarCliente()) {
			clienteView.getBtnEditarCliente().setIcon(new ImageIcon("Interno/edit2x.png"));
		}else if(e.getSource() == clienteView.getBtnLimparBusca()) {
			clienteView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}else if(e.getSource() == clienteView.getBtnVisualizarConta()) {
			clienteView.getBtnVisualizarConta().setIcon(new ImageIcon("Interno/conta2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() == clienteView.getbtnExcluirCliente()) {
			clienteView.getbtnExcluirCliente().setIcon(new ImageIcon("Interno/delete.png"));
		}else if(e.getSource() == clienteView.getbtnNovoCliente()) {
			clienteView.getbtnNovoCliente().setIcon(new ImageIcon("Interno/new.png"));
		}else if(e.getSource() == clienteView.getBuscarButton()) {
			clienteView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		}else if(e.getSource() == clienteView.getBtnEditarCliente()) {
			clienteView.getBtnEditarCliente().setIcon(new ImageIcon("Interno/edit.png"));
		}else if(e.getSource() == clienteView.getBtnLimparBusca()) {
			clienteView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}else if(e.getSource() == clienteView.getBtnVisualizarConta()) {
			clienteView.getBtnVisualizarConta().setIcon(new ImageIcon("Interno/conta.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getSource() == clienteView.getbtnExcluirCliente()) {
			clienteView.getbtnExcluirCliente().setIcon(new ImageIcon("Interno/delete.png"));
		}else if(e.getSource() == clienteView.getbtnNovoCliente()) {
			clienteView.getbtnNovoCliente().setIcon(new ImageIcon("Interno/new.png"));
		}else if(e.getSource() == clienteView.getBuscarButton()) {
			clienteView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		}else if(e.getSource() == clienteView.getBtnEditarCliente()) {
			clienteView.getBtnEditarCliente().setIcon(new ImageIcon("Interno/edit.png"));
		}else if(e.getSource() == clienteView.getBtnLimparBusca()) {
			clienteView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}else if(e.getSource() == clienteView.getBtnVisualizarConta()) {
			clienteView.getBtnVisualizarConta().setIcon(new ImageIcon("Interno/conta.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(e.getSource() == clienteView.getbtnExcluirCliente()) {
			clienteView.getbtnExcluirCliente().setIcon(new ImageIcon("Interno/delete2x.png"));
		}else if(e.getSource() == clienteView.getbtnNovoCliente()) {
			clienteView.getbtnNovoCliente().setIcon(new ImageIcon("Interno/new2x.png"));
		}else if(e.getSource() == clienteView.getBuscarButton()) {
			clienteView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		}else if(e.getSource() == clienteView.getBtnEditarCliente()) {
			clienteView.getBtnEditarCliente().setIcon(new ImageIcon("Interno/edit2x.png"));
		}else if(e.getSource() == clienteView.getBtnLimparBusca()) {
			clienteView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}else if(e.getSource() == clienteView.getBtnVisualizarConta()) {
			clienteView.getBtnVisualizarConta().setIcon(new ImageIcon("Interno/conta2x.png"));
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
		clienteView.getT1().interrupt();
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