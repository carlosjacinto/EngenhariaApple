package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import view.CadastroNotaEntradaView;

public class InputListenerCadastroNotaEntrada  implements MouseListener{
	CadastroNotaEntradaView cadastroNotaEntrada;

	public InputListenerCadastroNotaEntrada(CadastroNotaEntradaView cadastroNotaEntrada) {
		// TODO Auto-generated constructor stub
		this.cadastroNotaEntrada = cadastroNotaEntrada;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getBtnCancelar()) {
			cadastroNotaEntrada.dispose();
		}else if ((e.getSource()) == cadastroNotaEntrada.getBtnGravar()) {
			System.out.println("Botão ok Clicado");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {


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
}