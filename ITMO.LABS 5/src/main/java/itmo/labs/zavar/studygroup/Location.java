package itmo.labs.zavar.studygroup;

public class Location 
{
    private float x;
    private Float y; //Поле не может быть null
    private Long z; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 348, Поле может быть null
    
	public Location(float x, Float y, Long z, String name) throws IllegalArgumentException 
	{
		this.x = x;
		if(y == null)
		{
			throw new IllegalArgumentException("Y can't be null");
		}
		else
		{
			this.y = y;
		}
		
		if(z == null)
		{
			throw new IllegalArgumentException("Z can't be null");
		}
		else
		{
			this.z = z;
		}
		
		if(name != null && name.length() > 348)
		{
			throw new IllegalArgumentException("Name length can't be greater than 348");
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
		return "x=" + x + ", y=" + y + ", z=" + z + ", name=" + name;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}
	
	
}
