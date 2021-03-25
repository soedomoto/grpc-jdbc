package org.soedomoto.jdbc.grpcserver;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResultSetCollection {
    public Map<String, ResultSet> resultSetList = new HashMap();
}
