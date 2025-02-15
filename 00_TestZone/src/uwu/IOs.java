package uwu;

import java.io.*;

public class IOs {
	
	private final static String PATH = "C:\\Users\\erjie_dmjyixu\\Desktop\\DAM\\1.AccesoDeDatos\\2.Ejercicios\\00_TestPlace\\file.txt";
	
	public static void main(String[] args) {
		outFileOutStr(new byte[] {89, 32, 49});
	}
	
	//BYTES (SUS CLASES BASES SON INPUTSTREAM Y OUTPUTSTREAM)
	
	//FileInputStream
	private static void outFileOutStr(byte[] byteEscribir) {
		FileOutputStream filOutStr = null;
		try {
			filOutStr = new FileOutputStream(PATH);
			filOutStr.write(byteEscribir);
			filOutStr.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				filOutStr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//BufferedOutputStream
	private static void bufOutStr(int numerosAEscribir) {
		BufferedOutputStream bufOutStr = null;
		try {
			bufOutStr = new BufferedOutputStream(new FileOutputStream(PATH));
			bufOutStr.write(numerosAEscribir);
			bufOutStr.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufOutStr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//
	
	
	//CARACTERES (SUS CLASES BASES SON READER Y WRITER)
	
	//InputWriter
	private static void outFileWri(String textoAEscribir) {
		FileWriter filWri = null;
		try {
			filWri = new FileWriter(PATH);
			filWri.write(textoAEscribir);
			filWri.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				filWri.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
