import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Sanakirjatxt {
	
	public static String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
	public static String[] englanti = { "cat", "dog", "horse", "car", "boat" };


	public static void main(String[] args) throws IOException {
		

		
		//FileOutputStream apuTied = new FileOutputStream("C:\\Users\\miizu\\Documents\\GitHub\\tehtavia\\tehtävät\\tallennettu.oma");
		//ObjectOutputStream talteen = new ObjectOutputStream(apuTied);
		
		//FileInputStream fis = new FileInputStream("C:\\Users\\miizu\\Documents\\GitHub\\tehtavia\\tehtävät\\tallennettu.oma");
		//ObjectInputStream luettuData = new ObjectInputStream(fis);
		HashMap<String, String> sanakirja;
		Scanner tiedostoLukijaSuomi;
		Scanner tiedostoLukijaEnglanti;
		File sanatEnglanti = new File("sanakirja_englanti.txt");
		File sanatSuomi = new File("sanakirja_suomi.txt");
		
		if (sanatEnglanti.createNewFile()) {
			System.out.println("Tallennettua sanakirjaa ei löytynyt, luodaan...");
			sanakirja = new HashMap<String, String>();
			//Jos tiedostoja ei löydy, luodaan pieni 
			
			for (int i = 0; i < suomi.length; i++) {
				sanakirja.put(suomi[i], englanti[i]);
			}
			
        } else { 
        	System.out.println("Tallennettu sanakirja löytyi, ladataan...");
        	
        	tiedostoLukijaSuomi = new Scanner(sanatSuomi);
        	tiedostoLukijaEnglanti = new Scanner(sanatEnglanti);
        	sanakirja = new HashMap<String, String>();
        	String sanaSuomi;
        	String sanaEnglanti;
        	while (tiedostoLukijaSuomi.hasNextLine()) {
        		sanaSuomi = tiedostoLukijaSuomi.nextLine();
        		sanaEnglanti = tiedostoLukijaEnglanti.nextLine();
        		sanakirja.put(sanaSuomi, sanaEnglanti);
        	}
        	
        	tiedostoLukijaSuomi.close();
        	tiedostoLukijaEnglanti.close();
        	
        	
        }
		
		
		
		
		
		
		
		
		Scanner l = new Scanner(System.in);

		
		while (true) {
			System.out.println("Sanakirja: " + sanakirja);
			System.out.println("Mitä haluat tehdä? (valitse numeroilla 1-3)\n");
			System.out.println("1. Sanakirja");
			System.out.println("2. Lisää sanoja sanakirjaan");
			System.out.println("3. Tallenna txt ja lopeta ohjelma");
			
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
				
			}  else if (vastaus == 3) {
				
				try {
					FileWriter tallennusSuomi = new FileWriter("sanakirja_suomi.txt");
					FileWriter tallennusEnglanti = new FileWriter("sanakirja_englanti.txt");
					
					for (HashMap.Entry<String, String> entry : sanakirja.entrySet())
					{
					    tallennusSuomi.write(entry.getKey() + System.lineSeparator());
					    tallennusEnglanti.write(entry.getValue() + System.lineSeparator());
					    tallennusEnglanti.flush();
						tallennusSuomi.flush();
					}
					
					tallennusEnglanti.flush();
					tallennusSuomi.flush();
					tallennusEnglanti.close();
					tallennusSuomi.close();
					
				} catch (FileNotFoundException e1) {
					File txt1 = new File("sanakirja_suomi.txt");
					File txt2 = new File("sanakirja_englanti.txt");
					FileWriter tallennusSuomi = new FileWriter(txt1);
					FileWriter tallennusEnglanti = new FileWriter(txt2);
					
					for (HashMap.Entry<String, String> entry : sanakirja.entrySet())
					{
					    tallennusSuomi.write(entry.getKey() + System.lineSeparator());
					    tallennusEnglanti.write(entry.getValue() + System.lineSeparator());
					}
					
					tallennusEnglanti.flush();
					tallennusSuomi.flush();
					tallennusEnglanti.close();
					tallennusSuomi.close();
					
				}
				
				
				System.out.println("Lopetetaan...");
				System.exit(0);
			}
		}
		
		
		

	}

}
