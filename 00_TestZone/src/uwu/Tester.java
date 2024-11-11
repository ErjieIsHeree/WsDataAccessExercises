package uwu;

import java.sql.*;
import TesterMain

public class Tester {
	
	private static void prestamo(){
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		try {
			conn = BaseDatos.getConexion();
			query = "INSERT INTO prestamos(libro, socio, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
			pst = conn.PrepareStatement(query);
			pst.setString(1, pedirString("el titulo del libro"));
			pst.setString(2, pedirString("el nombre del socio"));
			pst.setInt(3, pedirInt("la fecha de inicio"));
			pst.setInt(4, pedirInt("la fecha de fin"));
			pst.executeUpdate();
			conn.commit();
		catch (Exception e){
			conn.rollBack();
			e.printStackTrace();
		}
		conn.close();
		pst.close();
	}

	private static void listadoLibrosPrestados(){
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		ResultSet rs = null;
		try{
			conn = BaseDatos.getConexion();
			query = "SELECT libros FROM prestamos";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()){
			String tituloLibro = rs.getString("libro");
			String nombreSocio = rs.getString("socio");
			String fechaInicio = rs.getString("fecha_inicio");
			String fechaFin = rs.getString("fecha_fin");
			System.out.println("Libro: " + tituloLibro + " - Socio: " + nombreSocio + " - Fecha inicio: " + fechaInicio + " - Fecha fin: " + fechaFin);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
