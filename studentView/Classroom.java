package studentView;

public class Classroom extends Entity
{
private String room_no;
private int capacity;

public Classroom()
{
room_no = null;
capacity = -1;
}

public Classroom(String room_no, int capacity)
{
this.room_no = room_no;
this.capacity = capacity;
}

public void setRoomNo(String room_no)
{
this.room_no = room_no;
}

public void setCapacity(int capacity)
{
this.capacity = capacity;
}

public String getRoomNo()
{
return room_no;
}

public int setCapacity()
{
return capacity;
}
}
