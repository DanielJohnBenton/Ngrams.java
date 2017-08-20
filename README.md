# Ngrams.java

A Java library for creating n-grams, skip-grams, bag of words, bag of n-grams, bag of skip-grams.

## Input

These methods take an `ArrayList<String>` of words to turn into n-grams, skip-grams, etc.

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

Create [n-grams](https://en.wikipedia.org/wiki/N-gram#Examples) from an `ArrayList<String>` of words.

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

### :shell: skipgrams

Create [skip-grams](https://en.wikipedia.org/wiki/N-gram#Skip-gram) from an `ArrayList<String>` of words.

| Parameter         | Type                       | Description                                                                                                                                                                                                                                                                             | 
|-------------------|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| words             | `ArrayList<String>` | An array of words e.g. `["these", "are", "words"]`                                                                                                                                                                                                                                      | 
| size              | `int`                  | Size of the n-grams e.g. `2`: `"these are", "are words"`                                                                                                                                                                                                                                | 
| distance          | `int`                  | Distance to skip to create skip-grams, e.g. `5` will create skip-grams using the base word (or n-gram) and n-grams from the 5 following words.                                                                                                                                          | 
| sortForDuplicates | `int`                  | Pass `Ngrams.SORT_NGRAMS` or `Ngrams.DONT_SORT_NGRAMS`. Sorting n-grams alphabetically can help flag up duplicates e.g. when creating a [bag of words/n-grams/skip-grams](https://en.wikipedia.org/wiki/Bag-of-words_model#Example_implementation). If you only care about pairing n-grams by proximity but not by direction, use `Ngrams.DONT_SORT_NGRAMS`. | 

Returns an `ArrayList<ArrayList<String>>` of n-grams found near one another within the given `distance` of words.

```
String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
ArrayList<String> words = Ngrams.sanitiseToWords(text);
ArrayList<ArrayList<String>> skipgrams = Ngrams.skipgrams(words, 1, 2, Ngrams.DONT_SORT_NGRAMS);
System.out.println(skipgrams.toString());
```

Output (truncated): `[[Turning, and], [Turning, turning], [and, turning], [and, in], [turning, in], [turning, the], ...`

You can choose instead to pass `Ngrams.SORT_NGRAMS` and this will make direction irrelevant (e.g. it will be easier to sport `["Turning", "and"]` and `["and", "turning"]` as the same words because they are now sorted to `["Turning", "and"]` and `["turning", "and"]`. Using method `bagOfSkipGrams` (passing `Ngrams.CASE_INSENSITIVE`) would then remove one of these as a duplicate.

```
ArrayList<ArrayList<String>> skipgrams = Ngrams.skipgrams(words, 1, 2, Ngrams.SORT_NGRAMS);
System.out.println(skipgrams.toString());
```

Output: `[[and, Turning], [Turning, turning], [and, turning], [and, in], [in, turning], [the, turning], ...`



