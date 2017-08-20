package ngrams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
	
	public static ArrayList<ArrayList<String>> skipgrams(ArrayList<String> words, int size, int distance, int sortForDuplicates)
	{
		ArrayList<String> ngrams = ngrams(words, size);
		
		ArrayList<ArrayList<String>> skipgrams = new ArrayList<ArrayList<String>>();
		
		int n = ngrams.size();
		for(int i = 0; i < n; i++)
		{
			int end = i + 1 + distance;
			
			for(int pos = i + 1; pos < end; pos++) {
				if(pos < n) {
					ArrayList<String> pair = new ArrayList<String>();
					pair.add(ngrams.get(i));
					pair.add(ngrams.get(pos));
					
					if(sortForDuplicates == SORT_NGRAMS) {
						pair.sort(String::compareToIgnoreCase);
					}
					
					skipgrams.add(pair);
				}
				else
				{
					break;
				}
			}
		}
		
		return skipgrams;
	}
	
	public static ArrayList<String> bagOfNgrams(ArrayList<String> words, int n, int caseSensitivity) {
		ArrayList<String> ngrams = ngrams(words, n);
		
		ArrayList<String> bag = new ArrayList<String>();
		HashMap<String, Boolean> lookup = new HashMap<String, Boolean>();
		
		int c = ngrams.size();
		for(int i = 0; i < c; i++) {
			String id = ngrams.get(i);
			
			if(caseSensitivity == CASE_INSENSITIVE) {
				id = id.toLowerCase();
			}
			
			if(lookup.get(id) == null) {
				bag.add(ngrams.get(i));
				lookup.put(id, true);
			}
		}
		
		return bag;
	}
	
	public static ArrayList<String> sanitiseToWords(String text)
	{
		String[] characters = text.split("");
		
		String sanitised = "";
		
		boolean onSpace = true;
		
		int xLastCharacter = text.length() - 1;
		for(int i = 0; i <= xLastCharacter; i++) {
			if(characters[i].matches("^[A-Za-z0-9$£%]$"))
			{
				sanitised += characters[i];
				onSpace = false;
			}
			else if(characters[i].equals("'") && i > 0 && i < xLastCharacter) {
				String surrounding = characters[i - 1] + characters[i + 1];
				if(surrounding.matches("^[A-Za-z]{2}$"))
				{
					sanitised +="'";
					onSpace = false;
				}
			}
			else if(!onSpace && i != xLastCharacter)
			{
				sanitised +=" ";
				onSpace = true;
			}
		}
		
		return new ArrayList<String>(Arrays.asList(sanitised.split("\\s+")));
	}
	
}












