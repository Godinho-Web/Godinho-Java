package org.libertas.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jogo")
public class Jogo {
	
	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
	@Column(name="id")
	private int idjogo;
	private String descricao;
	private String tipo;
	private String datalanc;
	public int getIdjogo() {
		return idjogo;
	}
	public void setIdjogo(int idjogo) {
		this.idjogo = idjogo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDatalanc() {
		return datalanc;
	}
	public void setDatalanc(String datalanc) {
		this.datalanc = datalanc;
	}
	@Override
	public String toString() {
		return "Jogo [idjogo=" + idjogo + ", descricao=" + descricao + ", tipo=" + tipo + ", datalanc=" + datalanc
				+ "]";
	}
	
	
	
	
	
	
}
