package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataBase;

public class ContaDAO {

	DataBase bd = DataBase.getInstance();
	Connection conex;
	private static ContaDAO contaDAO;
	
	public static synchronized ContaDAO getInstance() {
		if(contaDAO == null) {
			contaDAO = new ContaDAO();
		}
		return contaDAO;
	}
	

	public boolean atualizarConta(Pedido p) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			float aReceber = 0;
			float totalCompras = 0;

			ResultSet rs = stmt.executeQuery(
					"SELECT * from conta where Cliente_idCliente = '"+p.getIdCliente()+"'");
			while (rs.next()) {
				aReceber = rs.getFloat("aReceber");
				totalCompras = rs.getFloat("totalCompras");
			}
			aReceber += p.getPrecoPed();
			totalCompras += p.getPrecoPed();
			System.out.println(totalCompras);
			stmt.execute("UPDATE conta SET aReceber='" + aReceber + "', totalCompras='"
					+ totalCompras + "' where Cliente_idCliente = '" + p.getIdCliente()+"'");
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir pedidos..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);

		}
	}
	
	public boolean atualizaValorConta(float valor, int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			float aReceber = 0;
			float totalCompras = 0;

			ResultSet rs = stmt.executeQuery(
					"SELECT * from conta where Cliente_idCliente = '"+iid+"'");
			while (rs.next()) {
				aReceber = rs.getFloat("aReceber");
				totalCompras = rs.getFloat("totalCompras");
			}
			aReceber += valor;
			totalCompras += valor;
			stmt.execute("UPDATE conta SET aReceber='" + aReceber + "', totalCompras='"
					+ totalCompras + "' where Cliente_idCliente = '" +iid+"'");
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir pedidos..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);
		}
	}
	
	public float retornaAReceber(int iid) {
		conex = bd.Conectar();
		
		try {
			Statement stmt = conex.createStatement();
			float aReceber = 0;

			ResultSet rs = stmt.executeQuery(
					"SELECT * from conta where Cliente_idCliente = '"+iid+"'");
			while (rs.next()) {
				aReceber = rs.getFloat("aReceber");
			}
			return aReceber;
		}catch (SQLException sqle) {
			System.out.println("Erro ao inserir pedidos..." + sqle.getMessage());
			return -1;
		} finally {
			bd.Desconectar(conex);

		}
	}
	
	
	public int excluirConta(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM conta where Cliente_idCliente = " + iid;
			result = stmt.executeUpdate(SQL);

		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			result = 0;
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}
	
	public boolean abaterValor(int iid, float valor) {
		conex = bd.Conectar();
		
		try {
			Statement stmt = conex.createStatement();
			float pago = 0;
			float aReceber = 0;

			ResultSet rs = stmt.executeQuery(
					"SELECT * from conta where Cliente_idCliente = '"+iid+"'");
			while (rs.next()) {
				pago = rs.getFloat("pago");
				aReceber = rs.getFloat("aReceber");
			}
			pago += valor; 
			stmt.execute("UPDATE conta SET pago='" + pago + "', aReceber='"+ (aReceber-valor)+"' where Cliente_idCliente = '" + iid +"'");
			return true;
		}catch (SQLException sqle) {
			System.out.println("Erro ao inserir pedidos..." + sqle.getMessage());
			return false;
		} finally {
			bd.Desconectar(conex);

		}
	}
}
