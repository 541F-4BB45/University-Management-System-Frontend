package studentView;

public class Semester extends Entity
{
private String semester_ID;
private int semester_no;
private String semester_name;
private String start_date;
private String end_date;

public Semester()
{
semester_ID = null;
semester_no = -1;
semester_name = null;
start_date = null;
end_date = null;
}

public Semester(String semester_ID, int semester_no, String semester_name, String start_date,String end_date)
{
this.semester_ID = semester_ID;
this.semester_no = semester_no;
this.semester_name = semester_name;
this.start_date = start_date;
this.end_date = end_date;
}

public void setSemesterID(String semester_ID)
{
this.semester_ID = semester_ID;
}

public void setSemesterNo(int semester_no)
{
this.semester_no = semester_no;
}

public void setSemesterName(String semester_name)
{
this.semester_name = semester_name;
}

public void setStartDate(String start_date)
{
this.start_date = start_date;
}

public void setEndDate(String end_date)
{
this.end_date = end_date;
}

public String getSemesterID()
{
return semester_ID;
}

public int getSemesterNo()
{
return semester_no;
}

public String getSemesterName()
{
return semester_name;
}

public String getStartDate()
{
return start_date;
}

public String getEndDate()
{
return end_date;
}
}

