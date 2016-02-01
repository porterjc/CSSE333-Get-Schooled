package com.example.porterjc.getschooled;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by porterjc on 1/26/2016.
 */
public class ServerConnectClass {
    String ip = "titan.csse.rose-hulman.edu";
    String clazz = "net.sourceforge.jtds.jdbc.Driver";
    String dataB = "GetSchooledDatabase";
    String usern;
    String passw;

    public Connection connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String URL = null;
        try {
            Class.forName(clazz);
            URL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + dataB + ";user=GetSchooledUser;password=getschooledpassword;";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return connection;
    }

    public String getIP() {
        return ip;
    }
}
