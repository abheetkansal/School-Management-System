package model;

import exception.NoBalanceException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {
    School s1;

    @BeforeEach
    public void schoolSetUp() {
        s1 = new School(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
    }

    @Test
    public void testAddProfessor() {
        Professor p1 = new Professor(1, "aa", 10000);
        s1.addProfessor(p1);
        assertTrue(s1.getProfessors().contains(p1));
    }

    @Test
    public void testAddStudent() {
        Student st1 = new Student(1, "aa", "Science");
        s1.addStudent(st1);
        assertTrue(s1.getStudents().contains(st1));
    }

    @Test
    public void testAddMoneyReceived() {
        s1.addMoneyReceived(2000);
        assertEquals(2000, s1.getBalance());
    }

    @Test
    public void testAddNegativeMoneyReceived() {
        s1.addMoneyReceived(-2000);
        assertEquals(0, s1.getBalance());
    }

    @Test
    public void testReduceLittleMoneySpent() {
        s1.addMoneyReceived(5000);
        try {
            s1.reduceMoneySpent(2000);
        } catch (NoBalanceException e) {
            fail("Shouldn't have been called");
        }
        assertEquals(3000, s1.getBalance());
    }

    @Test
    public void testReduceEqualMoneySpent() {
        s1.addMoneyReceived(2000);
        try {
            s1.reduceMoneySpent(2000);
        } catch (NoBalanceException e) {
            fail("Shouldn't have been called");
        }
        assertEquals(0, s1.getBalance());
    }

    @Test
    public void testReduceLowBalanceMoreMoneySpent() {
        try {
            s1.reduceMoneySpent(2000);
            fail("Shouldn't have been called");
        } catch (NoBalanceException e) {
           //expected
        }
        assertEquals(0, s1.getBalance());
    }

    @Test
    public void testGetGrades() {
        assertEquals(0, s1.getGrades().size());
    }

    @Test
    public void testGetGradesOccupied() {
        s1.getGrades().add(new Transcript(12));
        assertEquals(1, s1.getGrades().size());
    }

    @Test
    public void testGetMemory() {
        assertEquals(0, s1.getMemory());
    }

    @Test
    public void testSetMemory() {
        s1.setMemory(220);
        assertEquals(220, s1.getMemory());
    }

    @Test
    public void testGetSpendings() {
        assertEquals(0, s1.getSpendings());
    }

    @Test
    public void testToJson() {
        Professor p1 = new Professor(1, "aa", 10000);
        s1.addProfessor(p1);
        Student st1 = new Student(1, "aa", "Science");
        s1.addStudent(st1);
        Transcript t1 = new Transcript(1);
        s1.getGrades().add(t1);
        JSONObject s = s1.toJson();
        assertTrue(s1.getStudents().contains(st1));
        assertEquals(0, s.getInt("balance"));
        assertEquals(0, s.getInt("spendings"));
        assertEquals(1, s.getJSONArray("professors").toList().size());
        assertEquals(1, s.getJSONArray("students").toList().size());
        assertEquals(1, s.getJSONArray("transcripts").toList().size());
    }
}

