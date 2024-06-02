import javax.swing.*;
import java.awt.*;

import java.io.*;

public class GUI extends JFrame {
    University university = new University("King Saud University", 10);

    public JTextField idField, nameField, graduateField, coursesField, researchHoursField;



    public GUI() {
        super("University Management System");

        setupInputPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setLocation(500, 250);
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(16, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));


        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField(10);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Student name:"));
        nameField = new JTextField(10);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("graduate(1) or under graduate(2):"));
        graduateField = new JTextField(10);
        inputPanel.add(graduateField);

        inputPanel.add(new JLabel("number of courses:"));
        coursesField = new JTextField(10);
        inputPanel.add(coursesField);

        inputPanel.add(new JLabel("research hours for graduate only:"));
        researchHoursField = new JTextField(10);
        inputPanel.add(researchHoursField);

        JButton addButton = new JButton("Add student");
        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int grad = !graduateField.getText().trim().isEmpty() ? Integer.parseInt(graduateField.getText().trim()) : 0;
                int course = !coursesField.getText().trim().isEmpty() ? Integer.parseInt(coursesField.getText().trim()) : 0;
                int research = !researchHoursField.getText().trim().isEmpty() ? Integer.parseInt(researchHoursField.getText().trim()) : 0;

                if (grad == 1) {
                    Graduate g = new Graduate(id, name, course, research);
                    for (int i = 0; i<course; i++){
                        String sName =JOptionPane.showInputDialog(null,"What is course "+(i+1)+" name");//Input
                        String  grade=JOptionPane.showInputDialog(null,"What is course "+(i+1)+" grade");//Input
                        int gr = Integer.parseInt(grade);
                        Course c = new Course(sName,gr);
                        g.addCourse(c);
                    }
                    university.addStudent(g);
                }else if (grad == 2){
                UnderGrad ug = new UnderGrad(id, name, course);
                    for (int i = 0; i<course; i++){
                        String sName =JOptionPane.showInputDialog(null,"What is course "+(i+1)+" name");//Input
                        String  grade=JOptionPane.showInputDialog(null,"What is course "+(i+1)+" grade");//Input
                        int gr = Integer.parseInt(grade);
                        Course c = new Course(sName,gr);
                        ug.addCourse(c);
                    }
                     university.addStudent(ug);
                }else if (grad == 0){

                }else throw new NumberFormatException();

                idField.setText("");
                nameField.setText("");
                graduateField.setText("");
                coursesField.setText("");
                researchHoursField.setText("");
                JOptionPane.showMessageDialog(GUI.this, "Student added!");

            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(GUI.this, "Error adding student: " + ex.getMessage());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID, courses.");
            }
        });
        inputPanel.add(addButton);

        JButton removeB = new JButton("Remove Student");
        removeB.addActionListener(e -> {
            try{
                String Id=JOptionPane.showInputDialog(null,"What is student ID");
                int num = Integer.parseInt(Id);
                Student [] s = university.getStudents();
                try {
                    int search = university.search(num);

                    university.removeStudent(s[search]);
                    JOptionPane.showMessageDialog(GUI.this, "Student removed!");

                }catch (Exception c) {
                    System.out.println(c.getMessage());
                    JOptionPane.showMessageDialog(this, "Student Not Found, try again");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter valid number for ID");
            }
        });
        inputPanel.add(removeB);

        JButton saveButton = new JButton("Save Students to File");
        saveButton.addActionListener(e -> saveStudentsToFile());
        inputPanel.add(saveButton);

        JButton loadButton = new JButton("Load Students from File");
        loadButton.addActionListener(e -> loadStudentsFromFile());
        inputPanel.add(loadButton);

        JButton displayB = new JButton("Display Students");
        displayB.addActionListener(e -> new studentListFrame(university));
        inputPanel.add(displayB);



        getContentPane().add(inputPanel, BorderLayout.NORTH);
    }

    private void saveStudentsToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                university.saveToFile(fileToSave.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Data saved to file: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
            }
        }
    }
    private void loadStudentsFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to load");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
                university.loadFromFile(fileToLoad.getAbsolutePath());

                JOptionPane.showMessageDialog(this, "Data loaded from file: " + fileToLoad.getAbsolutePath());

        }
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(GUI::new);

    }

}