package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import view.ClienteView;
import view.NotaEntradaView;
import view.PedidoView;
import view.PrincipalFunc;

public class InputListenerPrincipalFunc implements MouseListener {
	PrincipalFunc principalFunc;
	
	public InputListenerPrincipalFunc(PrincipalFunc principalFunc) {
		this.principalFunc = principalFunc;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == principalFunc.getSetaCima()) {
			principalFunc.getSetaCima().setVisible(false);
			principalFunc.getBarraAtalhos().setVisible(true);
			principalFunc.getBtPedido().setVisible(true);
			principalFunc.getBtCliente().setVisible(true);
			principalFunc.getBtNotaEntrada().setVisible(true);
			principalFunc.getBtSair().setVisible(true);
			principalFunc.getSetaBaixo().setVisible(true);
		}else if(e.getSource() == principalFunc.getSetaBaixo()) {
			principalFunc.getSetaCima().setVisible(true);
			principalFunc.getBarraAtalhos().setVisible(false);
			principalFunc.getBtPedido().setVisible(false);
			principalFunc.getBtCliente().setVisible(false);
			principalFunc.getBtNotaEntrada().setVisible(false);
			principalFunc.getBtSair().setVisible(false);
			principalFunc.getSetaBaixo().setVisible(false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == principalFunc.getBtCliente()) {
			principalFunc.getBtCliente().setIcon(new ImageIcon("Interno/cliente2x.png"));
		}else if(e.getSource() == principalFunc.getBtPedido()) {
			principalFunc.getBtPedido().setIcon(new ImageIcon("Interno/pedidos2x.png"));
		}else if(e.getSource() == principalFunc.getBtNotaEntrada()) {
			principalFunc.getBtNotaEntrada().setIcon(new ImageIcon("Interno/nota2x.png"));
		}else if(e.getSource() == principalFunc.getBtSair()) {
			principalFunc.getBtSair().setIcon(new ImageIcon("Interno/sair2x.png"));
		}  
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == principalFunc.getBtCliente()) {
			principalFunc.getBtCliente().setIcon(new ImageIcon("Interno/cliente.png"));
		}else if(e.getSource() == principalFunc.getBtPedido()) {
			principalFunc.getBtPedido().setIcon(new ImageIcon("Interno/pedidos.png"));
		}else if(e.getSource() == principalFunc.getBtNotaEntrada()) {
			principalFunc.getBtNotaEntrada().setIcon(new ImageIcon("Interno/nota.png"));
		}else if(e.getSource() == principalFunc.getBtSair()) {
			principalFunc.getBtSair().setIcon(new ImageIcon("Interno/sair.png"));
		} 
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == principalFunc.getBtCliente()) {
			principalFunc.getBtCliente().setIcon(new ImageIcon("Interno/cliente.png"));
			new ClienteView().setVisible(true);
		}else if(e.getSource() == principalFunc.getBtPedido()) {
			principalFunc.getBtPedido().setIcon(new ImageIcon("Interno/pedidos.png"));
			new PedidoView().setVisible(true);
		}else if(e.getSource() == principalFunc.getBtNotaEntrada()) {
			principalFunc.getBtNotaEntrada().setIcon(new ImageIcon("Interno/nota.png"));
			new NotaEntradaView().setVisible(true);
		}else if(e.getSource() == principalFunc.getBtSair()) {
			principalFunc.getBtSair().setIcon(new ImageIcon("Interno/sair.png"));
			principalFunc.dispose();
		}else if(e.getSource() == principalFunc.getmntmCliente()) {
			new ClienteView().setVisible(true);
		}else if(e.getSource() == principalFunc.getmntmPedidos()) {
			new PedidoView().setVisible(true);
		}else if(e.getSource() == principalFunc.getmntmNotaEntrada()) {
			new NotaEntradaView().setVisible(true);
		}  
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == principalFunc.getBtCliente()) {
			principalFunc.getBtCliente().setIcon(new ImageIcon("Interno/cliente2x.png"));
		}else if(e.getSource() == principalFunc.getBtPedido()) {
			principalFunc.getBtPedido().setIcon(new ImageIcon("Interno/pedidos2x.png"));
		}else if(e.getSource() == principalFunc.getBtNotaEntrada()) {
			principalFunc.getBtNotaEntrada().setIcon(new ImageIcon("Interno/nota2x.png"));
		}else if(e.getSource() == principalFunc.getBtSair()) {
			principalFunc.getBtSair().setIcon(new ImageIcon("Interno/sair2x.png"));
		}  
		
	}

}
