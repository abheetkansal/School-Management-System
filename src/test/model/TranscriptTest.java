package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranscriptTest {
    Transcript t1;

    @BeforeEach
    public void transcriptSetUp() {
        t1 = new Transcript(221);
    }

    @Test
    public void testSetMath() {
        t1.setMath(78);
        assertEquals(78, t1.getMathGrade());
    }

    @Test
    public void testSetEnglish() {
        t1.setEnglish(86);
        assertEquals(86, t1.getEnglishGrade());
    }

    @Test
    public void testSetPhysics() {
        t1.setPhysics(50);
        assertEquals(50,t1.getPhysicsGrade());
    }

    @Test
    public void testSetChemistry() {
        t1.setChemistry(47);
        assertEquals(47, t1.getChemistryGrade());
    }

    @Test
    public void testSetComputerScience() {
        t1.setComputerScience(100);
        assertEquals(100, t1.getComputerScienceGrade());
    }

    @Test
    public void testSetAccountancy() {
        t1.setAccountancy(98);
        assertEquals(98, t1.getAccountancyGrade());
    }

    @Test
    public void testSetBusinessStudies() {
        t1.setBusinessStudies(59);
        assertEquals(59, t1.getBusinessStudiesGrade());
    }

    @Test
    public void testSetEconomics() {
        t1.setEconomics(24);
        assertEquals(24, t1.getEconomicsGrade());
    }

    @Test
    public void testSetCivics() {
        t1.setCivics(30);
        assertEquals(30, t1.getCivicsGrade());
    }

    @Test
    public void testSetHistory() {
        t1.setHistory(80);
        assertEquals(80, t1.getHistoryGrade());
    }

    @Test
    public void testSetGeography() {
        t1.setGeography(90);
        assertEquals(90, t1.getGeographyGrade());
    }

    @Test
    public void testGetAverage() {
        t1.setMath(90);
        t1.setHistory(80);
        t1.setCivics(70);
        t1.setGeography(80);
        t1.setEnglish(32);
        assertEquals(70.4, t1.getAverage());
    }

    @Test
    public void testSetStudentID() {
        t1.setStudentID(220);
        assertEquals(220, t1.getStudentID());
    }

    @Test
    public void testToJson() {
        JSONObject t = t1.toJson();
        assertEquals(221, t.getInt("studentID"));
        assertEquals(0, t.getInt("math"));
        assertEquals(0, t.getInt("physics"));
        assertEquals(0, t.getInt("chemistry"));
        assertEquals(0, t.getInt("english"));
        assertEquals(0, t.getInt("computerScience"));
        assertEquals(0, t.getInt("businessStudies"));
        assertEquals(0, t.getInt("accountancy"));
        assertEquals(0, t.getInt("economics"));
        assertEquals(0, t.getInt("civics"));
        assertEquals(0, t.getInt("history"));
        assertEquals(0, t.getInt("geography"));
    }
}
