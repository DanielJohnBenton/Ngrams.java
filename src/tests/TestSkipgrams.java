package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestSkipgrams {

	public static void message(int size, int distance, boolean sortNgrams)
	{
		System.out.println("-----");
		System.out.println("Size: "+ size +" Skip: "+ distance +" Sort: "+ (sortNgrams ? "Y" : "N"));
	}
	
	public static void outputSkipgrams(ArrayList<ArrayList<String>> skipgrams) {
		int n = skipgrams.size();
		
		for(int i = 0; i < n; i++)
		{
			System.out.println(skipgrams.get(i).get(0) +" / "+ skipgrams.get(i).get(1));
		}
	}
	
	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		
		int[][] configs = { {1,1}, {1,5}, {2,5}, {3,5} };
		int n = configs.length;
		
		for(int i = 0; i < n; i++) {
			message(configs[i][0], configs[i][1], false);
			ArrayList<ArrayList<String>> skipgrams1 = Ngrams.skipgrams(words, configs[i][0], configs[i][1], Ngrams.DONT_SORT_NGRAMS);
			outputSkipgrams(skipgrams1);
			
			message(configs[i][0], configs[i][1], true);
			ArrayList<ArrayList<String>> skipgrams2 = Ngrams.skipgrams(words, configs[i][0], configs[i][1], Ngrams.SORT_NGRAMS);
			outputSkipgrams(skipgrams2);
		}
	}

}
