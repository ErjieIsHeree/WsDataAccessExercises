package practica;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Quieres crear un fichero cuyo contenido seran los numero"
				+ "s enteros del 0 al 100?\r\n"
				+ "Este se almacenará en la siguiente ruta:\r\n"
				+ "\"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\numNaturales.txt\"\r\n"
				+ "(Introduzca \"SI\" si lo desea o \"NO\" si no lo desea)");
		if (sc.nextLine().equalsIgnoreCase("Si")) {
			yi();
		}
		System.out.println("Programa acabado");
		sc.close();
	}
	
	/** El siguiente método crea el fichero <i>"numNaturales"</i. Dicho fichero
	 * contendrá los numeros naturales del 0 al 100.
	 * Este fichero será <i>".txt"</i> y se creará en la siguiente ruta:
	 * <i>"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\"</i>
	 * 
	 * <b>Exceptions</b>
	 * 1.<i>FileNotFoundException</i>. Si el programa no localiza el fichero en
	 * la direccion senalizada.
	 * 2.<i>IOException</i>. Se lanza cuando ocurre algún problema del paquete
	 * IO.
	 */
	public static void yi() {
		FileWriter fileWr = null;
		try {
			fileWr = new FileWriter("C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\"
					+ "numNaturales.txt");
			for (int i = 0; i <= 100; i++) {
				fileWr.write(i + " ");
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileWr.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Fichero creado.");
	}

}
