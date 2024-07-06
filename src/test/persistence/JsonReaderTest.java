package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest  {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            School sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySchool() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchool.json");
        try {
            School sl = reader.read();
            assertEquals(0, sl.getProfessors().size());
            assertEquals(0, sl.getStudents().size());
            assertEquals(0, sl.getGrades().size());
            assertEquals(0, sl.getSpendings());
            assertEquals(0, sl.getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSchool() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSchool.json");
        try {
            School sl = reader.read();
            assertEquals(1, sl.getProfessors().size());
            assertEquals("aha",sl.getProfessors().get(0).getProfessorName());
            assertEquals(1, sl.getStudents().size());
            assertEquals("abheet",sl.getStudents().get(0).getStudentName());
            assertEquals(1, sl.getGrades().size());
            assertEquals(0, sl.getSpendings());
            assertEquals(100, sl.getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}