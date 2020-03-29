package br.com.geradorASNbatch.model;

public class Empresa {

	public static final String GERA_ASN_SIM = "Sim";
	public static final String GERA_ASN_NAO = "Não";
	
	private String cnpj;
	private String bairro;
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String municipio;
	private String pais;
	private String codigoPais;
	private String tipo;
	private String razaoSocial;
	private String codigoEmpresa;
	private String codigoERP;
	private boolean geraASN;

	public String getBairro() {
		return bairro;
	}

	public Empresa setBairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public String getCep() {
		return cep;
	}

	public Empresa setCep(String cep) {
		this.cep = cep;
		return this;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Empresa setLogradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public String getNumero() {
		return numero;
	}

	public Empresa setNumero(String numero) {
		this.numero = numero;
		return this;
	}

	public String getComplemento() {
		return complemento;
	}

	public Empresa setComplemento(String complemento) {
		this.complemento = complemento;
		return this;
	}

	public String getMunicipio() {
		return municipio;
	}

	public Empresa setMunicipio(String municipio) {
		this.municipio = municipio;
		return this;
	}

	public String getPais() {
		return pais;
	}

	public Empresa setPais(String pais) {
		this.pais = pais;
		return this;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public Empresa setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
		return this;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Empresa setCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	public String getTipo() {
		return tipo;
	}

	public Empresa setTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public Empresa setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		return this;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public Empresa setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
		return this;
	}

	public String getCodigoERP() {
		return codigoERP;
	}

	public Empresa setCodigoERP(String codigoERP) {
		this.codigoERP = codigoERP;
		return this;
	}

	public boolean isGeraASN() {
		return geraASN;
	}
	
	public String geraASNtoString() {
		
		if (this.geraASN) {
			return GERA_ASN_SIM;
		}
		
		return GERA_ASN_NAO;
		
	}
	
	public Empresa geraASNtoBoolean(String geraASN) {
		
		if (geraASN.equals(geraASN)) {
			this.setGeraASN(true);
		} else {
			this.setGeraASN(false);
		}
		
		return this;
		
	}

	public Empresa setGeraASN(boolean geraASN) {
		this.geraASN = geraASN;
		return this;
	}

	@Override
	public String toString() {
		return "Empresa [cnpj=" + cnpj + ", bairro=" + bairro + ", cep=" + cep + ", logradouro=" + logradouro + ", numero="
				+ numero + ", complemento=" + complemento + ", municipio=" + municipio + ", pais=" + pais
				+ ", codigoPais=" + codigoPais + ", tipo=" + tipo + ", razaoSocial=" + razaoSocial + ", codigoEmpresa="
				+ codigoEmpresa + ", codigoERP=" + codigoERP + ", geraASN=" + geraASN + "]";
	}


}
