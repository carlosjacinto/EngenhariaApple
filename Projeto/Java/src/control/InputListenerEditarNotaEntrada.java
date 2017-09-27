package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.EditarNotaEntradaView;

public class InputListenerEditarNotaEntrada implements MouseListener {
	private EditarNotaEntradaView edicaoNotaEntrada;

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == edicaoNotaEntrada.getBtnCancelar()) {
			edicaoNotaEntrada.dispose();
		} else if ((e.getSource()) == edicaoNotaEntrada.getBtnGravar()) {
			System.out.println("Bot�o ok Clicado");
			capturarDadosNotaEntrada();
		}
	}

	public void capturarDadosNotaEntrada() {

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
}
