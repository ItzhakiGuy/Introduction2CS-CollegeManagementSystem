/**
* Course: Intro To Computer Science
* Homework: 8
* Name: Guy Itzhaki
* E-mail: itzhaki1234@gmail.com
*/

//import linkedList.*;

/** 
 * Represents a student.
 */
public class Student 
{
	
	private int sid; //Student ID                            
	private String name; //Student Name                        
	private LinkedList<CourseTaken> courseList; //Courses taken by the student
	
	/** 
	 * Constructs a new student with the given sid and name, and an empty course list.
	 * @param sid  the student's sid
	 * @param name the student's name
	 */
	public Student(int sid, String name) 
	{
		this.sid = sid;
		this.name = name;
		courseList = new LinkedList<CourseTaken>();
	}
	
	/** 
	 * Returns the sid of this student.
	 * @return the sid of this student.
	 */
	public int getSid() 
	{
		return sid;
	}
	
	/** 
	 * Returns the name of this student.
	 * @return the name of this student.
	 */
	public String getName() 
	{
		return name;
	}
	
	/** 
	 * Adds the given course and grade to the course list of this student.
	 * @param c     the course to add
	 * @param grade the grade in the added course 
	 */
	public void addCourse(Course c, int grade) 
	{
		// Put your code here.
		CourseTaken now=new CourseTaken(c, grade);
		this.courseList.add(now);
	}
	
	/** 
	 * deletes the given course and grade to the course list of this student.
	 * @param c     the course to add
	 * @param grade the grade in the added course 
	 */
	public void delCourse(Course c, int grade) 
	{
		// Put your code here.
		ListIterator<CourseTaken> iter=this.courseList.iterator();
		CourseTaken cout=iter.next();
		while (iter.hasNext())
		{
			if (cout.getGrade()==grade && cout.getCourse()==c)
				this.courseList.remove(cout);
			cout=iter.next();
		}
	}
	
	/** 
	 * Returns the grade that this student got in the given course, 
	 *  or -1 if the course was not taken by this student.
	 * @param c - the returned grade will be the grade in this course.
	 * @return the grade that this student got in the given course
	 */
	public int gradeInCourse(Course c) 
	{
		// Replace the following statement with your code.
		try
		{
			ListIterator<CourseTaken> iter=this.courseList.iterator();
			CourseTaken cout=iter.next();
			while (iter.hasNext())
			{
				if (cout.getCourse()==c)
					return cout.getGrade();
				cout=iter.next();
			}
			if (cout.getCourse()==c)
				return cout.getGrade();
			return -1;
		}
		catch (Exception s)
		{
			return -1;
		}
	}
	
	/** 
	 * Returns true if this student took the given course, false otherwise.
	 * @param c  the course we want to know whether or not the student took.
	 * @return true if this student took the given course, false otherwise.
	 */
	public boolean tookCourse(Course c)
	{
		// Replace the following statement with your code.
		int grade=this.gradeInCourse(c);
		if (grade!=-1)
			return true;
		else
			return false;
	}

	/**
	 * Prints this student, all the courses that s/he took, and the grade point average.
	 */
	public void studentReport() 
	{
		// Put your code here.
		ListIterator<CourseTaken> iter=this.courseList.iterator();
		if (iter.hasNext())
		{
			CourseTaken cout=iter.next();
			double count=0;
			int coucount=0;
			System.out.println("The student name is: "+this.name);
			while (iter.hasNext())
			{
				System.out.println(cout.getCourse()+", Grade: "+cout.getGrade());
				count+=(double)cout.getGrade();
				coucount++;
				cout=iter.next();
			}
			if (cout!=null) 
			{
				System.out.println(cout.getCourse()+", Grade: "+cout.getGrade());
				count+=(double)cout.getGrade();
				coucount++;
			}
			System.out.println("Student GPA is: "+(count/coucount));
		}
		else
			System.out.println("The student "+this.name+" has no courses");
	}
	
	/**
	 * Textual representation of this student.
	 */
	public String toString() 
	{
		return "Student " + sid + ": " + name;
	}
}