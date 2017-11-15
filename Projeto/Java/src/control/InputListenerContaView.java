package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ClienteDAO;
import model.ContaDAO;
import view.ContaView;
import view.PagamentoView;

public class InputListenerContaView implements MouseListener{
	ContaView contaView;
	ContaDAO contaDAO = new ContaDAO();
	
	public InputListenerContaView(ContaView contaView) {
		this.contaView = contaView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == contaView.getBtnRealizarPagamento()) {
			new PagamentoView(contaView.getCliente()).setVisible(true);
			contaView.mostrarDebitoConta().setText(""+contaDAO.retornaAReceber(contaView.getCliente().getIdCliente()));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
}
