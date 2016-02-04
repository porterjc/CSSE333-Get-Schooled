package com.example.porterjc.getschooled;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by porterjc on 1/26/2016.
 */
public class ServerConnectClass {
    String ip = "titan.csse.rose-hulman.edu";
    String clazz = "net.sourceforge.jtds.jdbc.Driver";
    String micClazz = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dataB = "GetSchooledDatabase";
    String usern;
    String passw;

    public Connection connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        System.setProperty("java.net.preferIPv6Addresses", "true");
        Connection connection = null;
        String URL = null;
        try {
            Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
           // Class.forName(micClazz);
            System.out.println("This works right?");
            URL = "jdbc:sqlserver://" + ip + ";"
                    + "databaseName=" + dataB + ";user=GetSchooledUser;password=getschooledpassword;";
            connection = d.connect(URL, new Properties());
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return connection;
    }

    public String getIP() {
        return ip;
    }
}
