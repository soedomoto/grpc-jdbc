package org.soedomoto.jdbc.grpcserver;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Component
public class JdbcClientCollection {
    Map<String, JdbcClient> jdbcClientList = new HashMap();

    public JdbcClient connect(String name, String driverUrl, String driverClass, String url, String username, String password) throws ClassNotFoundException, SQLException, MalformedURLException {
        if (! jdbcClientList.containsKey(name)) {
            JdbcClient client = JdbcClient.connect(name, driverUrl, driverClass, url, username, password);
            jdbcClientList.put(name, client);
        }
        return jdbcClientList.get(name);
    }

    public JdbcClient get(String name) {
        return jdbcClientList.get(name);
    }

    public Statement getStatement(String id) {
        for (JdbcClient jdbcClient : jdbcClientList.values()) {
            Statement stmt = jdbcClient.statementList.get(id);
            if (stmt != null) return stmt;
        }

        return null;
    }
}
