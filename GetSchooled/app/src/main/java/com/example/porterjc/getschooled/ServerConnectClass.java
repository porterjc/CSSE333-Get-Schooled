package com.example.porterjc.getschooled;

import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServerConnectClass {
    String ip = "titan.csse.rose-hulman.edu";
    String clazz = "net.sourceforge.jtds.jdbc.Driver";
    String micClazz = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dataB = "GetSchooledDatabase";
    String usern = "GetSchooledUser";
    String passw = "getschooledpassword";
    private static String mLoggedInUser;

    public Connection connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        System.setProperty("java.net.preferIPv6Addresses", "true");
        Connection connection = null;
        String URL = null;
        try {
            Class.forName(clazz);
           // Class.forName(micClazz);
            //System.out.println("This works right?");
            URL = "jdbc:jtds:sqlserver://" + ip + "/"
                     + dataB + ";prepareSQL=0"; //user=GetSchooledUser;mPassword=getschooledpassword;";
            connection = DriverManager.getConnection(URL, usern, passw);
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

    public static String getUser() {
        return mLoggedInUser;
    }

    public static void setUser(String currentUser) {
        mLoggedInUser = currentUser;
    }
}
