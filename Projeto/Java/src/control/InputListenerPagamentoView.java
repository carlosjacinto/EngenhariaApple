package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ContaDAO;
import view.PagamentoView;

public class InputListenerPagamentoView implements MouseListener{
	PagamentoView pagamentoView;
	ContaDAO contaDAO = new ContaDAO();
	
	public InputListenerPagamentoView(PagamentoView pagamentoView) {
		this.pagamentoView = pagamentoView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == pagamentoView.getBtnPagar()) {
			try {
				float valor = Float.parseFloat(pagamentoView.getTextField().getText());
				if(contaDAO.retornaAReceber(pagamentoView.getCliente().getIdCliente()) >= valor) {
					contaDAO.abaterValor(pagamentoView.getCliente().getIdCliente(), valor);
					
					pagamentoView.dispose();
				}
				
			}catch(NumberFormatException exc) {
				System.out.println(pagamentoView.getTextField().getText()+"Erro");
			}
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
