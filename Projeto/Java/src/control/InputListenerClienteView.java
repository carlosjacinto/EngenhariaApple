package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.CadastroClienteView;
import view.ClienteView;

public class InputListenerClienteView implements MouseListener, WindowListener {
	ClienteView clienteView;

	public InputListenerClienteView(ClienteView clienteView) {
		// TODO Auto-generated constructor stub
		this.clienteView = clienteView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == clienteView.getBuscarButton()) {
			System.out.println("Botão Ok clicado");
		}else if ((e.getSource()) == clienteView.getbtnNovoCliente()) {
			System.out.println("Botão Novo Clicado");
			new CadastroClienteView().setVisible(true);
		}else if(e.getSource() == clienteView.getTableCliente()) {
			System.err.println("Tabela clicada, linha: "+clienteView.getTableCliente().getSelectedRow());
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
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
