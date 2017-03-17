package com.sri.utils;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sridhar.easwaran on 1/5/2017.
 */
public class cmd {

    public static String run(String command) {
        String output = "";
        try {
            logManager.log.debug("Executing command : " + command);
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) output = scanner.next();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        logManager.log.debug("Output of command => " + command + " is => " + output);
        return output;
    }
}
