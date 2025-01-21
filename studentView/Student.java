package studentView;

public class Student extends Entity
{
private String student_ID;
private String student_name;
private String program_name;
private String department_ID;
private String semester_ID;
private String email;

public Student()
{
student_ID = null;
student_name = null;
program_name = null;
department_ID = null;
semester_ID = null;
email = null;
}

public Student(String student_ID, String student_name, String program_name, String department_ID , String semester_ID, String email)
{
this.student_ID = student_ID;
this.student_name = student_name;
this.program_name = program_name;
this.department_ID = department_ID;
this.semester_ID = semester_ID;
this.email = email;
}

public void setStudentID(String student_ID)
{
this.student_ID = student_ID;
}

public void setProgramName(String program_name)
{
this.program_name = program_name;
}

public void setDepartmentID(String department_ID)
{
this.department_ID = department_ID;
}

public void setSemesterID(String string)
{
this.semester_ID = string;
}

public void setEmail(String email)
{
this.email = email;
}

public String getStudentID()
{
return student_ID;
}

public String getStudentName(){
    return student_name;
}

public void setStudentName(String student_name){
    this.student_name = student_name;
}

public String getProgramName()
{
return program_name;
}

public String getDepartmentID()
{
return department_ID;
}

public String getSemesterID()
{
return semester_ID;
}

public String getEmail()
{
return email;
}
}
