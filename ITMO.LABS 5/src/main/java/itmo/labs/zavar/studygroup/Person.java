package itmo.labs.zavar.studygroup;

public class Person 
{
	
	private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Строка не может быть пустой, Поле может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null
    
	public Person(String name, String passportID, Color eyeColor, Color hairColor, Country nationality, Location location) throws IllegalArgumentException 
	{
		if(name == null || name.isEmpty())
		{
			throw new IllegalArgumentException("Name can't be null or empty");
		}
		else
		{
			this.name = name;
		}
		
		if(name != null && name.isEmpty())
		{
			throw new IllegalArgumentException("Passport ID can't be empty");
		}
		else
		{
			this.passportID = passportID;
		}
		
		if(eyeColor == null)
		{
			throw new IllegalArgumentException("Eye color can't be null");
		}
		else
		{
			this.eyeColor = eyeColor;
		}
		
		if(hairColor == null)
		{
			throw new IllegalArgumentException("Hair color can't be null");
		}
		else
		{
			this.hairColor = hairColor;
		}
		
		this.nationality = nationality;
		
		if(location == null)
		{
			throw new IllegalArgumentException("Location can't be null");
		}
		else
		{
			this.location = location;
		}
	}

	public String getName() 
	{
		return name;
	}

	public String getPassportID() 
	{
		return passportID;
	}

	public Color getEyeColor() 
	{
		return eyeColor;
	}

	public Color getHairColor() 
	{
		return hairColor;
	}

	public Country getNationality() 
	{
		return nationality;
	}

	public Location getLocation() 
	{
		return location;
	}
    
    @Override
	public String toString() 
    {
		return "name=" + name + ", passportID=" + passportID + ", eyeColor=" + eyeColor + ", hairColor="
				+ hairColor + ", nationality=" + nationality + ", locationX=" + location.getX() + ", locationY=" + location.getY() + 
				", locationZ=" + location.getZ() + ", locationName=" + location.getName();
	}
    
}
