package model;

import javax.swing.table.DefaultTableModel;

import view.ClienteView;

public class AtualizaTabelaCliente implements Runnable {

	ClienteView clienteView;
	ClienteDAO clieDAO = new ClienteDAO();
	private String busca = "";

	public AtualizaTabelaCliente(ClienteView clienteView) {
		this.clienteView = clienteView;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				Thread.sleep(3000);
				atualizarTabelaCliente();
			}
		} catch (InterruptedException e) {

		}
	}

	private void atualizarTabelaCliente() {

		String[][] funcs = clieDAO.listaClienteArray(busca);
		String[] colunas = { "id", "Nome", "CPF", "Endereço", "Telefone", "Nascimento" };

		DefaultTableModel model = new DefaultTableModel(funcs, colunas) {

			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		int l = clienteView.getTableCliente().getSelectedRow();

		clienteView.getTableCliente().setModel(model);
		try {
			clienteView.getTableCliente().setRowSelectionInterval(l, l);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}

		clienteView.repaint();
		clienteView.revalidate();
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
