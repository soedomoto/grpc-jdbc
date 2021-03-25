package org.soedomoto.jdbc.grpcserver;

import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class JdbcClient {
    public final String driverUrl;
    public final String driverClass;
    public final String url;
    public final String name;
    public final String username;
    public final String password;

    public final Connection connection;
    Map<String, Statement> statementList = new HashMap();
    Map<String, ResultSet> resultSetList = new HashMap();

    public static UUID UUIDtime() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        long least64SigBits = random63BitLong + variant3BitFlag;

        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        long most64SigBits = (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;

        return new UUID(most64SigBits, least64SigBits);
    }

    static JdbcClient connect(String name, String driverUrl, String driverClass, String url, String username, String password) throws SQLException, MalformedURLException {
        return new JdbcClient(name, driverUrl, driverClass, url, username, password);
    }

    private JdbcClient(String name, String driverUrl, String driverClass, String url, String username, String password) throws SQLException, MalformedURLException {
        this.name = name;
        this.driverUrl = driverUrl;
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;

        JarClassLoader jcl = new JarClassLoader();
        jcl.add(new URL(this.driverUrl));

        Properties properties = new Properties();
        properties.put("user", this.username);
        properties.put("password", this.password);

        JclObjectFactory factory = JclObjectFactory.getInstance();
        Driver obj = (Driver) factory.create(jcl, this.driverClass);
        this.connection = obj.connect(url, properties);
    }

    public Set<String> getStatementIds() {
        return statementList.keySet();
    }

    public String createStatement() throws SQLException {
        String id = UUIDtime().toString();
        Statement statement = connection.createStatement();
        statementList.put(id, statement);
        return id;
    }

    public ResultSet executeQuery(String s) throws SQLException {
        return connection.createStatement().executeQuery(s);
    }
}
