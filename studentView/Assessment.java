package studentView;

public class Assessment extends Entity
{
private String assessment_ID;
private String assessment_type;
private String course_ID;

public Assessment()
{
assessment_ID = null;
assessment_type = null;
course_ID = null;
}

public Assessment(String assessment_ID, String assessment_name, String course_ID)
{
this.assessment_ID = assessment_ID;
this.assessment_type = assessment_name;
this.course_ID = course_ID;
}

public void setAssessmentID(String assessment_ID)
{
this.assessment_ID = assessment_ID;
}

public void setAssessmentName(String assessment_name)
{
this.assessment_type = assessment_name;
}

public void setCourseID(String course_ID)
{
this.course_ID = course_ID;
}

public String getAssessmentID()
{
return assessment_ID;
}

public String getAssessmentName()
{
return assessment_type;
}

public String getCourseID()
{
return course_ID;
}
}
