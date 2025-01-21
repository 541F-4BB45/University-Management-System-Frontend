package studentView;

public class Club extends Entity
{
private String club_ID;
private String club_name;

public Club()
{
club_ID = null;
club_name = null;
}

public Club(String club_ID, String club_name)
{
this.club_ID = club_ID;
this.club_name = club_name;
}

public void setClubID(String club_ID)
{
this.club_ID = club_ID;
}

public void setClubName(String club_name)
{
this.club_name = club_name;
}

public String getClubID()
{
return club_ID;
}

public String getClubName()
{
return club_name;
}
}