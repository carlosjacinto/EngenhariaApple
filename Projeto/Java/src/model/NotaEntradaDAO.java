package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataBase;

public class NotaEntradaDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex;

	public boolean gravarCompra(NotaEntrada c) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO Compra(numeroNFECompra, nomeFornecCompra, cnpjFornecCompra, dataCompra, outrosCompra, valorTotalCompra, chaveAcessoCompra, dataEntradaCompra, idFuncCompra)VALUES ('"
							+ c.getNumeroNota() + "','" + c.getFornecedor() + "','" + c.getCnpj() + "','"
							+ c.getDataCompra() + "','" + c.getOutros() + "','" + c.getTotal() + "','"
							+ c.getChaveAcesso() + "','" + c.getDataEntrada() +  "','" + c.getIdFuncionario()+"') ");
			System.out.println("deu bom");
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);
		}

	}

	public boolean VerificaCompra(int nota, String scnpj) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM compra";
			ResultSet rs = stmt.executeQuery(SQL);
			int n;
			String cnpj;
			while (rs.next()) {
				n = rs.getInt("numeroNFECompra");
				cnpj = rs.getString("cnpjFornecCompra");

				if (n==nota && scnpj.toLowerCase().equals(cnpj.toLowerCase())) {
					return false;
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
	
	public String[][] listaNotaEntradaArray(String campo) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM compra WHERE numeroNFECompra LIKE '%" + campo + "%' OR idFuncCompra LIKE '%" + campo
					+ "%'";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			String clientes[][] = new String[size][6];
			int cont = 0;
			while (rs.next()) {
				clientes[cont][1] = rs.getString("nomeFornecCompra");
				clientes[cont][0] = "" + rs.getInt("numeroNFECompra");
				clientes[cont][2] = rs.getString("cnpjFornecCompra");
				clientes[cont][4] = "" + rs.getInt("idFuncCompra");
				clientes[cont][5] = "" + rs.getString("dataEntradaCompra");
				clientes[cont][3] = "" + rs.getFloat("valorTotalCompra");
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
			String SQL = "SELECT * FROM compra where idCompra = "+iid;
			ResultSet rs = stmt.executeQuery(SQL);
			NotaEntrada nota = new NotaEntrada();
			while (rs.next()) {
				nota.setFornecedor(rs.getString("nomeFornecCompra"));
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
	
	public int excluirNotaEntrada(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM compra where idCompra = "+iid;
			result = stmt.executeUpdate(SQL);
			
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}
}
