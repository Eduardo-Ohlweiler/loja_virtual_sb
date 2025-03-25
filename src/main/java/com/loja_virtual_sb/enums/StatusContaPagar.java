package com.loja_virtual_sb.enums;

public enum StatusContaPagar {
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	NEGOCIADA("Renegociada");
	
	private String descricao;
	
	private StatusContaPagar(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String toString() {
		return this.getDescricao();
	}
}
