/**
* Course: Intro To Computer Science
* Homework: 8
* Name: Guy Itzhaki
* E-mail: itzhaki1234@gmail.com
*/

//import linkedList.*;

/**
 * Represents a college, and college management operations.
 * A college has courses, and students. Students take courses and get grades.
 * (See the Course, Student, and CourseTaken classes for more details).
 */
public class College 
{
	
	private static String nl =  System.getProperty("line.separator");

	private String name; // the name of this college
	private LinkedList<Course> courses;
	private LinkedList<Student> students;
	
	/**
	 * Constructs a new college, with empty student and course lists.
	 */
	public College(String name) 
	{
		this.name = name;
		this.courses = new LinkedList<Course>();
		this.students = new LinkedList<Student>();
	}
	
	/** 
	 * Adds the given course to the course list of this college.
	 * @param cid   course id.
	 * @param title course title.
	 */
	public void addCourse(int cid, String title) 
	{
		// Put your code here
		Course cou=new Course(cid, title);
		if (getCourse(cid)!=null)
			this.courses.remove(getCourse(cid));
		this.courses.add(cou);
	}
	
	/**
	 * Prints a list of all the courses.
	 */
	public void coursesList() 
	{
		System.out.println(courses);
	}

	/** 
	 * If the given course is in this college, removes it and returns true.
	 * Otherwise returns false.
	 * @param  cid the course to remove.
	 * @return True if the course was removed, false if there is no such course. 
	 */
	public boolean removeCourse(int cid) 
	{
		// Replace the return statement with your code.
		// Note: You can get the course with the given cid by calling
		// the private getCourse method.
		try
		{
			Course c=getCourse(cid);
			if (studentsWhoTook(c).size()!=0)
			{
				ListIterator<Student> iter=studentsWhoTook(c).iterator();
				Student stu=iter.next();
				while (iter.hasNext())
				{
					stu.delCourse(c, stu.gradeInCourse(c));
					stu=iter.next();
				}
				stu.delCourse(c, stu.gradeInCourse(c));
			}
			return this.courses.remove(c);
		}
		catch (Exception s)
		{
			System.out.println("Youv'e got a wrong cid: "+cid);
			return false;
		}
		
	}
	
	// Returns the course that has the given id, or null if there is no such course.
	private Course getCourse(int cid) 
	{
		// Replace the return statement with your code.	
		try
		{
			ListIterator<Course> iter=this.courses.iterator();
			Course cout=iter.next();
			while (iter.hasNext())
			{
				if (cout.getCid()==cid)
					return cout;
				cout=iter.next();
			}
			if (cout.getCid()==cid)
				return cout;
			return null;
		}
		catch (Exception e)
		{
			System.out.println("Youv'e got a wrong Course ID: "+cid);
			return null;
		}
	}
	
	/** 
	 * Adds the given student to the students list of this college.
	 * @param sid   student id
	 * @param name  student name
	 */
	public void addStudent(int sid, String name) 
	{
		// Put your code here
		Student news=new Student(sid,name);
		if (getStudent(sid)!=null)
			this.students.remove(getStudent(sid));
		this.students.add(news);
	}
	
	/**
	 * Prints a list of all the students.
	 */
	public void studentsList() 
	{
		System.out.println(students);
	}
	
	/** 
	 * If the given student is in this college, removes it and returns true.
	 * Otherwise returns false.
	 * @param  sid  the student's id.
	 * @return True if the student was removed, false if there is no such student. 
	 */
	public boolean removeStudent(int sid) 
	{
		// Replace the return statement with your code.
		// Note: You can get the student with the given sid by calling
		// the private getStudent method.
		try
		{
			return this.students.remove(getStudent(sid));
		}
		catch (Exception e)
		{
			System.out.println("Youv'e inserted wrong student ID: "+sid);
			return false;
		}
	}
	
	// Returns the student that has the given id, or null if there is no such student.
	private Student getStudent(int sid) 
	{
		// Replace the return statement with your code.
		try
		{
			ListIterator<Student> iter=this.students.iterator();
			Student stu=iter.next();
			while (iter.hasNext())
			{
				if (stu.getSid()==sid)
					return stu;
				stu=iter.next();
			}
			if (stu.getSid()==sid)
				return stu;
			return null;
		}
		catch (Exception e)
		{
			System.out.println("Youv'e got a wrong Student ID: "+sid);
			return null;
		}
	}
	
	/** 
	 * Adds the given course to the course list of the given student.
	 * @param sid   student id
	 * @param cid   course id
	 * @param grade student's grade in the course 
	 */
	public void addCourseTaken(int sid, int cid, int grade) 
	{
		// Put your code here.
		try 
		{
			if (getCourse(cid)!=null)
			{
				Course c=getCourse(cid);
				int gradeold=getStudent(sid).gradeInCourse(c);
				if (gradeold!=-1)
				{
					getStudent(sid).delCourse(c, gradeold);
				}
				getStudent(sid).addCourse(c, grade);
			}
				
		}
		catch (Exception e)
		{
			if (getCourse(cid)==null)
				System.out.println("Youv'e got a wrong course - try again - Course ID: "+cid);
			else
				if (getStudent(sid)==null)
					System.out.println("Youv'e got a wrong student - try again - Student ID: "+sid);
				else
					System.out.println("Youv'e got a wrong student and a wrong course - try again");
		}
	}
	
	/** 
	 * Prints the student report of the given student.
	 * See the Student class for more details.
	 * @param sid  student id
	 */
	public void studentReport(int sid) 
	{
		// Put your code here
		// Your code should call the student's studentReport method
		try
		{
			getStudent(sid).studentReport();
		}
		catch (NullPointerException e)
		{
			System.out.println("Youv'e got a wrong student: " + sid);
		}
	}
	
	/**
	 * Prints a list of all the students who took the course with the given cid.
	 * @param cid  the course
	 */
	public void courseReport(int cid) 
	{
		// Put your code here.
		try
		{
			Course c=getCourse(cid);
			System.out.println("The course "+c.getTitle()+" was taken by:");
			if (studentsWhoTook(c).size()!=0)
				System.out.println(studentsWhoTook(c));
			else
				System.out.println("None");
		}
		catch (Exception e)
		{
			System.out.println("Youv'e got a wrong course: "+cid);
		}
	}
	
	/** 
	 * Prints the number of students in the given course
	 * @param cid  course id
	 */
	public void printSize(int cid) 
	{
		// Put your code here.
		try 
		{
			Course c=getCourse(cid);
			LinkedList<Student> stl=studentsWhoTook(c);
			System.out.println("In Course: "+c.getTitle()+" there are "+stl.size()+" students");
		}
		catch (Exception e)
		{
			if (getCourse(cid)==null)
				System.out.println("Youv'e got a wrong course: "+cid);
			else
				System.out.println("You Don't Have Students in class");
		}
	}
	
	// Returns a list of all the students who took the given course
	private LinkedList<Student> studentsWhoTook(Course c) 
	{
		// replace the following statement with your code.
		try
		{
			LinkedList<Student> took=new LinkedList();
			ListIterator<Student> iter=this.students.iterator();
			if (iter.hasNext())
			{
				Student stu=iter.next();
				while (iter.hasNext())
				{
					if (stu.tookCourse(c))
						took.add(stu);
					stu=iter.next();
				}
				if (stu.tookCourse(c))
					took.add(stu);
			}
			return took;
		}
		catch (Exception e)
		{
			if (c==null)
				System.out.println("Youv'e got a wrong course: "+c);
			return new LinkedList<Student>();
		}
	}
	
	/** 
	 * Prints the student with the highest grade in the given course.
	 * @param cid  course id
	 */
	public void topPerfomerReport(int cid) 
	{
		// Put your code here
		try
		{
			ListIterator<Student> iter=studentsWhoTook(getCourse(cid)).iterator();
			LinkedList<Student> sthigh=new LinkedList();
			Student stu=iter.next();
			int maxgrade=0;
			Student maxi=stu;
			Course c=getCourse(cid);
			while (iter.hasNext())
			{
				if (stu.tookCourse(c))
					if (stu.gradeInCourse(c)>maxgrade)
					{
						maxgrade=stu.gradeInCourse(c);
						maxi=stu;
						sthigh=new LinkedList();
						sthigh.add(stu);
					}
					else
						if (stu.gradeInCourse(c)==maxgrade)
							sthigh.add(stu);
				stu=iter.next();
			}
			if (stu.tookCourse(c))
				if (stu.gradeInCourse(c)>maxgrade)
				{
					maxgrade=stu.gradeInCourse(c);
					maxi=stu;
				}
				else
					if (stu.gradeInCourse(c)==maxgrade)
						sthigh.add(stu);
			System.out.println("Course: "+getCourse(cid).getTitle());
			System.out.println("The highest grade for the course: "+maxgrade+" The student that achieved the grade is: ");
			System.out.println(sthigh);
		}
		catch (Exception e)
		{
			System.out.println("Youv'e got a wrong course: "+cid);
		}
	}

	/** 
	 * Returns the college name
	 * @return the college name 
	 */
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * A textual representation of this college, along with its courses and students.
	 */
	public String toString() 
	{
		String str = name + nl;
		str += "courses:" + nl;
		str += courses.toString() + nl;
		str += "students:" + nl;
		str += students.toString() + nl;
		return str;
	}
}
