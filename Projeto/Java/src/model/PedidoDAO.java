package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataBase;

public class PedidoDAO {

	DataBase bd = DataBase.getInstance();
	Connection conex;

	public int gravarPedido(Pedido p, String[][] produtos) {
		conex = bd.Conectar();
		int codigo = -1;
		try {

			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO pedido(Cliente_idCliente, Funcionario_idFuncionario, dataPedido, valorTotalPedido)VALUES ('"
							+ p.getIdCliente() + "','" + p.getIdFuncionario() + "','" + p.getDataPed() + "','"
							+ p.getPrecoPed() + "') ");

			System.out.println("deu bom gravar cabeçalho");

			ResultSet rs = stmt.executeQuery("SELECT MAX(idPedido) FROM PEDIDO");
			while(rs.next()) {
				codigo = rs.getInt("MAX(idPedido)");
			}
	

			System.out.println(codigo);
			for (int i = 0; i < produtos.length; i++) {

				stmt.execute(
						"INSERT INTO pedido_has_produto(Pedido_idPedido, Produto_idProduto, qtdVenda, qtdControle, precoUnitItem, precoTotalItem)VALUES ('"
								+ codigo + "','" + Integer.parseInt(produtos[i][0]) + "','"
								+ Integer.parseInt(produtos[i][2]) + "','" + Integer.parseInt(produtos[i][2]) + "','"
								+ Double.parseDouble(produtos[i][3]) / Integer.parseInt(produtos[i][2]) + "','"
								+ Double.parseDouble(produtos[i][3]) + "') ");
			}
			System.out.println("deu bom gravar produtos");
			return codigo;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir pedidos..." + sqle.getMessage());
			return -1;
		} finally {
			bd.Desconectar(conex);

		}

	}

	public boolean VerificaPedido(int nota, String scnpj) {
		conex = bd.Conectar();

		return false;
	}

	public Pedido RetornaPedido(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM pedido where idPedido = " + iid;
			ResultSet rs = stmt.executeQuery(SQL);
			Pedido nota = new Pedido();
			while (rs.next()) {
				nota.setDataPed(rs.getDate("dataPedido"));
				nota.setIdCliente(rs.getInt("Cliente_idCliente"));
				nota.setIdFuncionario(rs.getInt("Funcionario_idFuncionario"));
				nota.setIdPedido(rs.getInt("idPedido"));
				nota.setPrecoPed(rs.getInt("valorTotalPedido"));
			}
			return nota;
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}
	
	public String[][] retornaProdutosPed(int iid){
		conex = bd.Conectar();
		String produtos[][] = null;
		System.out.println(iid);
		
		Statement stmt;
		try {
			stmt = conex.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Produto_idProduto, nomeProduto,qtdVenda,precoTotalItem from pedido_has_produto inner join Produto on Produto_idProduto = idProduto where Pedido_idPedido = "+iid); 
			int cont = 0;
			rs.last();
			produtos = new String[rs.getRow()][4];
			System.out.println(rs.getRow());
			rs.beforeFirst();
			while(rs.next()) {
				produtos[cont][0] = ""+rs.getInt("Produto_idProduto");
				produtos[cont][1] = rs.getString("nomeProduto");
				System.out.println(produtos[cont][1]);
				produtos[cont][2] = ""+rs.getInt("qtdVenda");
				produtos[cont][3] = ""+rs.getDouble("precoTotalItem");
				cont++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bd.Desconectar(conex);
		return produtos;
	}

	public String[][] listaPedidoArray(String campo) {
		conex = bd.Conectar();
		String pedidos[][] = null;
		
		Statement stmt;
		try {
			stmt = conex.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idPedido, nomeCliente,cpfCliente,nomeFunc,dataPedido,valorTotalPedido FROM PEDIDO INNER JOIN CLIENTE ON Cliente_idCliente = idCliente INNER JOIN FUNCIONARIO ON Funcionario_idFuncionario = idFuncionario where idPedido like '%"+ campo+"%' or nomeCliente like '%"+campo+"%' order by idPedido"); 
			int cont = 0;
			rs.last();
			pedidos = new String[rs.getRow()][6];
			rs.beforeFirst();
			while(rs.next()) {
				pedidos[cont][0] = ""+rs.getInt("idPedido");
				pedidos[cont][1] = rs.getString("nomeCliente");
				pedidos[cont][2] = rs.getString("cpfCliente");
				pedidos[cont][4] = rs.getString("nomeFunc");
				pedidos[cont][5] = rs.getDate("dataPedido")+"";
				pedidos[cont][3] = rs.getFloat("valorTotalPedido")+"";
				cont++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bd.Desconectar(conex);
		return pedidos;
	}

	public int excluirPedido(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM pedido_has_produto where Pedido_idPedido = "+iid;
			result = stmt.executeUpdate(SQL);
			SQL = "DELETE FROM pedido where idPedido = " + iid;
			result = stmt.executeUpdate(SQL);
			

		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}
}
