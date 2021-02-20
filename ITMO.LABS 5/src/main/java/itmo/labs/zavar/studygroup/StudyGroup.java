package itmo.labs.zavar.studygroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class StudyGroup implements Comparable<StudyGroup>
{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private int expelledStudents; //Значение поля должно быть больше 0
    private long transferredStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null
    
    public StudyGroup() throws IllegalArgumentException
    {
    	
    }
    
    public StudyGroup(long id, String name, Coordinates coordinates, Long studentsCount, int expelledStudents, long transferredStudents, FormOfEducation formOfEducation, Person groupAdmin) throws IllegalArgumentException
    {
    	if(id <= 0)
    	{
    		throw new IllegalArgumentException("Id should be greater than 0");
    	}
    	else
    	{
    		this.id = id;
    	}
    	
    	if(name == null || name.isEmpty() )
    	{
    		throw new IllegalArgumentException("Name can't be null or empty");
    	}
    	else
    	{
    		this.name = name;
    	}
    	
    	if(coordinates == null)
    	{
    		throw new IllegalArgumentException("Coordinates can't be null");
    	}
    	else
    	{
    		this.coordinates = coordinates;
    	}
    	 
    	creationDate = LocalDate.now();
    	
    	if(studentsCount == null || studentsCount <= 0)
    	{
    		throw new IllegalArgumentException("Students count should be greater than 0 and can't be null");
    	}
    	else
    	{
    		this.studentsCount = studentsCount;
    	}
    	
    	if(expelledStudents <= 0)
    	{
    		throw new IllegalArgumentException("Expelled students count should be greater than 0");
    	}
    	else
    	{
    		this.expelledStudents = expelledStudents;
    	}
    	
    	if(transferredStudents <= 0)
    	{
    		throw new IllegalArgumentException("Transferred students count should be greater than 0");
    	}
    	else
    	{
    		this.transferredStudents = transferredStudents;
    	}
    	
    	if(formOfEducation == null)
    	{
    		throw new IllegalArgumentException("Form of education can't be null");
    	}
    	else
    	{
    		this.formOfEducation = formOfEducation;
    	}
    	
    	this.groupAdmin = groupAdmin;
    }

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		if(name == null || name.isEmpty() )
    	{
    		throw new IllegalArgumentException("Name can't be null or empty");
    	}
    	else
    	{
    		this.name = name;
    	}
	}

	public Coordinates getCoordinates() 
	{
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates)
	{
		if(coordinates == null)
    	{
    		throw new IllegalArgumentException("Coordinates can't be null");
    	}
    	else
    	{
    		this.coordinates = coordinates;
    	}
	}

	public Long getStudentsCount() 
	{
		return studentsCount;
	}

	public void setStudentsCount(Long studentsCount) 
	{
    	if(studentsCount == null || studentsCount <= 0)
    	{
    		throw new IllegalArgumentException("Students count should be greater than 0 and can't be null");
    	}
    	else
    	{
    		this.studentsCount = studentsCount;
    	}
	}

	public int getExpelledStudents() 
	{
		return expelledStudents;
	}

	public void setExpelledStudents(int expelledStudents) 
	{
		if(expelledStudents <= 0)
    	{
    		throw new IllegalArgumentException("Expelled students count should be greater than 0");
    	}
    	else
    	{
    		this.expelledStudents = expelledStudents;
    	}
	}

	public long getTransferredStudents() 
	{
		return transferredStudents;
	}

	public void setTransferredStudents(Long transferredStudents) 
	{
    	if(transferredStudents <= 0)
    	{
    		throw new IllegalArgumentException("Transferred students count should be greater than 0");
    	}
    	else
    	{
    		this.transferredStudents = transferredStudents;
    	}
	}

	public FormOfEducation getFormOfEducation() 
	{
		return formOfEducation;
	}

	public void setFormOfEducation(FormOfEducation formOfEducation) 
	{
		if(formOfEducation == null)
    	{
    		throw new IllegalArgumentException("Form of education can't be null");
    	}
    	else
    	{
    		this.formOfEducation = formOfEducation;
    	}
	}
	
	public Person getGroupAdmin() 
	{
		return groupAdmin;
	}

	public void setGroupAdmin(Person groupAdmin) 
	{
		this.groupAdmin = groupAdmin;
	}
	
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
    	if(id <= 0)
    	{
    		throw new IllegalArgumentException("Id should be greater than 0");
    	}
    	else
    	{
    		this.id = id;
    	}
	}

	public LocalDate getCreationLocalDate() 
	{
		return creationDate;
	}
	
	public Date getCreationDate() throws ParseException 
	{
		return new SimpleDateFormat("yyyy-MM-dd").parse(creationDate.toString());
	}

	public void setCreationDate(Date date) 
	{
		this.creationDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public String toString() 
	{
		return "StudyGroup [id=" + id + ", name=" + name + ", coordinateX=" + coordinates.getX() + ", coordinateY=" + coordinates.getY() + ", creationDate="
				+ creationDate + ", studentsCount=" + studentsCount + ", expelledStudents=" + expelledStudents
				+ ", transferredStudents=" + transferredStudents + ", formOfEducation=" + formOfEducation
				+ ", groupAdmin=[" + groupAdmin + "]]";
	}

	@Override
	public int compareTo(StudyGroup o) 
	{
		return o.getCreationLocalDate().compareTo(this.creationDate);
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + expelledStudents;
		result = prime * result + ((formOfEducation == null) ? 0 : formOfEducation.hashCode());
		result = prime * result + ((groupAdmin == null) ? 0 : groupAdmin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((studentsCount == null) ? 0 : studentsCount.hashCode());
		result = prime * result + (int) (transferredStudents ^ (transferredStudents >>> 32));
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
		StudyGroup other = (StudyGroup) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (expelledStudents != other.expelledStudents)
			return false;
		if (formOfEducation != other.formOfEducation)
			return false;
		if (groupAdmin == null) {
			if (other.groupAdmin != null)
				return false;
		} else if (!groupAdmin.equals(other.groupAdmin))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (studentsCount == null) {
			if (other.studentsCount != null)
				return false;
		} else if (!studentsCount.equals(other.studentsCount))
			return false;
		if (transferredStudents != other.transferredStudents)
			return false;
		return true;
	}
}
