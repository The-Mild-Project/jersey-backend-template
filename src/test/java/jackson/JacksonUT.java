package jackson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.the.mild.project.server.jackson.JacksonTest;
import com.the.mild.project.server.jackson.util.JacksonHandler;

public class JacksonUT {

    @Test
    public void jacksonTest() {
        final String key = "test";
        final String value = "resource";
        final String expected = String.format("{\"key\":\"%s\",\"value\":\"%s\"}", key, value);

        final JacksonTest test = new JacksonTest(key, value);
        final String result = JacksonHandler.stringify(test);

        assertEquals(String.format("\"%s\" did not match expected \"%s\"", result, expected), result, expected);
    }
}
