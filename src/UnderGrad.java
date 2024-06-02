import java.io.Serializable;

public class UnderGrad extends Student implements Serializable {

    public UnderGrad(int id, String name, int size) {
        super(id, name, size);
    }
    public UnderGrad(UnderGrad ug){
        super(ug);
    }

    @Override
    public double calcGPA() {
        if (getAverage() == -1)
            return -1;
        return getAverage()/20;
    }
}
