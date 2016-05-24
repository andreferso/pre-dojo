package com.amil.dojo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amil.dojo.exception.FormatoArquivoInvalidoException;

public class PartidaUtils {

	public static String obterNumeroPartida(String linhaAcao) throws Exception {

		Matcher result = Pattern.compile("\\s\\d+\\s").matcher(linhaAcao);

		if (result.find())
			return result.group(0).trim();

		throw new FormatoArquivoInvalidoException();

	}

}