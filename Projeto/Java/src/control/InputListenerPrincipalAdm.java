package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.PDFGenerator;
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
		if (e.getSource() == principalAdm.getSetaCima()) {
			principalAdm.getSetaCima().setVisible(false);
			principalAdm.getBarraAtalhos().setVisible(true);
			principalAdm.getBtProd().setVisible(true);
			principalAdm.getBtFunc().setVisible(true);
			principalAdm.getBtSair().setVisible(true);
			principalAdm.getSetaBaixo().setVisible(true);
			principalAdm.getBtDesligar().setVisible(true);
		} else if (e.getSource() == principalAdm.getSetaBaixo()) {
			principalAdm.getSetaCima().setVisible(true);
			principalAdm.getBarraAtalhos().setVisible(false);
			principalAdm.getBtProd().setVisible(false);
			principalAdm.getBtFunc().setVisible(false);
			principalAdm.getBtSair().setVisible(false);
			principalAdm.getSetaBaixo().setVisible(false);
			principalAdm.getBtDesligar().setVisible(false);
		}else if(e.getSource() == principalAdm.getmnSobre()) {
		}


	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func2x.png"));
		} else if (e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod2x.png"));
		} else if (e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair2x.png"));
		} else if (e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func.png"));
		} else if (e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod.png"));
		} else if (e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair.png"));
		} else if (e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func.png"));
			new FuncionarioView().setVisible(true);
		} else if (e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod.png"));
			new ProdutoView().setVisible(true);
		} else if (e.getSource() == principalAdm.getBtSair()) {
			int result = JOptionPane.showConfirmDialog(null, "Deseja realmente sair da sessão usuário?", "Sair",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair.png"));
				principalAdm.dispose();

				new LoginView().setVisible(true);
			}
		} else if (e.getSource() == principalAdm.getmntmFuncionario()) {
			new FuncionarioView().setVisible(true);
		} else if (e.getSource() == principalAdm.getmntmProdutos()) {
			new ProdutoView().setVisible(true);
		} else if (e.getSource() == principalAdm.getmntmSair()) {
			int result = JOptionPane.showConfirmDialog(null, "Deseja realmente sair da sessão usuário?", "Sair",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				principalAdm.dispose();
				new LoginView().setVisible(true);
			}
		} else if (e.getSource() == principalAdm.getBtDesligar()) {
			int result = JOptionPane.showConfirmDialog(null, "Deseja realmente sair da aplicação?", "Sair",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar.png"));
				principalAdm.dispose();
			}
		} else if (e.getSource() == principalAdm.getmntmRelProd()) {
			try {
				boolean result = new PDFGenerator().createPDF();
				if(!result) JOptionPane.showMessageDialog(null, "Estoque Vazio!", null,
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório!", null,
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == principalAdm.getmntmDesligar()) {
			int result = JOptionPane.showConfirmDialog(null, "Deseja realmente sair da aplicação?", "Sair",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION)
				principalAdm.dispose();
		}else if (e.getSource() == principalAdm.getmntmRelProd()) {
			try {
				boolean result = new PDFGenerator().createPDF();
				if(!result) JOptionPane.showMessageDialog(null, "Estoque Vazio!", null,
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório!", null,
						JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == principalAdm.getmnSobre()) {
			System.out.println("teste");
			JOptionPane.showMessageDialog(null, "Apple Cart\r\n" + 
					"Versão 1.9.3\r\n" + 
					"\r\n" + 
					"Desenvolvedores: Carlos Henrique, Eduardo Rotundaro, Pedro Carvalho e Daniel Borges.\r\n" + 
					"\r\nCopyright © 2017 - CEPD Software s.r.o. Todos os direitos reservados.", null,
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == principalAdm.getBtFunc()) {
			principalAdm.getBtFunc().setIcon(new ImageIcon("Interno/func2x.png"));
		} else if (e.getSource() == principalAdm.getBtProd()) {
			principalAdm.getBtProd().setIcon(new ImageIcon("Interno/prod2x.png"));
		} else if (e.getSource() == principalAdm.getBtSair()) {
			principalAdm.getBtSair().setIcon(new ImageIcon("Interno/sair2x.png"));
		} else if (e.getSource() == principalAdm.getBtDesligar()) {
			principalAdm.getBtDesligar().setIcon(new ImageIcon("Interno/desligar2x.png"));
		}

	}

}
