package word;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class wordCombinationTest {
    @Test
    public void testIsEnglishWord_Success() throws IOException {
        Dictionary.loadDictionary();
        boolean isTrue = Dictionary.isEnglishWord("Work");
        Assert.assertEquals(isTrue, true);
    }

    @Test
    public void testIsEnglishWord_Fail() throws IOException {
        Dictionary.loadDictionary();
        boolean isTrue = Dictionary.isEnglishWord("ooll");
        Assert.assertEquals(isTrue, false);
    }
}