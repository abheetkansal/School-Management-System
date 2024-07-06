# My Personal Project

## School Management System

<h4>Indian High School</h4>

##### What will the application do?  
In this project, I aim to create a *School Management System*
 that will help **Indian High School** to keep a 
a record of all its students, professors, and 
finances. It will ease in some function in an 
easy and efficient manner.
One of the major purposes of this project 
is to digitize almost all the minor operations 
of the school like generation of Transcripts based on Streams in Indian Education System with added security. 
The app will handle operations from entering grades to managing funds a breeze.

#### Who will use it?
The application is designed to ease the work and meant to be used by professors, students, and principal in the School in the following ways:- 

The application helps a *Student* in:
- Paying Tuition
- Viewing Total Tuition
- Viewing Transcript
- Calculate Average Score

The application helps a *Professor* in:
- Adding Students to Class
- Entering Student Grades
- Collecting Salary
- Viewing Total Salary

The application helps a *Principal* in:
- Viewing Student List
- Viewing Professor List
- Viewing Student Transcript
- Viewing School's Bank Balance
- Viewing School's Spendings
- Adding New Professors
- Adding New Students

In addition to this there are various security measures being taken place so that no one can fiddle with the 
school's database. Everyone has a personal id and the principal has an access code to access everything. It is 
mainly designed to reduce the manual workforce required in paper documents with added security for data protection.


##### Why is this project of interest to you?
Being an International Student in Canada from India, a lot of things were different for me in High School. India 
is a developing nation where High Schools still require a lot of paperwork which needs to be managed that creates
 a lot of delays and loss of information in the workflow. To help the school and see the change it brings in the school
  through this application thus helping the professors and general employees would be a really good thing to do and
   also help me in testing my knowledge to a great extent from the knowledge I gained at UBC. Moreover, by 
   doing this it will help the Indian High School in keeping their documents safe, secure ,and long lasting in 
   their database.

###### **User Stories**
In the context of a to-do application:

- As a user, I want to be able to control as a Principal with an access code.
- As a user, I want to be able to add multiple new professor to my existing list of professor in school with Principal View.
- As a user, I want to be able to add multiple new student to my existing list of students in school with Principal View.
- As a user, I want to be able to check funds of the School with the Principal View.
- As a user, I want to be able to add multiple new student to my existing list of students in school with Professor View.
- As a user, I want to be able to add grades of students in a particular faculty in School with the Professor View.
- As a user, I want to be able to check and collect salary from the School with the Professor View.
- As a user, I want to be able to view School Transcript with the Student View.
- As a user, I want to be able to check and pay tuition of the School with the Student View.
- As a user, I want to be able to save my school's data to file for all Student, Professor, and Principal View.
  As a user, I want to be able to be able to load the school's data from file for all Student, Professor, and Principal View.


###### **Phase 4: Task 2**

- I chose the option 1 which is "Test and design a class in your model package 
that is robust and you must have at least one method that throws a checked exception. 
You must have one test for the case where the exception is expected and another where the 
exception is not expected."
- The class School in the model package plays a role in this tas with its reduceMoneySpent() 
which is made robust covering exceptions.


###### **Phase 4: Task 3**

- The UML diagram shows the extensive association between the classes of 
the programme.
- The UML has a School Class which is associated with other classes in model class
and connects them to the entire set of classes in the ui package.
- The code can be refactored for grades entry of students in all faculties to eliminate 
duplicate code and make the file size short if the time had permitted.
- The code can also be separated into classes for the ProfessorView and PrincipalView
to carry out specific tasks in their own classes and divide the number of functions.


###### **Acknowledgements**

- Carter, P. (2020). JsonSerializationDemo [Github Repository]. Retrieved 2020, from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
- Koenig, M. (2020). Bye Sounds. Retrieved November 20, 2020, from http://soundbible.com/suggest.php?q=bye