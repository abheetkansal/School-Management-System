package ui;

import exception.NoBalanceException;
import model.Professor;
import model.School;
import model.Student;
import model.Transcript;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//EFFECTS: represents the console based functioning of Indian High School Database Management System
public class SchoolManagementSystemUI {
    private static final String JSON_STORE = "./data/school.json";
    public int test1 = 0;
    public int test2 = 0;
    private School ihs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: initializes certain variables and objects that are used in the programme
    public SchoolManagementSystemUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        ihs = new School(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        Scanner s1 = new Scanner(System.in);
        boolean ans = true;
        loadSchool();

        while (ans) {
            menu();
            Integer selectedOption = Integer.parseInt(s1.nextLine());

            menuOptions(s1, selectedOption);
            System.out.println("Type true to login again or false to logout of system");
            ans = Boolean.parseBoolean(s1.nextLine());
        }
        saveSchool();
    }

    //EFFECTS: corresponds to the value of selected option in the switch
    public void menuOptions(Scanner s1, Integer selectedOption) {
        switch (selectedOption) {

            case 1:
                studentView(s1);
                break;

            case 2:
                professorView(s1);
                break;

            case 3:
                principalView(s1);
                break;
        }
    }

    //EFFECTS: checks the value entered and verifies the access code of principle to access its functions further
    public void principalView(Scanner s1) {
        System.out.println("Welcome Principal");
        System.out.println("Enter access code");
        Integer code = Integer.parseInt(s1.nextLine());
        if (code == 30128516) {
            boolean control = true;
            while (control) {
                principalOptions();

                Integer option = Integer.parseInt(s1.nextLine());

                principalOptionsHandler(s1, option);
                System.out.println("Do you want to do any other action true/false?");
                control = Boolean.parseBoolean(s1.nextLine());
            }
        } else {
            System.out.println("Invalid Principal Access Code");
        }
    }

    //EFFECTS: corresponds to the value of selected option in the switch for the appropriate case
    public void principalOptionsHandler(Scanner s1, Integer option) {
        switch (option) {

            case 1:
                studentListViewer();
                break;

            case 2:
                professorListViewer();
                break;

            case 3:
                studentTranscriptViewer(s1);
                break;

            case 4:
                System.out.println("The school's current bank balance: " + ihs.getBalance());
                break;

            case 5:
                System.out.println("The school's current spendings: " + ihs.getSpendings());
                break;

            case 6:
                professorAdder(s1);
                break;

            case 7:
                studentAdder(s1);
                break;
        }
    }

    //EFFECTS: views the transcript of the appropriate student matched with Student ID
    public void studentTranscriptViewer(Scanner s1) {
        System.out.println("Enter Student ID to view Transcript");
        Integer psid = Integer.parseInt(s1.nextLine());
        for (int i = 0; i < ihs.getStudents().size(); i++) {
            if (ihs.getStudents().get(i).getStudentID() == psid) {
                transcriptViewer(i);
            }
        }
    }

    //EFFECTS: adds a student to the List of Students in School
    public void studentAdder(Scanner s1) {
        System.out.println("Enter the Student ID you want to create for student");
        Integer sid = Integer.parseInt(s1.nextLine());
        System.out.println("Enter the Student Name");
        String name = s1.nextLine();
        System.out.println("Enter the Student Faculty");
        String faculty = s1.nextLine();
        ihs.addStudent(new Student(sid, name, faculty));
        ihs.getGrades().add(new Transcript(sid));
    }

    //EFFECTS: adds a professor by entering all its required details
    public void professorAdder(Scanner s1) {
        System.out.println("Enter the Professor ID you want to create for professor");
        Integer pid = Integer.parseInt(s1.nextLine());
        System.out.println("Enter the Professor Name");
        String pname = s1.nextLine();
        System.out.println("Enter the Professor Total Salary");
        Integer psalary = Integer.parseInt(s1.nextLine());
        ihs.addProfessor(new Professor(pid, pname, psalary));
    }

    //EFFECTS: views a list of professors present in the school with all their details
    public void professorListViewer() {
        for (int i = 0; i < ihs.getProfessors().size(); i++) {
            System.out.println("Professor Name: "
                    + ihs.getProfessors().get(i).getProfessorName());
            System.out.println("Professor ID: "
                    + ihs.getProfessors().get(i).getProfessorID());
            System.out.println("Professor Total Salary: "
                    + ihs.getProfessors().get(i).getTotalSalary());
        }
    }

    //EFFECTS: views a list of students present in the school with all their details
    public void studentListViewer() {
        for (int i = 0; i < ihs.getStudents().size(); i++) {
            System.out.println("Student Name: "
                    + ihs.getStudents().get(i).getStudentName());
            System.out.println("Student ID: "
                    + ihs.getStudents().get(i).getStudentID());
            System.out.println("Student Faculty: "
                    + ihs.getStudents().get(i).getFaculty());
            System.out.println("Student Total Tuition: "
                    + ihs.getStudents().get(i).getTotalTuition());
            System.out.println("Student Pending Tuition: "
                    + ihs.getStudents().get(i).getPendingTuition());
        }
    }

    //EFFECTS: views the various options available for principal
    public void principalOptions() {
        System.out.println("Press 1 to View Student List");
        System.out.println("Press 2 to View Professor List");
        System.out.println("Press 3 to View Student Transcript");
        System.out.println("Press 4 to View School's Bank Balance");
        System.out.println("Press 5 to View School's Spendings");
        System.out.println("Press 6 to Add Professor");
        System.out.println("Press 7 to Add Student");
    }

    //EFFECTS: checks if the professor id entered in the professor view is valid for the professor to perform action
    public void professorView(Scanner s1) {
        System.out.println("Welcome to Professor View");
        System.out.println("Enter your Professor ID");
        Integer professorID = Integer.parseInt(s1.nextLine());
        for (int i = 0; i < ihs.getProfessors().size(); i++) {
            if (ihs.getProfessors().get(i).getProfessorID() == professorID) {
                test2 = 1;
                boolean action = true;
                while (action) {
                    professorActions();

                    Integer option = Integer.parseInt(s1.nextLine());

                    professorActionsHandler(s1, i, option);
                    System.out.println("Do you want to do any other action true/false?");
                    action = Boolean.parseBoolean(s1.nextLine());
                }
            }
        }
        if (test2 == 0) {
            System.out.println("Invalid Professor ID");
        }
    }

    //EFFECTS: checks the option entered by the professor for the following operation in switch case to take place
    public void professorActionsHandler(Scanner s1, int i, Integer option) {
        switch (option) {
            case 1:
                studentAdder(s1);
                break;

            case 2:
                studentGradesEntry(s1);
                break;

            case 3:
                salarySender(i);
                break;

            case 4:
                System.out.println("Your current salary: " + ihs.getProfessors()
                        .get(i).getTotalSalary());
                break;
        }
    }

    //EFFECTS: sends the current salary of professor from the school's funds to professor
    public void salarySender(int i) {
        System.out.println("Sending Salary");
        ihs.getProfessors().get(i)
                .sendSalary(ihs.getProfessors().get(i).getTotalSalary());
        try {
            ihs.reduceMoneySpent(ihs.getProfessors().get(i).getTotalSalary());
        } catch (NoBalanceException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: checks the faculty of student and redirects it to the required subjects grade entry classes
    public void studentGradesEntry(Scanner s1) {
        System.out.println("Enter Student ID");
        Integer psid = Integer.parseInt(s1.nextLine());
        for (Transcript t : ihs.getGrades()) {

            if (t.getStudentID() == psid) {
                for (int k = 0; k < ihs.getStudents().size(); k++) {
                    if (ihs.getStudents().get(k).getStudentID() == psid) {
                        if (ihs.getStudents().get(k).getFaculty().equals("Science")) {
                            scienceStudentGradesEntry(s1, k);
                        } else if (ihs.getStudents()
                                .get(k).getFaculty().equals("Commerce")) {
                            commerceStudentGradesEntry(s1, k);
                        } else {
                            artsStudentGradesEntry(s1, k);
                        }
                    }
                }
            }
        }
    }

    //EFFECTS: enters the grade of Arts students in their required subjects
    public void artsStudentGradesEntry(Scanner s1, int k) {
        System.out.println("Enter Math Grade");
        ihs.getGrades().get(k).setMath(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter English Grade");
        ihs.getGrades().get(k).setEnglish(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter History Grade");
        ihs.getGrades().get(k).setHistory(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Civics Grade");
        ihs.getGrades().get(k).setCivics(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Geography Grade");
        ihs.getGrades().get(k).setGeography(Integer.parseInt(s1
                .nextLine()));
    }

    //EFFECTS: enters the grade of Commerce students in their required subjects
    public void commerceStudentGradesEntry(Scanner s1, int k) {
        System.out.println("Enter Math Grade");
        ihs.getGrades().get(k).setMath(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter English Grade");
        ihs.getGrades().get(k).setEnglish(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Accountancy Grade");
        ihs.getGrades().get(k).setAccountancy(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Business Studies Grade");
        ihs.getGrades().get(k)
                .setBusinessStudies(Integer.parseInt(s1
                        .nextLine()));
        System.out.println("Enter Economics Grade");
        ihs.getGrades().get(k).setEconomics(Integer.parseInt(s1
                .nextLine()));
    }

    //EFFECTS: enters the grade of Science students in their required subjects
    public void scienceStudentGradesEntry(Scanner s1, int k) {
        System.out.println("Enter Math Grade");
        ihs.getGrades().get(k).setMath(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Chemistry Grade");
        ihs.getGrades().get(k).setChemistry(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Physics Grade");
        ihs.getGrades().get(k).setPhysics(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter English Grade");
        ihs.getGrades().get(k).setEnglish(Integer.parseInt(s1
                .nextLine()));
        System.out.println("Enter Computer Science Grade");
        ihs.getGrades().get(k).setComputerScience(Integer
                .parseInt(s1.nextLine()));
    }

    //EFFECTS: shows a list of actions professor can take
    public void professorActions() {
        System.out.println("Press 1 to Add Students to Class");
        System.out.println("Press 2 to Enter Student Grades");
        System.out.println("Press 3 to Collect Salary");
        System.out.println("Press 4 to View Total Salary");
    }

    //EFFECTS: opens the Student View by matching student id to verify valid student
    public void studentView(Scanner s1) {
        System.out.println("Welcome to Student View");
        System.out.println("Enter your Student ID");
        Integer id = Integer.parseInt(s1.nextLine());
        for (int i = 0; i < ihs.getStudents().size(); i++) {
            if (ihs.getStudents().get(i).getStudentID() == id) {
                test1 = 1;
                boolean action = true;
                while (action) {
                    studentActions();
                    Integer option = Integer.parseInt(s1.nextLine());
                    action = studentOptionChooser(s1, i, option);
                }
            }
        }
        if (test1 == 0) {
            System.out.println("Invalid Student ID");
        }
    }

    //EFFECTS: prints the transcript of students in Arts
    public boolean studentOptionChooser(Scanner s1, int i, Integer option) {
        boolean action;
        switch (option) {

            case 1:
                tuitionPayer(s1, i);
                break;

            case 2:
                tuitionViewer(i);
                break;

            case 3:
                transcriptViewer(i);
                break;

            case 4:
                averageViewer(i);
                break;
        }
        System.out.println("Do you want to do any other action true/false?");
        action = Boolean.parseBoolean(s1.nextLine());
        return action;
    }

    //EFFECTS: views the actions available for student in student view
    public void studentActions() {
        System.out.println("Press 1 to Pay Tuition");
        System.out.println("Press 2 to view Total Tuition");
        System.out.println("Press 3 to view Transcript");
        System.out.println("Press 4 to calculate my Average");
    }

    //EFFECTS: views the average grade of student in a particular faculty
    public void averageViewer(int i) {
        System.out.println("Your current average: " + ihs.getGrades().get(i)
                .getAverage());
    }

    //EFFECTS: shows the complete student transcript based on his faculty
    public void transcriptViewer(int i) {
        if (ihs.getStudents().get(i).getFaculty().equals("Science")) {
            printScienceTranscript(i);
        } else if (ihs.getStudents().get(i).getFaculty().equals("Commerce")) {
            printCommerceTranscript(i);
        } else {
            printArtsTranscript(i);
        }
    }

    //EFFECTS: views the total tuition fee of student
    public void tuitionViewer(int i) {
        System.out.println("Your Total Tuition Fee is " + ihs.getStudents().get(i).getTotalTuition());
    }

    //EFFECTS: pays the tuition fee of student and adds to school funds the money received
    public void tuitionPayer(Scanner s1, int i) {
        System.out.println("Enter the amount you want to pay?");
        Integer tuition = Integer.parseInt(s1.nextLine());
        ihs.getStudents().get(i).payTuition(tuition);
        ihs.addMoneyReceived(tuition);
        System.out.println("Your remaining tuition fee is : " + ihs.getStudents().get(i)
                .getPendingTuition());
    }

    //EFFECTS: Creates the main menu for the console window
    public void menu() {
        System.out.println("Welcome to Indian High School");
        System.out.println("Choose one of the following option");
        System.out.println("Press 1 for Student");
        System.out.println("Press 2 for Professor");
        System.out.println("Press 3 for Principal");
    }

    //EFFECTS: prints the transcript of students in Commerce
    public void printCommerceTranscript(int i) {
        System.out.println("The student transcript is as follows");
        System.out.println("Math " + ihs.getGrades().get(i).getMathGrade());
        System.out.println("Business Studies " + ihs.getGrades().get(i).getBusinessStudiesGrade());
        System.out.println("Accountancy " + ihs.getGrades().get(i).getAccountancyGrade());
        System.out.println("English " + ihs.getGrades().get(i).getEnglishGrade());
        System.out.println("Economics " + ihs.getGrades().get(i).getEconomicsGrade());
    }

    //EFFECTS: prints the transcript of students in Science
    public void printScienceTranscript(int i) {
        System.out.println("The student transcript is as follows");
        System.out.println("Math " + ihs.getGrades().get(i).getMathGrade());
        System.out.println("Physics " + ihs.getGrades().get(i).getPhysicsGrade());
        System.out.println("Chemistry " + ihs.getGrades().get(i).getChemistryGrade());
        System.out.println("English " + ihs.getGrades().get(i).getEnglishGrade());
        System.out.println("Computer Science " + ihs.getGrades().get(i).getComputerScienceGrade());
    }

    //EFFECTS: prints the transcript of students in Arts
    public void printArtsTranscript(int i) {
        System.out.println("The student transcript is as follows");
        System.out.println("Math " + ihs.getGrades().get(i).getMathGrade());
        System.out.println("History " + ihs.getGrades().get(i).getHistoryGrade());
        System.out.println("Civics " + ihs.getGrades().get(i).getCivicsGrade());
        System.out.println("English " + ihs.getGrades().get(i).getEnglishGrade());
        System.out.println("Geography " + ihs.getGrades().get(i).getGeographyGrade());
    }

    // EFFECTS: saves the workroom to file
    private void saveSchool() {
        try {
            jsonWriter.open();
            jsonWriter.write(ihs);
            jsonWriter.close();
            System.out.println("Saved Indian High School data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSchool() {
        try {
            ihs = jsonReader.read();
            System.out.println("Loaded Indian High School data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}