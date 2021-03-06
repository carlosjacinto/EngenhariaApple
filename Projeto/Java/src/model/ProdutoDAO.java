package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import control.DataBase;

public class ProdutoDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex;
	private static ProdutoDAO classeDAO;
	
	public static synchronized ProdutoDAO getInstance() {
		if(classeDAO == null) {
			classeDAO = new ProdutoDAO();
		}
		return classeDAO;
	}

	public boolean gravarProduto(Produto p) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO Produto(nomeProduto, precoVendaProduto, descricaoProduto, dataCadastroProduto, percLucro)VALUES ('"
							+ p.getNomeProduto() + "','" + p.getPrecoVendaProduto() + "','" + p.getDescricaoProduto()
							+ "','" + p.getDataCadastroProduto() + "','" + p.getPercentualLucro() + "') ");

			if (p.getFotoProduto() != null) {
				int codigo = buscaCodigoProduto(p.getNomeProduto());
				CopiarImagemProduto(codigo, p.getFotoProduto());
			}
			System.out.println("deu bom");
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);
		}

	}

	public int buscaCodigoProduto(String snome) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM produto";
			ResultSet rs = stmt.executeQuery(SQL);
			String nome;
			while (rs.next()) {
				nome = rs.getString("nomeProduto");

				if (nome.toLowerCase().equals(snome.toLowerCase())) {
					return rs.getInt("idProduto");
				}
			}
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return 0;
		} finally {
			bd.Desconectar(conex);
		}
		return 0;
	}

	public Produto RetornaProduto(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM produto where idProduto = " + iid;
			ResultSet rs = stmt.executeQuery(SQL);
			Produto p = new Produto();
			while (rs.next()) {
				p.setIdProduto(rs.getInt("idProduto"));
				p.setDataCadastroProduto(rs.getDate("dataCadastroProduto"));
				p.setNomeProduto(rs.getString("nomeProduto"));
				p.setPrecoCompraProduto(rs.getFloat("precoCompraProduto"));
				p.setPrecoVendaProduto(rs.getFloat("precoVendaProduto"));
				p.setQtdEstoqueProduto(rs.getInt("qtdEstoqueProduto"));
				p.setDescricaoProduto(rs.getString("descricaoProduto"));
				p.setUltimaDataCompraProduto(rs.getDate("ultimaDataCompraProduto"));
				p.setPercentualLucro(rs.getInt("percLucro"));
			}
			return p;
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public boolean verificaNome(String snome) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM produto";
			ResultSet rs = stmt.executeQuery(SQL);
			String nome;
			while (rs.next()) {
				nome = rs.getString("nomeProduto");

				if (nome.toLowerCase().equals(snome.toLowerCase())) {
					return true;
				}
			}
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);
		}
		return false;
	}

	public void CopiarImagemProduto(int codigo, String caminho) {
		FileInputStream origem = null;
		FileOutputStream destino = null;
		FileChannel fcOrigem = null;
		FileChannel fcDestino = null;
		try {
			origem = new FileInputStream(caminho);
			System.out.println();
			destino = new FileOutputStream("Media/Produto/" + codigo + ".png");
			fcOrigem = origem.getChannel();
			fcDestino = destino.getChannel();
			fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
			origem.close();
			destino.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean editarProduto(Produto p) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute("UPDATE produto SET nomeProduto='" + p.getNomeProduto() + "', precoVendaProduto='"
					+ p.getPrecoVendaProduto() + "', descricaoProduto='" + p.getDescricaoProduto()
					+ "' where idProduto = " + p.getIdProduto());
			stmt.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao alterar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return false;
	}

	public int excluirProduto(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM produto where idProduto = " + iid;
			result = stmt.executeUpdate(SQL);

		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}

	public double buscarPrecoVenda(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT precoVendaProduto FROM Produto WHERE idProduto = " + iid;
			ResultSet rs = stmt.executeQuery(SQL);

			double precoVenda = 0;
			while (rs.next()) {
				precoVenda = rs.getDouble("precoVendaProduto");
			}
			rs.close();
			stmt.close();
			return precoVenda;
		} catch (SQLException sqle) {
			System.out.println("Erro ao listar..." + sqle.getMessage());
			return 0;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public String[][] listarProdutos(String campo) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM Produto WHERE nomeProduto LIKE '%" + campo + "%' OR idProduto LIKE '%" + campo
					+ "%'";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			String produtos[][] = new String[size][5];
			int cont = 0;
			while (rs.next()) {
				produtos[cont][1] = rs.getString("nomeProduto");
				produtos[cont][0] = "" + rs.getInt("idProduto");
				produtos[cont][2] = "" + rs.getLong("precoVendaProduto");
				produtos[cont][3] = "" + rs.getLong("precoCompraProduto");
				produtos[cont][4] = "" + rs.getInt("qtdEstoqueProduto");
				cont++;
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (SQLException sqle) {
			System.out.println("Erro ao listar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public ArrayList<Produto> retornaProdutoArrayList(String campo) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM Produto WHERE nomeProduto LIKE '%" + campo + "%' OR idProduto LIKE '%" + campo
					+ "%'";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			rs.beforeFirst();
			ArrayList<Produto> produtos = new ArrayList<>();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setDataCadastroProduto(rs.getDate("dataCadastroProduto"));
				produto.setDescricaoProduto("descricaoProduto");
				produto.setPercentualLucro(rs.getInt("percLucro"));
				produto.setPrecoCompraProduto(rs.getFloat("precoCompraProduto"));
				produto.setPrecoVendaProduto(rs.getFloat("precoVendaProduto"));
				produto.setQtdEstoqueProduto(rs.getInt("qtdEstoqueProduto"));
				produto.setUltimaDataCompraProduto(rs.getDate("ultimaDataCompraProduto"));
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (SQLException sqle) {
			System.out.println("Erro ao listar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}
	
	public String[] buscarNomeeId() {
		conex = bd.Conectar();
		String produtos[];
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT idProduto,nomeProduto FROM Produto";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			produtos = new String[size];
			int cont = 0;
			while (rs.next()) {
				produtos[cont] = rs.getInt("idProduto") + "-" + rs.getString("nomeProduto");
				cont++;
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (SQLException sqle) {
			System.out.println("Erro ao listar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public boolean PermitirExclusaoProduto(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT COUNT(*) FROM pedido_has_produto WHERE Produto_idProduto = " + iid);
			rs.next();
			if (rs.getInt("count(*)") != 0)
				return false;

			rs = stmt.executeQuery("SELECT COUNT(*) FROM compra_has_produto WHERE Produto_idProduto = " + iid);
			rs.next();
			if (rs.getInt("count(*)") != 0)
				return false;

		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return true;
	}

	public boolean atualizaProdutoCompra(String[][] produtos, boolean preco, int numNFe) {
		conex = bd.Conectar();

		try {
			Statement stmt = (Statement) conex.createStatement();
			float perc = 0;
			int estoque = 0;
			int qtdControle = 0;
			Date data = new Date(System.currentTimeMillis());

			ResultSet rs;
			if (preco) {
				for (int i = 0; i < produtos.length; i++) {

					rs = stmt.executeQuery(
							"SELECT qtdEstoqueProduto,percLucro, qtdControle FROM Produto INNER JOIN compra_has_produto  WHERE Produto_idProduto = idProduto AND Compra_idCompra = '"
									+ numNFe + "' AND idProduto = " + Integer.parseInt(produtos[i][0]));
					while (rs.next()) {
						perc = rs.getInt("percLucro");
						estoque = rs.getInt("qtdEstoqueProduto");
						qtdControle = rs.getInt("qtdControle");
					}

					estoque += Integer.parseInt(produtos[i][3]) - qtdControle;
					perc = (float) (1 + perc / 100.0);
					System.out.println(perc);
					stmt.execute("UPDATE produto SET qtdEstoqueProduto='" + estoque + "', precoCompraProduto='"
							+ Double.parseDouble(produtos[i][2]) + "', ultimaDataCompraProduto='" + data
							+ "', precoVendaProduto='" + Double.parseDouble(produtos[i][2]) * perc
							+ "' where idProduto = " + Integer.parseInt(produtos[i][0]));

					stmt.execute("UPDATE compra_has_produto SET qtdControle='" + Integer.parseInt(produtos[i][3])
							+ "' where Produto_idProduto = " + Integer.parseInt(produtos[i][0])
							+ " and Compra_idCompra = '" + numNFe + "'");

					perc = 0;
					estoque = 0;
					qtdControle = 0;

				}
			} else {
				for (int i = 0; i < produtos.length; i++) {

					rs = stmt.executeQuery(
							"SELECT qtdEstoqueProduto, qtdControle FROM Produto INNER JOIN compra_has_produto  WHERE Produto_idProduto = idProduto AND Compra_idCompra = '"
									+ numNFe + "' AND idProduto = " + Integer.parseInt(produtos[i][0]));
					while (rs.next()) {
						estoque = rs.getInt("qtdEstoqueProduto");
						qtdControle = rs.getInt("qtdControle");
					}

					estoque += Integer.parseInt(produtos[i][3]) - qtdControle;
					System.out.println(perc);
					stmt.execute("UPDATE produto SET qtdEstoqueProduto='" + estoque + "' where idProduto = "
							+ Integer.parseInt(produtos[i][0]));

					stmt.execute("UPDATE compra_has_produto SET qtdControle='" + Integer.parseInt(produtos[i][3])
							+ "' where Produto_idProduto = " + Integer.parseInt(produtos[i][0])
							+ " and Compra_idCompra = '" + numNFe + "'");

					estoque = 0;
					qtdControle = 0;

				}
			}
			stmt.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Erro ao atualizar produtos..." + sqle.getMessage());
			return false;
		} finally {

			bd.Desconectar(conex);

		}
	}

	public boolean atualizaProdutoVenda(String[][] produtos, int numNFe) {
		conex = bd.Conectar();

		try {
			Statement stmt = (Statement) conex.createStatement();
			int estoque = 0;
			int qtdControle = 0;

			ResultSet rs;

			for (int i = 0; i < produtos.length; i++) {

				rs = stmt.executeQuery(
						"SELECT qtdEstoqueProduto, qtdControle FROM Produto INNER JOIN pedido_has_produto  WHERE Produto_idProduto = idProduto AND Pedido_idPedido = '"
								+ numNFe + "' AND idProduto = " + Integer.parseInt(produtos[i][0]));
				while (rs.next()) {
					estoque = rs.getInt("qtdEstoqueProduto");
					qtdControle = rs.getInt("qtdControle");
				}

				estoque -= (Integer.parseInt(produtos[i][2]) - qtdControle);
				stmt.execute("UPDATE produto SET qtdEstoqueProduto='" + estoque + "' where idProduto = "
						+ Integer.parseInt(produtos[i][0]));

				stmt.execute("UPDATE pedido_has_produto SET qtdControle='" + Integer.parseInt(produtos[i][2])
						+ "' where Produto_idProduto = " + Integer.parseInt(produtos[i][0]) + " and Pedido_idPedido = '"
						+ numNFe + "'");

				estoque = 0;
				qtdControle = 0;

			}

			stmt.close();
			return true;

		} catch (

		SQLException sqle) {
			System.out.println("Erro ao atualizar produtos..." + sqle.getMessage());
			return false;
		} finally {

			bd.Desconectar(conex);

		}

	}

}
