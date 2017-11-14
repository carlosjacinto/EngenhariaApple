package model;

import javax.swing.table.DefaultTableModel;

import view.PedidoView;

public class AtualizaTabelaPedido implements Runnable{

	PedidoView pedidoView;
	PedidoDAO pedidoDAO = new PedidoDAO();
	private String busca = "";

	public AtualizaTabelaPedido(PedidoView pedidoView) {
		this.pedidoView = pedidoView;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				Thread.sleep(3000);
				atualizarTabelaPedido();
			}
		} catch (InterruptedException e) {

		}
	}

	private void atualizarTabelaPedido() {
		String[][] pedidos = pedidoDAO.listarPedidos(busca);
		String[] colunas = { "Número", "Nome", "CPF", "Total", "Funcionário", "Data do Cadastro" };

		DefaultTableModel model = new DefaultTableModel(pedidos, colunas) {

			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		int l = pedidoView.buscarPedidos().getSelectedRow();

		pedidoView.buscarPedidos().setModel(model);
		try {
			pedidoView.buscarPedidos().setRowSelectionInterval(l, l);
		} catch (IllegalArgumentException e) {
			
		}
			// TODO: handle exception

		pedidoView.repaint();
		pedidoView.revalidate();
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
