package model;

import java.util.Date;

public class Produto {
	private int idProduto;
	private String nomeProduto;
	private float precoCompraProduto;
	private float precoVendaProduto;
	private int qtdEstoqueProduto;
	private String fotoProduto;
	private String descricaoProduto;
	private Date ultimaDataCompraProduto;
	private Date dataCadastroProduto;
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public float getPrecoCompraProduto() {
		return precoCompraProduto;
	}
	public void setPrecoCompraProduto(float precoCompraProduto) {
		this.precoCompraProduto = precoCompraProduto;
	}
	public Date getUltimaDataCompraProduto() {
		return ultimaDataCompraProduto;
	}
	public void setUltimaDataCompraProduto(Date ultimaDataCompraProduto) {
		this.ultimaDataCompraProduto = ultimaDataCompraProduto;
	}
	public Date getDataCadastroProduto() {
		return dataCadastroProduto;
	}
	public void setDataCadastroProduto() {
		this.dataCadastroProduto = new Date(System.currentTimeMillis());
	}
	public float getPrecoVendaProduto() {
		return precoVendaProduto;
	}
	public void setPrecoVendaProduto(float precoVendaProduto) {
		this.precoVendaProduto = precoVendaProduto;
	}
	public int getQtdEstoqueProduto() {
		return qtdEstoqueProduto;
	}
	public void setQtdEstoqueProduto(int qtdEstoqueProduto) {
		this.qtdEstoqueProduto = qtdEstoqueProduto;
	}
	public String getFotoProduto() {
		return fotoProduto;
	}
	public void setFotoProduto(String fotoProduto) {
		this.fotoProduto = fotoProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	
	
	
}
