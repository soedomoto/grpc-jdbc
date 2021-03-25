package org.soedomoto.jdbc.grpcserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import org.soedomoto.jdbc.grpc.connection.ConnectionServiceGrpc;
import org.soedomoto.jdbc.grpc.connection.Data;
import org.soedomoto.jdbc.grpc.connection.ServiceMessages;
import org.soedomoto.jdbc.grpc.database_metadata.DatabaseMetadataServiceGrpc;
import org.soedomoto.jdbc.grpc.statement.StatementServiceGrpc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class GrpcConnection implements Connection {
    private final String jdbcUrl;
    private final String urlProperties;
    private boolean closed = false;
    private List<GrpcStatement> statements = new ArrayList<>();

    protected Data.Connection remoteConnection;

    protected ConnectionServiceGrpc.ConnectionServiceBlockingStub remoteConnectionService;
    protected StatementServiceGrpc.StatementServiceBlockingStub remoteStatementService;
    protected DatabaseMetadataServiceGrpc.DatabaseMetadataServiceBlockingStub remoteDatabaseMetadataService;

    public GrpcConnection(String jdbcUrl, Properties info, String urlProperties) throws SQLException {
        // validate argument(s)
        if (jdbcUrl == null || jdbcUrl.length() == 0) {
            throw new IllegalArgumentException(GrpcResources.getString("noPath"));
        }
        this.jdbcUrl = jdbcUrl;
        this.urlProperties = urlProperties;

        // check for properties
        if (info != null) {
            setProperties(info);
        }
    }

    private void setProperties(Properties info) throws SQLException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(
                        String.valueOf(info.get("proxy-host")),
                        Integer.parseInt(String.valueOf(info.get("proxy-port"))))
                .usePlaintext()
                .build();

        this.remoteConnectionService = ConnectionServiceGrpc.newBlockingStub(channel);
        this.remoteStatementService = StatementServiceGrpc.newBlockingStub(channel);
        this.remoteDatabaseMetadataService = DatabaseMetadataServiceGrpc.newBlockingStub(channel);

        try {
            this.remoteConnection = this.remoteConnectionService.connect(ServiceMessages.ConnectRequest.newBuilder()
                    .setDriverUrl(String.valueOf(info.get("driver-url")))
                    .setDriverClass(String.valueOf(info.get("driver-class")))
                    .setConnectionName(String.valueOf(info.get("connection-name")))
                    .setDriverUsername(String.valueOf(info.get("driver-username")))
                    .setDriverPassword(String.valueOf(info.get("driver-password")))
                    .setJdbcUrl(this.jdbcUrl)
                    .build());
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public Statement createStatement() throws SQLException {
        try {
            checkOpen();

            org.soedomoto.jdbc.grpc.statement.Data.Statement remoteStatement = this.remoteStatementService
                    .createStatement(org.soedomoto.jdbc.grpc.statement.ServiceMessages.CreateStatementRequest.newBuilder()
                            .setConnection(this.remoteConnection)
                            .build());

            GrpcStatement statement = new GrpcStatement(this, remoteStatement, ResultSet.TYPE_FORWARD_ONLY);
            statements.add(statement);
            return statement;
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String s) throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.statement.Data.PreparedStatement remoteStatement = this.remoteStatementService
                    .prepareStatement(org.soedomoto.jdbc.grpc.statement.ServiceMessages.PrepareStatementRequest.newBuilder()
                            .setConnection(this.remoteConnection)
                            .setSql(s)
                            .build());

            GrpcPreparedStatement statement = new GrpcPreparedStatement(this, remoteStatement, s, ResultSet.TYPE_FORWARD_ONLY);
            statements.add(statement);
            return statement;
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return null;
    }

    @Override
    public String nativeSQL(String s) throws SQLException {
        return null;
    }

    @Override
    public void setAutoCommit(boolean b) throws SQLException {

    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    @Override
    public void commit() throws SQLException {

    }

    @Override
    public void rollback() throws SQLException {

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return new GrpcDatabaseMetaData(this);
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {

    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return false;
    }

    @Override
    public void setCatalog(String s) throws SQLException {

    }

    @Override
    public String getCatalog() throws SQLException {
        return null;
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {

    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

    }

    @Override
    public void setHoldability(int i) throws SQLException {

    }

    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return null;
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {

    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {

    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        return null;
    }

    @Override
    public Clob createClob() throws SQLException {
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
        return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        return false;
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {

    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {

    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return null;
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return null;
    }

    @Override
    public void setSchema(String s) throws SQLException {

    }

    @Override
    public String getSchema() throws SQLException {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {

    }

    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {

    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }

    private void checkOpen() throws SQLException {
        if (closed)
            throw new SQLException(GrpcResources.getString("closedConnection"));
    }
}
