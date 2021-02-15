package itmo.labs.zavar.studygroup;

public class Coordinates 
{

	private Double x; //Значение поля должно быть больше -573, Поле не может быть null
    private Float y; //Поле не может быть null
    
    public Coordinates(Double x, Float y) throws IllegalArgumentException 
    {
		if(x == null || x <= -573)
		{
			throw new IllegalArgumentException("X should be greater than -573 and can't be null");
		}
		else
		{
			this.x = x;
		}
		
		if(y == null)
		{
			throw new IllegalArgumentException("Y should be a number and can't be null");
		}
		else
		{
			this.y = y;
		}
	}

	public Double getX() 
    {
		return x;
	}

	public Float getY() 
	{
		return y;
	}

	public static String from(Double x, Float y)
	{
		return "x=" + x + ", y=" + y;
	}
	
	@Override
	public String toString() 
	{
		return "x=" + x + ", y=" + y;
	}
	
}
