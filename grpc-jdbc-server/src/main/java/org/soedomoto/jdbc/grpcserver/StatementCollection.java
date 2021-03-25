package org.soedomoto.jdbc.grpcserver;

import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Component
public class StatementCollection {
    public Map<String, Statement> statementList = new HashMap();
}
