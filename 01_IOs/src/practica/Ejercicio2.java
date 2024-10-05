package practica;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Quieres crear un fichero cuyo nombre y contenido seran "
				+ "pedidos por consola.\r\nEl contenido a introducir deberan ser "
				+ "numeros que al final se sumaran y se\r\naniadiran al fichero y se "
				+ "imprimira por consola"
				+ "Este se almacenará en la siguiente ruta:\r\n"
				+ "\"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\nombre.txt\"\r\n"
				+ "(Introduzca \"SI\" si lo desea o \"NO\" si no lo desea)");
		if (sc.nextLine().equalsIgnoreCase("si")) {
			System.out.println("Introduzca el nombre que deseas para el fichero");
			String nomFic = sc.nextLine();
			er(nomFic);
			sc.close();
		}
		System.out.println("Programa cerrado");
	}

	/**El siguiente metodo craera un fichero cuyo nombre se dara por parametro y
	 * en ella se almacenara una serie de numeros que el usuario desee
	 * introducir. Y este imprimira su suma por consola y lo añadira al fichero.
	 * El fichero se encontrara en la ruta:
	 * <i>"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\"</i>
	 * 
	 * <b>Exceptions</b>
	 * 1.<i>FileNotFoundException</i>. Si el programa no localiza el fichero en
	 * la direccion senalizada.
	 * 2.<i>IOException</i>. Se lanza cuando ocurre algún problema del paquete
	 * IO.
	 */
	private static void er(String nomFic) {
		Scanner sc = new Scanner(System.in);
		String filRot = ("C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\" + nomFic + ".txt");
		int sumTot = 0;
		
		try {
			BufferedWriter bufWri = new BufferedWriter(new FileWriter(filRot));
			String aux = null;
			System.out.println("Introduzca un numero:\r\n"
					+ "(Cuando acabe introduzca OK)");
			while (!(aux = sc.nextLine()).equalsIgnoreCase("OK")) {
				System.out.println("Introduzca un numero:\r\n"
						+ "(Cuando acabe introduzca ok)");
				bufWri.write(aux);
				bufWri.newLine();
				sumTot += Integer.parseInt(aux);
			}
			System.out.println("La suma total de los numeros es: " + sumTot);
			bufWri.write("La suma total de los numeros es: " + sumTot);
			bufWri.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Fichero creado.");
		sc.close();
	}

}
