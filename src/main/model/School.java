package model;

import exception.NoBalanceException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

//The class represents the data for School in Indian High School
public class School implements Writable {

    private List<Professor> professors;
    private List<Student> students;
    private List<Transcript> grades;
    private int balance;
    private int spendings;
    private int memory = 0;

    //EFFECTS: Constructs a School object with all its initialized data like lists, balance, and spendings
    public School(List<Professor> professors, List<Student> students, List<Transcript> grades) {
        this.professors = professors;
        this.students = students;
        this.grades = grades;
        balance = 0;
        spendings = 0;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Transcript> getGrades() {
        return grades;
    }

    //MODIFIES: this
    //EFFECTS: adds a new professor to the list of professors
    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    //MODIFIES: this
    //EFFECTS: adds a new student to the list of students
    public void addStudent(Student student) {
        students.add(student);
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSpendings() {
        return spendings;
    }

    public void setSpendings(int spendings) {
        this.spendings = spendings;
    }

    //MODIFIES: this
    //EFFECTS: adds a money received to the overall balance if it is greater than 0
    public void addMoneyReceived(int moneyReceived) {
        if (moneyReceived > 0) {
            balance += moneyReceived;
        }
    }

    //MODIFIES: this
    //EFFECTS: reduces money spent from overall balance if balance - moneySpent is greater or equal to 0 else throw
    // NoBalanceException
    public void reduceMoneySpent(int moneySpent) throws NoBalanceException {
        if (!(balance - moneySpent >= 0)) {
            throw new NoBalanceException();
        }
        spendings += moneySpent;
        balance -= moneySpent;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", balance);
        json.put("spendings", spendings);
        json.put("professors", professorsToJson());
        json.put("students", studentsToJson());
        json.put("transcripts", transcriptsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray professorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Professor p : professors) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : students) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray transcriptsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Transcript t : this.grades) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}


