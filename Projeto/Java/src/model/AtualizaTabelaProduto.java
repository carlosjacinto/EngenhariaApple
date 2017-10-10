package model;

import java.util.NoSuchElementException;

import javax.swing.table.DefaultTableModel;

import view.ProdutoView;

public class AtualizaTabelaProduto implements Runnable {

	ProdutoView produtoView;
	ProdutoDAO produtoDAO = new ProdutoDAO();
	private String busca = "";

	public AtualizaTabelaProduto(ProdutoView produtoView) {
		this.produtoView = produtoView;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				Thread.sleep(3000);
				atualizarTabelaCliente();
			}
		} catch (InterruptedException e) {

		}catch(NoSuchElementException e) {
			System.out.println("Achou");
		}
	}

	private void atualizarTabelaCliente() {

		String[][] funcs = produtoDAO.listaProdutoArray(busca);
		String[] colunas = {"id","Nome", "Preço de Venda(R$)", "Preço de Compra(R$)", "Quantidade"};

		DefaultTableModel model = new DefaultTableModel(funcs, colunas) {

			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		int l = produtoView.getTableProduto().getSelectedRow();

		produtoView.getTableProduto().setModel(model);
		try {
			produtoView.getTableProduto().setRowSelectionInterval(l, l);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}

		produtoView.repaint();
		produtoView.revalidate();
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
