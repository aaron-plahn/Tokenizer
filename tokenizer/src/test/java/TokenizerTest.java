import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.digiteched.tokenizer.Tokenizer.Tokenizer;

public class TokenizerTest {
    @Test
    public void shouldSplitOnWhiteSpace(){
        // Arrange
        Tokenizer tokenizer = new Tokenizer();

        String testDocument = "I am a sentence with exactly 8 white spaces.";

        // 8 spaces => 9 tokens
        int expectedNumberOfTokens = 9;

        // Act
        String[] result = tokenizer.tokenize(testDocument);

        assertEquals(result.length, expectedNumberOfTokens);
    }
}
