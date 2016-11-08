package properties;

import java.util.ArrayList;
import java.util.Date;

public class RandomNumberGenerator {

	private String OKS_email;
	
	public RandomNumberGenerator(String OKS_email) {
		this.OKS_email = OKS_email;
	}
	
	public String generateEntercode() {
		String result = null;
		
		String email[] = OKS_email.split("@");
		char encoded[] = new char[email[0].length()];
		result = "";
		
		// generate randomized int
		ArrayList<Integer> random = new ArrayList<Integer>();
		for (int i = 0; i < email[0].length(); i++) {
			while (true) {
				double math = Math.random() * (email[0].length());
				if (random.contains((int) math)) {
				} else {
					random.add((int) math);
					break;
				}
			}
		}
		
		for (int i = 0; i < email[0].length(); i++) {
			encoded[i] = email[0].charAt(random.get(i));
			String code = String.format("0x%02X", (int) encoded[i]);
			result += code.split("x")[1];
		}
		
		String longdate = Long.toString(new Date().getTime());
		longdate = longdate.substring(longdate.length() - 4);
		
		result += longdate;
		
		return result;
	}
}
