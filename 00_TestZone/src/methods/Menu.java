package methods;

import java.util.Scanner;

public class Menu {
	@SuppressWarnings("unused")
	private static int selMen(int canOpc) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca la opción que desee:");
		boolean optCor = false;
		int opt = 0;
		do {
			try {
				do {
					opt = sc.nextInt();
					sc.nextLine();
					if (opt < 1 || opt > canOpc) {
						System.out.println("Esta opción no existe, elija otra");
					}
				} while (opt < 1 || opt > canOpc);
				optCor = true;
			}
			catch (Exception e) {
				System.out.println("La opción debe ser un número de las que el "
						+ "menú muestre");
			}
		} while (!optCor);
		sc.close();
		return opt;
	}
}
