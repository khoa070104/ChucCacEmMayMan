package model;

import java.util.Map;

/**
 * Bộ kiểm thử độc lập cho chức năng đếm từ và ký tự.
 */
public class TextCounterTest {

    public static void main(String[] args) {
        testExampleContent();
        testRepeatedWordsAndSpaces();
        testEmptyContent();
        testInvalidContent();
        System.out.println("TextCounterTest: PASSED");
    }

    private static void testExampleContent() {
        TextCounter counter = new TextCounter("hello world");
        assertCount(counter.countWords(), "hello", 1);
        assertCount(counter.countWords(), "world", 1);
        assertCount(counter.countCharacters(), 'l', 3);
        assertMissing(counter.countCharacters(), ' ');
    }

    private static void testRepeatedWordsAndSpaces() {
        TextCounter counter = new TextCounter("java  java code");
        assertCount(counter.countWords(), "java", 2);
        assertCount(counter.countWords(), "code", 1);
        assertMissing(counter.countCharacters(), ' ');
        assertCount(counter.countCharacters(), 'a', 4);
    }

    private static void testEmptyContent() {
        TextCounter counter = new TextCounter("");
        if (!counter.countWords().isEmpty() || !counter.countCharacters().isEmpty()) {
            throw new AssertionError("Empty content must produce empty counters.");
        }
    }

    private static void testInvalidContent() {
        try {
            new TextCounter(null);
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // Kết quả mong đợi.
        }
    }

    private static <T> void assertCount(Map<T, Integer> counter, T key, int expected) {
        if (!Integer.valueOf(expected).equals(counter.get(key))) {
            throw new AssertionError("Unexpected count for " + key + ".");
        }
    }

    private static <T> void assertMissing(Map<T, Integer> counter, T key) {
        if (counter.containsKey(key)) {
            throw new AssertionError("Counter must not contain " + key + ".");
        }
    }
}
