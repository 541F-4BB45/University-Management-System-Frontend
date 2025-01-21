package studentView;

public class Event extends Entity
{
private String event_ID;
private String club_ID;
private String event_date;
private String event_type;

public Event()
{
event_ID = null;
club_ID = null;
event_date = null;
event_type = null;
}

public Event(String event_ID, String club_ID, String event_date, String event_type)
{
this.event_ID = event_ID;
this.club_ID = club_ID;
this.event_date = event_date;
this.event_type = event_type;
}

public void setEventID(String event_ID)
{
this.event_ID = event_ID;
}

public void setClubID(String club_ID)
{
this.club_ID = club_ID;
}

public void setEventDate(String event_date)
{
this.event_date = event_date;
}

public void setEventType(String event_type)
{
this.event_type = event_type;
}

public String getEventID()
{
return event_ID;
}

public String getClubID()
{
return club_ID;
}

public String getEventDate()
{
return event_date;
}

public String getEventType()
{
return event_type;
}
}
