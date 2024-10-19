package gestion;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.File;
import java.util.ArrayList;

public class ColeccionVideojuegos {
	
	public static void main(String[] args) {
		
		File coleccionVideojuegosXML = new File("C:\\Users\\Erjie\\Desktop\\DAM\\02_SecondYear\\1.AccesoDeDatos\\2.Ejercicios\\WsEjercicios\\02_EsquemaXML\\ColeccionVideojuegos.xml");
		Document arbolDOM = deXmlADom(coleccionVideojuegosXML);
		leerXML(arbolDOM);
		
	}

	/**
	 * Este metodo devuelve el objeto Document que contiene un arbol DOM de un
	 * fichero xml.
	 * 
	 * @param fichero la ruta al fichero xml
	 * @return Document que contendra el arbol DOM
	 */
	private static Document deXmlADom(File fichero) {
		
		DocumentBuilderFactory fabrica = null;
		DocumentBuilder constructorDoc = null;
		Document doc = null;
		
		try {
			
			fabrica = DocumentBuilderFactory.newInstance();
			fabrica.setIgnoringComments(true);
			fabrica.setIgnoringElementContentWhitespace(true);
			constructorDoc = fabrica.newDocumentBuilder();
			doc = constructorDoc.parse(fichero);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Arbol DOM no creado correctamente");
			return null;
		}
		System.out.println("Arbol DOM creado correctamente");
		return doc;
	}
	
	/**
	 * Este metodo devuelve el contenido de todos los nodos del nodo videojuego del XML
	 * 
	 * @param n
	 * @return
	 */
	public static ArrayList<String> procesarVideojuego(Node n) {
		ArrayList<String> listaVidInfo = new ArrayList<String>();
		NodeList listaNodos = n.getChildNodes();
		//Add atirbuto
		listaNodos.getAttributes().item(0).getTextContent();
		//Add elementos
		for (int i = 0; i < listaNodos.getLength(); i++) {
			Node nodo = listaNodos.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				listaVidInfo.add(nodo.getTextContent());
			}
		}
		return listaVidInfo;
	}
	
	/**
	 * Método que recorre el archivo XML y muestra la información de todos los videojuegos, usando DOM.
	 * 
	 * @param doc
	 */
	public static void leerXML(Document doc) {
		//Entrada
		
		System.out.println("\r\n"
				+ "------------------------------------------------------\r\n"
				+ "                 Leer XML videojuego\r\n"
				+ "------------------------------------------------------\r\n"
				+ "");
		
		String salida = null;
		NodeList listaNodos = doc.getChildNodes();
		
		for (int i = 0; i < listaNodos.getLength(); i++) {
			ArrayList<String> listaVidInfo = procesarVideojuego(listaNodos.item(i));
			salida += "\n Videojuego: " + listaVidInfo.get(0);
			salida += "\n Desarrollador: " + listaVidInfo.get(1);
			salida += "\n Plataforma: " + listaVidInfo.get(2);
			salida += "\n PEGI: " + listaVidInfo.get(4);
			salida += "\n Anio lanzamiento: " + listaVidInfo.get(3);
			salida += "\n PEGI: " + listaVidInfo.get(4);
			salida += "\n Copias disponibles: " + listaVidInfo.get(5);
		}
		System.out.println(salida);
	}
	
	/**
	 * Método para añadir un nuevo videojuego al archivo XML. Los datos serán introducidos por el usuario y añadidos al DOM.
	 * 
	 * @param doc
	 * @param titulo
	 * @param desarrollador
	 * @param plataforma
	 * @param genero
	 * @param anno
	 * @param clasificacion
	 * @param numCopias
	 */
	public static void añadirVideojuego(Document doc, String titulo, String desarrollador, String plataforma, String genero, int anno, String clasificacion, int numCopias) {
		
		
		
	}
	
	
	/**
	 * Método que permite ejecutar una consulta XPath introducida por el usuario y mostrar los resultados.
	 * 
	 * @param doc
	 * @param consulta
	 */
	public static void realizarConsultaXPath(Document doc, String consulta) {
		
	}
	
	/**
	 * Método para cargar el archivo XML y devolver un objeto Document que representa el DOM.
	 * 
	 * @param archivo
	 * @return
	 */
	public static Document cargarXML(String archivo) {
		Document doc = null;
		
		
		
		return doc;
	}
	
	
	/**
	 * Método para guardar el archivo XML con las modificaciones realizadas (por ejemplo, tras añadir un nuevo videojuego).
	 * @param doc
	 * @param archivo
	 */
	public static void guardarXML(Document doc, String archivo) {
		
	}
	
}
