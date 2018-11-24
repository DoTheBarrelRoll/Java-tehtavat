import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class SanakirjaXML {
	public static String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
	public static String[] englanti = { "cat", "dog", "horse", "car", "boat" };

	
	//Tallennetaan sanakirja sisältö XML muotoon
	public static void vieXML(HashMap<String, String> kirja) throws IOException {
		FileOutputStream fos = new FileOutputStream("sanakirja.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(kirja);
		encoder.close();
		fos.close();
	}
	
	//Tuodaan sanakirja XML tiedostosta
	public static HashMap<String, String> tuoXML() throws IOException {
		FileInputStream fis = new FileInputStream("sanakirja.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		HashMap<String, String> purettukirja = (HashMap) decoder.readObject();
		decoder.close();
		fis.close();
		return purettukirja;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, EOFException {
		
		HashMap<String, String> sanakirja;
		File kirjasto = new File("sanakirja.xml");
		
		//Tarkistetaan, onko ohjelman juuressa sanakirja.xml tiedostoa
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
			
			int vastaus = l.nextInt();
			
			if (vastaus == 1) {
				String buffer = l.nextLine();
				while (true) {
					
					System.out.println("Minkä sanan käännöksen haluat tietää? (tyhjä sana lopettaa)");
					String sana = l.nextLine();
					String avain = sana.toLowerCase();
					
					//Tarkistetaan, löytyykö käyttäjän antamaa sanaa sanakirjasta tai jos sana on tyhjä,
					//mennää takaisin valikkoon
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
					
					//Tarkistetaan antoiko käyttäjä sanan, jos ei, mennää takaisin valikkoon
					if (suomeksi.isEmpty()) {
						break;
					} else {
						System.out.println("Anna sana englanniksi (tyhjä keskeyttää): ");
						String englanniksi = l.nextLine();
						if (englanniksi.isEmpty()) {
							break;
						} else {
							//Lisätään sanat sanakirjaa pienillä kirjaimilla ongelmien välttämiseksi
							sanakirja.put(suomeksi.toLowerCase(), englanniksi.toLowerCase());
						}
					}
				
				}
				
			} else if (vastaus == 3) {
				
				//viedään sanakirja XML tiedostoon ja lopetetaan ohjelma
				vieXML(sanakirja);
				System.out.println("Lopetetaan...");
				l.close();
				System.exit(0);
			} else {
				System.out.println("Virheellinen valinta, yritä uudelleen.");
				l.nextLine();
			}
		}
		
		
		
	}

}
