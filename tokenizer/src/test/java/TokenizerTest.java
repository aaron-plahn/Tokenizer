import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.digiteched.tokenizer.Tokenizer.Tokenizer;

public class TokenizerTest {
    @Test
    public void shouldSplitOnWhiteSpace() {
        // Arrange
        Tokenizer tokenizer = new Tokenizer();

        String testDocument = "We have one sentence with exactly 8 white spaces.";

        // 8 spaces => 9 tokens
        int expectedNumberOfTokens = 9;

        // Act
        String[] result = tokenizer.tokenize(testDocument);

        assertEquals(expectedNumberOfTokens,result.length);
    }

    @Test
    public void shouldRemoveCaseDifferences() {
        // Arrange
        Tokenizer tokenizer = new Tokenizer();

        String testDocument = "Hello HELLO hEllO hello";

        // Act
        String[] result = tokenizer.tokenize(testDocument);

        String firstToken = result[0];

        ArrayList<String> inconsistentlyCasedTokens = new ArrayList<String>();

        for (int i = 1; i < result.length; i++) {
            String currentToken = result[i];

            if (!currentToken.equals(firstToken)) {
                inconsistentlyCasedTokens.add(currentToken);
            }
        }

        assertEquals(0, inconsistentlyCasedTokens.size());
    }

    @Test
    public void shouldRemovePunctuation() {
        // Arrange
        Tokenizer tokenizer = new Tokenizer();

        // 14 expected tokens
        String testDocument = "I have, some punctuation marks like? I also have. I even have, with excitement!!";

        // Act
        String[] result = tokenizer.tokenize(testDocument);


        ArrayList<String> tokensWithPunctuation = new ArrayList<String>();

        for (String token : result) {
            if (token.contains(".") || token.contains(",") || token.contains("?") || token.contains("!")) {
                tokensWithPunctuation.add(token);
            }
        }

        assertEquals(0, tokensWithPunctuation.size());
    }

    @Test
    public void shouldRemoveStopWords() {
        // Arrange
        Tokenizer tokenizer = new Tokenizer();

        // 21 words =- 10 stop word occurrences = 11 expected words
        String testDocument = "This is a test document. It has THe following stop words: the, i, and IS. A and it are some of the stopwords.";

        // Act
        String[] result = tokenizer.tokenize(testDocument);

        ArrayList<String> stopwords = new ArrayList<String>();
        stopwords.add("a");
        stopwords.add("the");
        stopwords.add("it");
        stopwords.add("i");
        stopwords.add("is");

        String[] foundStopWords = Arrays.stream(result)
                .filter(t -> stopwords.stream().anyMatch(
                        (stopword) -> t.equalsIgnoreCase(stopword)))
                .toArray(String[]::new);

        assertEquals(0, foundStopWords.length);
    }
}
