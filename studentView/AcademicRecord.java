package studentView;

public class AcademicRecord extends Entity
{
private String grade_ID;
private String student_ID;
private double CGPA;

public AcademicRecord()
{
grade_ID = null;
student_ID = null;
CGPA = -1.0;
}

public AcademicRecord(String grade_ID, String student_ID, double CGPA)
{
this.grade_ID = grade_ID;
this.student_ID = student_ID;
this.CGPA = CGPA;
}

public void setGradeID(String grade_ID)
{
this.grade_ID = grade_ID;
}

public void setStudentID(String student_ID)
{
this.student_ID = student_ID;
}

public void setCGPA(double CGPA)
{
this.CGPA = CGPA;
}

public String getGradeID()
{
return grade_ID;
}

public String getStudentID()
{
return student_ID;
}

public double getCGPA()
{
return CGPA;
}
}
