package org.soedomoto.jdbc.grpcserver;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class GrpcDriver implements Driver {
    public final static String URL_PREFIX = "jdbc:soedomoto:grpc:";
    
    public Connection connect(String url, Properties info) throws SQLException {
        writeLog("GrpcDriver:connect() - url=" + url);
        // check for correct url
        if (!url.startsWith(URL_PREFIX))
        {
            return null;
        }

        // strip any properties from end of URL and set them as additional
        // properties
        String urlProperties = "";
        int questionIndex = url.indexOf('?');
        if (questionIndex >= 0)
        {
            info = new Properties(info);
            urlProperties = url.substring(questionIndex);
            String[] split = urlProperties.substring(1).split("&");
            for (int i = 0; i < split.length; i++)
            {
                int equalsIndex = split[i].indexOf("=");
                if (equalsIndex <= 0)
                    throw new SQLException(GrpcResources.getString("invalidProperty") + ": " + split[i]);
                int lastEqualsIndex = split[i].lastIndexOf("=");
                if (lastEqualsIndex != equalsIndex)
                    throw new SQLException(GrpcResources.getString("invalidProperty") + ": " + split[i]);

                try
                {
                    String key = URLDecoder.decode(split[i].substring(0, equalsIndex), "UTF-8");
                    String value = URLDecoder.decode(split[i].substring(equalsIndex + 1), "UTF-8");
                    info.setProperty(key, value);
                }
                catch (UnsupportedEncodingException e)
                {
                    // we know UTF-8 is available
                }
            }
            url = url.substring(0, questionIndex);
        }

        // get filepath from url
        String jdbcUrl = url.substring(URL_PREFIX.length());
        writeLog("GrpcDriver:connect() - jdbcUrl=" + jdbcUrl);

        return new GrpcConnection(jdbcUrl, info, urlProperties);
    }

    public boolean acceptsURL(String url) throws SQLException {
        writeLog("GrpcDriver:accept() - url=" + url);
        return url.startsWith(URL_PREFIX);
    }

    public DriverPropertyInfo[] getPropertyInfo(String s, Properties properties) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return 0;
    }

    public int getMinorVersion() {
        return 0;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException(GrpcResources.getString("methodNotSupported") +
                ": Driver.getParentLogger()");
    }

    public static void writeLog(String message) {
        PrintWriter logWriter = DriverManager.getLogWriter();
        if (logWriter != null)
            logWriter.println("GrpcJdbc: " + message);
    }

    static
    {
        try
        {
            java.sql.DriverManager.registerDriver(new GrpcDriver());
        }
        catch (SQLException e)
        {
            throw new RuntimeException(GrpcResources.getString("initFailed") + ": " + e.getMessage());
        }
    }
}
