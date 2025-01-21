package studentView;

public class Professor extends Entity
{
private String professor_ID;
private String professor_name;
private String email;
private String department_ID;

public Professor()
{
professor_ID = null;
professor_name = null;
email = null;
department_ID = null;
}

public Professor(String professor_ID, String professor_name, String email, String department_ID)
{
this.professor_ID = professor_ID;
this.professor_name = professor_name;
this.email = email;
this.department_ID = department_ID;
}

public void setProfessorID(String professor_ID)
{
this.professor_ID = professor_ID;
}

public void setProfessorName(String professor_name)
{
this.professor_name = professor_name;
}

public void setEmail(String email)
{
this.email = email;
}

public void setDepartmentID(String department_ID)
{
this.department_ID = department_ID;
}

public String getProfessorID()
{
return professor_ID;
}

public String getProfessorName()
{
return professor_name;
}

public String getEmail()
{
return email;
}

public String getDepartmentID()
{
return department_ID;
} 
}