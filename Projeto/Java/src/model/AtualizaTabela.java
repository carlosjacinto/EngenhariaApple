package model;

import javax.swing.table.DefaultTableModel;

import view.ClienteView;
import view.FuncionarioView;

public class AtualizaTabela implements Runnable{
	
	FuncionarioView funcionarioView;
	FuncionarioDAO funcDAO = new FuncionarioDAO();
	ClienteView clienteView;
	ClienteDAO clieDAO = new ClienteDAO();
	private String busca = "";
	
	public AtualizaTabela(FuncionarioView funcionarioView) {
		this.funcionarioView = funcionarioView;
	}

	/*public AtualizaTabela(ClienteView clienteView) {
		this.clienteView = clienteView;
	}*/
	@Override
	public void run() {
		try {
			for(;;) {
				Thread.sleep(3000);
				atualizarTabelaFunc();
				//atualizarTabelaCliente();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generatee.printStackTrace();
		}
	}

	private void atualizarTabelaFunc() {
		
		String[][] funcs = funcDAO.listaFuncionarioArray(busca);
		String[] colunas = {"id","Nome", "CPF", "Endereço", "Telefone","Nascimento"};
		
		DefaultTableModel model = new DefaultTableModel(funcs,colunas) {
			 
			/**
			 * 
			 */
			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean []{  
			            false, false, false, false,false,false
			        };  
			        @Override  
			        public boolean isCellEditable(int rowIndex, int columnIndex) {  
			            return canEdit [columnIndex];  
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

	
/*	private void atualizarTabelaCliente() {
		String[][] clientes = clieDAO.listaClienteArray("");
		String[] colunas = {"id","Nome", "CPF", "Endereço", "Telefone","Nascimento"};
		
		DefaultTableModel model = new DefaultTableModel(clientes,colunas) {
		
			private static final long serialVersionUID = -7680235106608274804L;
			boolean[] canEdit = new boolean []{  
			            false, false, false, false,false,false
			        };  
			        @Override  
			        public boolean isCellEditable(int rowIndex, int columnIndex) {  
			            return canEdit [columnIndex];  
			        }
		};
		clienteView.getTableCliente().setModel(model);
		clienteView.repaint();
		clienteView.revalidate();
	}
	*/
}
