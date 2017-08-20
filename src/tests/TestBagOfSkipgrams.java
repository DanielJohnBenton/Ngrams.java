package tests;
import java.util.ArrayList;

import ngrams.Ngrams;

public class TestBagOfSkipgrams {

	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		
		int[][] configs = {
				{1,5,Ngrams.SORT_NGRAMS,Ngrams.CASE_SENSITIVE},
				{1,5,Ngrams.SORT_NGRAMS,Ngrams.CASE_INSENSITIVE},
				{1,5,Ngrams.DONT_SORT_NGRAMS,Ngrams.CASE_SENSITIVE},
				{1,5,Ngrams.DONT_SORT_NGRAMS,Ngrams.CASE_INSENSITIVE}
		};
		int nConfigs = configs.length;
		
		for(int iConfigs = 0; iConfigs < nConfigs; iConfigs++) {
			ArrayList<ArrayList<String>> skipgrams = Ngrams.bagOfSkipgrams(words, configs[iConfigs][0], configs[iConfigs][1], configs[iConfigs][2], configs[iConfigs][3]);
			
			System.out.println("Size: "+ configs[iConfigs][0] +" Skip: "+ configs[iConfigs][1] +" Sort: "+ ((configs[iConfigs][2] == Ngrams.SORT_NGRAMS) ? "Y" : "N") +" Case sensitive: "+ ((configs[iConfigs][3] == Ngrams.CASE_SENSITIVE) ? "Y" : "N") +" Returns: "+ skipgrams.size() +" "+ skipgrams.toString());
		}
	}

}
