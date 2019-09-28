package com.restAssuredData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Resources {

	public static String generateStringFromResource(String path) {
		String generatedStr = null;
		try {
			generatedStr = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return generatedStr;
	}
}
