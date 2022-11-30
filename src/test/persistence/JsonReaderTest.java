package persistence;

import model.NPC;
import model.StatBlock;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFileCase() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            reader.readLibrary();
            reader.readEncounter();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFileCase() {
        JsonReader reader = new JsonReader("./data/testAutoBlocksAppEmptyCase.json");
        try {
            List<StatBlock> library = reader.readLibrary();
            List<NPC> encounter = reader.readEncounter();
            assertEquals(0, library.size());
            assertEquals(0, encounter.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderBaseCase() {
        JsonReader reader = new JsonReader("./data/testAutoBlocksAppBaseCase.json");
        try {
            List<StatBlock> library = reader.readLibrary();
            List<NPC> encounter = reader.readEncounter();
            assertEquals(2, library.size());
            assertEquals(3, encounter.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
