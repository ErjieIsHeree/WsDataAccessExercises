package practica;

import java.io.*;
import java.util.Scanner;

public class Ejercicio3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Desea crear un fichero cuyo nombre y contenido seran int"
				+ "roducidos por consola?"
				+ "Este se almacenará en la siguiente ruta:\r\n"
				+ "\"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\nombre.txt\"\r\n"
				+ "(Introduzca \"SI\" si lo desea o \"NO\" si no lo desea)");
		if (sc.nextLine().equalsIgnoreCase("si")) {
			System.out.println("Dime el nombre que deseas darle al fichero");
			String nom = sc.nextLine();
			System.out.println("Cuantas cadenas de caracteres te gustaria introduci"
					+ "r?");
			int can = sc.nextInt();
			sc.nextLine();
			String[] lisTex = new String[can];
			for (int i = 0; i < can; i++) {
				System.out.println("Dime la " + (i+1) + "º linea");
				lisTex[i] = sc.nextLine();
			}
			san(nom, lisTex);
		}
		System.out.println("Programa cerrado");
		sc.close();
	}
	
	/**
	 * El metodo recibira un array de cadenas de caracteres y un nombre por parametro.
	 * Se creara un fichero con el nombre y tendra de contenido las cadenas de
	 * caracteres del array. Cada cadena sera una linea en el fichero y cada espacio
	 * que hay entre las cadenas, se convertiran en asteriscos.
	 * Este fichero sera <i>".txt"</i> y se encontrara en la ruta:
	 * <i>"C:\\Users\\Erjie\\Desktop\\DAM\\cositas"</i>
	 * 
	 * <b>Exceptions</b>
	 * 1.<i>FileNotFoundException</i>. Si el programa no localiza el fichero en
	 * la direccion senalizada.
	 * 2.<i>IOException</i>. Se lanza cuando ocurre algún problema del paquete
	 * IO.
	 * 
	 * @param nom <i>nombre</i> del fichero
	 * @param tex <i>cadena</i> de caracteres que se introducira en el fichero
	 */
	private static void san(String nom, String[] lisTex) {
		String rut = ("C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\" + nom + ".txt");
		try {
			BufferedWriter bufWri = new BufferedWriter(new FileWriter(rut));
			for (int i = 0; i < lisTex.length; i++) {
				lisTex[i] = lisTex[i].replace(" ", "*");
				bufWri.write(lisTex[i]);
				bufWri.newLine();
			}
			bufWri.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Fichero creado.");
	}

}
