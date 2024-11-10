package methods;

import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	
	
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
	
	static String pedir(String valor) {
		System.out.println("Introduzca " + valor + ":");
		return sc.nextLine();
	}
	
}
