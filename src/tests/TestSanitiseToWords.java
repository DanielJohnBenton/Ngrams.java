package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestSanitiseToWords {

	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		
		System.out.println("Words:");
		
		int n = words.size();
		for(int i = 0; i < n; i++) {
			System.out.println("\t'" + words.get(i) +"'");
		}
	}

}
