import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
public class Sanakirja {
	public static String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
	public static String[] englanti = { "cat", "dog", "horse", "car", "boat" };
	//public static HashMap <String, String> sanakirja = new HashMap<String, String>();
	
	public static void vieXML(HashMap<String, String> kirja) throws IOException {
		FileOutputStream fos = new FileOutputStream("sanakirja.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(kirja);
		encoder.close();
		fos.close();
	}
	
	public static HashMap<String, String> tuoXML() throws IOException {
		FileInputStream fis = new FileInputStream("sanakirja.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		HashMap<String, String> purettukirja = (HashMap) decoder.readObject();
		decoder.close();
		fis.close();
		return purettukirja;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, EOFException {
		
		//FileOutputStream apuTied = new FileOutputStream("C:\\Users\\miizu\\Documents\\GitHub\\tehtavia\\tehtävät\\tallennettu.oma");
		//ObjectOutputStream talteen = new ObjectOutputStream(apuTied);
		
		//FileInputStream fis = new FileInputStream("C:\\Users\\miizu\\Documents\\GitHub\\tehtavia\\tehtävät\\tallennettu.oma");
		//ObjectInputStream luettuData = new ObjectInputStream(fis);
		HashMap<String, String> sanakirja;
		File kirjasto = new File("sanakirja.xml");
		if (kirjasto.createNewFile()) {
			System.out.println("Tallennettua sanakirjaa ei löytynyt, luodaan...");
			sanakirja = new HashMap<String, String>();
			//laitetaan sanakirjaa sanat ja käännökset
			
			for (int i = 0; i < suomi.length; i++) {
				sanakirja.put(suomi[i], englanti[i]);
			}
        }else { 
        	System.out.println("Tallennettu sanakirja löytyi, ladataan...");
        	sanakirja = tuoXML();
        }
		
		
		
		
		
		
		
		
		Scanner l = new Scanner(System.in);
		
		while (true) {
			System.out.println("Sanakirja: " + sanakirja);
			System.out.println("Mitä haluat tehdä? (valitse numeroilla 1-3)\n");
			System.out.println("1. Sanakirja");
			System.out.println("2. Lisää sanoja sanakirjaan");
			System.out.println("3. Tallenna XML ja lopeta ohjelma");
			System.out.println("4. Tallenna txt ja lopeta ohjelma");
			
			int vastaus = l.nextInt();
			
			if (vastaus == 1) {
				String buffer = l.nextLine();
				while (true) {
					
					System.out.println("Minkä sanan käännöksen haluat tietää? (tyhjä sana lopettaa)");
					String avain = l.nextLine();
					
					if (sanakirja.containsKey(avain)) {
						System.out.println("Sanan \"" + avain + "\" käännös on \"" + sanakirja.get(avain) + "\"");
						continue;
					} else if (sanakirja.containsKey(avain) == false && !(avain.isEmpty())){
						System.out.println("Sanaa ei löydy, yritä uudelleen");
						continue;
					} else if (avain.isEmpty());{
						break;
					}
					
				}
			} else if (vastaus == 2) {
				String buffer = l.nextLine();
				while (true) {
					l.reset();
					System.out.println("Anna sana suomeksi(tyhjä sana lopettaa): ");
					String suomeksi = l.nextLine();
					if (suomeksi.length() > 0) {
						
					} else {
						break;
					}
					System.out.println("Anna sana englanniksi: ");
					String englanniksi = l.nextLine();
					sanakirja.put(suomeksi, englanniksi);
				}
				
			} else if (vastaus == 3) {
				vieXML(sanakirja);
				System.out.println("Lopetetaan...");
				System.exit(0);
			} else if (vastaus == 4) {
				
				try {
					PrintWriter tallennus = new PrintWriter("sanakirja.txt");
					tallennus.println(sanakirja);
			
					tallennus.flush();
					tallennus.close();
				} catch (FileNotFoundException e1) {
					File txt = new File("sanakirja.txt");
					PrintWriter kirjoittaja = new PrintWriter(txt);
					kirjoittaja.println(sanakirja);
				}
				
				System.exit(0);
			}
		}
		
		
		
	}

}
