package com.loja_virtual_sb.enums;

public enum StatusContaReceber {
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada");
	
	private String descricao;
	
	private StatusContaReceber(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String toString() {
		return this.getDescricao();
	}
}
