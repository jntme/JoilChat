package javaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by jnt on 05/05/16.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        String myString = "Star Wars is kind of the coolest movie ever produced.";
        String[] list = myString.split(" ") ;

        ArrayList<String> testList = new ArrayList(Arrays.asList(list));

        Collections.reverse(testList);

        String returnString = "";

        for (String s : testList) {
            returnString += s + " ";
        }

        System.out.println(returnString);
    }
}
