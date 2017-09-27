package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.LoginView;
import view.Principal;
import view.PrincipalAdm;

public class InputListenerLoginView implements MouseListener{
	LoginView loginView;
	
	public InputListenerLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == loginView.getBtnLogin()) {
			capturaDadosLogin();
			loginView.dispose();
		}else if(e.getSource() == loginView.getBtnCancelar()) {
			new Principal().setVisible(true);
			loginView.dispose();
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
	
	public void capturaDadosLogin() {
		//TODO: Pegar dados e verificar login
		new PrincipalAdm().setVisible(true);
	}
}
