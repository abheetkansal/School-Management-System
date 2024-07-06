package persistence;

import model.Professor;
import model.School;
import model.Student;
import model.Transcript;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads School from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads School from file and returns it;
    // throws IOException if an error occurs reading data from file
    public School read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchool(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses School from JSON object and returns it
    private School parseSchool(JSONObject jsonObject) {
        Integer balance = jsonObject.getInt("balance");
        Integer spendings = jsonObject.getInt("spendings");
        School sl = new School(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        sl.setBalance(balance);
        sl.setSpendings(spendings);
        addProfessorsToList(sl, jsonObject);
        addStudentsToList(sl, jsonObject);
        addTranscriptsToList(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses list of professors from JSON object and adds them to School
    private void addProfessorsToList(School sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("professors");
        for (Object json : jsonArray) {
            JSONObject nextProfessor = (JSONObject) json;
            addProfessor(sl, nextProfessor);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses list of students from JSON object and adds them to School
    private void addStudentsToList(School sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(sl, nextStudent);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses list of transcripts from JSON object and adds them to School
    private void addTranscriptsToList(School sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("transcripts");
        for (Object json : jsonArray) {
            JSONObject nextTranscript = (JSONObject) json;
            addTranscript(sl, nextTranscript);
        }
    }


    // MODIFIES: sl
    // EFFECTS: parses professor from JSON object and adds it to School
    private void addProfessor(School sl, JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        Integer salary = jsonObject.getInt("salary");
        Integer salaryRecieved = jsonObject.getInt("salaryRecieved");
        Professor professor = new Professor(id, name, salary);
        professor.setSalaryRecieved(salaryRecieved);
        sl.addProfessor(professor);
    }

    // MODIFIES: sl
    // EFFECTS: parses student from JSON object and adds it to School
    private void addStudent(School sl, JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        Integer paidTuition = jsonObject.getInt("paidTuition");
        String faculty = jsonObject.getString("faculty");
        Integer totalTuition = jsonObject.getInt("totalTuition");
        Student student = new Student(id, name, faculty);
        student.setPaidTuition(paidTuition);
        student.setTotalTuition(totalTuition);
        sl.addStudent(student);
    }

    // MODIFIES: sl
    // EFFECTS: parses transcript from JSON object and adds it to School
    private void addTranscript(School sl, JSONObject jsonObject) {
        Integer studentID = jsonObject.getInt("studentID");
        Integer math = jsonObject.getInt("math");
        Integer physics = jsonObject.getInt("physics");
        Integer chemistry = jsonObject.getInt("chemistry");
        Integer english = jsonObject.getInt("english");
        Integer computerScience = jsonObject.getInt("computerScience");
        Integer businessStudies = jsonObject.getInt("businessStudies");
        Integer accountancy = jsonObject.getInt("accountancy");
        Integer economics = jsonObject.getInt("economics");
        Integer history = jsonObject.getInt("history");
        Integer civics = jsonObject.getInt("civics");
        Integer geography = jsonObject.getInt("geography");
        Transcript transcript = new Transcript(studentID);
        setSubjects(sl, math, physics, chemistry, english, computerScience,
                businessStudies, accountancy, economics, history, civics, geography, transcript);
    }

    // MODIFIES: sl, math, physics, chemistry, english, computerScience,
    //                businessStudies, accountancy, economics, history, civics, geography, transcript
    // EFFECTS: sets grades for transcript from JSON object (helper)
    private void setSubjects(School sl, Integer math, Integer physics, Integer chemistry,
                             Integer english, Integer computerScience, Integer businessStudies,
                             Integer accountancy, Integer economics, Integer history, Integer civics,
                             Integer geography, Transcript transcript) {
        transcript.setMath(math);
        transcript.setPhysics(physics);
        transcript.setChemistry(chemistry);
        transcript.setEnglish(english);
        transcript.setComputerScience(computerScience);
        transcript.setBusinessStudies(businessStudies);
        transcript.setAccountancy(accountancy);
        transcript.setEconomics(economics);
        transcript.setHistory(history);
        transcript.setCivics(civics);
        transcript.setGeography(geography);
        sl.getGrades().add(transcript);
    }
}
