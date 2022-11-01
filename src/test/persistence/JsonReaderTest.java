package persistence;

import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CITATION: from JsonSerializationDemo
public class JsonReaderTest implements JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library library = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/emptyLibrary.json");
        try {
            Library library = reader.read();
            assertEquals("main library", library.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
