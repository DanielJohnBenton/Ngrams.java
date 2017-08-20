# Ngrams.java

A Java library for creating n-grams, skip-grams, bag of words, bag of n-grams, bag of skip-grams.

## Input

These methods take an `ArrayList` of words to turn into n-grams, skip-grams, etc.

```
package test;
import java.util.ArrayList;
import ngrams.Ngrams;

public class Test_001 {

	public static void main(String[] args) {
		String text = "These are some words";
		ArrayList<String> words = Ngrams.sanitiseToWords(text);
		ArrayList<String> ngrams = Ngrams.ngrams(words, 2);
		
		System.out.println(ngrams.toString());
	}

}
```

Output: `[These are, are some, some words]`