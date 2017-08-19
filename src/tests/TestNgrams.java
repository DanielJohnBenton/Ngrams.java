package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestNgrams {

	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		
		int[] sizes = new int[] {1, 2, 4, 100};
		int n = sizes.length;
		
		for(int i = 0; i < n; i++) {
			ArrayList<String> ngrams = Ngrams.ngrams(words, sizes[i]);
			int c = ngrams.size();
			int last = c - 1;
			
			String message = "Size: "+ sizes[i] +" Returns: "+ c +" (";
			
			for(int j = 0; j <= last; j++) {
				message +="'"+ ngrams.get(j) +"'";
				
				if(j != last) {
					message +=", ";
				}
			}
			
			message +=")";
			
			System.out.println(message);
		}
	}

}
