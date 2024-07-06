package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student st1;

    @BeforeEach
    public void studentSetUp() {
        st1 = new Student(221, "Abheet", "Science");
    }

    @Test
    public void testGetLargePendingTuition() {
        int tuition = 28000;
        st1.payTuition(2000);
        assertEquals(tuition, st1.getPendingTuition());
    }

    @Test
    public void testGetLittlePendingTuition() {
        int tuition = 1000;
        st1.payTuition(29000);
        assertEquals(tuition, st1.getPendingTuition());
    }

    @Test
    public void testGetNoPendingTuition() {
        int tuition = 0;
        st1.payTuition(30000);
        assertEquals(tuition, st1.getPendingTuition());
    }

    @Test
    public void testPayLittleTuition() {
        int tuition = 2000;
        st1.payTuition(2000);
        assertEquals(tuition, st1.getTuitionPaid());
    }

    @Test
    public void testPayFullTuition() {
        int tuition = 30000;
        st1.payTuition(30000);
        assertEquals(tuition, st1.getTuitionPaid());
    }

    @Test
    public void testGetStudentID() {
        assertEquals(221, st1.getStudentID());
    }

    @Test
    public void testGetTotalTuition() {
        assertEquals(30000, st1.getTotalTuition());
    }

    @Test
    public void testGetFaculty() {
        assertEquals("Science", st1.getFaculty());
    }

    @Test
    public void testGetStudentName() {
        assertEquals("Abheet", st1.getStudentName());
    }

    @Test
    public void testSetPaidTuition() {
        st1.setPaidTuition(30);
        assertEquals(30, st1.getTuitionPaid());
    }

    @Test
    public void testSetTotalTuition() {
        assertEquals(30000, st1.getTotalTuition());
    }

    @Test
    public void testToJson() {
        JSONObject s = st1.toJson();
        assertEquals("Abheet", s.getString("name"));
        assertEquals(221, s.getInt("id"));
        assertEquals("Science", s.getString("faculty"));
        assertEquals(0, s.getInt("paidTuition"));
        assertEquals(30000, s.getInt("totalTuition"));
    }
}



