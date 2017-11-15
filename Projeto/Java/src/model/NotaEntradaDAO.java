package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import control.DataBase;

public class NotaEntradaDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex;

	public int gravarCompra(NotaEntrada c, String[][] produtos) {
		conex = bd.Conectar();
		int codigo = -1;
		try {
			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO Compra(numeroNFECompra, nomeFornecCompra, cnpjFornecCompra, dataCompra, outrosCompra, valorTotalCompra, chaveAcessoCompra, dataEntradaCompra, idFuncCompra)VALUES ('"
							+ c.getNumeroNota() + "','" + c.getFornecedor() + "','" + c.getCnpj() + "','"
							+ c.getDataCompra() + "','" + c.getOutros() + "','" + c.getTotal() + "','"
							+ c.getChaveAcesso() + "','" + c.getDataEntrada() + "','" + c.getIdFuncionario() + "') ");
			System.out.println("deu bom gravar cabeçalho");

			ResultSet rs = stmt.executeQuery("SELECT MAX(idCompra) FROM Compra");
			while (rs.next()) {
				codigo = rs.getInt("MAX(idCompra)");
			}

			System.out.println(codigo);
			for (int i = 0; i < produtos.length; i++) {

				stmt.execute(
						"INSERT INTO compra_has_produto(Compra_idCompra, Produto_idProduto, qtdCompra, qtdControle, precoUnitItem, precoTotalItem)VALUES ('"
								+ codigo + "','" + Integer.parseInt(produtos[i][0]) + "','"
								+ Integer.parseInt(produtos[i][3]) + "','0','" + Double.parseDouble(produtos[i][2])
								+ "','" + Double.parseDouble(produtos[i][4]) + "') ");
			}
			System.out.println("deu bom gravar produtos");
			return codigo;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir compras..." + sqle.getMessage());
			return -1;
		} finally {
			bd.Desconectar(conex);

		}

	}

	public int VerificaCompra(NotaEntrada c) {
		conex = bd.Conectar();
		int id = -1;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM compra";
			ResultSet rs = stmt.executeQuery(SQL);
			int nota;
			String cnpj;
			while (rs.next()) {
				nota = rs.getInt("numeroNFECompra");
				cnpj = rs.getString("cnpjFornecCompra");

				if (c.getNumeroNota() == nota && c.getCnpj().toLowerCase().equals(cnpj.toLowerCase())) {
					id = rs.getInt("idCompra");
				}
			}
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return id;
		} finally {
			bd.Desconectar(conex);
		}
		return id;
	}

	public String[][] listarNotas(String campo) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM compra INNER JOIN funcionario ON idfuncCompra = idFuncionario WHERE numeroNFECompra LIKE '%"
					+ campo + "%' OR idFuncCompra LIKE '%" + campo + "%'";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			String clientes[][] = new String[size][7];
			int cont = 0;
			while (rs.next()) {
				clientes[cont][1] = rs.getString("nomeFornecCompra");
				clientes[cont][0] = "" + rs.getInt("numeroNFECompra");
				clientes[cont][2] = rs.getString("cnpjFornecCompra");
				clientes[cont][4] = rs.getString("nomeFunc");
				clientes[cont][5] = "" + rs.getString("dataEntradaCompra");
				clientes[cont][3] = "" + rs.getFloat("valorTotalCompra");
				clientes[cont][6] = "" + rs.getInt("idCompra");
				cont++;
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException sqle) {
			System.out.println("Erro ao listar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public NotaEntrada RetornaNotaEntrada(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM compra where idCompra = " + iid;
			ResultSet rs = stmt.executeQuery(SQL);
			NotaEntrada nota = new NotaEntrada();
			while (rs.next()) {
				nota.setFornecedor(rs.getString("nomeFornecCompra"));
				System.out.println(nota.getFornecedor());
				nota.setCnpj(rs.getString("cnpjFornecCompra"));
				nota.setNumeroNota(rs.getInt("numeroNFECompra"));
				nota.setIdCompra(rs.getInt("idCompra"));
				nota.setDataCompra(rs.getString("dataCompra"));
				nota.setOutros(rs.getFloat("outrosCompra"));
				nota.setTotal(rs.getFloat("valorTotalCompra"));
				nota.setChaveAcesso(rs.getString("chaveAcessoCompra"));
				nota.setDataEntrada(rs.getDate("dataEntradaCompra"));
				nota.setIdFuncionario(rs.getInt("idFuncCompra"));

			}
			return nota;
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public boolean excluirNota(int iid) {
		conex = bd.Conectar();
		ResultSet rs;
		try {
			Statement stmt = (Statement) conex.createStatement();

			rs = stmt.executeQuery("SELECT * FROM compra_has_produto where compra_idCompra = " + iid);

			HashMap<Integer, Integer> prod = new HashMap<Integer, Integer>();
			int cod = 0, qtd = 0, qtdAntigo = 0;
			while (rs.next()) {
				cod = rs.getInt("Produto_idProduto");
				qtd = rs.getInt("qtdCompra");
				prod.put(cod, qtd);

			}

			for (int i : prod.keySet()) {
				rs = stmt.executeQuery("SELECT qtdestoqueProduto FROM produto where idProduto = " + i);
				while (rs.next()) {
					qtdAntigo = rs.getInt("qtdestoqueProduto");
					qtdAntigo -= prod.get(i);
				}
				stmt.executeUpdate("update produto set qtdestoqueProduto = " + qtdAntigo + " where idProduto = "+i);
				qtd=0;
			}
			stmt.executeUpdate("DELETE FROM compra_has_produto where compra_idCompra = " + iid);
			stmt.executeUpdate("DELETE FROM compra where idCompra = " + iid);

			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao excluir..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);
		}

	}

	public String[][] retornaProdutosNota(int iid) {
		conex = bd.Conectar();
		String produtos[][] = null;
		System.out.println(iid);

		Statement stmt;
		try {
			stmt = conex.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT Produto_idProduto, nomeProduto,qtdCompra,precoTotalItem, precoUnitItem from compra_has_produto inner join Produto on Produto_idProduto = idProduto where Compra_idCompra = "
							+ iid);
			int cont = 0;
			rs.last();
			produtos = new String[rs.getRow()][5];
			System.out.println(rs.getRow());
			rs.beforeFirst();
			while (rs.next()) {
				produtos[cont][0] = "" + rs.getInt("Produto_idProduto");
				produtos[cont][1] = rs.getString("nomeProduto");
				produtos[cont][2] = "" + rs.getDouble("precoUnitItem");
				produtos[cont][3] = "" + rs.getDouble("precoTotalItem");
				produtos[cont][4] = "" + rs.getInt("qtdCompra");

				cont++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bd.Desconectar(conex);
		return produtos;
	}

	public float editarCompra(NotaEntrada c, String[][] produtos) {
		conex = bd.Conectar();

		float vAntigo = -1;
		ResultSet rs;
		ResultSet rs1;
		try {

			Statement stmt = conex.createStatement();
			rs = stmt.executeQuery("SELECT valorTotalCompra FROM compra where idCompra = " + c.getIdCompra());
			while (rs.next()) {
				vAntigo = rs.getFloat("valorTotalCompra");
			}

			stmt.execute("UPDATE compra SET valorTotalCompra='" + c.getTotal() + "'  WHERE idCompra='" + c.getIdCompra()
					+ "' ");

			int estoque = 0;
			int qtdControle = 0;
			for (int i = 0; i < produtos.length; i++) {
				rs = stmt.executeQuery("SELECT * FROM compra_has_produto  WHERE Produto_idProduto = '"
						+ Integer.parseInt(produtos[i][0]) + "' AND Compra_idCompra = " + c.getIdCompra());
				if (!rs.next()) {
					stmt.execute(
							"INSERT INTO compra_has_produto(Compra_idCompra, Produto_idProduto, qtdCompra, qtdControle, precoUnitItem, precoTotalItem)VALUES ('"
									+ c.getIdCompra() + "','" + Integer.parseInt(produtos[i][0]) + "','"
									+ Integer.parseInt(produtos[i][3]) + "','0','" + Double.parseDouble(produtos[i][2])
									+ "','" + Double.parseDouble(produtos[i][4]) + "') ");

					rs1 = stmt.executeQuery(
							"SELECT qtdEstoqueProduto, qtdControle FROM Produto INNER JOIN compra_has_produto  WHERE Produto_idProduto = idProduto AND Compra_idCompra = '"
									+ c.getIdCompra() + "' AND idProduto = " + Integer.parseInt(produtos[i][0]));
					while (rs1.next()) {
						estoque = rs.getInt("qtdEstoqueProduto");
						qtdControle = rs.getInt("qtdControle");
					}

					estoque -= (Integer.parseInt(produtos[i][2]) - qtdControle);
					stmt.execute("UPDATE produto SET qtdEstoqueProduto='" + estoque + "' where idProduto = "
							+ Integer.parseInt(produtos[i][0]));

					stmt.execute("UPDATE compra_has_produto SET qtdControle='" + Integer.parseInt(produtos[i][2])
							+ "' where Produto_idProduto = " + Integer.parseInt(produtos[i][0])
							+ " and Compra_idCompra = '" + c.getIdCompra() + "'");

					estoque = 0;
					qtdControle = 0;

				}

			}

			return vAntigo;
		} catch (SQLException sqle) {
			System.out.println("Erro ao alterar compras..." + sqle.getMessage());
			return vAntigo;
		} finally {
			bd.Desconectar(conex);

		}

	}

}
