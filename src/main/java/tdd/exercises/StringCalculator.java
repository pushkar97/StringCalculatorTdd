package tdd.exercises;

public class StringCalculator {

    public int add(String s) {
        if(s.isBlank()) return 0;
        int sum = 0;
        for(String n : s.split(",|\\n")){
            sum +=Integer.parseInt(n);
        }
        return sum;
    }
}
