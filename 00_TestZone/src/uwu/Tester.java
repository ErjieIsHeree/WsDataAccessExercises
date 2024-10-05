package uwu;

import java.io.*;

public class Tester {
	
	public static void main(String[] args) {
		
		try {
			BufferedWriter filRot = new BufferedWriter(new FileWriter("C:\\Users\\Erjie\\OneDrive\\Escritorio\\DAM\\cositas\\uwu.txt", true));
			filRot.newLine();
			filRot.write("hola");
			filRot.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
