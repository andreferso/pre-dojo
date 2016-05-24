package com.amil.dojo.util;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JogadorUtils {

	public static String obterNomeAssassino(String linhaAcao) throws Exception {

		Matcher result = compile("^\\w+\\s.*killed").matcher(linhaAcao);

		if (result.find())
			return result.group(0).replace("killed", "").trim();
		else if (linhaAcao.contains("<WORLD>"))
			return "<WORLD>";

		throw new Exception();

	}

	public static String obterNomeVitima(String linhaAcao) throws Exception {

		Matcher result = Pattern.compile("(killed)(\\s)(.*)(\\s)(using|by)").matcher(linhaAcao);

		if (result.find())
			return result.group(0).replace("killed", "").replace("using", "").replace("by", "").trim();

		throw new Exception();

	}

}