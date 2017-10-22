package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import view.FuncionarioView;
import view.LoginView;
import view.PrincipalAdm;
import view.ProdutoView;

public class InputListenerPrincipalAdm implements MouseListener {
	PrincipalAdm principalAdm;
	
	public InputListenerPrincipalAdm(PrincipalAdm principalAdm) {
		this.principalAdm = principalAdm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == principalAdm.getSetaCima()) {
			principalAdm.getSetaCima().setVisible(false);
			principalAdm.getBarraAtalhos().setVisible(true);
			principalAdm.getBtProd().setVisible(true);
			principalAdm.getBtFunc().setVisible(true);
			principalAdm.getBtSair().setVisible(true);
			principalAdm.getSetaBaixo().setVisible(true);
			principalAdm.getBtDesligar().setVisible(true);
		}else if(e.getSource() == principalAdm.getSetaBaixo()) {
			principalAdm.getSetaCima().setVisible(true);
			principalAdm.getBarraAtalhos().setVisible(false);
			principalAdm.getBtProd().setVisible(false);
			principalAdm.getBtFunc().setVisible(false);
			principalAdm.getBtSair().setVisible(false);
			principalAdm.getSetaBaixo().setVisible(false);
			principalAdm.getBtDesligar().setVisible(false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func2x.png"));
		}else if(e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod2x.png"));
		}else if(e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair2x.png"));
		} else if(e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar2x.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func.png"));
		}else if(e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod.png"));
		}else if(e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair.png"));
		} else if(e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar.png"));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func.png"));
			new FuncionarioView().setVisible(true);
		}else if(e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod.png"));
			new ProdutoView().setVisible(true);
		}else if(e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair.png"));
			principalAdm.dispose();
			new LoginView().setVisible(true);
		}else if(e.getSource() == principalAdm.getmntmFuncionario()) {
			new FuncionarioView().setVisible(true);
		}else if(e.getSource() == principalAdm.getmntmProdutos()) {
			new ProdutoView().setVisible(true);
		}else if(e.getSource() == principalAdm.getmntmSair()) {
			principalAdm.dispose();
			new LoginView().setVisible(true);
		}  else if(e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar.png"));
			principalAdm.dispose();
		}else if(e.getSource() == principalAdm.getmntmDesligar()) {
			principalAdm.dispose();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func2x.png"));
		}else if(e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod2x.png"));
		}else if(e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair2x.png"));
		} else if(e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar2x.png"));
		}
		
	}

}
