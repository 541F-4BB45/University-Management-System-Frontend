-- Professor Table 

CREATE TABLE Professor (
  	Professor_id varchar (5),
  	Professor_name varchar (255),
  	Professor_email varchar (255),
  	Department_id varchar (5),
  
  	PRIMARY Key (Professor_id),
  	FOREIGN KEY (Department_id) REFERENCES Department(Dep_ID)
 );


-- Course Table 

CREATE TABLE Course 
(
	Course_id varchar (5),
      	Course_name varchar (255),
      	Department_id varchar(5),
      	Semester_no int ,
      	
      	PRIMARY KEY (Course_id),
    	FOREIGN KEY (Department_id) REFERENCES Department(Dep_ID),
      
  );


-- Program Table 

  CREATE TABLE Program (
    	Program_name varchar (255),
    	Department_ID varchar (5),
    
    	PRIMARY Key (Program_name),
    	FOREIGN Key (Department_ID) REFERENCES Department(Dep_ID)
    
   );


-- Enrollment Table     

   CREATE TABLE Enrollment (
     	Student_id varchar (5),
     	Course_id varchar (5),
    	Department_id varchar (5),
     	Semester_ID varchar (5) ,
     
     	FOREIGN Key (Student_id) REFERENCES Student(Student_ID),
     	FOREIGN Key (Course_id) REFERENCES Course(Course_id),
     	FOREIGN Key (Department_id) REFERENCES Department (Dep_ID),
  );


-- Department Table 

CREATE TABLE Department 
(
	Dep_ID VARCHAR(5),
  	Dep_Name VARCHAR(255),
  	PRIMARY KEY(Dep_ID)
);


-- Assessment Table 

CREATE TABLE Assessment 
(
	Assessment_ID VARCHAR(5),
  	Assessment_Type VARCHAR(255),
  	Course_ID VARCHAR(5),
  	PRIMARY KEY(Assessment_ID),
  	FOREIGN KEY(Course_ID) REFERENCES Course(Course_ID)
);


-- Academic Record Table  

CREATE TABLE AcademicRecord
(
	Grade_ID VARCHAR(5),
  	Student_ID VARCHAR(5),
  	Assessment_ID VARCHAR(5),
  	Semester_ID varchar (5),
  	PRIMARY KEY(Grade_ID),
  	FOREIGN KEY(Student_ID) REFERENCES Student(Student_ID),
  	Foreign KEY(Assessment_ID) REFERENCES Assessment(Assessment_ID),
);


-- Student Table  

CREATE TABLE Student 
(
	Student_ID VARCHAR(5),
  	Student_Name VARCHAR(255),
  	Program_Name VARCHAR(255),
  	Department_ID VARCHAR(5),
  	Semester_ID varchar(3),
  	Email VARCHAR(255),
  	PRIMARY KEY(Student_ID),
  	FOREIGN KEY(Program_Name) REFERENCES Program(Program_Name),
  	FOREIGN KEY(Semester_ID) REFERENCES Semester(Semester_ID),
  	FOREIGN KEY(Department_ID) REFERENCES Department(Department_ID)
);


-- Semester Table 

CREATE TABLE Semester 
(
	Semester_ID varchar (3) PRIMARY KEY,
	Semester_No INT,
  	Semester_Name VARCHAR(6),
  	Start_Date DATE,
  	End_Date DATE,
);


-- Classroom Table  

CREATE TABLE Classroom
(
	Room_No VARCHAR(5),
  	Capacity INT
);


-- Event Table 

CREATE TABLE Event 
(
	Event_ID VARCHAR(5),
  	Club_ID VARCHAR(5),
  	Event_Date DATE,
  	Event_Type VARCHAR(255)
  	PRIMARY KEY(Event_ID),
  	FOREIGN KEY(Club_ID) REFERENCES Clubs(Club_ID)
);

    
-- Members Table 

CREATE TABLE Members
(
	Member_ID VARCHAR(5),
  	Member_Name VARCHAR(255),
  	Club_ID VARCHAR(5),
  	PRIMARY KEY(Member_ID),
  	FOREIGN KEY(Club_ID) REFERENCES Clubs(Club_ID)
);


--Clubs Table

CREATE TABLE Clubs
(	
	Club_ID varchar (5) PRIMARY KEY,
	Club_Name varchar (50)
);

-- Academic Record Table

CREATE TABLE Academic_record
(
    Grade_ID VARCHAR(5) NOT NULL,
    Student_ID VARCHAR(5) NOT NULL,
    CGPA DECIMAL(3, 2) NOT NULL,
    PRIMARY KEY (Grade_id),
    FOREIGN KEY (Student_id) REFERENCES Student(Student_ID)
);

