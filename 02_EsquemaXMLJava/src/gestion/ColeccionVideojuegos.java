package gestion;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//PC: "C:\Users\Erjie\OneDrive\Escritorio\DAM\WsDataAccessExercises\02_EsquemaXML\ColeccionVideojuegos.xml"

public class ColeccionVideojuegos {
	
	public static void main(String[] args) {
		File coleccionVideojuegosXML = new File("C:\\Users\\Erjie\\OneDrive\\Escritorio\\DAM\\WsDataAccessExercises\\02_EsquemaXML\\ColeccionVideojuegos.xml");
		Scanner sc = new Scanner(System.in);
		Document arbolDOM = deXmlADom(coleccionVideojuegosXML);
		
		int opcion = 0;
		do {
			
			System.out.println("\r\n"
					+ "------------------------------------------------------\r\n"
					+ "                 Menu XML videojuego\r\n"
					+ "------------------------------------------------------\r\n");
			
			System.out.println("1. Leer el archivo XML (DOM)\r\n"
					+ "2. Aniadir un nuevo videojuego\r\n"
					+ "3. Realizar consulta XPath personalizada\r\n"
					+ "4. Salir\r\n"
					+ "Introduzca la opcion deseada:");
			opcion = sc.nextInt();
			sc.nextLine();
			switch (opcion) {
			case 1:
				leerXML(arbolDOM);
				break;
			case 2:
				System.out.println("Introduzca el titulo:");
				String titulo = sc.nextLine();
				System.out.println("Introduzca el desarrollador:");
				String desarrollador = sc.nextLine();
				System.out.println("Introduzca el plataforma:");
				String plataforma = sc.nextLine();
				System.out.println("Introduzca el genero:");
				String genero = sc.nextLine();
				System.out.println("Introduzca el anio:");
				int anio = sc.nextInt();
				sc.nextLine();
				System.out.println("Introduzca la clasificacion de edad:");
				int pEGI = sc.nextInt();
				sc.nextLine();
				System.out.println("Introduzca la cantidad de copias:");
				int copias = sc.nextInt();
				sc.nextLine();
				aniadirVideojuego(arbolDOM, titulo, desarrollador, plataforma, genero, anio, pEGI, copias);
				break;
			case 3:
				System.out.println("Arbol DOM: ColeccionVideojuegos - videojuego - (titulo, desarrollador, plataforma, genero, anioLanzamiento, PEGI, copiasDisponibles)");
				System.out.println("Introduzca la consulta que desea realizar al xpath:");
				String consulta = sc.nextLine();
				realizarConsultaXPath(arbolDOM, consulta);
				break;
			case 4:
				System.out.println("Programa cerrado");
				break;
			default:
				System.out.println("Esta opcion no existe introduzca otra.");
			}
		} while (opcion != 4);
		sc.close();
	}

	/**
	 * Este metodo devuelve el objeto Document que contiene un arbol DOM de un
	 * fichero xml.
	 * No lei el metodo cargarXML antes de hacer este ;-;
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
	 * Este metodo introducira un nuevo nodo videojuego con sus correspondiente nodos hijos y lo guardara
	 * con el nombre que el usuario haya introducido.
	 * Se guardara en la siguiente ruta:
	 * C:\Users\Erjie\OneDrive\Escritorio\DAM\WsDataAccessExercises\02_EsquemaXML
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
	public static void aniadirVideojuego(Document doc, String titulo, String desarrollador, String plataforma, String genero, int anio, int clasificacion, int numCopias) {
		Node nodoPadre = null;
		Node nodoHijo = null;
		@SuppressWarnings("unused")
		Node nodo_texto = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			//Videojuego
			nodoPadre = doc.createElement("videojuego");
			nodoPadre.appendChild(doc.getFirstChild());
			
			//Titulo
			((Element) nodoPadre).setAttribute("Titulo", titulo);
			//desarrollador
			nodoHijo = doc.createElement("desarrollador");
			nodo_texto = doc.createTextNode(desarrollador);
			nodoHijo.appendChild(nodoPadre);
			//plataforma
			nodoHijo = doc.createElement("plataforma");
			nodo_texto = doc.createTextNode(plataforma);
			nodoHijo.appendChild(nodoPadre);
			//genero
			nodoHijo = doc.createElement("genero");
			nodo_texto = doc.createTextNode(genero);
			nodoHijo.appendChild(nodoPadre);
			//Anio
			nodoHijo = doc.createElement("anio");
			nodo_texto = doc.createTextNode(String.valueOf(numCopias));
			nodoHijo.appendChild(nodoPadre);
			//PEGI
			nodoHijo = doc.createElement("PEGI");
			nodo_texto = doc.createTextNode(String.valueOf(clasificacion));
			nodoHijo.appendChild(nodoPadre);
			//Titulo
			nodoHijo = doc.createElement("copiasDisponibles");
			nodo_texto = doc.createTextNode(String.valueOf(numCopias));
			nodoHijo.appendChild(nodoPadre);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("¿Qué nombre deseas darle al nuevo archivo xml?");
		guardarXML(doc, sc.nextLine());
		sc.close();
		System.out.println("Videojuego aniadido correctamente");
	}
	
	
	/**
	 * Este metodo ejecuta una consulta realizada por el usuario mediante el XPath
	 * al objeto Document.
	 * 
	 * @param doc objeto document a introducir, debe ser un arbol DOM lo que contenga (desconzco que pasa si contiene otra cosa)
	 * @param consulta consulta XPath que se le desee realizar al objeto doc
	 */
	public static void realizarConsultaXPath(Document doc, String consulta) {
		XPathFactory xpathFabrica = null;
		XPath xpath = null;
		XPathExpression xpathExpresion = null;
		NodeList listaNodos = null;
		
		System.out.println("\r\n"
				+ "------------------------------------------------------\r\n"
				+ "                 Consulta XPath\r\n"
				+ "------------------------------------------------------\r\n");
		
		try {
			xpathFabrica  = XPathFactory.newInstance();
			xpath = xpathFabrica.newXPath();
			
			xpathExpresion = xpath.compile(consulta);
			
			listaNodos = (NodeList) xpathExpresion.evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < listaNodos.getLength(); i++) {
				System.out.println(consulta + ":" + listaNodos.item(i).getTextContent());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para guardar el archivo XML.
	 * Este se guardara el archivo que tenga el programa guardado, es decir, con todas las modificaciones
	 * realizadas y en la siguiente ruta:
	 * C:\Users\Erjie\OneDrive\Escritorio\DAM\WsDataAccessExercises\02_EsquemaXML
	 * 
	 * El nombre de archivo debera ser introducido mediante un parametro de entrada
	 * 
	 * @param doc arbol DOM a guardar
	 * @param archivo nombre del archivo a crear
	 */
	public static void guardarXML(Document doc, String archivo) {
		String rutaFichero = "C:\\Users\\Erjie\\OneDrive\\Escritorio\\DAM\\WsDataAccessExercises\\02_EsquemaXML\\" + archivo + ".xml";
		TransformerFactory fabricaTransformador = null;
		Transformer transformador = null;
		StreamResult resultado = null;
		DOMSource fuente = null;
		
		try {
			fabricaTransformador = TransformerFactory.newInstance();
			transformador = fabricaTransformador.newTransformer();
			fuente = new DOMSource(doc);
			resultado = new StreamResult(new File(rutaFichero));
			transformador.transform(fuente, resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
