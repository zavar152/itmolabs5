package itmo.labs.zavar.studygroup;

import java.time.LocalDate;

import itmo.labs.zavar.exception.IllegalParameterException;

public class StudyGroup 
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
    
    public StudyGroup(long id, String name, Coordinates coordinates, Long studentsCount, int expelledStudents, long transferredStudents, FormOfEducation formOfEducation, Person groupAdmin) throws IllegalParameterException
    {
    	if(id <= 0)
    	{
    		throw new IllegalParameterException("Id should be greater than 0");
    	}
    	else
    	{
    		this.id = id;
    	}
    	
    	if(name == null || name.isEmpty() )
    	{
    		throw new IllegalParameterException("Name can't be null or empty");
    	}
    	else
    	{
    		this.name = name;
    	}
    	
    	if(coordinates == null)
    	{
    		throw new IllegalParameterException("Coordinates can't be null");
    	}
    	else
    	{
    		this.coordinates = coordinates;
    	}
    	
    	creationDate = LocalDate.now();
    	
    	if(studentsCount == null || studentsCount <= 0)
    	{
    		throw new IllegalParameterException("Students count should be greater than 0 and can't be null");
    	}
    	else
    	{
    		this.studentsCount = studentsCount;
    	}
    	
    	if(expelledStudents <= 0)
    	{
    		throw new IllegalParameterException("Expelled students count should be greater than 0");
    	}
    	else
    	{
    		this.expelledStudents = expelledStudents;
    	}
    	
    	if(formOfEducation == null)
    	{
    		throw new IllegalParameterException("Form of education can't be null");
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
		this.name = name;
	}

	public Coordinates getCoordinates() 
	{
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) 
	{
		this.coordinates = coordinates;
	}

	public Long getStudentsCount() 
	{
		return studentsCount;
	}

	public void setStudentsCount(Long studentsCount) 
	{
		this.studentsCount = studentsCount;
	}

	public int getExpelledStudents() 
	{
		return expelledStudents;
	}

	public void setExpelledStudents(int expelledStudents) 
	{
		this.expelledStudents = expelledStudents;
	}

	public long getTransferredStudents() 
	{
		return transferredStudents;
	}

	public void setTransferredStudents(long transferredStudents) 
	{
		this.transferredStudents = transferredStudents;
	}

	public FormOfEducation getFormOfEducation() 
	{
		return formOfEducation;
	}

	public void setFormOfEducation(FormOfEducation formOfEducation) 
	{
		this.formOfEducation = formOfEducation;
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

	public LocalDate getCreationDate() 
	{
		return creationDate;
	}

	@Override
	public String toString() 
	{
		return "StudyGroup [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate="
				+ creationDate + ", studentsCount=" + studentsCount + ", expelledStudents=" + expelledStudents
				+ ", transferredStudents=" + transferredStudents + ", formOfEducation=" + formOfEducation
				+ ", groupAdmin=" + groupAdmin + "]";
	}
    
	
	
}
