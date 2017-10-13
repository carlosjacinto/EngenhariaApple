package model;

import javax.swing.table.DefaultTableModel;

import view.NotaEntradaView;

public class AtualizaTabelaNotaEntrada implements Runnable{


	NotaEntradaView notaEntradaView;
	NotaEntradaDAO notaEntradaDAO = new NotaEntradaDAO();
	private String busca = "";

	public AtualizaTabelaNotaEntrada(NotaEntradaView notaEntradaView) {
		this.notaEntradaView = notaEntradaView;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				Thread.sleep(3000);
				atualizarTabelaNotaEntrada();
			}
		} catch (InterruptedException e) {

		}
	}

	private void atualizarTabelaNotaEntrada() {

		String[][] funcs = notaEntradaDAO.listaNotaEntradaArray(busca);
		String[] colunas = { "Número", "Nome", "CNPJ", "Total", "Funcionário", "Data do Cadastro" };

		DefaultTableModel model = new DefaultTableModel(funcs, colunas) {

			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		int l = notaEntradaView.getTableNotaEntrada().getSelectedRow();

		notaEntradaView.getTableNotaEntrada().setModel(model);
		try {
			notaEntradaView.getTableNotaEntrada().setRowSelectionInterval(l, l);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}

		notaEntradaView.repaint();
		notaEntradaView.revalidate();
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
