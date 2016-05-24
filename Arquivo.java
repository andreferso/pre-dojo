package com.amil.dojo.arquivo;

import java.util.Collection;
import java.util.LinkedList;

import com.amil.dojo.entity.Partida;
import com.amil.dojo.exception.FormatoArquivoInvalidoException;
import com.amil.dojo.util.PartidaUtils;

public class Arquivo {

	private Collection<Partida> partidas = new LinkedList<>();

	private enum Movimento {

		NOVA_PARTIDA("New match") {
			@Override
			Partida prosseguir(Partida partida, Collection<Partida> partidas, String linha) throws Exception {
				return new Partida(PartidaUtils.obterNumeroPartida(linha));
			}
		},

		PROXIMO("killed") {
			@Override
			Partida prosseguir(Partida partida, Collection<Partida> partidas, String linha) throws Exception {

				partida.proximoMovimento(linha);

				return partida;

			}
		},

		FIM_PARTIDA("has ended") {
			@Override
			Partida prosseguir(Partida partida, Collection<Partida> partidas, String linha) {

				partidas.add(partida);

				return partida;

			}
		};

		private String regex;

		private Movimento(String regex) {
			this.regex = regex;
		}

		abstract Partida prosseguir(Partida partida, Collection<Partida> partidas, String linha) throws Exception;

		static Partida proximo(Collection<Partida> partidas, Partida partida, String linha) throws Exception {

			for (Movimento movimento : Movimento.values())

				if (linha.contains(movimento.regex))
					return movimento.prosseguir(partida, partidas, linha);

			throw new FormatoArquivoInvalidoException();

		}

	}

	public Collection<Partida> partidas(Collection<String> linhasLog) throws Exception {

		Partida partidaAtual = null;
		for (String linha : linhasLog)
			partidaAtual = Movimento.proximo(partidas, partidaAtual, linha);

		return partidas;

	}

}
