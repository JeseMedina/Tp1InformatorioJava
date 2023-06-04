/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.utils;

/**
 *
 * @author Jesé
 */
public class Utils {

    public String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder capitalizedStr = new StringBuilder();
        String[] words = str.split("\\s+");
        for (String word : words) {
            if (word.length() > 1) {
                capitalizedStr.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase()).append(" ");
            } else {
                capitalizedStr.append(Character.toUpperCase(word.charAt(0))).append(" ");
            }
        }

        return capitalizedStr.toString().trim();
    }
}
