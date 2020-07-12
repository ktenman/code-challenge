package base;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertNotNull;

public class FileUtils {
    public static String getJson(String fileName) {
        try {
            InputStream stream = currentThread().getContextClassLoader().getResourceAsStream(fileName);
            assertNotNull(fileName + " not found in resources directory", stream);
            return IOUtils.toString(stream, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
