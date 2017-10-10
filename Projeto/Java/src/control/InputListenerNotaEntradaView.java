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
		// TODO Auto-generated method stub
		if (e.getSource() == notaEntradaView.getBuscarButton()) {
			String[][] notas = notaDAO.listaNotaEntradaArray(notaEntradaView.getTextBusca().getText());
			String[] colunas = { "Numero", "Nome", "CNPJ", "Total", "Funcionario", "Data do Cadastro" };

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

			// EditarNotaEntradaView edicaoNotaEntradaView = new EditarNotaEntradaView();
			// edicaoNotaEntradaView.setVisible(true);
		} else if ((e.getSource()) == notaEntradaView.getbtnNovoNotaEntrada()) {
			CadastroNotaEntradaView cadastroNotaEntradaView;
			System.out.println("Botão Novo Clicado");
			cadastroNotaEntradaView = new CadastroNotaEntradaView();
			cadastroNotaEntradaView.setVisible(true);
		} else if (e.getSource() == notaEntradaView.getTableNotaEntrada()) {
			// String id =
			// (notaEntradaView.getTableNotaEntrada().getModel().getValueAt(notaEntradaView.getTableNotaEntrada().getSelectedRow(),
			// 0).toString());
			// NotaEntrada nota = notaDAO.RetornaNotaEntrada(Integer.parseInt(id));
			// new EditarNotaEntradaView(nota).setVisible(true);
		} else if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			confirmarExclusao();
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			if (notaEntradaView.getTableNotaEntrada().getSelectedRow() != -1) {
				String id = (notaEntradaView.getTableNotaEntrada().getModel()
						.getValueAt(notaEntradaView.getTableNotaEntrada().getSelectedRow(), 0).toString());
				NotaEntrada nota = notaDAO.RetornaNotaEntrada(Integer.parseInt(id));
				new EditarNotaEntradaView(nota).setVisible(true);
			} else
				JOptionPane.showMessageDialog(null, "Selecione Uma Nota de Entrada!", null, JOptionPane.WARNING_MESSAGE);
		}
	}

	public void confirmarExclusao() {
		// TODO Auto-generated method stub
		int i = notaEntradaView.getTableNotaEntrada().getSelectedRow();
		if (i != -1) {
			int result = JOptionPane.showConfirmDialog(null,
					"Tem certeza que quer excluir " + notaEntradaView.getTableNotaEntrada().getValueAt(i, 1) + "?",
					"Excluir", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				int sucesso = notaDAO.excluirNotaEntrada(
						Integer.parseInt(notaEntradaView.getTableNotaEntrada().getValueAt(i, 0).toString()));

				if (sucesso == 1)
					JOptionPane.showMessageDialog(null, "Nota de Entrada Excluido!", null, JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Erro ao tentar excluir!", null,
							JOptionPane.INFORMATION_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Selecione Uma Nota de Entrada!", null, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/delete2x.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/new2x.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/edit2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/delete.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/new.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/edit.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/delete.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/new.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/edit.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == notaEntradaView.getbtnExcluirNotaEntrada()) {
			notaEntradaView.getbtnExcluirNotaEntrada().setIcon(new ImageIcon("Interno/delete2x.png"));
		} else if (e.getSource() == notaEntradaView.getbtnNovoNotaEntrada()) {
			notaEntradaView.getbtnNovoNotaEntrada().setIcon(new ImageIcon("Interno/new2x.png"));
		} else if (e.getSource() == notaEntradaView.getBuscarButton()) {
			notaEntradaView.getBuscarButton().setIcon(new ImageIcon("Interno/search-icon2x.png"));
		} else if (e.getSource() == notaEntradaView.getBtnEditarNotaEntrada()) {
			notaEntradaView.getBtnEditarNotaEntrada().setIcon(new ImageIcon("Interno/edit2x.png"));
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		notaEntradaView.getT1().interrupt();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
