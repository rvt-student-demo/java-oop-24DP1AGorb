package rvt;

import java.util.HashMap;

public class IOU {
    private HashMap<String, Double> sums;

    public IOU() {
        this.sums = new HashMap<>();
    }

    public void setSum(String name, double amount) {
        this.sums.put(name, amount);
    }

    public double howMuchDoIOweTo(String name) {
        return this.sums.getOrDefault(name, 0.0);
    }
}
