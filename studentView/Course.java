package studentView;

public class Course extends Entity
{
private String course_ID;
private String course_name;
private String department_ID;
private int semester_no;

public Course()
{
course_ID = null;
course_name = null;
department_ID = null;
semester_no = -1;
}

public Course(String course_ID, String course_name, String department_ID, int semester_no)
{
this.course_ID = course_ID;
this.course_name = course_name;
this.department_ID = department_ID;
this.semester_no = semester_no;
}

public void setCourseID(String course_ID)
{
this.course_ID = course_ID;
}

public void setCourseName(String course_name)
{
this.course_name = course_name;
}

public void setDepartmentID(String department_ID)
{
this.department_ID = department_ID;
}

public void setSemesterNo(int semester_no)
{
this.semester_no = semester_no;
}

public String getCourseID()
{
return course_ID;
}

public String getCourseName()
{
return course_name;
}

public String getDepartmentID()
{
return department_ID;
}

public int getSemesterNo()
{
return semester_no;
}
}
