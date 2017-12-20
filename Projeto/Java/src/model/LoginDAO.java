package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataBase;

public class LoginDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex = bd.Conectar();
	private static LoginDAO classeDAO;
	
	public static synchronized LoginDAO getInstance() {
		if(classeDAO == null) {
			classeDAO = new LoginDAO();
		}
		return classeDAO;
	}
	
	public int verificarSeExisteLogin(String cpf, String senha) {
		conex = bd.Conectar();
		
		Statement stmt;
		ResultSet rs;
		try {
			stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM funcionario WHERE cpfFunc LIKE '" + cpf + "' and senhaFunc LIKE '" + senha
					+ "'";
			rs = stmt.executeQuery(SQL);
			if(!rs.next()) {
				return 0;
			}
			if(rs.getBoolean("administrador")) {
				return 1;
			}
			return 2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
		
	}

}
