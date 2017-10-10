package model;

import javax.swing.table.DefaultTableModel;

import view.FuncionarioView;

public class AtualizaTabelaFuncionario implements Runnable {

	FuncionarioView funcionarioView;
	FuncionarioDAO funcDAO = new FuncionarioDAO();
	private String busca = "";

	public AtualizaTabelaFuncionario(FuncionarioView funcionarioView) {
		this.funcionarioView = funcionarioView;
	}

	public void run() {
		try {
			for (;;) {
				Thread.sleep(3000);
				atualizarTabelaFunc();

			}
		} catch (InterruptedException e) {
			// TODO Auto-generatee.printStackTrace();
		}
	}

	private void atualizarTabelaFunc() {

		String[][] funcs = funcDAO.listaFuncionarioArray(busca);
		String[] colunas = { "id", "Nome", "CPF", "Endereço", "Telefone", "Nascimento" };

		DefaultTableModel model = new DefaultTableModel(funcs, colunas) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		int l = funcionarioView.getTableFuncionario().getSelectedRow();

		funcionarioView.getTableFuncionario().setModel(model);
		try {
			funcionarioView.getTableFuncionario().setRowSelectionInterval(l, l);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}

		funcionarioView.repaint();
		funcionarioView.revalidate();
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}
}
