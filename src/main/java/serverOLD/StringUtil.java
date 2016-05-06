package serverOLD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jnt on 05/05/16.
 */
public class StringUtil {

    /**
     * Returns the reverted String.
     *
     * @param s the String
     * @return the reverted String
     */
    public static String reverseString(String s) {

        String[] list = s.split(" ");

        ArrayList<String> testList = new ArrayList(Arrays.asList(list));

        Collections.reverse(testList);

        String returnString = "";

        boolean runThrough = false;
        for (String str : testList) {
            returnString += str + " ";
            runThrough = true;
        }

        //cuttin the last space away if ran through
        if (runThrough) returnString = returnString.substring(0, returnString.length() - 1);

        return returnString;
    }
}
