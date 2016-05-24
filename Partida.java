package com.amil.dojo.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.amil.dojo.enums.Tipo;

public class Partida {

	private int numeroPartida;

	private Map<String, Jogador> jogadores = new HashMap<>();

	public Partida(String obterNumeroPartida) {
		this.numeroPartida = Integer.valueOf(obterNumeroPartida);
	}

	public void proximoMovimento(String linha) throws Exception {
		Tipo.incluirOuAtualizarJogadorNaPartida(linha.substring(22), this);
	}

	public int getNumeroPartida() {
		return numeroPartida;
	}

	public Map<String, Jogador> getJogadores() {
		return jogadores;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Partida: " + numeroPartida + "\n");

		for (Jogador jogador : new TreeSet<>(jogadores.values()))
			builder.append(jogador.getNome() + " - " + jogador.quantidadeAssassinatos() + " - "
					+ jogador.quantidadeMortes() + "\n");

		return builder.toString();

	}

}
