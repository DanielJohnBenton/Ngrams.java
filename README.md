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
- [ngrams](https://github.com/DanielJohnBenton/Ngrams.java#shell-ngrams)
- [skipgrams](https://github.com/DanielJohnBenton/Ngrams.java#shell-skipgrams)
- [bagOfNgrams](https://github.com/DanielJohnBenton/Ngrams.java#shell-bagofngrams)
- [bagOfWords](https://github.com/DanielJohnBenton/Ngrams.java#shell-bagofwords)
- [bagOfSkipgrams](https://github.com/DanielJohnBenton/Ngrams.java#shell-bagofskipgrams)
- [concatSkipgrams](https://github.com/DanielJohnBenton/Ngrams.java#shell-concatskipgrams)
- [sanitiseWords](https://github.com/DanielJohnBenton/Ngrams.java#shell-sanitisetowords)

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

Output (truncated): `[[and, Turning], [Turning, turning], [and, turning], [and, in], [in, turning], [the, turning], ...`

### :shell: bagOfNgrams

Generate n-grams and remove duplicates. Can be case sensitive or insensitive by passing `Ngrams.CASE_SENSITIVE` or `Ngrams.CASE_INSENSITIVE`.

| Parameter       | Type                       | Description                                                                                                                                                                                                                                              | 
|-----------------|----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| words           | `ArrayList<String>` | An array of words e.g. `["these", "are", "words"]`.                                                                                                                                                                                                      | 
| n               | `int`                  | Size of the n-grams e.g. `2` creates bigrams `["these are", "are words"]`                                                                                                                                                                                | 
| caseSensitivity | `int`                  | Pass `Ngrams.CASE_SENSITIVE` or `Ngrams.CASE_INSENSITIVE`. Case insensitive calls will ignore differences in case when removing duplicates e.g. `"Turning"`, `"turning"`, `"TURNING"` will all be seen as identical and reduced to just `"Turning"`. | 

Returns an `ArrayList<String>` of n-grams with duplicates removed.

```
String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
ArrayList<String> words = Ngrams.sanitiseToWords(text);
ArrayList<String> bagOfNgrams = Ngrams.bagOfNgrams(words, 1, Ngrams.CASE_INSENSITIVE);
System.out.println(bagOfNgrams.toString());
```

Output: `[Turning, and, in, the, widening, gyre, falcon, cannot, hear, falconer, Things, fall, apart, centre, hold, Mere, anarchy, is, loosed, upon, world]`

### :shell: bagOfWords

This is just a wrapper function for readability that called `bagOfNgrams` with an n-gram size (`n`) of `1`.

| Parameter       | Type                       | Description                                                                                                                                                                                                                                              | 
|-----------------|----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| words           | `ArrayList<String>` | An array of words e.g. `["these", "are", "words"]`.                                                                                                                                                                                                      | 
| caseSensitivity | `int`                  | Pass `Ngrams.CASE_SENSITIVE` or `Ngrams.CASE_INSENSITIVE`. Case insensitive calls will ignore differences in case when removing duplicates e.g. `"Turning"`, `"turning"`, `"TURNING"` will all be seen as identical and reduces to just `"Turning"`. | 

Returns an `ArrayList<String>` of words with duplicates removed.

```
ArrayList<String> bagOfWords = Ngrams.bagOfWords(words, Ngrams.CASE_INSENSITIVE);
```

### :shell: bagOfSkipgrams

Generates skip-grams and removes duplicates. Can ignore direction by passing `Ngrams.SORT_NGRAMS`. Can be case insensitive by passing `Ngrams.CASE_INSENSITIVE`.

| Parameter         | Type                       | Description                                                                                                                                                                                                                                                                                                                                                        | 
|-------------------|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| words             | `ArrayList<String>` | An array of words e.g. `["these", "are", "words"]`                                                                                                                                                                                                                                                                                                                 | 
| size              | `int`                  | Size of the n-grams e.g. `2`: `"these are", "are words"`                                                                                                                                                                                                                                                                                                           | 
| distance          | `int`                  | Distance to skip to create skip-grams, e.g. `5` will create skip-grams using the base word (or n-gram) and n-grams from the 5 following words.                                                                                                                                                                                                                     | 
| sortForDuplicates | `int`                  | Pass `Ngrams.SORT_NGRAMS` or `Ngrams.DONT_SORT_NGRAMS`. Sorting n-grams alphabetically can help flag up duplicates e.g. when creating a [bag of words/n-grams/skip-grams](https://en.wikipedia.org/wiki/Bag-of-words_model#Example_implementation). If you only care about pairing n-grams by proximity but not by direction, use `Ngrams.DONT_SORT_NGRAMS`. | 
| caseSensitivity   | `int`                  | Pass `Ngrams.CASE_SENSITIVE` or `Ngrams.CASE_INSENSITIVE`. Case insensitive calls will ignore differences in case when removing duplicates e.g. `"Turning"`, `"turning"`, `"TURNING"` will all be seen as identical and reduces to just `"Turning"`.                                                                                                           | 

Returns an `ArrayList<ArrayList<String>>` of paired n-grams.

Case sensitive, direction sensitive:

```
String text = "Something and SOMETHING and something and something";
ArrayList<String> words = new ArrayList<String>(Arrays.asList(text.split("\\s+")));
ArrayList<ArrayList<String>> bagOfSkipgrams = Ngrams.bagOfSkipgrams(words, 2, 2, Ngrams.DONT_SORT_NGRAMS, Ngrams.CASE_SENSITIVE);
System.out.println(bagOfSkipgrams.toString());
```

Output:

```
[ [Something and, and SOMETHING],
  [Something and, SOMETHING and],
  [and SOMETHING, SOMETHING and],
  [and SOMETHING, and something],
  [SOMETHING and, and something],
  [SOMETHING and, something and],
  [and something, something and],
  [and something, and something],
  [something and, and something] ]
```

Case sensitive, direction insensitive `Ngrams.bagOfSkipgrams(words, 2, 2, Ngrams.SORT_NGRAMS, Ngrams.CASE_SENSITIVE)`:

```
[ [and SOMETHING, Something and],
 [Something and, SOMETHING and],
 [and SOMETHING, SOMETHING and],
 [and SOMETHING, and something],
 [and something, SOMETHING and],
 [SOMETHING and, something and],
 [and something, something and],
 [and something, and something] ]
```

Case insensitive, direction insensitive `Ngrams.bagOfSkipgrams(words, 2, 2, Ngrams.SORT_NGRAMS, Ngrams.CASE_INSENSITIVE)`:

```
[ [and SOMETHING, Something and], 
[Something and, SOMETHING and], 
[and SOMETHING, and something] ]
```

Case insensitive, direction sensitive `Ngrams.bagOfSkipgrams(words, 2, 2, Ngrams.DONT_SORT_NGRAMS, Ngrams.CASE_INSENSITIVE)`:

```
[ [Something and, and SOMETHING], 
[Something and, SOMETHING and], 
[and SOMETHING, SOMETHING and], 
[and SOMETHING, and something] ]
```

### :shell: concatSkipgrams

Pass skip-grams through this method if you would prefer a simpler `ArrayList<String>` where skip-grams have been concatenated into a single string.

| Parameter | Type                                | Description                                                                                                                                                                  | 
|-----------|-------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| skipGrams | `ArrayList<ArrayList<String>>` | Skip-grams created using `skipGrams` or `bagOfSkipGrams` which you want to simplify into `ArrayList<String>` by joining each n-gram pair into one string. | 

```
ArrayList<String> words = new ArrayList<String>(Arrays.asList("These are some words".split("\\s+")));
ArrayList<String> skipgrams = Ngrams.concatSkipgrams(
	Ngrams.skipgrams(words, 2, 2, Ngrams.DONT_SORT_NGRAMS)
);
System.out.println(skipgrams.toString());
```

Output: `[These are are some, These are some words, are some some words]`

### :shell: sanitiseToWords

A rudimentary method that attempts to refine messy text into an `ArrayList<String>` of words.

| Parameter | Type     | Description                                   | 
|-----------|----------|-----------------------------------------------| 
| text      | `String` | The source text you want to split into words. | 

Note that this is mainly only good for English-language text - it does not support accented characters etc.

Its approach is to replace anything outwith a small list of allowable characters with a space, avoiding any double spacing, and then split by those spaces.

This works quite well for many English-language texts - with the occasional mistake.

However, you may prefer to roll your own sanitisation/splitting/[tokenisation method](https://en.wikipedia.org/wiki/Lexical_analysis#Tokenization) based more closely on your source text(s).

```
String text = "   Turning and turning in the widening gyre\r\n    The falcon cannot hear the falconer;\r\n    Things fall apart; the centre cannot hold;\r\n    Mere anarchy is loosed upon the world   ";
ArrayList<String> words = Ngrams.sanitiseToWords(text);
int last = words.size() - 1;

String output = "[";

for(int i = 0; i <= last; i++) {
	output +="'"+ words.get(i) +"'";
	
	if(i != last) {
		output +=", ";
	}
}

output +="]";

System.out.println(output);
```

Output:

```
[ 'Turning', 'and', 'turning', 'in', 'the', 'widening', 'gyre',
  'The', 'falcon', 'cannot', 'hear', 'the', 'falconer',
  'Things', 'fall', 'apart', 'the', 'centre', 'cannot', 'hold',
  'Mere', 'anarchy', 'is', 'loosed', 'upon', 'the', 'world' ]
```