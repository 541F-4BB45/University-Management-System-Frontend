package studentView;

public class Member extends Entity
{
private String member_ID;
private String member_name;
private String club_ID;

public Member()
{
member_ID = null;
member_name = null;
club_ID = null;
}

public Member(String member_ID, String member_name, String club_ID)
{
this.member_ID = member_ID;
this.member_name = member_name;
this.club_ID = club_ID;
}

public void setMemberID(String member_ID)
{
this.member_ID = member_ID;
}

public void setMemberName(String member_name)
{
this.member_name = member_name;
}

public void setClubID(String club_ID)
{
this.club_ID = club_ID;
}

public String getMemberID()
{
return member_ID;
}

public String getMemberName()
{
return member_name;
}

public String getClubID()
{
return club_ID;
}
}

