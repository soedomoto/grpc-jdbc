package org.soedomoto.jdbc.grpcserver.examples;

import java.sql.*;
import java.util.Properties;

public class Basic {
    public static void main(String[] args) throws Exception {
        // Load the driver.
        Class.forName("org.soedomoto.jdbc.grpcserver.GrpcDriver");

        // Create a connection to directory given as first command line
        // parameter. Driver properties are passed in URL format
        // (or alternatively in a java.utils.Properties object).
        //
        // A single connection is thread-safe for use by several threads.
        Properties info = new Properties();
        info.put("connection-name", "backoffice2020");
        info.put("proxy-host", "localhost");
        info.put("proxy-port", 6565);
        info.put("driver-url", "https://repo1.maven.org/maven2/com/h2database/h2/1.4.199/h2-1.4.199.jar");
        info.put("driver-class", "org.h2.Driver");
        info.put("driver-username", "sa");
        info.put("driver-password", "");

        String url = "jdbc:soedomoto:grpc:jdbc:h2:file:///run/media/soedomoto/PROJECT/2020-backoffice-devops/server/backoffice2020";
        Connection conn = DriverManager.getConnection(url, info);

        ResultSet cats = conn.getMetaData().getCatalogs();
        while (cats.next()) {
            String cat = cats.getString(1);
            ResultSet schs = conn.getMetaData().getSchemas();
            while (schs.next()) {
                for (int c=1; c<=schs.getMetaData().getColumnCount(); c++) {
                    String cname = schs.getMetaData().getColumnName(c);
                    String sch = schs.getString(c);
                    int a = 1;
                }
            }
        }

//        ResultSet tbls = conn.getMetaData().getTables("BACKOFFICE2020", "BACKOFFICE2020", "%", new String[]{});
//        while (tbls.next()) {
//            for (int c=1; c<=tbls.getMetaData().getColumnCount(); c++) {
//                String tbl = tbls.getString(c);
//                int a = 1;
//            }
//        }

//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SHOW TABLES");

//        PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER_ACTIVE_DEVICE WHERE user = ?");
//        ps.setString(1, "340050036");
//        ResultSet rs = ps.executeQuery();
//
//        ResultSetMetaData rsmd = rs.getMetaData();
//        while (rs.next()) {
//            for (int c=1; c<=rsmd.getColumnCount(); c++) {
//                String val = rs.getString(c);
//                int a = 1;
//            }
//        }
//
//        // Clean up
//        conn.close();
    }
}
