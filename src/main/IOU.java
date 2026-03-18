package rvt;
import java.util.HashMap;

public class IOU{
    IOU mattsIOU = new IOU();
    mattsIOU.setSum("Arthur", 51.5);
    mattsIOU.setSum("Michael", 30);

System.out.println(mattsIOU.howMuchDoIOweTo("Arthur"));
System.out.println(mattsIOU.howMuchDoIOweTo("Michael"));
}

class IOU{
    String name;
    double owned;

    IOU(String name, double owned){
        this.name = name;
        this.owned = naliks;
    }
    public double toWhom(String name){
        return naliks;
    }
}
