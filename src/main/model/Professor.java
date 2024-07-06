package model;

import org.json.JSONObject;
import persistence.Writable;

//The class represents the data for professors in Indian High School
public class Professor implements Writable {
    private int professorID;
    private String professorName;
    private int totalSalary;
    private int salaryRecieved;

    //EFFECTS: Constructs a professor object with all its initialized data
    public Professor(int id, String name, int salary) {
        this.professorID = id;
        this.professorName = name;
        this.totalSalary = salary;
        this.salaryRecieved = 0;
    }

    public int getProfessorID() {
        return professorID;
    }

    public String getProfessorName() {
        return professorName;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public int getSalaryRecieved() {
        return salaryRecieved;
    }

    public void setSalaryRecieved(int salaryRecieved) {
        this.salaryRecieved = salaryRecieved;
    }

    //MODIFIES: this
    //EFFECTS: adds salary collected to salary received
    public void sendSalary(int salary) {
        salaryRecieved += salary;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", professorID);
        json.put("name", professorName);
        json.put("salary", totalSalary);
        json.put("salaryRecieved", salaryRecieved);
        return json;
    }
}
