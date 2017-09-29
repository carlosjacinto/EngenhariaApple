package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.table.DefaultTableModel;

import model.FuncionarioDAO;
import view.CadastroFuncionarioView;
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
			String[][] funcs = funcDAO.listaFuncionarioArray(funcionarioView.getTextBusca().getText());
			String[] colunas = {"id","Nome", "CPF", "Endere�o", "Telefone","Nascimento"};
			
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
			
			//EditarFuncionarioView edicaoFuncionarioView = new EditarFuncionarioView();
			//edicaoFuncionarioView.setVisible(true);
		} else if ((e.getSource()) == funcionarioView.getbtnNovoFuncionario()) {
			CadastroFuncionarioView cadastroFuncionarioView;
			System.out.println("Bot�o Novo Clicado");
			cadastroFuncionarioView = new CadastroFuncionarioView();
			cadastroFuncionarioView.setVisible(true);
		} else if (e.getSource() == funcionarioView.getTableFuncionario()) {
			System.err.println("Tabela clicada, linha: " + funcionarioView.getTableFuncionario().getSelectedRow());
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

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		funcionarioView.getT1().interrupt();
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
