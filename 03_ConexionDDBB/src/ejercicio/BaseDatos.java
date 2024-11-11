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
	
	public static void OperacionesLibro() {
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
		default:
			System.out.println("La eleccion " + eleccion + " no existe.");
		}
		
	}
	
	public static void OperacionesSocio() {
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
				String userPide = null;
				pst.setString(1, userPide = pedirString("qué desea cambiar (titulo, numero_ejemplares, editorial, numero_paginas, ano_edicion)"));
				if (userPide.equalsIgnoreCase("titulo") || userPide.equalsIgnoreCase("editorial")) {
					pst.setString(2, pedirString("el valor nuevo"));
				} else {
					pst.setInt(2, pedirInt("el valor nuevo"));
				}
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
				String userPide = null;
				pst.setString(1, userPide = pedirString("qué desea cambiar (nombre, apellidos, edad, direccion, telefono)"));
				if (userPide.equalsIgnoreCase("edad")) {
					pst.setInt(2, pedirInt("el valor nuevo"));
				} else {
					pst.setString(2, pedirString("el valor nuevo"));
				}
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
		ResultSet rs = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM libros WHERE titulo = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("el titulo del libro"));
			rs = pst.executeQuery();
			while (rs.next()) {
				String autor = rs.getString("autor");
				int numeroEjemplares = rs.getInt("numero_ejemplares");
				String editorial = rs.getString("editorial");
				int numeroPaginas = rs.getInt("numero_paginas");
				int anoEdicion = rs.getInt("ano_edicion");
				System.out.println("Autor: " + autor + " - Numero ejemplares: " + numeroEjemplares + " - Editorial: " + editorial + " - Numero paginas: " + numeroPaginas + " - Anio edicion: " + anoEdicion);
			}
		} catch (Exception e) {
			System.out.println("Libro no encontrado");
			e.printStackTrace();
		}
	}
	
	private static void consultaPorNombre() {
		Connection conn = null;
		String query = null;
		ResultSet rs = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM socios WHERE nombre = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("el nombre del socio"));
			rs = pst.executeQuery();
			while (rs.next()) {
				String apellidos = rs.getString("apellidos");
				int edad = rs.getInt("edad");
				String direccion = rs.getString("direccion");
				String telefono = rs.getString("telefono");
				System.out.println("Apellidos: " + apellidos + " - Edad: " + edad + " - Direccion: " + direccion + " - Telefono: " + telefono);
			}
		} catch (Exception e) {
			System.out.println("Socio no encontrado");
			e.printStackTrace();
		}
	}
	
	private static void consultaPorApellido() {
		Connection conn = null;
		String query = null;
		ResultSet rs = null;
		try {
			conn = getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = "SELECT * FROM socios WHERE nombre = \"?\"";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("los apellidos del socio"));
			rs = pst.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				int edad = rs.getInt("edad");
				String direccion = rs.getString("direccion");
				String telefono = rs.getString("telefono");
				System.out.println("Nombre: " + nombre + " - Edad: " + edad + " - Direccion: " + direccion + " - Telefono: " + telefono);
			}
		} catch (Exception e) {
			System.out.println("Socio no encontrado");
			e.printStackTrace();
		}
	}
	
	private static int menuOperacionesDe(String tipo) {
		String menu = "1.Dar de alta un " + tipo + "\r\n"
				+ "2.Dar de baja un " + tipo + "\r\n"
				+ "3.Modificar un " + tipo + "\r\n";
		int can = 0;
		if (tipo.equals("libro")) {
			menu += "4.Consulta por titulo";
			can = 4;
		} else {
			menu += "4.Consulta por nombre\r\n"
					+ "5.Consulta por apellido";
			can = 5;
		}
		return eleccionDelMenu(menu, can);
	}

	public static void realizarPrestamo() {
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		try {
			conn = BaseDatos.getConexion();
			query = "INSERT INTO prestamos(libro_id, socio_id, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
			pst = conn.prepareStatement(query);
			pst.setString(1, pedirString("el id del libro"));
			pst.setString(2, pedirString("el id del socio"));
			pst.setString(3, pedirString("la fecha de inicio"));
			pst.setString(4, pedirString("la fecha de fin"));
			pst.executeUpdate();
			conn.commit();
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void ConsultasPrestamo() {
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
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = BaseDatos.getConexion();
			query = "SELECT * FROM libros";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				String tituloLibro = rs.getString("libro_id");
				String nombreSocio = rs.getString("socio_id");
				String fechaInicio = rs.getString("fecha_inicio");
				String fechaFin = rs.getString("fecha_fin");
				System.out.println("Libro: " + tituloLibro + " - Socio: " + nombreSocio + " - Fecha inicio: " + fechaInicio + " - Fecha fin: " + fechaFin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void numeroLibroPrestadosASocio() {
		Connection conn = null;
		ResultSet rs = null;
		int id = pedirInt("el id del socio");
		String query = "SELECT * FROM prestamos WHERE socio_id = " + id;
		Statement st = null;
		try {
			conn = BaseDatos.getConexion();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			int cantidadLibrosPrestados = 0;
			while (rs.next()) {
				cantidadLibrosPrestados++;
			}
			System.out.println("Total de " + cantidadLibrosPrestados + " libros prestados");
		} catch (Exception e) {
			System.out.println("Socio no encontrado");
			e.printStackTrace();
		}
	}
	
	private static void librosConRetrasoDeDevolucion() {
		Connection conn = null;
		Statement st = null;
		String fechaHoy = pedirString("la fecha de hoy en formato (dd/mm/aaaa)");
		String[] fechaHoyS = fechaHoy.split("/");
		String query = "SELECT * FROM prestamos WHERE fecha_fin = \"" + fechaHoy + "\"";
		ResultSet rs = null;
		try {
			conn = BaseDatos.getConexion();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			String librosConRetraso = "";
			if (rs.next()) {
				String fechaFin = rs.getString("fecha_fin");
				String [] fechaFinS = fechaFin.split("/");
				if (Integer.parseInt(fechaFinS[2]) >= Integer.parseInt(fechaHoyS[2])) {
					if (Integer.parseInt(fechaFinS[1]) >= Integer.parseInt(fechaHoyS[1])) {
						if (Integer.parseInt(fechaFinS[0]) >= Integer.parseInt(fechaHoyS[0])) {
							librosConRetraso +="Id libro: " + rs.getString("libro_id") + "\r\n";
						}
					}
				}
				System.out.println(librosConRetraso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void sociosConRetrasoDeDevolucion() {
		Connection conn = null;
		Statement st = null;
		String fechaHoy = pedirString("la fecha de hoy en formato (dd/mm/aaaa)");
		String[] fechaHoyS = fechaHoy.split("/");
		String query = "SELECT * FROM prestamos WHERE fecha_fin = \"" + fechaHoy + "\"";
		ResultSet rs = null;
		try {
			conn = BaseDatos.getConexion();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			String librosConRetraso = "";
			if (rs.next()) {
				String fechaFin = rs.getString("fecha_fin");
				String [] fechaFinS = fechaFin.split("/");
				if (Integer.parseInt(fechaFinS[2]) >= Integer.parseInt(fechaHoyS[2])) {
					if (Integer.parseInt(fechaFinS[1]) >= Integer.parseInt(fechaHoyS[1])) {
						if (Integer.parseInt(fechaFinS[0]) >= Integer.parseInt(fechaHoyS[0])) {
							librosConRetraso += "Id socio: " + rs.getString("socio_id") + "\r\n";
						}
					}
				}
				System.out.println(librosConRetraso);
			}
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
