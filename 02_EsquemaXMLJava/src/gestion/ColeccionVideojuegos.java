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
	 * Este metodo devuelve el los nodos hijos en forma de texto introducidos en un
	 * ArrayList.
	 * 
	 * @param n nodo padre del que se sacara la informacion
	 * @return ArrayList<String> contenidod en forma de texto de los nodos hijo
	 */
	public static ArrayList<String> procesarXMLaLista(Node n) {
		ArrayList<String> listaVidInfo = new ArrayList<String>();
		NodeList listaNodos = n.getChildNodes();
		//Add atributo
		listaVidInfo.add(n.getAttributes().item(0).getTextContent());
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
		ArrayList<String> listaVidInfo = null;
		String salida = "";
		Node nodo = null;
		NodeList listaNodosVideojuego = doc.getChildNodes().item(0).getChildNodes();
		System.out.println("\r\n"
				+ "------------------------------------------------------\r\n"
				+ "                 Leer XML videojuego\r\n"
				+ "------------------------------------------------------\r\n");
		
		for (int i = 0; i < listaNodosVideojuego.getLength(); i++) {
			nodo = listaNodosVideojuego.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				listaVidInfo = procesarXMLaLista(nodo);
				salida += "\r\n------Videojuego " + listaVidInfo.get(0) + "------";
				salida += "\r\n Desarrollador: " + listaVidInfo.get(1);
				salida += "\r\n Plataforma: " + listaVidInfo.get(2);
				salida += "\r\n PEGI: " + listaVidInfo.get(4);
				salida += "\r\n Anio lanzamiento: " + listaVidInfo.get(3);
				salida += "\r\n PEGI: " + listaVidInfo.get(4);
				salida += "\r\n Copias disponibles: " + listaVidInfo.get(5) + "\r\n";
			}
		}
		System.out.println(salida);
	}
	
	/**
	 * Metodo para aniadir un nuevo videojuego al archivo XML.
	 * 
	 * @param doc arbol DOM en la que se encuentra el videojuego
	 * @param titulo titulo del videojuego
	 * @param desarrollador desarrollador del videojuego
	 * @param plataforma plataformas disponibles de videojuego
	 * @param genero genero del videojuego
	 * @param anio anio de lanzamiento del videojuego
	 * @param clasificacion clasificacion de edad del videojuego
	 * @param numCopias cantidad de copias del videojuego
	 */
	public static void añadirVideojuego(Document doc, String titulo, String desarrollador, String plataforma, String genero, String anio, String clasificacion, String numCopias) {
		Node nodoPadre = null;
		Node nodoHijo = null;
		Node nodo_texto = null;
		
		try {
			//Videojuego
			nodoPadre = doc.createElement("videojuego");
			nodoPadre.appendChild(doc.getFirstChild());
			
			//Titulo
			((Element) nodoPadre).setAttribute("Titulo", titulo);
			//desarrollador
			nodoHijo = doc.createElement("Desarrollador");
			nodo_texto = doc.createTextNode(desarrollador);
			nodoHijo.appendChild(nodoPadre);
			//plataforma
			nodoHijo = doc.createElement("Plataforma");
			nodo_texto = doc.createTextNode(plataforma);
			nodoHijo.appendChild(nodoPadre);
			//genero
			nodoHijo = doc.createElement("Genero");
			nodo_texto = doc.createTextNode(genero);
			nodoHijo.appendChild(nodoPadre);
			//Anio
			nodoHijo = doc.createElement("Anio");
			nodo_texto = doc.createTextNode(anio);
			nodoHijo.appendChild(nodoPadre);
			//PEGI
			nodoHijo = doc.createElement("PEGI");
			nodo_texto = doc.createTextNode(clasificacion);
			nodoHijo.appendChild(nodoPadre);
			//Titulo
			nodoHijo = doc.createElement("Titulo");
			nodo_texto = doc.createTextNode(numCopias);
			nodoHijo.appendChild(nodoPadre);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	 * Metodo para guardar el archivo XML con las modificaciones realizadas.
	 * @param doc
	 * @param archivo
	 */
	public static void guardarXML(Document doc, String archivo) {
		try {
			File archivo_xml = new File("salida.xml");
			OutputFormat format = new OutputFormat(doc);
			format.setIndenting(true); // Indentación para mejor legibilidad 
			// Crear el serializador XML 
			XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);
			// Serializar el árbol DOM en el archivo XML serializer.serialize(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
