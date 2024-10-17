package gestion;

import java.io.File;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

public class ColeccionVideojuegos {
	
	public static void main(String[] args) {
		
		Document doc = null;
		File fic = new File("C:\\Users\\Erjie\\Desktop\\DAM\\02_SecondYear\\1.AccesoDeDatos\\2.Ejercicios\\WsEjercicios\\02_EsquemaXML\\ColeccionVideojuegos.xml");
		
		deXmlADom(doc, fic);
		
	}
	
	private static int deXmlADom (Document doc, File fichero) {
		
		DocumentBuilderFactory fabrica = null;
		DocumentBuilder docBui = null;
		
		try {
			
			fabrica = new DocumentBuilderFactory.newInstance();
			fabrica.setIgnoringComments(true);
			fabrica.setIgnoringElementContentWhitespace(true);
			docBui = fabrica.newDocumentBuilder();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			return -1;
		}
		
	}

}
