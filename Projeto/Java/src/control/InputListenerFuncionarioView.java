package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Funcionario;
import model.FuncionarioDAO;
import view.CadastroFuncionarioView;
import view.EditarFuncionarioView;
import view.FuncionarioView;

public class InputListenerFuncionarioView implements MouseListener, WindowListener {
	FuncionarioView funcionarioView;
	private FuncionarioDAO funcDAO = new FuncionarioDAO();
	

	public InputListenerFuncionarioView(FuncionarioView funcionarioView) {
		// TODO Auto-generated constructor stub
		this.funcionarioView = funcionarioView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == funcionarioView.getBuscarButton()) {
			mudarTabela();
			
			//EditarFuncionarioView edicaoFuncionarioView = new EditarFuncionarioView();
			//edicaoFuncionarioView.setVisible(true);
		} else if ((e.getSource()) == funcionarioView.getbtnNovoFuncionario()) {
			CadastroFuncionarioView cadastroFuncionarioView;
			System.out.println("Botão Novo Clicado");
			cadastroFuncionarioView = new CadastroFuncionarioView();
			cadastroFuncionarioView.setVisible(true);
		} else if (e.getSource() == funcionarioView.getTableFuncionario()) {
			//String id = (funcionarioView.getTableFuncionario().getModel().getValueAt(funcionarioView.getTableFuncionario().getSelectedRow(), 0).toString());
			//Funcionario func = funcDAO.RetornaFuncionario(Integer.parseInt(id));
			//new EditarFuncionarioView(func).setVisible(true);
		}else if(e.getSource() == funcionarioView.getbtnExcluirFuncionario()) {
			confirmarExclusao();
		}else if(e.getSource() == funcionarioView.getBtnEditarFuncionario()) {
			if(funcionarioView.getTableFuncionario().getSelectedRow()!=-1) {
				String id = (funcionarioView.getTableFuncionario().getModel().getValueAt(funcionarioView.getTableFuncionario().getSelectedRow(), 0).toString());
				Funcionario func = funcDAO.RetornaFuncionario(Integer.parseInt(id));
				new EditarFuncionarioView(func).setVisible(true);
			}else JOptionPane.showMessageDialog(null, "Selecione Um Funcionário!", null,
					JOptionPane.WARNING_MESSAGE);
		}else if(e.getSource() == funcionarioView.getBtnLimparBusca()) {
			funcionarioView.getTextBusca().setText("");
			mudarTabela();
		}
	}

	public void mudarTabela() {
		String[][] funcs = funcDAO.listaFuncionarioArray(funcionarioView.getTextBusca().getText());
		String[] colunas = {"id","Nome", "CPF", "Endereço", "Telefone","Nascimento"};
		
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
		funcionarioView.getTableFuncionario().setModel(model);
		funcionarioView.repaint();
		funcionarioView.revalidate();
		funcionarioView.setBuscaAT1(funcionarioView.getTextBusca().getText());
	}
	
	public void confirmarExclusao() {
		// TODO Auto-generated method stub
		int i = funcionarioView.getTableFuncionario().getSelectedRow();
		if(i!=-1) {
			int result = JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir "
						+funcionarioView.getTableFuncionario().getValueAt(i, 1)+"?",
					"Excluir", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int sucesso = funcDAO.excluirFuncionario(Integer.parseInt(funcionarioView.getTableFuncionario().getValueAt(i, 0).toString()));
				
				if(sucesso == 1)JOptionPane.showMessageDialog(null, "Funcionário Excluido!", null,
						JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}else JOptionPane.showMessageDialog(null, "Selecione Um Funcionário!", null,
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == funcionarioView.getbtnExcluirFuncionario()) {
			funcionarioView.getbtnExcluirFuncionario().setIcon(new ImageIcon("Interno/delete2x.png"));
		}else if(e.getSource() == funcionarioView.getbtnNovoFuncionario()) {
			funcionarioView.getbtnNovoFuncionario().setIcon(new ImageIcon("Interno/new2x.png"));
		}else if(e.getSource() == funcionarioView.getBuscarButton()) {
			funcionarioView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		}else if(e.getSource() == funcionarioView.getBtnEditarFuncionario()) {
			funcionarioView.getBtnEditarFuncionario().setIcon(new ImageIcon("Interno/edit2x.png"));
		}else if(e.getSource() == funcionarioView.getBtnLimparBusca()) {
			funcionarioView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == funcionarioView.getbtnExcluirFuncionario()) {
			funcionarioView.getbtnExcluirFuncionario().setIcon(new ImageIcon("Interno/delete.png"));
		}else if(e.getSource() == funcionarioView.getbtnNovoFuncionario()) {
			funcionarioView.getbtnNovoFuncionario().setIcon(new ImageIcon("Interno/new.png"));
		}else if(e.getSource() == funcionarioView.getBuscarButton()) {
			funcionarioView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		}else if(e.getSource() == funcionarioView.getBtnEditarFuncionario()) {
			funcionarioView.getBtnEditarFuncionario().setIcon(new ImageIcon("Interno/edit.png"));
		}else if(e.getSource() == funcionarioView.getBtnLimparBusca()) {
			funcionarioView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == funcionarioView.getbtnExcluirFuncionario()) {
			funcionarioView.getbtnExcluirFuncionario().setIcon(new ImageIcon("Interno/delete.png"));
		}else if(e.getSource() == funcionarioView.getbtnNovoFuncionario()) {
			funcionarioView.getbtnNovoFuncionario().setIcon(new ImageIcon("Interno/new.png"));
		}else if(e.getSource() == funcionarioView.getBuscarButton()) {
			funcionarioView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		}else if(e.getSource() == funcionarioView.getBtnEditarFuncionario()) {
			funcionarioView.getBtnEditarFuncionario().setIcon(new ImageIcon("Interno/edit.png"));
		}else if(e.getSource() == funcionarioView.getBtnLimparBusca()) {
			funcionarioView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == funcionarioView.getbtnExcluirFuncionario()) {
			funcionarioView.getbtnExcluirFuncionario().setIcon(new ImageIcon("Interno/delete2x.png"));
		}else if(e.getSource() == funcionarioView.getbtnNovoFuncionario()) {
			funcionarioView.getbtnNovoFuncionario().setIcon(new ImageIcon("Interno/new2x.png"));
		}else if(e.getSource() == funcionarioView.getBuscarButton()) {
			funcionarioView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		}else if(e.getSource() == funcionarioView.getBtnEditarFuncionario()) {
			funcionarioView.getBtnEditarFuncionario().setIcon(new ImageIcon("Interno/edit2x.png"));
		}else if(e.getSource() == funcionarioView.getBtnLimparBusca()) {
			funcionarioView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		funcionarioView.getT1().interrupt();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
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

}
