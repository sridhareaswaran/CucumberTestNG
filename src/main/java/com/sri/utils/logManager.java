package com.sri.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by sridhar.easwaran on 1/5/2017.
 */
public class logManager {
    public static Logger log;

    public static void initLogManager() {
        log = Logger.getLogger(logManager.class);
        log.setLevel(Level.ALL);
    }
}
