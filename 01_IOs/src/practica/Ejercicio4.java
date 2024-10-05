package practica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Desea leer un fichero?"
				+ "Este se almacenará en la siguiente ruta:\r\n"
				+ "\"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\nombre.txt\"\r\n"
				+ "(Introduzca \"SI\" si lo desea o \"NO\" si no lo desea)");
		if (sc.nextLine().equalsIgnoreCase("si")) {
			System.out.println("Introduce el nombre del fichero:");
			String nomFic = sc.nextLine();
			shi(nomFic);
		}
		System.out.println("Programa cerrado");
		sc.close();
	}
	
	/**El siguiente metodo recibira un nombre de un fichero por parametro. Dicho
	 * fichero contendra cadenas de caracteres separados mediante asteriscos.
	 * Estos asteriscos serán eliminados y, por tanto, cada linea del fichero
	 * sera imprimido como una sola cadena.
	 * El fichero se encontrara en la ruta:
	 * <i>"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\"</i>
	 * 
	 * @param nomFic nombre del fichero
	 */
	private static void shi(String nomFic) {
		String filRot = "C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\" + nomFic + ".txt";
		try {
			BufferedReader bufRea = new BufferedReader(new FileReader(filRot));
			String cadena = null;
			while ((cadena=bufRea.readLine()) != null) {
				System.out.println(cadena.replace("*", " "));
			}
			bufRea.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
