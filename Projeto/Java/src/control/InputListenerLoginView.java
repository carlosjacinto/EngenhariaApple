package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import model.LoginDAO;
import view.LoginView;
import view.Principal;
import view.PrincipalAdm;
import view.PrincipalFunc;

public class InputListenerLoginView implements MouseListener{
	LoginView loginView;
	
	public InputListenerLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == loginView.getBtnLogin()) {
			capturaDadosLogin();
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
		
		try {
			String cpf = loginView.getTextCpf().getText();
			String senha = String.copyValueOf(loginView.getPasswordField().getPassword());
			int flag = LoginDAO.getInstance().verificarSeExisteLogin(cpf, senha);
			if(flag == 2) {
				new PrincipalFunc().setVisible(true);
				loginView.dispose();
			}else if(flag == 1) {
				new PrincipalAdm().setVisible(true);
				loginView.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Login Inválido!","Erro Login", JOptionPane.ERROR_MESSAGE);
				loginView.getPasswordField().setText("");
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "O campo CPF de conter apenas números!","Erro Login", JOptionPane.ERROR_MESSAGE);
			loginView.getPasswordField().setText("");
		}
		
		
	}
}
