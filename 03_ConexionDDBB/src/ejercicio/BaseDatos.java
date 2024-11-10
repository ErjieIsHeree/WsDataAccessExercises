package ejercicio;

import java.sql.*;
import java.util.Scanner;

public class BaseDatos {

	private static Scanner sc = new Scanner(System.in);
	
	private static Connection getConexion() throws Exception {
		String url = "jdbc:mysql://localhost:3306/biblioteca";
		String nombre = "root";
		String contra = "root";
		return DriverManager.getConnection(url, nombre, contra);
	}
	
	protected static void OperacionesLibro() {
		int eleccion = menuOperacionesDe("libro");
		
		switch (eleccion) {
		case 1:
			darDeAlta(1);
			break;
		case 2:
			darDeBaja(1);
			break;
		case 3:
			modificar(1);
			break;
		case 4:
			consultaPorTitulo();
			break;
		case 5:
			consultaPorAutor();
			break;
		default:
			System.out.println("La eleccion " + eleccion + " no existe.");
		}
		
	}
	
	protected static void OperacionesSocio() {
		int eleccion = menuOperacionesDe("socio");
		
		switch (eleccion) {
		case 1:
			darDeAlta(2);
			break;
		case 2:
			darDeBaja(2);
			break;
		case 3:
			modificar(2);
			break;
		case 4:
			consultaPorNombre();
			break;
		case 5:
			consultaPorApellido();
			break;
		default:
			System.out.println("La elección " + eleccion + " no existe.");
		}
		
	}
	
	private static void darDeAlta(int tipo) {
		Connection conn = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = null;
		PreparedStatement pst = null;
		if (tipo == 1) {
			try {
				query = "INSERT INTO libros (titulo, numero_ejemplares, editorial, numero_paginas, ano_edicion) VALUES (?,?, ?, ?, ?)";
				pst = conn.prepareStatement(query);
				pst.setString(1, pedirString("el titulo del libro"));
				pst.setInt(2, pedirInt("la cantidad de ejemplares"));
				pst.setString(3, pedirString("la editorial"));
				pst.setInt(4, pedirInt("el numero de paginas"));
				pst.setInt(5, pedirInt("el anio de edicion"));
				pst.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				query = "INSERT INTO socios (nombre, apellidos, edad, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
				pst = conn.prepareStatement(query);
				pst.setString(1, pedirString("su nombre"));
				pst.setString(2, pedirString("su apellidos"));
				pst.setInt(3, pedirInt("su edad"));
				pst.setString(4, pedirString("su direccion"));
				pst.setString(5, pedirString("su telefono"));
				pst.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}

	private static void darDeBaja(int tipo) {
		Connection conn = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = null;
		if (tipo == 1) {
			try {
				query = "DELETE FROM libros WHERE titulo = ?";
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, pedirString("el titulo del libro"));
				pst.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} else {
			try {
				query = "DELETE FROM socios WHERE nombre = ?";
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, pedirString("el nombre del socio"));
				pst.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
	
	private static void modificar(int tipo) {
		Connection conn = null;
		String query = null;
		if (tipo == 1) {
			try {
				conn = getConexion();
			} catch (Exception e) {
				e.printStackTrace();
			}
			query = "UPDATE libros SET ? = \"?\" WHERE id = \"?\"";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, pedirString("qué desea cambiar (titulo, numero_ejemplares, editorial, numero_paginas, ano_edicion)"));
				pst.setString(2, pedirString("el valor nuevo"));
				pst.setString(3, pedirString("el id del libro"));
				pst.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				conn = getConexion();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e.printStackTrace();
				}
				e.printStackTrace();
			}
			query = "UPDATE socios SET ? = \"?\" WHERE id = \"?\"";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, pedirString("qué desea cambiar (nombre, apellidos, edad, direccion, telefono)"));
				pst.setString(2, pedirString("el valor nuevo"));
				pst.setString(3, pedirString("el id del socio"));
				pst.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
	
	private static void consultaPorTitulo() {
		Connection conn = null;
		String query = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM libros WHERE titulo = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("el titulo del libro"));
			pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void consultaPorAutor() {
		Connection conn = null;
		String query = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM libros WHERE autor = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("el autor del libro"));
			pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void consultaPorNombre() {
		Connection conn = null;
		String query = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM socios WHERE nombre = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("el nombre del socio"));
			pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void consultaPorApellido() {
		Connection conn = null;
		String query = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM socios WHERE apellidos = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("los apellidos del socio"));
			pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int menuOperacionesDe(String tipo) {
		String menu = "1.Dar de alta un " + tipo + "\r\n"
				+ "2.Dar de baja un " + tipo + "\r\n"
				+ "3.Modificar un " + tipo + "\r\n";
		if (tipo.equals("libro")) {
			menu += "4.Consulta por titulo\r\n"
					+ "5.Consulta por autor";
		} else {
			menu += "4.Consulta por nombre\r\n"
					+ "5.Consulta por apellido";
		}
		return eleccionDelMenu(menu, 5);
	}

	protected static void realizarPrestamo() throws Exception {
		
	}
	
	protected static void ConsultasPrestamo() {
		int eleccion = menuConsultaPrestamo();
		
		switch(eleccion) {
		case 1:
			listadoLibrosPrestados();
			break;
		case 2:
			numeroLibroPrestadosASocio();
			break;
		case 3:
			librosConRetrasoDeDevolucion();
			break;
		case 4:
			sociosConRetrasoDeDevolucion();
			break;
		default:
			System.out.println("La elección " + eleccion + " no existe.");
		}
		
	}
	
	private static void listadoLibrosPrestados() {
		Connection conn = null;
		String query = null;
		try {
			conn = BaseDatos.getConexion();
			query = "SELECT * FROM libros";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int menuConsultaPrestamo() {
		String menu = "1.Listado de libros prestados actualmente.\r\n"
				+ "2.Número de libros prestados a un socio determinado.\r\n"
				+ "3.Libros que han superado la fecha de fin de préstamo.\r\n"
				+ "4.Socios que tienen libros que han superado la fecha de fin de préstamo.";
		return eleccionDelMenu(menu, 4);
	}
	
	private static int eleccionDelMenu(String menu, int canOpc) {
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
	
	private static String pedirString(String valor) {
		System.out.println("Introduzca " + valor + ":");
		return sc.nextLine();
	}
	
	private static int pedirInt(String valor) {
		System.out.println("Introduzca " + valor + ":");
		return sc.nextInt();
	}
}
