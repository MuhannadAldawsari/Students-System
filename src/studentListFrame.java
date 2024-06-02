import javax.swing.*;
import java.awt.*;

class studentListFrame extends JFrame {
    private JTextArea studentListTextArea;
    private University university;

    public studentListFrame(University university) {
        super("students List");
        this.university = university;
        setSize(400, 400);
        setLocation(250,250);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        studentListTextArea = new JTextArea();
        studentListTextArea.setEditable(false);
        add(new JScrollPane(studentListTextArea), BorderLayout.CENTER);
        refreshStudentList();
        setVisible(true);
    }

    public void refreshStudentList() {
        StringBuilder sb = new StringBuilder();
        for (Student emp : university.getStudents()) {
            if (emp != null) sb.append(emp.toString()).append("\n");

        }
        studentListTextArea.setText(sb.toString());
    }
}
