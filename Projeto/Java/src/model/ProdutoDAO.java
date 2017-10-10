package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataBase;

public class ProdutoDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex;

	public boolean gravarProduto(Produto p) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO Produto(nomeProduto, precoVendaProduto, descricaoProduto, dataCadastroProduto)VALUES ('"
							+ p.getNomeProduto() + "','" + p.getPrecoVendaProduto() + "','" + p.getDescricaoProduto()+ "','" + p.getDataCadastroProduto() +"') ");

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
			String SQL = "SELECT * FROM produto where idProduto = "+iid;
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
			stmt.execute("UPDATE funcionario SET nomeProduto='" + p.getNomeProduto() + "', precoVendaProduto='" + p.getPrecoVendaProduto() + "', descricaoProduto='" + p.getDescricaoProduto() + "' ");
			stmt.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao alterar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return false;
	}

	public String[][] listaProdutoArray(String campo) {
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
				System.out.println(rs.getString("nomeProduto"));
				produtos[cont][1] = rs.getString("nomeProduto");
				produtos[cont][0] = "" + rs.getInt("idProduto");
				produtos[cont][2] = "" + rs.getLong("precoVendaProduto");
				produtos[cont][3] = "" + rs.getLong("precoVendaProduto");
				produtos[cont][4] = "" + rs.getDate("qtdEstoqueProduto");
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
}
