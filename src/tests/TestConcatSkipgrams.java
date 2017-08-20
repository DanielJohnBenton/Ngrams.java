package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestConcatSkipgrams {

	public static void main(String[] args) {
		String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		ArrayList<String> skipgrams = Ngrams.concatSkipgrams(Ngrams.skipgrams(words, 2, 5, Ngrams.DONT_SORT_NGRAMS));
		
		System.out.println(skipgrams.toString());
	}

}
