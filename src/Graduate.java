import java.io.Serializable;

public class Graduate extends Student implements Serializable {

    private int researchHours;

    public Graduate(int id, String name, int size, int researchHours) {
        super(id, name, size);
        this.researchHours = researchHours;
    }
    public Graduate(Graduate g){
        super(g);
        this.researchHours = g.researchHours;
    }
    public int getResearchHours() {
        return researchHours;
    }
    @Override
    public double calcGPA() {
        if (getAverage() == -1)
            return -1;
        return getAverage()/25 + researchHours * 0.05;
    }
    @Override
    public String toString() {
        return super.toString()+", Research  hours completed: "+researchHours;
    }
}
