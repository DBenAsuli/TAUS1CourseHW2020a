package il.ac.tau.cs.sw1.ex8.wordsRank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileIndexTest {

	public static final String INPUT_FOLDER = "./test_resources";

	@Test
	public void getCountInFileTest() {
		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		try {
			assertEquals(fIndex.getCountInFile("memory.txt", "MEMORY"), 3);
			assertEquals(fIndex.getCountInFile("memory.txt", "MemorY"), 3);
			assertEquals(fIndex.getCountInFile("memory.txt", "is"), 2);
			assertEquals(fIndex.getCountInFile("memory.txt", "computer"), 4);
			assertEquals(fIndex.getCountInFile("memory.txt", "cayley hamilton"), 0);
		} catch (FileIndexException e) {
			fail("should not reach this line");
		}

	}

	@Test
	public void getRankForWordInFileTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		try {
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "COMPUTER"), 1);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "memory"), 2);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "is"), 3);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "640k"), 4);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "a"), 5);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "anyone"), 6);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "ever"), 7);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "more"), 8);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "need"), 9);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "on"), 10);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "than"), 11);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "will"), 12);
			assertEquals(fIndex.getRankForWordInFile("memory.txt", "cayley hamilton"), 42);
		} catch (FileIndexException e) {
			fail("should not reach this line");
		}

	}

	@Test
	public void getAverageRankForWordTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		assertEquals(fIndex.getAverageRankForWord("anyone"), 4);
		assertEquals(fIndex.getAverageRankForWord("COMPuter"), 18);
		assertEquals(fIndex.getAverageRankForWord("memory"), 19);
		assertEquals(fIndex.getAverageRankForWord("is"), 19);
		assertEquals(fIndex.getAverageRankForWord("640k"), 20);
		assertEquals(fIndex.getAverageRankForWord("a"), 20);
		assertEquals(fIndex.getAverageRankForWord("ever"), 21);
		assertEquals(fIndex.getAverageRankForWord("need"), 22);
		assertEquals(fIndex.getAverageRankForWord("more"), 22);
		assertEquals(fIndex.getAverageRankForWord("cayley-hamilton"), 22);
		assertEquals(fIndex.getAverageRankForWord("https://www.youtube.com/watch?v=rnqaxulzlae"), 23);
		assertEquals(fIndex.getAverageRankForWord("than"), 23);
		assertEquals(fIndex.getAverageRankForWord("forever"), 23);
		assertEquals(fIndex.getAverageRankForWord("on"), 23);
		assertEquals(fIndex.getAverageRankForWord("will"), 24);
		assertEquals(fIndex.getAverageRankForWord("java"), 24);
		assertEquals(fIndex.getAverageRankForWord("C++"), 39);

	}

	@Test
	public void getWordsWithAverageRankSmallerThanKTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		List<String> res = fIndex.getWordsWithAverageRankSmallerThanK(5);
		List<String> expected = Arrays.asList(new String[] { "anyone" });
		assertEquals(res, expected);

		res = fIndex.getWordsWithAverageRankSmallerThanK(20);
		expected = Arrays.asList(new String[] { "anyone", "computer", "memory", "is" });
		Collections.sort(res);
		Collections.sort(expected);
		assertEquals(res, expected);

		res = fIndex.getWordsWithAverageRankSmallerThanK(20);
		expected = Arrays.asList(new String[] { "anyone", "computer", "memory", "is" });
		Collections.sort(res);
		Collections.sort(expected);
		assertEquals(res, expected);

	}

	@Test
	public void getWordsWithMinRankSmallerThanKTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		List<String> res = fIndex.getWordsWithMinRankSmallerThanK(1);
		List<String> expected = new ArrayList<>();
		assertEquals(res, expected);

		res = fIndex.getWordsWithMinRankSmallerThanK(2);
		expected = Arrays.asList(new String[] { "anyone", "computer" });
		Collections.sort(res);
		Collections.sort(expected);
		assertEquals(res, expected);

		res = fIndex.getWordsWithMinRankSmallerThanK(3);
		expected = Arrays.asList(new String[] { "computer", "memory", "anyone", "cayley-hamilton" + "" });
		Collections.sort(res);
		Collections.sort(expected);
		assertEquals(res, expected);

	}

	@Test
	public void getWordsWithMaxRankSmallerThanKTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		List<String> res = fIndex.getWordsWithMaxRankSmallerThanK(6);
		List<String> expected = new ArrayList<>();
		assertEquals(res, expected);
		
		res = fIndex.getWordsWithMaxRankSmallerThanK(7);
		expected = Arrays.asList(new String[] { "anyone" });
		assertEquals(res, expected);

		res = fIndex.getWordsWithMaxRankSmallerThanK(36);
		System.out.println(res);
		expected = Arrays.asList(new String[] { "anyone", "640k", "a", "memory", "will", "need", "more", "is", "ever",
				"computer", "than", "on" });
		Collections.sort(res);
		Collections.sort(expected);
		assertEquals(res, expected);

	}

}
