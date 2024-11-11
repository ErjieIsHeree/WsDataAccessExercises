package ejercicio;

import java.util.Scanner;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String menu = "Elija una de las siguientes opciones:\r\n"
			+ "1.Operaciones de libros\r\n"
			+ "2.Operaciones de socios\r\n"
			+ "3.Realizar prestamo\r\n"
			+ "4.Consultar prestamos\r\n"
			+ "5.Apagar programa";
		int eleccion = 0;
		
		do {
			eleccion = eleccionDelMenu(menu, 5);
			switch (eleccion) {
			case 1:
				BaseDatos.operacionesLibro();
				break;
			case 2:
				BaseDatos.operacionesSocio();
				break;
			case 3:
				BaseDatos.realizarPrestamo();
				break;
			case 4:
				BaseDatos.consultasPrestamo();
				break;
			case 5:
				System.out.println("Cerrando programa.");
				break;
			}
		} while (true);
		
	}
	
	static int eleccionDelMenu(String menu, int canOpc) {
		int opt = 0;
		do {
			System.out.println("Introduzca la opción que desee:\r\n"
					+ menu);
			opt = sc.nextInt();
			sc.nextLine();
			if (opt < 1 || opt > canOpc) {
				System.out.println("Esta opción no existe.\r\n"
						+ "Introduzca otra:");
			}
		} while (opt < 1 || opt > canOpc);
		sc.close();
		return opt;
	}

}
