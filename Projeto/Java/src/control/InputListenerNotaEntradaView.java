package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.NotaEntrada;
import model.NotaEntradaDAO;
import view.CadastroNotaEntradaView;
import view.EditarNotaEntradaView;
import view.NotaEntradaView;

public class InputListenerNotaEntradaView implements MouseListener, WindowListener {
	NotaEntradaView notaEntradaView;
	private NotaEntradaDAO notaDAO = new NotaEntradaDAO();

	public InputListenerNotaEntradaView(NotaEntradaView notaEntradaView) {

		this.notaEntradaView = notaEntradaView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == notaEntradaView.getBuscarButton()) {
			mudarTabela();
		} else if ((e.getSource()) == notaEntradaView.getbtnNovoNotaEntrada()) {
			CadastroNotaEntradaView cadastroNotaEntradaView;
			cadastroNotaEntradaView = new CadastroNotaEntradaView();
			cadastroNotaEntradaView.setVisible(true);
			notaEntradaView.getTextBusca().setText("");
			mudarTabela();
		} else if (e.getSource() == notaEntradaView.getTableNotaEntrada()) {

		} else if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			confirmarExclusao();
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			if (notaEntradaView.getTableNotaEntrada().getSelectedRow() != -1) {
				String id = (notaEntradaView.getTableNotaEntrada().getModel()
						.getValueAt(notaEntradaView.getTableNotaEntrada().getSelectedRow(), 0).toString());
				NotaEntrada nota = notaDAO.RetornaNotaEntrada(Integer.parseInt(id));
				new EditarNotaEntradaView(nota).setVisible(true);
				notaEntradaView.getTextBusca().setText("");
				mudarTabela();
			} else
				JOptionPane.showMessageDialog(null, "Selecione Uma Nota de Entrada!", null, JOptionPane.WARNING_MESSAGE);
		}else if(e.getSource() == notaEntradaView.getBtnLimparBusca()) {
			notaEntradaView.getTextBusca().setText("");
			mudarTabela();
		}
	}

	public void mudarTabela() {
		String[][] notas = notaDAO.listaNotaEntradaArray(notaEntradaView.getTextBusca().getText());
		String[] colunas = { "Número", "Nome", "CNPJ", "Total", "Funcionário", "Data do Cadastro" };

		DefaultTableModel model = new DefaultTableModel(notas, colunas) {
			/**
			* 
			*/
			private static final long serialVersionUID = -7018342759131611914L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		notaEntradaView.getTableNotaEntrada().setModel(model);
		notaEntradaView.repaint();
		notaEntradaView.revalidate();
		notaEntradaView.setBuscaAT1(notaEntradaView.getTextBusca().getText());
	}

	public void confirmarExclusao() {
		
		int i = notaEntradaView.getTableNotaEntrada().getSelectedRow();
		if (i != -1) {
			int result = JOptionPane.showConfirmDialog(null,
					"Tem certeza que quer excluir " + notaEntradaView.getTableNotaEntrada().getValueAt(i, 1) + "?",
					"Excluir", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				int sucesso = notaDAO.excluirNotaEntrada(
						Integer.parseInt(notaEntradaView.getTableNotaEntrada().getValueAt(i, 0).toString()));

				if (sucesso == 1)
					JOptionPane.showMessageDialog(null, "Nota de Entrada Excluido!", null,
							JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
							JOptionPane.INFORMATION_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Selecione Uma Nota de Entrada!", null, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/deleteNota2x.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/newNota2x.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/editNota2x.png"));
		}else if(e.getSource() == notaEntradaView.getBtnLimparBusca()) {
			notaEntradaView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/deleteNota.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/newNota.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/editNota.png"));
		}else if(e.getSource() == notaEntradaView.getBtnLimparBusca()) {
			notaEntradaView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/deleteNota.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/newNota.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/editNota.png"));
		}else if(e.getSource() == notaEntradaView.getBtnLimparBusca()) {
			notaEntradaView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/deleteNota2x.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/newNota2x.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-iconNota2x.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/editNota2x.png"));
		}else if(e.getSource() == notaEntradaView.getBtnLimparBusca()) {
			notaEntradaView.getBtnLimparBusca().setIcon(new ImageIcon("Interno/clean-search2x.png"));
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
		

	}

	@Override
	public void windowClosed(WindowEvent e) {
		

	}

	@Override
	public void windowClosing(WindowEvent e) {
		notaEntradaView.getT1().interrupt();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		

	}

	@Override
	public void windowOpened(WindowEvent e) {
		

	}

}
