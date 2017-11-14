package model;

import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.Test;

public class TestCases {
	
	private Cliente newCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("01601601616");
		cliente.setFoto("");
		cliente.setRua("Rua");
		cliente.setComplemento("complemento");
		cliente.setNumero("001");
		cliente.setBairro("bairro");
		cliente.setCidade("cidade");
		cliente.setCep("7890");
		cliente.setDataNascimento("30/04/3004");
		cliente.setTelefone("34713471");
		cliente.setCelular("988988898");
		cliente.setDataCadastro(new Date(3004-10-30));
		
		return cliente;	
	}
	
	//Test case 001
	@Test
	public void testGravarClienteSucesso() {
		Cliente cliente = newCliente();
		cliente.setNome("Cliente");
		ClienteDAO dao = new ClienteDAO();
		
		assertTrue(dao.gravarCliente(cliente));
	}
	
	//Test case 002
	@Test
	public void testGravarClienteFracasso() {
		Cliente cliente = newCliente();
		ClienteDAO dao = new ClienteDAO();
		
		assertFalse(dao.gravarCliente(cliente));
	}
	
	//Test case 003
	@Test
	public void testBuscaCodigoSucesso() {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = newCliente();
		cliente.setNome("Name");
		cliente.setCpf("89089089089");
		dao.gravarCliente(cliente);
		
		assertEquals(1, dao.buscaCodigoCliente("89089089089"));
	}
	
	//Test case 004
	@Test
	public void testBuscaCodigoFracasso() {
		ClienteDAO dao = new ClienteDAO();
		
		assertEquals(0, dao.buscaCodigoCliente("11111111111"));
	}
	
	//Test case  005
	@Test
	public void testRetornarClienteSucesso(){
		Cliente cliente = newCliente();
		cliente.setNome("Cliente");
		cliente.setCpf("67867867867");
		ClienteDAO dao = new ClienteDAO();
		dao.gravarCliente(cliente);
		
		assertEquals(cliente.getNome(), dao.RetornaCliente(dao.buscaCodigoCliente(cliente.getCpf())).getNome() );
	}
	
	//Test case 006
	@Test
	public void testRetornarClienteFracasso() {
		ClienteDAO dao = new ClienteDAO();
		
		assertNull(dao.RetornaCliente(9090).getNome() );
	}
	
	//Test case 007
	@Test
	public void verificarCPFSucesso() {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = newCliente();
		cliente.setNome("Name");
		cliente.setCpf("11122233344");
		dao.gravarCliente(cliente);
		
		assertTrue(dao.verificaCPF("11122233344"));
	}
	
	//Test case 008
	@Test
	public void verificarCPFFracasso() {
		ClienteDAO dao = new ClienteDAO();
		
		assertFalse(dao.verificaCPF("90909090"));
	}
	
	//Test case 009
	@Test
	public void copiarImagemClienteSucesso() {
		
	}
	
	//Test case 010
	@Test
	public void copiarImagemClienteFracasso() {
		
	}
	
	//Test case 011
	@Test
	public void editarClienteSucesso() {
		
	}
	
	//Test case 012
	@Test
	public void editarClienteFracasso() {
		
	}
	
	//Test case 013
	@Test
	public void clientesArraySucesso() {
		ClienteDAO dao = new ClienteDAO();
		
		assertEquals(2, dao.listarClientes("1").length);
	}
	
	//Test case 014
	public void clientesArrayFracasso() {
		ClienteDAO dao = new ClienteDAO();
		
		assertEquals(0, dao.listarClientes("invalid").length);
	}
	
	//Test case 015
	@Test
	public void excluirClienteSucesso() {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = newCliente();
		cliente.setNome("Name");
		cliente.setIdCliente(987);
		dao.gravarCliente(cliente);
		
		assertEquals(1, dao.excluirCliente(987));
	}
	
	//Test case 016
	@Test
	public void excluirClienteFracasso() {
		ClienteDAO dao = new ClienteDAO();
		
		assertEquals(0, dao.excluirCliente(909090));
	}
}
