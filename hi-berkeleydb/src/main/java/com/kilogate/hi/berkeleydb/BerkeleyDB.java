package com.kilogate.hi.berkeleydb;

import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * BerkeleyDB
 *
 * @author kilogate
 * @create 2021/6/7 16:51
 **/
public class BerkeleyDB {
    private Environment environment;
    private Database database;

    public BerkeleyDB() {
        init();
    }

    private void init() {
        // envHome
        String envPath = "/opt/work/BerkeleyDB";
        File envHome = new File(envPath);

        if (!envHome.exists()) {
            envHome.mkdirs();
        }

        // envConfig
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        envConfig.setCacheSize(512 * 1024 * 1024);

        // 1. environment
        this.environment = new Environment(envHome, envConfig);

        // dbConfig
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);

        // 2. database
        String dbName = "MyBerkeleyDB";
        this.database = environment.openDatabase(null, dbName, dbConfig);

        System.out.printf("init success, envPath: %s, dbName: %s", envPath, dbName);
    }

    public boolean putString(String key, String value) {
        if (key == null || key.length() == 0 || value == null || value.length() == 0) {
            return false;
        }

        DatabaseEntry keyEntry = new DatabaseEntry(key.getBytes(StandardCharsets.UTF_8));
        DatabaseEntry valueEntry = new DatabaseEntry(value.getBytes(StandardCharsets.UTF_8));
        OperationStatus operationStatus = this.database.put(null, keyEntry, valueEntry);

        return operationStatus == OperationStatus.SUCCESS;
    }

    public String getString(String key) {
        if (key == null || key.length() == 0) {
            return null;
        }

        DatabaseEntry keyEntry = new DatabaseEntry(key.getBytes(StandardCharsets.UTF_8));
        DatabaseEntry valueEntry = new DatabaseEntry();
        OperationStatus operationStatus = this.database.get(null, keyEntry, valueEntry, LockMode.DEFAULT);

        if (operationStatus != OperationStatus.SUCCESS) {
            return null;
        }

        return new String(valueEntry.getData(), StandardCharsets.UTF_8);
    }

    public boolean deleteString(String key) {
        DatabaseEntry keyEntry = new DatabaseEntry(key.getBytes(StandardCharsets.UTF_8));
        OperationStatus operationStatus = this.database.delete(null, keyEntry);

        return operationStatus == OperationStatus.SUCCESS;
    }

    public Map<String, String> getAllString() {
        Map<String, String> result = new HashMap<>();

        // cursorConfig
        CursorConfig cursorConfig = new CursorConfig();
        cursorConfig.setReadCommitted(true);

        // entry
        DatabaseEntry keyEntry = new DatabaseEntry();
        DatabaseEntry valueEntry = new DatabaseEntry();

        // scan
        Cursor cursor = null;
        try {
            cursor = this.database.openCursor(null, cursorConfig);

            while (cursor.getNext(keyEntry, valueEntry, LockMode.DEFAULT) != OperationStatus.NOTFOUND) {
                String key = new String(keyEntry.getData(), StandardCharsets.UTF_8);
                String value = new String(valueEntry.getData(), StandardCharsets.UTF_8);
                result.put(key, value);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return result;
    }

    public void close() {
        try {
            if (this.database != null) {
                this.database.close();
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            if (this.environment != null) {
                this.environment.close();
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BerkeleyDB berkeleyDB = new BerkeleyDB();

        for (int i = 0; i < 100; i++) {
            berkeleyDB.putString("key" + i, "value" + i);
        }

        berkeleyDB.deleteString("key1");
        berkeleyDB.deleteString("key2");
        berkeleyDB.deleteString("key3");

        Map<String, String> allData = berkeleyDB.getAllString();
        System.out.println(allData);

        String value1 = berkeleyDB.getString("key1");
        System.out.println(value1);

        String value11 = berkeleyDB.getString("key11");
        System.out.println(value11);

        berkeleyDB.close();
    }
}
