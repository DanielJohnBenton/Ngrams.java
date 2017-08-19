package tests;
import java.util.ArrayList;
import ngrams.Ngrams;

public class TestNgrams {

	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>();
		words.add("these");
		words.add("are");
		words.add("some");
		words.add("words");
		words.add("and");
		words.add("stuff");
		words.add("and");
		words.add("some");
		words.add("more");
		
		ArrayList<String> ngrams = Ngrams.ngrams(words, 2);
		
		for(int i = 0; i < ngrams.size(); i++)
		{
			System.out.println(ngrams.get(i));
		}
	}

}
