package ch.joil.joilchat.server;

import org.junit.Test;
import serverOLD.StringUtil;

import static org.junit.Assert.*;

/**
 * Created by jnt on 05/05/16.
 */
public class StringUtilTest {
    @Test
    public void reverseString() throws Exception {
        String normalString = "this is a normal string";
        System.out.println("before: " + normalString);

        String afterString = StringUtil.reverseString(normalString);
        System.out.println("after: " + afterString);

        assertEquals(afterString, "string normal a is this");
    }
}