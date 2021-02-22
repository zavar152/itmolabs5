package itmo.labs.zavar.studygroup;

/**
 * Class for creation of persons. Contains getters and setters for important fields.
 * 
 * @author Zavar
 * @version 1.2
 */
public class Person 
{
	private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Строка не может быть пустой, Поле может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null
    
    /**
     * 
     * @param name Person's name. Name can't be <tt>null</tt> or empty.
     * @param passportID Person's passport ID. Passport ID can't be empty.
     * @param eyeColor Person's eye color. Eye color can't be <tt>null</tt>.
     * @param hairColor Person's hair color. Hair color can't be <tt>null</tt>.
     * @param nationality Person's nationality. Can be <tt>null</tt>
     * @param location Person's location. Location can't be <tt>null</tt>.
     * @throws IllegalArgumentException If any parameter is wrong.
     * 
     * @see {@link Color}, {@link Country}, {@link Location}
     */
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

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eyeColor == null) ? 0 : eyeColor.hashCode());
		result = prime * result + ((hairColor == null) ? 0 : hairColor.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((passportID == null) ? 0 : passportID.hashCode());
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
		Person other = (Person) obj;
		if (eyeColor != other.eyeColor)
			return false;
		if (hairColor != other.hairColor)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationality != other.nationality)
			return false;
		if (passportID == null) {
			if (other.passportID != null)
				return false;
		} else if (!passportID.equals(other.passportID))
			return false;
		return true;
	}
}
