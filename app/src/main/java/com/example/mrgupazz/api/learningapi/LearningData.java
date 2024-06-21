package com.example.mrgupazz.api.learningapi;

import java.util.ArrayList;

public class LearningData {
    private static String[] alphabet = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private static String[] alphabetDetails = {
            "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words",
            "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words",
            "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words"
    };

    static ArrayList<Learning> getListData(String[] alphabets) {
        ArrayList<Learning> list = new ArrayList<>();
        for (int position = 0; position < alphabets.length; position++) {
            Learning learning = new Learning();
            learning.setNames(alphabet[position]);
            learning.setDetails(alphabetDetails[position]);
            list.add(learning);
        }
        return list;
    }

    public static ArrayList<Learning> getListData() {
        return getListData(alphabet);
    }
}
