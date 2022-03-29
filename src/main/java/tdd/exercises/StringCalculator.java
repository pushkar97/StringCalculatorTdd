package tdd.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String s) {
        if(s.isBlank()) return 0;
        String delimiter = ",";
        Matcher matcher = getMatcherForCustomDelimiter(s);
        if (matcher.find()){
            String group = matcher.group();
            delimiter = group.substring(2,group.length()-1);
            s = s.replace(group, "");
        }
        String regex = delimiter + "|\\n";
        return findSum(s, regex);
    }

    private int findSum(String input, String delimiter) {
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        for(String n : input.split(delimiter)) {
            int i = Integer.parseInt(n);
            if (i >= 0) sum += i;
            else negatives.add(i);
        }
        if (!negatives.isEmpty()) throw new RuntimeException("negatives not allowed: "+ negatives);
        return sum;
    }

    private Matcher getMatcherForCustomDelimiter(String s) {
        Pattern p = Pattern.compile("//(.+)\\n");
        return p.matcher(s);
    }
}
