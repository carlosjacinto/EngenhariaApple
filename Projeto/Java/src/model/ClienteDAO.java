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

public class ClienteDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex;

	public boolean gravarCliente(Cliente c) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO Cliente(nomeCliente, ruaCliente, compCliente, numeroCliente, bairroCliente, cidadeCliente, cepCliente, telefoneCliente, celularCliente, cpfCliente, dataCadastro, dataNascCliente)VALUES ('"
							+ c.getNome() + "','" + c.getRua() + "','" + c.getComplemento() + "','" + c.getNumero()
							+ "','" + c.getBairro() + "','" + c.getCidade()  + "','" + c.getCep() + "','" + c.getTelefone() + "','" + c.getCelular() + "','" + c.getCpf() +"','" + c.getDataCadastro() +"','" + c.getDataNascimento() +"') ");

			if (c.getFoto() != null) {
				int codigo = buscaCodigoCliente(c.getCpf());
				CopiarImagemCliente(codigo, c.getFoto());
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
	

	public int buscaCodigoCliente(String CPF) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM cliente";
			ResultSet rs = stmt.executeQuery(SQL);
			String cpfF;
			while (rs.next()) {
				cpfF = rs.getString("cpfCliente");

				if (cpfF.toLowerCase().equals(CPF.toLowerCase())) {
					return rs.getInt("idCliente");
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

	public Cliente RetornaCliente(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM cliente where idCliente = "+iid;
			ResultSet rs = stmt.executeQuery(SQL);
			Cliente c = new Cliente();
			while (rs.next()) {
				c.setIdCliente(rs.getInt("idCliente"));
				c.setNome(rs.getString("nomeCliente"));
				c.setCpf(rs.getString("cpfCliente"));
				c.setBairro(rs.getString("bairroCliente"));
				c.setCidade(rs.getString("cidadeCliente"));
				c.setRua(rs.getString("ruaCliente"));
				c.setNumero(rs.getString("numeroCliente"));
				c.setCep(rs.getString("cepCliente"));
				c.setDataCadastro(rs.getDate("dataCadastro"));
				c.setComplemento(rs.getString("compCliente"));
				c.setDataNascimento(rs.getString("dataNascCliente"));
				c.setTelefone(rs.getString("telefoneCliente"));
				c.setCelular(rs.getString("celularCliente"));
				
			}
			return c;
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}
	public boolean verificaCPF(String CPF) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM cliente";
			ResultSet rs = stmt.executeQuery(SQL);
			String cpfF;
			while (rs.next()) {
				cpfF = rs.getString("cpfCliente");

				if (cpfF.toLowerCase().equals(CPF.toLowerCase())) {
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

	public void CopiarImagemCliente(int codigo, String caminho) {
		FileInputStream origem = null;
		FileOutputStream destino = null;
		FileChannel fcOrigem = null;
		FileChannel fcDestino = null;
		try {
			origem = new FileInputStream(caminho);
			System.out.println();
			destino = new FileOutputStream("Media/Cliente/" + codigo + ".png");
			fcOrigem = origem.getChannel();
			fcDestino = destino.getChannel();
			fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
			origem.close();
			destino.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean editarCliente(Cliente c) {
		conex = bd.Conectar();
		System.out.println("entrou");
		try {
			Statement stmt = conex.createStatement();
			stmt.execute("UPDATE cliente SET nomeCliente='" + c.getNome() + "', ruaCliente='" + c.getRua()
					+ "', compCliente='" + c.getComplemento() + "', numeroCliente='" + c.getNumero() + "', bairroCliente='"
					+ c.getBairro() + "', cidadeCliente='" + c.getCidade() + "', dataNascCliente='" + c.getDataNascimento()
					+ "', cepCliente ='" + c.getCep() + "', celularCliente='" + c.getCelular() + "', telefoneCliente='" + c.getTelefone() + "'  WHERE idCliente='" + c.getIdCliente() + "' ");
			stmt.close();
			if (c.getFoto() != null) {
				CopiarImagemCliente(c.getIdCliente(), c.getFoto());
			}
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao alterar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return false;
	}

	public String[][] listaClienteArray(String campo) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM Cliente WHERE nomeCliente LIKE '%" + campo + "%' OR cpfCliente LIKE '%" + campo
					+ "%'";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			String clientes[][] = new String[size][6];
			int cont = 0;
			while (rs.next()) {
				clientes[cont][1] = rs.getString("nomeCliente");
				clientes[cont][0] = "" + rs.getInt("idCliente");
				clientes[cont][2] = "" + rs.getString("cpfCliente");
				clientes[cont][4] = "" + rs.getString("telefoneCliente");
				clientes[cont][5] = "" + rs.getString("dataNascCliente");
				clientes[cont][3] = rs.getString("ruaCliente");
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

	public int excluirCliente(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM cliente where idCliente = "+iid;
			result = stmt.executeUpdate(SQL);
			
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}
	
}
