package studentView;

public class Department extends Entity
{
private String department_ID;
private String department_name;

public Department()
{
department_ID = null;
department_name = null;
}

public Department(String department_ID, String department_name)
{
this.department_ID = department_ID;
this.department_name = department_name;
}

public void setDepartmentID(String department_ID)
{
this.department_ID = department_ID;
}

public void setDepartmentName(String department_name)
{
this.department_name = department_name;
}

public String getDepartmentID()
{
return department_ID;
}

public String getDepartmentName()
{
return department_name;
}
}