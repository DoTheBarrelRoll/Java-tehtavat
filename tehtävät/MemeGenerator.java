import java.util.Scanner;



import java.util.Random;
public class MemeGenerator {

	public static void main(String[] args) {
		
		Scanner l = new Scanner(System.in);
		Random r = new Random();
		System.out.println("Anna teksti: ");
		String text = l.nextLine();
		
		for(int i = 0; i < text.length(); i++) {
			int maybe = r.nextInt(2);
			if (maybe == 0) {
				char kirjain = text.charAt(i);
				String teksti = String.valueOf(kirjain);
				System.out.print(teksti.toUpperCase());
			} else {
				char kirjain = text.charAt(i);
				String teksti = String.valueOf(kirjain);
				System.out.print(teksti.toLowerCase());
			}
		}
		l.close();

	}

}
