package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorTest {
    Professor p1;

    @BeforeEach
    public void professorSetUp() {
        p1 = new Professor(123, "aaa", 10000);
    }

    @Test
    public void testHalfSendSalary() {
        int salary = 5000;
        p1.sendSalary(salary);
        assertEquals(p1.getSalaryRecieved(), salary);
    }

    @Test
    public void testLittleSendSalary() {
        int salary = 1000;
        p1.sendSalary(salary);
        assertEquals(p1.getSalaryRecieved(), salary);
    }

    @Test
    public void testSendSalary() {
        int salary = 10000;
        p1.sendSalary(salary);
        assertEquals(p1.getSalaryRecieved(), salary);
    }

    @Test
    public void testGetProfessorID() {
        p1.getProfessorID();
        assertEquals(123, p1.getProfessorID());
    }

    @Test
    public void testGetProfessorName() {
        p1.getProfessorName();
        assertEquals("aaa", p1.getProfessorName());
    }

    @Test
    public void testGetTotalSalary() {
        p1.getTotalSalary();
        assertEquals(10000, p1.getTotalSalary());
    }

    @Test
    public void testSetSalaryReieved() {
        p1.setSalaryRecieved(30);
        assertEquals(30, p1.getSalaryRecieved());
    }

    @Test
    public void testToJson() {
        JSONObject p = p1.toJson();
        assertEquals("aaa", p.getString("name"));
        assertEquals(123, p.getInt("id"));
        assertEquals(10000, p.getInt("salary"));
        assertEquals(0, p.getInt("salaryRecieved"));
    }
}

