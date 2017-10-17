package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Test;

public class TestCases {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	private Cliente newCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("01601601616");
		cliente.setFoto("URL"); //colocar path valido
		cliente.setRua("Rua");
		cliente.setComplemento("complemento");
		cliente.setNumero("001");
		cliente.setBairro("bairro");
		cliente.setCidade("cidade");
		cliente.setCep("cep");
		cliente.setDataNascimento("30/04/3004");
		cliente.setTelefone("34713471");
		cliente.setCelular("988988898");
		cliente.setDataCadastro(new Date(3004-10-30));
		
		return cliente;	
	}
	
	@Test
	public void testGravarClienteSucesso() {
		Cliente cliente = newCliente();
		cliente.setNome("Cliente");
		ClienteDAO dao = new ClienteDAO();
		
		assertTrue(dao.gravarCliente(cliente));
	}
	
	@Test
	public void testClienteFracasso() {
		Cliente cliente = newCliente();
		ClienteDAO dao = new ClienteDAO();
		
		assertFalse(dao.gravarCliente(cliente));
	}
	
	@Test
	public void testBuscaCodigoSucesso() {
		ClienteDAO dao = new ClienteDAO();
		assertEquals(1, dao.buscaCodigoCliente(""));//colocar cpf cadastrado
	}
	
	@Test
	public void testBuscaCodigoFracasso() {
		ClienteDAO dao = new ClienteDAO();
		assertEquals(0, dao.buscaCodigoCliente("11111111111"));//colocar cpf cadastrado
	}
	
	@Test
	public void testRetornarClienteSucesso(){
		Cliente cliente = newCliente();
		cliente.setNome("Cliente");
		ClienteDAO dao = new ClienteDAO();
		dao.gravarCliente(cliente);
		
		assertEquals(cliente.getNome(), dao.RetornaCliente(dao.buscaCodigoCliente(cliente.getCpf())).getNome() );
	}
	
	@Test
	public void testRetornarClienteFracasso() {
		
		Cliente cliente = newCliente();
		cliente.setNome("Cliente");
		ClienteDAO dao = new ClienteDAO();
		dao.gravarCliente(cliente);
		
		assertNull(dao.RetornaCliente(dao.buscaCodigoCliente("")).getNome() );
		
	}
	
	

}
