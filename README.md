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

## Methods
- ngrams
- skipgrams
- bagOfNgrams
- bagOfWords
- bagOfSkipgrams
- concatSkipgrams
- sanitiseWords

### :shell: ngrams

Create [n-grams](https://en.wikipedia.org/wiki/N-gram#Examples) from an `ArrayList` of words.

| Parameter | Type                     | Description                                                                    | 
|-----------|--------------------------|--------------------------------------------------------------------------------| 
| words     | `ArrayList<String>` | An array of words e.g. `["these", "are", "words"]`                           | 
| n         | `int`                  | Size of the n-grams, e.g. `2` will create bigrams `["these are", "are words"]` | 

Returns an `ArrayList<String>` of n-grams of size `n` words.

```
String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
ArrayList<String> words = Ngrams.sanitiseToWords(text);
ArrayList<String> ngrams = Ngrams.ngrams(words, 4);
System.out.println(ngrams.toString());
```

Output (truncated): `[Turning and turning in, and turning in the, turning in the widening, in the widening gyre, ...`