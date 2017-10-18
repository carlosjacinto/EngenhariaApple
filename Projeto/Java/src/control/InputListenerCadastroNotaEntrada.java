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
		}else if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			System.out.println("botao add produto na tabela");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
				if (e.getSource() == cadastroNotaEntrada.getPesqProduto()) {
					cadastroNotaEntrada.getPesqProduto().setIcon(new ImageIcon("Interno/search-icon2x.png"));
				} else if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
					cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
				} 

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getPesqProduto()) {
			cadastroNotaEntrada.getPesqProduto().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		} 

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == cadastroNotaEntrada.getPesqProduto()) {
			cadastroNotaEntrada.getPesqProduto().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add.png"));
		} 

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (e.getSource() == cadastroNotaEntrada.getPesqProduto()) {
			cadastroNotaEntrada.getPesqProduto().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		} else if (e.getSource() == cadastroNotaEntrada.getLblAddProduto()) {
			cadastroNotaEntrada.getLblAddProduto().setIcon(new ImageIcon("Interno/add2x.png"));
		} 

	}
}