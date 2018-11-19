import java.util.Scanner;
import java.util.Scanner.*;
import java.util.HashMap;
public class Sanakirja {
	public static String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
	public static String[] englanti = { "cat", "dog", "horse", "car", "boat" };
	public static HashMap <String, String> sanakirja = new HashMap<String, String>();

	public static void main(String[] args) {
		
		//laitetaan sanakirjaa sanat ja käännökset
		for (int i = 0; i < suomi.length; i++) {
			sanakirja.put(suomi[i], englanti[i]);
		}
		
		Scanner l = new Scanner(System.in);
		
		while (true) {
			System.out.println("Sanakirja: " + sanakirja);
			System.out.println("Mitä haluat tehdä? (valitse numeroilla 1-3)\n");
			System.out.println("1. Sanakirja");
			System.out.println("2. Lisää sanoja sanakirjaan");
			System.out.println("3. Lopeta ohjelma");
			
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
				System.out.println("Lopetetaan...");
				System.exit(0);
			}
		}
		
		
		
	}

}
