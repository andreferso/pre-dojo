package com.amil.dojo.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amil.dojo.entity.Jogador;
import com.amil.dojo.entity.Partida;
import com.amil.dojo.exception.FormatoArquivoInvalidoException;

public enum Tipo {

	ASSASSINO("^([\\w]+)(\\s)(.*)(killed)") {

		public void incluir(String nome, Map<String, Jogador> jogadores) {

			if (nome.equalsIgnoreCase("<WORLD>"))
				return;

			Jogador assassino = new Jogador(nome);

			assassino.setArmas(new HashMap<String, Integer>());
			assassino.getArmas().put("testeArma", 1);

			assassino.matou();

			jogadores.put(nome, assassino);

		}

		public void atualizar(Jogador jogador) {

			jogador.matou();

		}

	},

	VITIMA("(killed)(\\s)(.*)(\\s)(using|by)") {

		public void incluir(String nome, Map<String, Jogador> jogadores) {

			if (nome.equalsIgnoreCase("<WORLD>"))
				return;

			Jogador vitima = new Jogador(nome);
			vitima.morto();

			jogadores.put(nome, vitima);

		}

		public void atualizar(Jogador jogador) {

			jogador.morto();

		}

	};

	private String regex;

	private Tipo(String regex) {
		this.regex = regex;
	}

	abstract void incluir(String nome, Map<String, Jogador> jogadores);

	abstract void atualizar(Jogador jogador);

	public static void incluirOuAtualizarJogadorNaPartida(String linha, Partida partida)
			throws FormatoArquivoInvalidoException {

		for (Tipo tipo : Tipo.values()) {

			Matcher matcher = Pattern.compile(tipo.regex).matcher(linha);
			if (matcher.find()) {

				String nome = matcher.group(0).replace("killed", "").replace("using", "").replace("by", "").trim();

				Map<String, Jogador> jogadores = partida.getJogadores();
				if (!jogadores.containsKey(nome))
					tipo.incluir(nome, jogadores);
				else
					tipo.atualizar(jogadores.get(nome));

			}

		}

	}

	public String regex() {
		return regex;
	}

}