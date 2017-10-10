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

public class FuncionarioDAO {
	DataBase bd = DataBase.getInstance();
	Connection conex = bd.Conectar();

	public boolean gravarFuncionario(Funcionario f) {
		conex = bd.Conectar();
		int adm=0;
		if(f.isAdministrador()) adm=1;
		try {
			Statement stmt = conex.createStatement();
			stmt.execute(
					"INSERT INTO funcionario(nomeFunc, ruaFunc, compFunc, numeroFunc, bairroFunc, cidadeFunc, dataNascFunc, dataAdmissaoFunc, cepFunc, senhaFunc, telefoneFunc, celularFunc, cpfFunc, salarioFunc, comissaoFunc, administrador)VALUES ('"
							+ f.getNome() + "','" + f.getRua() + "','" + f.getComplemento() + "','" + f.getNumero()
							+ "','" + f.getBairro() + "','" + f.getCidade() + "','" + f.getDataNascimento() + "','"
							+ f.getDataAdmissao() + "','" + f.getCep() + "','" + f.getSenha() + "','" + f.getTelefone() + "','" + f.getCelular() + "','" + f.getCpf() + "','"
							+ f.getSalario() + "','" + f.getComissao() + "','" + adm +"') ");

			if (f.getFoto() != null) {
				int codigo = buscaCodigoFuncionario(f.getCpf());
				CopiarImagemFuncionario(codigo, f.getFoto());
			}else {
				int codigo = buscaCodigoFuncionario(f.getCpf());
				CopiarImagemFuncionario(codigo, "Interno/default-avatar.png");
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

	public int buscaCodigoFuncionario(String CPF) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM funcionario";
			ResultSet rs = stmt.executeQuery(SQL);
			String cpfF;
			while (rs.next()) {
				cpfF = rs.getString("cpfFunc");

				if (cpfF.toLowerCase().equals(CPF.toLowerCase())) {
					return rs.getInt("idFuncionario");
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
	
	public int excluirFuncionario(int iid) {
		conex = bd.Conectar();
		int result = 0;
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "DELETE FROM funcionario where idFuncionario = "+iid;
			result = stmt.executeUpdate(SQL);
			
		} catch (SQLException sqle) {
			System.out.println("Erro ao consultar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return result;
	}

	public Funcionario RetornaFuncionario(int iid) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM funcionario where idFuncionario = "+iid;
			ResultSet rs = stmt.executeQuery(SQL);
			Funcionario func = new Funcionario();
			while (rs.next()) {
				func.setNome(rs.getString("nomeFunc"));
				func.setIdFuncionario(rs.getInt("idFuncionario"));
				func.setCpf(rs.getString("cpfFunc"));
				func.setDataNascimento(rs.getString("dataNascFunc"));
				func.setTelefone(rs.getLong("telefoneFunc"));
				func.setRua(rs.getString("ruaFunc"));
				func.setBairro(rs.getString("bairroFunc"));
				func.setCidade(rs.getString("cidadeFunc"));
				func.setAdministrador(rs.getBoolean("administrador"));
				func.setCelular(rs.getLong("celularFunc"));
				func.setCep(rs.getString("cepFunc"));
				func.setComissao(rs.getDouble("comissaoFunc"));
				func.setComplemento(rs.getString("compFunc"));
				func.setDataAdmissao(rs.getDate("dataAdmissaoFunc"));
				func.setNumero(rs.getString("numeroFunc"));
				func.setSalario(rs.getDouble("salarioFunc"));
				func.setSenha(rs.getString("senhaFunc"));
			}
			return func;
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
			String SQL = "SELECT * FROM funcionario";
			ResultSet rs = stmt.executeQuery(SQL);
			String cpfF;
			while (rs.next()) {
				cpfF = rs.getString("cpfFunc");

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

	public void CopiarImagemFuncionario(int codigo, String caminho) {
		FileInputStream origem = null;
		FileOutputStream destino = null;
		FileChannel fcOrigem = null;
		FileChannel fcDestino = null;
		try {
			origem = new FileInputStream(caminho);
			System.out.println();
			destino = new FileOutputStream("Media/Funcionario/" + codigo + ".png");
			fcOrigem = origem.getChannel();
			fcDestino = destino.getChannel();
			fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
			origem.close();
			destino.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean editarFuncionario(Funcionario f) {
		conex = bd.Conectar();
		try {
			Statement stmt = conex.createStatement();
			stmt.execute("UPDATE funcionario SET nomeFunc='" + f.getNome() + "', ruaFunc='" + f.getRua()
					+ "', compFunc='" + f.getComplemento() + "', numeroFunc='" + f.getNumero() + "', bairroFunc='"
					+ f.getBairro() + "', cidadeFunc='" + f.getCidade() + "', dataNascFunc='" + f.getDataNascimento()
					+ "', cepFunc ='" + f.getCep() + "', celularFunc='" + f.getCelular() + "', senhaFunc='"
					+ f.getSenha() + "', salarioFunc='" + f.getSalario() + "', comissaoFunc='" + f.getComissao()
					+ "', telefoneFunc='" + f.getTelefone() + "'  WHERE idFuncionario='" + f.getIdFuncionario() + "' ");
			stmt.close();
			if (f.getFoto() != null) {
				CopiarImagemFuncionario(f.getIdFuncionario(), f.getFoto());
			}
			return true;
		} catch (SQLException sqle) {
			System.out.println("Erro ao alterar..." + sqle.getMessage());
		} finally {
			bd.Desconectar(conex);
		}
		return false;
	}

	public String[][] listaFuncionarioArray(String campo) {
		conex = bd.Conectar();
		try {
			Statement stmt = (Statement) conex.createStatement();
			String SQL = "SELECT * FROM funcionario WHERE nomeFunc LIKE '%" + campo + "%' OR cpfFunc LIKE '%" + campo
					+ "%'";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			String funcs[][] = new String[size][6];
			int cont = 0;
			while (rs.next()) {
				funcs[cont][1] = rs.getString("nomeFunc");
				funcs[cont][0] = "" + rs.getInt("idFuncionario");
				funcs[cont][2] = "" + rs.getLong("cpfFunc");
				funcs[cont][4] = "" + rs.getLong("telefoneFunc");
				funcs[cont][5] = "" + rs.getString("dataNascFunc");
				funcs[cont][3] = rs.getString("ruaFunc");
				cont++;
			}
			rs.close();
			stmt.close();
			return funcs;
		} catch (SQLException sqle) {
			System.out.println("Erro ao listar..." + sqle.getMessage());
			return null;
		} finally {
			bd.Desconectar(conex);
		}
	}
}

/*
public ArrayList<Funcionario> buscaCPFNomeFuncionario(String campo) {
	conex = bd.Conectar();

	try {
		Statement stmt = (Statement) conex.createStatement();
		String SQL = "SELECT * FROM funcionario WHERE nomeFunc LIKE '%" + campo + "%' OR cpfFunc LIKE '" + campo
				+ "'";
		ResultSet rs = stmt.executeQuery(SQL);
		ArrayList<Funcionario> funcs = new ArrayList<>();
		Funcionario func = new Funcionario();
		while (rs.next()) {
			func.setNome(rs.getString("nomeFunc"));
			func.setIdFuncionario(rs.getInt("idFuncionario"));
			func.setCpf(rs.getString("cpfFunc"));
			func.setDataNascimento(rs.getDate("dataNascFunc"));
			func.setTelefone(rs.getLong("telefoneFunc"));
			func.setRua(rs.getString("ruaFunc"));
			funcs.add(func);

		}
		rs.close();
		stmt.close();
		return funcs;
	} catch (SQLException sqle) {
		System.out.println("Erro ao consultar..." + sqle.getMessage());
		return null;
	} finally {
		bd.Desconectar(conex);
	}
}

public ArrayList<Funcionario> listaFuncionario() {
	conex = bd.Conectar();

	try {
		Statement stmt = (Statement) conex.createStatement();
		String SQL = "SELECT * FROM funcionario";
		ResultSet rs = stmt.executeQuery(SQL);
		ArrayList<Funcionario> funcs = new ArrayList<>();
		Funcionario func = new Funcionario();
		while (rs.next()) {
			func.setNome(rs.getString("nomeFunc"));
			func.setIdFuncionario(rs.getInt("idFuncionario"));
			func.setCpf(rs.getString("cpfFunc"));
			func.setDataNascimento(rs.getDate("dataNascFunc"));
			func.setTelefone(rs.getLong("telefoneFunc"));
			func.setRua(rs.getString("ruaFunc"));
			func.setBairro(rs.getString("bairroFunc"));
			func.setCidade(rs.getString("cidadeFunc"));
			func.setAdministrador(rs.getBoolean("administrador"));
			func.setCelular(rs.getLong("celularFunc"));
			func.setCep(rs.getString("cepFunc"));
			func.setComissao(rs.getDouble("comissaoFunc"));
			func.setComplemento(rs.getString("compFunc"));
			func.setAdmissao(rs.getDate("dataAdmissao"));
			func.setFoto(rs.getString("fotoFunc"));
			func.setNumero(rs.getString("numeroFunc"));
			func.setSalario(rs.getDouble("salarioFunc"));
			func.setSenha(rs.getString("senhaFunc"));
			funcs.add(func);
		}
		rs.close();
		stmt.close();
		return funcs;
	} catch (SQLException sqle) {
		System.out.println("Erro ao consultar..." + sqle.getMessage());
		return null;
	} finally {
		bd.Desconectar(conex);
	}
}
*/
