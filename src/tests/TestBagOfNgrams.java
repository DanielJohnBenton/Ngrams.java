package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestBagOfNgrams {

	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		
		int[][] configs = { {1,Ngrams.CASE_SENSITIVE}, {1,Ngrams.CASE_INSENSITIVE}, {2,Ngrams.CASE_INSENSITIVE} };
		int nConfigs = configs.length;
		
		for(int iConfigs = 0; iConfigs < nConfigs; iConfigs++) {
			ArrayList<String> ngrams = Ngrams.bagOfNgrams(words, configs[iConfigs][0], configs[iConfigs][1]);
			int nNgrams = ngrams.size();
			int last = nNgrams - 1;
			
			String message = "Size: "+ configs[iConfigs][0] +" Case sensitive: "+ ((configs[iConfigs][1] == Ngrams.CASE_SENSITIVE) ? "Y" : "N") +" Returns: "+ nNgrams +" (";
			
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
