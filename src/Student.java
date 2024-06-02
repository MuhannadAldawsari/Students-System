import java.io.Serializable;

public abstract class Student implements Serializable {
    private int id;
    private String name;
    protected Course[] Courses;
    protected int nbCourses;

    public Student(int id, String name, int size) {
        this.id = id;
        this.name = name;
        Courses = new Course[size];
        nbCourses = 0;
    }
    public Student(Student s){
        this.id = s.id;
        this.name = s.name;
        Courses = new Course[s.Courses.length];
        for (int i = 0; i<s.nbCourses; i++)
            this.Courses[i] = new Course(s.Courses[i]);
        this.nbCourses = s.nbCourses;
    }
    public boolean addCourse(Course c){
        if (nbCourses == Courses.length)
            return false;
        Courses[nbCourses++] = new Course(c);
        return true;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getAverage(){
        if (nbCourses == 0) return -1;
        double sum = 0;
        for (int i = 0; i<nbCourses; i++)
            sum+= Courses[i].getGrade();
        return sum / nbCourses;
    }
    public abstract double calcGPA();

    @Override
    public String toString() {
        return "ID: "+id+", Name: "+name;
    }
}
