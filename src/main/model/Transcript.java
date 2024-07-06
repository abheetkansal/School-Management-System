package model;

import org.json.JSONObject;
import persistence.Writable;

//The class represents the data for grades of student in their registered courses in Indian High School
public class Transcript implements Writable {

    private int math;
    private int physics;
    private int chemistry;
    private int english;
    private int computerScience;
    private int businessStudies;
    private int accountancy;
    private int economics;
    private int history;
    private int civics;
    private int geography;
    private double average;
    private int studentID;

    //EFFECTS: Constructs an object for Transcript and initializes the required data
    public Transcript(int studentID) {
        this.studentID = studentID;
        this.math = 0;
        this.physics = 0;
        this.chemistry = 0;
        this.english = 0;
        this.computerScience = 0;
        this.businessStudies = 0;
        this.accountancy = 0;
        this.economics = 0;
        this.history = 0;
        this.civics = 0;
        this.geography = 0;
        this.average = 0;
    }

    //MODIFIES: this
    //EFFECTS: takes in all the subjects and gives the average score of students including all subjects
    public double getAverage() {
        average = (double) (math + physics + chemistry + english + computerScience
                + businessStudies + accountancy + economics + history + civics + geography) / 5;
        return average;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public void setComputerScience(int computerScience) {
        this.computerScience = computerScience;
    }

    public void setBusinessStudies(int businessStudies) {
        this.businessStudies = businessStudies;
    }

    public void setAccountancy(int accountancy) {
        this.accountancy = accountancy;
    }

    public void setEconomics(int economics) {
        this.economics = economics;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public void setCivics(int civics) {
        this.civics = civics;
    }

    public void setGeography(int geography) {
        this.geography = geography;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getBusinessStudiesGrade() {
        return businessStudies;
    }

    public int getAccountancyGrade() {
        return accountancy;
    }

    public int getEconomicsGrade() {
        return economics;
    }

    public int getHistoryGrade() {
        return history;
    }

    public int getCivicsGrade() {
        return civics;
    }

    public int getGeographyGrade() {
        return geography;
    }

    public int getMathGrade() {
        return math;
    }

    public int getPhysicsGrade() {
        return physics;
    }

    public int getChemistryGrade() {
        return chemistry;
    }

    public int getEnglishGrade() {
        return english;
    }

    public int getComputerScienceGrade() {
        return computerScience;
    }

    public int getStudentID() {
        return studentID;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentID", studentID);
        json.put("math", math);
        json.put("physics", physics);
        json.put("chemistry", chemistry);
        json.put("english", english);
        json.put("computerScience", computerScience);
        json.put("businessStudies", businessStudies);
        json.put("accountancy", accountancy);
        json.put("economics", economics);
        json.put("civics", civics);
        json.put("history", history);
        json.put("geography", geography);
        return json;
    }

}
