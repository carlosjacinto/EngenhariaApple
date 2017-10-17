package model;

import java.sql.Date;

public class Pedido {
	private int idPedido;
	private int idCliente;
	private int idFuncionario;
	private Date dataPed;
	private float precoPed;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public float getPrecoPed() {
		return precoPed;
	}

	public void setPrecoPed(float precoPed) {
		this.precoPed = precoPed;
	}

	public Date getDataPed() {
		return dataPed;
	}

	public void setDataPed(Date dataPed) {
		this.dataPed = dataPed;
	}

}
