package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestBagOfWords {

	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		System.out.println("Original number of words: "+ words.size());
		
		int[] configs = {Ngrams.CASE_SENSITIVE, Ngrams.CASE_INSENSITIVE};
		int nConfigs = configs.length;
		
		for(int iConfigs = 0; iConfigs < nConfigs; iConfigs++) {
			ArrayList<String> ngrams = Ngrams.bagOfWords(words, configs[iConfigs]);
			int nNgrams = ngrams.size();
			int last = nNgrams - 1;
			
			String message = "Size: 1 Case sensitive: "+ ((configs[iConfigs] == Ngrams.CASE_SENSITIVE) ? "Y" : "N") +" Returns: "+ nNgrams +" (";
			
			for(int iNgrams = 0; iNgrams <= last; iNgrams++) {
				message +="'"+ ngrams.get(iNgrams) +"'";
				
				if(iNgrams != last) {
					message +=",";
				}
			}
			
			message +=")";
			
			System.out.println(message);
		}
	}

}
