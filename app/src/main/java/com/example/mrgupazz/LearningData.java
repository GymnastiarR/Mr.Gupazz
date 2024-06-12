package com.example.mrgupazz;

import java.util.ArrayList;

public class LearningData {
    private static String [] alphabet ={
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private static String[] alphabetDetails ={
            "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words",
            "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words",
            "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words", "1581 Words",
    };
    static ArrayList<Learning>  getListData() {
        ArrayList<Learning> list = new ArrayList<>();
        for (int position = 0; position<alphabet.length; position++){
            Learning learning = new Learning();
            learning.setNames(alphabet[position]);
            learning.setDetails(alphabetDetails[position]);
        }
        return list;
    }
}
