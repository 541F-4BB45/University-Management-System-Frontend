package studentView;

public class Program extends Entity
{
private String program_name;
private String department_ID;

public Program()
{
program_name = null;
department_ID = null;
}

public Program(String program_name, String department_ID)
{
this.program_name = program_name;
this.department_ID = department_ID;
}

public void setProgramName(String program_name)
{
this.program_name = program_name;
}

public void setDepartmentID(String department_ID)
{
this.department_ID = department_ID;
}

public String getProgramName()
{
return program_name;
}

public String getDepartmentID()
{
return department_ID;
}
}
