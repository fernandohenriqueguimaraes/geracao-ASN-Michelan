package br.com.geradorASNbatch.model;

import java.math.BigDecimal;

public class Produto {

	private String partNumber;
	private String cai;
	private String cad;
	private String modelo;
	private String tipoProduto;
	private String unidadeMedida;
	private BigDecimal volume;
	private BigDecimal altura;
	private BigDecimal largura;
	private BigDecimal comprimento;

	public String getPartNumber() {
		return partNumber;
	}

	public Produto setPartNumber(String partNumber) {
		this.partNumber = partNumber;
		return this;
	}

	public String getModelo() {
		return modelo;
	}

	public Produto setModelo(String modelo) {
		this.modelo = modelo;
		return this;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public Produto setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
		return this;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public Produto setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
		return this;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Produto setVolume(BigDecimal volume) {
		this.volume = volume;
		return this;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public Produto setAltura(BigDecimal altura) {
		this.altura = altura;
		return this;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public Produto setLargura(BigDecimal largura) {
		this.largura = largura;
		return this;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public Produto setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
		return this;
	}

	public String getCai() {
		return cai;
	}

	public Produto setCai(String cai) {
		this.cai = cai;
		return this;
	}

	public String getCad() {
		return cad;
	}

	public Produto setCad(String cad) {
		this.cad = cad;
		return this;
	}

	@Override
	public String toString() {
		return "Produto [partNumber=" + partNumber + ", cai=" + cai + ", cad=" + cad + ", modelo=" + modelo
				+ ", tipoProduto=" + tipoProduto + ", unidadeMedida=" + unidadeMedida + ", volume=" + volume
				+ ", altura=" + altura + ", largura=" + largura + ", comprimento=" + comprimento + "]";
	}

}
