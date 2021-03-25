package org.soedomoto.jdbc.grpcserver;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Component
public class PreparedStatementCollection {
    public Map<String, PreparedStatement> preparedStatementList = new HashMap();
}
