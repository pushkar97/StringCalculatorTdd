package tdd.exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String s) {
        if(s.isBlank()) return 0;
        String delimiter = ",";
        Pattern p = Pattern.compile("//(.+)\\n");
        Matcher matcher = p.matcher(s);
        if (matcher.find()){
            String group = matcher.group();
            delimiter = group.substring(2,group.length()-1);
            s = s.replace(group, "");
        }
        String regex = delimiter + "|\\n";
        int sum = 0;
        for(String n : s.split(regex)) sum +=Integer.parseInt(n);
        return sum;
    }
}
