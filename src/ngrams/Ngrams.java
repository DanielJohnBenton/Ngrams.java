package ngrams;
import java.util.ArrayList;

public class Ngrams {
	public static int SORT_NGRAMS = 1;
	public static int DONT_SORT_NGRAMS = 2;
	public static int CASE_SENSITIVE = 3;
	public static int CASE_INSENSITIVE = 4;
	
	public static ArrayList<String> ngrams(ArrayList<String> words, int n) {
		if(n <= 1) {
			return new ArrayList<String>(words);
		}
		
		ArrayList<String> ngrams = new ArrayList<String>();
		
		int c = words.size();
		for(int i = 0; i < c; i++) {
			if((i + n - 1) < c) {
				int stop = i + n;
				String ngramWords = words.get(i);
				
				for(int j = i + 1; j < stop; j++) {
					ngramWords +=" "+ words.get(j);
				}
				
				ngrams.add(ngramWords);
			}
		}
		
		return ngrams;
	}
	
	/*public static ArrayList<ArrayList<String>> skipgrams(ArrayList<String> words, int size, int distance, int sortForDuplicates)
	{
		ArrayList<String> ngrams = ngrams(words, size);
		//.sort(String::compareToIgnoreCase);
		
		ArrayList<ArrayList<String>> skipgrams = new ArrayList<ArrayList<String>>();
		
		int n = ngrams.size();
		for(int i = 0; i < n; i++)
		{
			int end = i + 1 + distance;
			
			for(int pos = i + 1; pos < end; pos++) {
				if(pos < n) {
					if(sortForDuplicates == SORT_NGRAMS) {
						
					}
				}
			}
		}
	}*/
}












