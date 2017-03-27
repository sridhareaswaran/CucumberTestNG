package com.sri.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sridhar.easwaran on 12/28/2016.
 */

public class configReader {

    static Yaml yaml = new Yaml();

    static InputStream baseConfig_IS = null;
    static InputStream envConfig_IS = null;

    public static Map<String, String> baseConfig_data = new HashMap<>();
    public static Map<String, HashMap<String, String>> envConfig_data = new HashMap<String, HashMap<String, String>>();
    public static Map<String, String> current_Env_Data = new HashMap<>();
    public static String ENV = null;
    public static String RunTestsIn = null;
    public static String BROWSER = null;

    public static void initConfigReader() {
        try {

            baseConfig_IS = configReader.class.getResourceAsStream("/config/baseConfig.yml");
            envConfig_IS = configReader.class.getResourceAsStream("/config/envConfig.yml");

        } catch (Exception e) {
            e.printStackTrace();
        }

        baseConfig_data = (HashMap<String, String>) yaml.load(baseConfig_IS);
        envConfig_data = (HashMap<String, HashMap<String, String>>) yaml.load(envConfig_IS);

        /** ENV setup **/
        if (System.getProperty("env") != null)
            ENV = System.getProperty("env");
        else
            ENV = baseConfig_data.get("TestEnvironment");

        /** Browser setup **/
        if (System.getProperty("browser") != null)
            BROWSER = System.getProperty("browser").toLowerCase();
        else
            BROWSER = baseConfig_data.get("Browser").toLowerCase();

        logManager.log.info(BROWSER);

        /** RunTestIN setup (local/remote) **/
        if (System.getProperty("RunTestsIn") != null)
            RunTestsIn = System.getProperty("RunTestsIn").toLowerCase();
        else
            RunTestsIn = baseConfig_data.get("RunTestsIn").toLowerCase();

        logManager.log.info(RunTestsIn);

        /** get the current ENV details in to HashMap **/
        current_Env_Data = envConfig_data.get(ENV);
    }

    public HashMap<String, String> getBaseConfig_data() {
        return (HashMap<String, String>) baseConfig_data;
    }

    public HashMap<String, String> getCurrent_Env_Data() {
        return (HashMap<String, String>) current_Env_Data;
    }

}
