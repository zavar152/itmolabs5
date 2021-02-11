package itmo.labs.zavar.studygroup;

import itmo.labs.zavar.exception.IllegalParameterException;

public class Location 
{
    private float x;
    private Float y; //Поле не может быть null
    private Long z; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 348, Поле может быть null
    
	public Location(float x, Float y, Long z, String name) throws IllegalParameterException 
	{
		this.x = x;
		if(y == null)
		{
			throw new IllegalParameterException("Y can't be null");
		}
		else
		{
			this.y = y;
		}
		
		if(z == null)
		{
			throw new IllegalParameterException("Z can't be null");
		}
		else
		{
			this.z = z;
		}
		
		if(name != null && name.length() > 348)
		{
			throw new IllegalParameterException("Name length can't be greater than 348");
		}
		else
		{
			this.name = name;
		}
	}

	public float getX() 
	{
		return x;
	}

	public Float getY() 
	{
		return y;
	}

	public Long getZ() 
	{
		return z;
	}

	public String getName() 
	{
		return name;
	}
	
	@Override
	public String toString() 
	{
		return "Location [x=" + x + ", y=" + y + ", z=" + z + ", name=" + name + "]";
	}
}
