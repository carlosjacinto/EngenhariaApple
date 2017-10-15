package control;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import model.ProdutoDAO;
import view.CadastroPedidoView;

public class InputListenerCadastroPedido implements MouseListener {
	CadastroPedidoView cadastroPedido;
	ProdutoDAO produtoDAO = new ProdutoDAO();
	String[][] dados;
	double valorTotal = 0;
	public String[][] getDados() {
		return dados;
	}

	int cont=0;

	public InputListenerCadastroPedido(CadastroPedidoView cadastroPedido) {
		// TODO Auto-generated constructor stub
		this.cadastroPedido = cadastroPedido;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cadastroPedido.getBtnCancelar()) {
			cadastroPedido.dispose();
		} else if ((e.getSource()) == cadastroPedido.getBtnGravar()) {
			System.out.println("Botão ok Clicado");
		}if(e.getSource() == cadastroPedido.getBtnAdd()) {
			if(!cadastroPedido.getSpinnerQtde().getValue().toString().equals("0")) {
				int qtd = Integer.parseInt(cadastroPedido.getSpinnerQtde().getValue().toString());
				String produto[] = ((String) cadastroPedido.getComboBoxProduto().getSelectedItem()).split("-");
				double precoVenda = produtoDAO.buscarPrecoVenda(Integer.parseInt(produto[0]));
				double precoProdutos = qtd*precoVenda;
				String[] p = {produto[0],produto[1],qtd+"",precoProdutos+""};
				if(getDados() == null ) {
					dados = new String[1][4];
					dados[0][0] = p[0];
					dados[0][1] = p[1];
					dados[0][2] = p[2];
					dados[0][3] = p[3];
					
				}else {
					int i = dados.length;
					String[][] aux = new String[i+1][4];
					for(int j=0;j<i;j++) {
						aux[j][0] = dados[j][0];
						aux[j][1] = dados[j][1];
						aux[j][2] = dados[j][2];
						aux[j][3] = dados[j][3];
					}
					aux[i][0]= p[0];
					aux[i][1]= p[1];
					aux[i][2]= p[2];
					aux[i][3]= p[3];
					dados = new String[i+1][4];
					for(int j=0;j<=i;j++) {
						dados[j][0] = aux[j][0];
						dados[j][1] = aux[j][1];
						dados[j][2] = aux[j][2];
						dados[j][3] = aux[j][3];
					}
					
				}
				String[] colunas = {"id","Nome", "Quantidade", "Preço(R$)"};
				
				DefaultTableModel model = new DefaultTableModel(dados,colunas) {
					 /**
					 * 
					 */
					private static final long serialVersionUID = -7018342759131611914L;
					boolean[] canEdit = new boolean []{  
					            false, false, false, false
					        };  
					        @Override  
					        public boolean isCellEditable(int rowIndex, int columnIndex) {  
					            return canEdit [columnIndex];  
					        }
				};
				cadastroPedido.getTableFuncionario().setModel(model);
				cadastroPedido.repaint();
				cadastroPedido.revalidate();
				valorTotal += precoProdutos;
				cadastroPedido.getTextPreco().setText(valorTotal+"");
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add.png"));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == cadastroPedido.getBtnAdd()) {
			cadastroPedido.getBtnAdd().setIcon(new ImageIcon("Interno/add2x.png"));
		}

	}
}