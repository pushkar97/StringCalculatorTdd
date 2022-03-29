package tdd.exercises;

public class StringCalculator {

    public int add(String s) {
        if(s.isBlank()) return 0;
        return Integer.parseInt(s);
    }
}
