package studentView;

public class Pair<T, V>
{
private T element_one;
private V element_two;

public Pair(T element_one, V
element_two)
{
this.element_one = element_one;
this.element_two = element_two;
}

public void setFirst(T element_one)
{
this.element_one = element_one;
}

public void setSecond(V element_two)
{
this.element_two = element_two;
}

public T getFirst()
{
return element_one;
}

public V getSecond()
{
return element_two;
}

public String getEventName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEventName'");
}
}