package studentView;

public class Enrollment extends Entity
{
private String student_ID;
private String course_ID;
private String department_ID;
private String semester_ID;

public Enrollment()
{
student_ID = null;
course_ID = null;
department_ID = null;
semester_ID = null;
}

public Enrollment(String student_ID, String course_ID, String department_ID, String semester_ID)
{
this.student_ID = student_ID;
this.course_ID = course_ID;
this.department_ID = department_ID;
this.semester_ID = semester_ID;
}

public void setStudentID(String student_ID)
{
this.student_ID = student_ID;
}

public void setCourseID(String course_ID)
{
this.course_ID = course_ID;
}

public void setDepartmentID(String department_ID)
{
this.department_ID = department_ID;
}

public void setSemesterID(String semester_ID)
{
this.semester_ID = semester_ID;
}

public String getStudentID()
{
return student_ID;
}

public String getCourseID()
{
return course_ID;
}

public String getDepartmentID()
{
return department_ID;
}

public String getSemesterID()
{
return semester_ID;
}
}