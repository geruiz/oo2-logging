package edu.unlp.oo2;

import java.util.logging.Logger;

public class Sample {

	public static void main(String[] args) {
	
		Logger logger = Logger.getLogger("sample");
		logger.addHandler(
			new HideWordsHandler(
				new MailHandler("destino@gmail.com"),
				"mensaje", "nivel", "hola")
			);
		
		logger.info("Contenido del mensaje en el nivel de INFO");
		
		logger.severe("Contenido del mensaje en el nivel de SEVERE");
	}
}
