package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            School sl = new School(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySchool() {
        try {
            School sl = new School(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            sl = reader.read();
            assertEquals(0, sl.getProfessors().size());
            assertEquals(0, sl.getStudents().size());
            assertEquals(0, sl.getGrades().size());
            assertEquals(0, sl.getSpendings());
            assertEquals(0, sl.getBalance());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSchool() {
        try {
            School sl = new School(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            sl.addProfessor(new Professor(1, "aha", 1000));
            sl.addStudent(new Student(23, "abheet", "Science"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            sl = reader.read();
            assertEquals(1, sl.getProfessors().size());
            assertEquals("aha",sl.getProfessors().get(0).getProfessorName());
            assertEquals(1, sl.getStudents().size());
            assertEquals("abheet",sl.getStudents().get(0).getStudentName());
            assertEquals(0, sl.getGrades().size());
            assertEquals(0, sl.getSpendings());
            assertEquals(0, sl.getBalance());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}