package com.amil.dojo.entity;

import java.util.Map;

public class Jogador implements Comparable<Jogador> {

	private String nome;

	private Map<String, Integer> armas;

	private int assassinatos;

	private int mortes;

	public Jogador(String nome) {
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void morto() {
		mortes++;
	}

	public void matou() {
		assassinatos++;
	}

	public int quantidadeAssassinatos() {
		return assassinatos;
	}

	public int quantidadeMortes() {
		return mortes;
	}

	public Map<String, Integer> getArmas() {
		return armas;
	}

	public void setArmas(Map<String, Integer> armas) {
		this.armas = armas;
	}

	@Override
	public int compareTo(Jogador o) {
		return Integer.valueOf(o.assassinatos).compareTo(this.assassinatos);
	}

}
