package com.C10G14.WorldFitBackend.util;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DtoFormatter {
    public String format (String string){
        if (string != null && !string.isEmpty()){
            return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        }
        return string;
    }
    public String formatName(String string) {
        if (string != null && !string.isEmpty()) {
            String[] words = string.split("\\s+");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (!word.isEmpty()) {
                    String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                    result.append(capitalizedWord);
                    if (i < words.length - 1) {
                        result.append(" ");
                    }
                }
            }
            return result.toString();
        }
            return string;

    }
}
