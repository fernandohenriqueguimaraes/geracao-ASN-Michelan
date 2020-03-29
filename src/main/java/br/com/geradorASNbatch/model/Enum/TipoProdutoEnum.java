package br.com.geradorASNbatch.model.Enum;

public enum TipoProdutoEnum {
	
	ANEL("Anel"),
	CAMARA("Camara"),
	MEMS("MEMS"),
	PASTA("Pasta de Montagem"),
	PNEU("Pneu"),
	PROTETOR("Protetor");
	
	private String descricao;

	private TipoProdutoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
