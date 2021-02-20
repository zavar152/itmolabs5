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
	
	@Override
	public String toString() 
	{
		return "x=" + x + ", y=" + y;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		Coordinates other = (Coordinates) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	
	
	
}
