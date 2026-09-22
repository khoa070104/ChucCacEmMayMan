package model;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Lớp TextCounter cung cấp các phương thức đếm số lần xuất hiện của từng từ
 * và từng ký tự trong một chuỗi.
 *
 * @version 22/09/2026
 */
public class TextCounter {

    private final String content;

    /**
     * Khởi tạo đối tượng với nội dung cần thống kê.
     *
     * @param content nội dung cần đếm từ và ký tự
     */
    public TextCounter(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content must not be null.");
        }
        this.content = content;
    }

    /**
     * Đếm số lần xuất hiện của từng từ trong nội dung.
     *
     * @return bảng chứa từ và số lần xuất hiện tương ứng
     */
    public Map<String, Integer> countWords() {
        Map<String, Integer> wordCounter = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(content);

        // Tách lần lượt từng từ và cập nhật số lần xuất hiện.
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1);
        }
        return wordCounter;
    }

    /**
     * Đếm số lần xuất hiện của từng ký tự, không bao gồm khoảng trắng.
     *
     * @return bảng chứa ký tự và số lần xuất hiện tương ứng
     */
    public Map<Character, Integer> countCharacters() {
        Map<Character, Integer> characterCounter = new HashMap<>();

        // Duyệt qua toàn bộ chuỗi và cập nhật số lần xuất hiện của từng ký tự.
        for (int i = 0; i < content.length(); i++) {
            char character = content.charAt(i);

            // Khoảng trắng chỉ dùng để phân tách từ nên không được tính là ký tự.
            if (!Character.isWhitespace(character)) {
                characterCounter.put(
                        character, characterCounter.getOrDefault(character, 0) + 1);
            }
        }
        return characterCounter;
    }
}
