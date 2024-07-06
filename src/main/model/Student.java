package model;

import org.json.JSONObject;
import persistence.Writable;

//The class represents the data for students in Indian High School
public class Student implements Writable {
    private int studentID;
    private String studentName;
    private int paidTuition;
    private int totalTuition;
    public String faculty;

    //EFFECTS: Constructs a Student object and initializes the data required
    public Student(int id, String name, String faculty) {
        this.paidTuition = 0;
        this.totalTuition = 30000;
        this.studentID = id;
        this.studentName = name;
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setPaidTuition(int paidTuition) {
        this.paidTuition = paidTuition;
    }

    public void setTotalTuition(int totalTuition) {
        this.totalTuition = totalTuition;
    }

    //MODIFIES: this
    //EFFECTS: adds tuition paid at the current time to the overall paidTuition
    public void payTuition(int tuition) {
        paidTuition += tuition;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getTuitionPaid() {
        return paidTuition;
    }

    public int getTotalTuition() {
        return totalTuition;
    }

    //MODIFIES: this
    //EFFECTS: shows the tuition that is left to be paid by subtracting paidTuition from it
    public int getPendingTuition() {
        return totalTuition - paidTuition;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", studentID);
        json.put("name", studentName);
        json.put("faculty", faculty);
        json.put("paidTuition", paidTuition);
        json.put("totalTuition", totalTuition);
        return json;
    }

}
