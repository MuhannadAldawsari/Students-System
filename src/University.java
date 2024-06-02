
import java.io.*;


public class University  {
    private String name;
    private Student[] students;
    private int nbStudents;

    public Student[] getStudents() {
        return students;
    }
    public int getNbStudents() {
        return nbStudents;
    }
    public University(String name, int size) {
        this.name = name;
        students = new Student[size];
        nbStudents = 0;
    }
    public int search(int id) throws Exception {
        int index;
        for (int i = 0; i<nbStudents; i++){
            if (students[i].getId() == id){
                index = i;
                return index;
            }
        }
            throw new Exception("Student not Found!");
    }
    public boolean addStudent(Student s) throws IllegalStateException {
        if (nbStudents == students.length )
            throw new IllegalStateException("Array of students is full");

        if (s instanceof UnderGrad)
            students[nbStudents++] = new UnderGrad((UnderGrad) s);
        else if (s instanceof Graduate)
            students[nbStudents++] = new Graduate((Graduate) s);
        return true;
    }
    public void removeStudent(Student s) throws Exception {
        int index = search(s.getId());

        students[index] = students[nbStudents-1];
        students[nbStudents-1] = null;
        nbStudents--;
        System.out.println("Student removed!");
    }
    public Student getMaxGPA() {
        Student max = students[0];
        for (int i = 1; i<nbStudents; i++){
            if (students[i].calcGPA() > max.calcGPA())
                max = students[i];
        }
        return max;
    }
    public int getNumberOfGrade(){
        int count = 0;
        for (int i = 0; i<nbStudents; i++){
             if (students[i] instanceof Graduate)
                count++;
        }
        return count;
    }
    public void Display(){
        for (int i = 0; i<nbStudents; i++) {
            System.out.println(students[i].toString()+", Number of courses: "+students[i].nbCourses);
            for (int b = 0; b<students[i].nbCourses; b++)
                 System.out.println("Course "+(b+1)+" : "+students[i].Courses[b].toString());
        }
    }
    public void saveToFile(String file) throws IOException {
        File f = new File(file);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream objStream = new ObjectOutputStream(fos);

        for (int i = 0; i<nbStudents; i++)
            objStream.writeObject(students[i]);

        fos.close();
        objStream.close();
    }
    public void loadFromFile(String file){
        try {
            File f = new File(file);
            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream objStream = new ObjectInputStream(fos);

            while (true){
                students[nbStudents] = (Student) objStream.readObject();
                nbStudents++;
            }
        }catch (ClassNotFoundException f){
            System.out.println(f);

        }catch (EOFException e){
            System.out.print("");
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
