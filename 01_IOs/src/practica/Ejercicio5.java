package practica;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Ejercicio5 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Desea crear un fichero que contenga un calendario?"
				+ "Este se almacenará en la siguiente ruta:\r\n"
				+ "\"C:\\Users\\Erjie\\Desktop\\DAM\\cositas\\nombre.txt\"\r\n"
				+ "(Introduzca \"SI\" si lo desea o \"NO\" si no lo desea)");
		if (sc.nextLine().equalsIgnoreCase("si")) {
			System.out.println("Introduce el mes en formato numerico:\r\n"
					+ "1 (Enero), 2 (Febrero), 3 (Marzo)... 12 (Diciembre)");
			int numMes = sc.nextInt();
			System.out.println("Introduce la inicial del dia de la semana que sea"
					+ " el primer dia del mes:\r\n"
					+ "L (Lunes), M (Martes), X (Miercoles), J (Jueves)...");
			sc.nextLine();
			String diaSemana = sc.nextLine();
			calendario(numMes, diaSemana);
		}
		sc.close();
		System.out.println("Programa cerrado");
	}
	
	/**
	 * El siguiente metodo recogera por parametros el <i>"numMes"</i> y el
	 * <i>"diaSemana"</i>. El mes se usara para crear el nombre de un fichero cuyo
	 * nombre sera <i>mes"numeroDeMes".txt</i>, en el se incluiran los dias que tenga el
	 * mes y al lado de cada dia se le aniadira el dia de la semana, de tal forma que
	 * quedaria asi:
	 * 1X2J3V4S6D... 31L
	 * El fichero se encontrara en la ruta:
	 * <i>"C:\\Users\\Erjie\\Desktop\\DAM\\cositas"</i>
	 * 
	 * 
	 * @param numMes valor del 1 al 12 que se volvera a pedir en caso de que no sea asi.
	 * @param semana valores (L, M, X, J, V, S, D)que se volvera a pedir en caso de que no sea asi.
	 */
	private static void calendario(int numMes, String diaSemana) {
		Scanner sc = new Scanner(System.in);
		LinkedList<String> lisSem = new LinkedList<String>();
		lisSem.add("L");
		lisSem.add("M");
		lisSem.add("X");
		lisSem.add("J");
		lisSem.add("V");
		lisSem.add("S");
		lisSem.add("D");
		
		//Control numero de mes existente
		while (numMes < 1 || numMes > 12) {
			System.out.println("Introduzca el numero de un mes existente:");
			numMes = sc.nextInt();
		}
		
		//Control de dias del mes
		//Mes de 31 dias == 0
		int tipoMes = 31;
		//
		if (numMes < 7 && numMes % 2 == 0 || numMes > 8 && numMes % 2 != 0) {
			//Mes de 30 dias
			tipoMes = 30;
		}
		if (numMes == 2) {
			//Febrero
			tipoMes = 29;
		}
		
		//Control dia de semana existente y dia de semana en numero
		boolean semCor = false;
		int diaSem = 0;
		do {
			for (String c : lisSem) {
				//Comprueba que la letra introducida concuerde con algun dia semanal
				if (c.equalsIgnoreCase(diaSemana)) {
					semCor = true;
				}
			}
			if (!semCor) {
				System.out.println("Introduzca la incial de la semana existente:\r\n"
						+ "(L = lunes, M = martes, X = Miercoles, J = Jueves, V = Viernes, "
						+ "S = Sabado, D = Domingo)");
				diaSemana = sc.nextLine();
			}
		} while(!semCor);
		
		//Da un valor numerico del 1-7 al dia de la semana
		if (diaSemana.equalsIgnoreCase("L")) {
			diaSem = 0;
		} else if (diaSemana.equalsIgnoreCase("M")) {
			diaSem = 1;
		} else if (diaSemana.equalsIgnoreCase("X")) {
			diaSem = 2;
		} else if (diaSemana.equalsIgnoreCase("J")) {
			diaSem = 3;
		} else if (diaSemana.equalsIgnoreCase("V")) {
			diaSem = 4;
		} else if (diaSemana.equalsIgnoreCase("S")) {
			diaSem = 5;
		} else if (diaSemana.equalsIgnoreCase("D")) {
			diaSem = 6;
		}
		
		//Trabajo sobre el fichero
		String filRot = "C:\\Users\\Erjie\\OneDrive\\Escritorio\\DAM\\cositas\\mes"+ numMes +".txt";
		try {
			BufferedWriter bufWri = new BufferedWriter(new FileWriter(filRot, false));
			bufWri.write("");
			bufWri.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
				e.printStackTrace();
		}
		try {
			BufferedWriter bufWri = new BufferedWriter(new FileWriter(filRot, true));
			for (int i = 1; i <= tipoMes; i++) {
				bufWri.write(i + lisSem.get(diaSem++));
				if (diaSem == 7) {
					diaSem = 0;
				}
			}
			bufWri.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
				e.printStackTrace();
		}
		sc.close();
		System.out.println("Fichero creado");
	}

}
