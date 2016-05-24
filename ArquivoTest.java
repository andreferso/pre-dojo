package com.amil.dojo.arquivo;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.readAllLines;

import java.io.IOException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amil.dojo.entity.Partida;

public class ArquivoTest {

	private Collection<String> arquivo;

	@Before
	public void init() {

		try {
			arquivo = readAllLines(getDefault().getPath("log.txt"), defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void contabilizarNumeroDePartidas() throws Exception {

		Arquivo log = new Arquivo();

		Collection<Partida> partidas = log.partidas(arquivo);

		Assert.assertEquals(1, partidas.size());

	}

}
