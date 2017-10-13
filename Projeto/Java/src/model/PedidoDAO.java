package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataBase;

public class PedidoDAO {

	DataBase bd = DataBase.getInstance();
	Connection conex;

	public boolean gravarPedido(Pedido c) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute("");
			System.out.println("deu bom");
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao inserir..." + sqle.getMessage());
			return false;
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

			}
			return nota;
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}

	public String[][] listaPedidoArray(String campo) {
		conex = bd.Conectar();
		int size =0;
		String pedidos[][] = new String[size][6];

		bd.Desconectar(conex);
		return pedidos;
	}

	public int excluirPedido(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM pedido where idPedido = " + iid;
			result = stmt.executeUpdate(SQL);

		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}
}
