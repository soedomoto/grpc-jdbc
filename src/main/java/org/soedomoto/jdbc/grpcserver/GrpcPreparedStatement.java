package org.soedomoto.jdbc.grpcserver;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import org.joda.time.DateTime;
import org.soedomoto.jdbc.grpc.statement.Data;
import org.soedomoto.jdbc.grpc.statement.ServiceMessages;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class GrpcPreparedStatement extends GrpcStatement implements PreparedStatement {
    private final String sql;
    private final Data.PreparedStatement remotePreparedStatement;
    private Map<Integer, Data.Column> params = new HashMap<>();

    public GrpcPreparedStatement(GrpcConnection grpcConnection, Data.PreparedStatement remoteStatement, String s, int typeForwardOnly) {
        super(grpcConnection, typeForwardOnly);
        this.remotePreparedStatement = remoteStatement;
        this.sql = s;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteStatementService
                    .executeQuery(ServiceMessages.ExecuteQueryRequest.newBuilder()
                            .setPreparedStatement(remotePreparedStatement)
                            .setSql(sql)
                            .addAllParams(params.values())
                            .build());

            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int executeUpdate() throws SQLException {
        try {
            Data.ExecuteUpdateResult remoteResultSet = grpcConnection.remoteStatementService
                    .executeUpdate(ServiceMessages.ExecuteQueryRequest.newBuilder()
                            .setPreparedStatement(remotePreparedStatement)
                            .setSql(sql)
                            .addAllParams(params.values())
                            .build());

            return remoteResultSet.getUpdatedRows();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public void setNull(int i, int i1) throws SQLException {
        throw new SQLException(GrpcResources.getString("methodNotSupported") + ": setNull(int,int)");
    }

    @Override
    public void setBoolean(int i, boolean b) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setBoolValue(b).build());
    }

    @Override
    public void setByte(int i, byte b) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setByteValue(ByteString.copyFrom(new byte[]{b})).build());
    }

    @Override
    public void setShort(int i, short i1) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setShortValue(i1).build());
    }

    @Override
    public void setInt(int i, int i1) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setIntValue(i1).build());
    }

    @Override
    public void setLong(int i, long l) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setLongValue(l).build());
    }

    @Override
    public void setFloat(int i, float v) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setFloatValue(v).build());
    }

    @Override
    public void setDouble(int i, double v) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setDoubleValue(v).build());
    }

    @Override
    public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setBigdecimalValue(bigDecimal.doubleValue()).build());
    }

    @Override
    public void setString(int i, String s) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setStringValue(s).build());
    }

    @Override
    public void setBytes(int i, byte[] bytes) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setBytesValue(ByteString.copyFrom(bytes)).build());
    }

    @Override
    public void setDate(int i, Date date) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setDateValue(new DateTime(date.getTime()).toString("yyyy-MM-dd")).build());
    }

    @Override
    public void setTime(int i, Time time) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setDateValue(new DateTime(time.getTime()).toString("yyyy-MM-dd")).build());
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp) throws SQLException {
        params.put(i, Data.Column.newBuilder().setIndex(i).setDateValue(new DateTime(timestamp.getTime()).toString()).build());
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void setUnicodeStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void clearParameters() throws SQLException {
        params.clear();
    }

    @Override
    public void setObject(int i, Object o, int i1) throws SQLException {

    }

    @Override
    public void setObject(int i, Object o) throws SQLException {

    }

    @Override
    public boolean execute() throws SQLException {
        return false;
    }

    @Override
    public void addBatch() throws SQLException {

    }

    @Override
    public void setCharacterStream(int i, Reader reader, int i1) throws SQLException {

    }

    @Override
    public void setRef(int i, Ref ref) throws SQLException {

    }

    @Override
    public void setBlob(int i, Blob blob) throws SQLException {

    }

    @Override
    public void setClob(int i, Clob clob) throws SQLException {

    }

    @Override
    public void setArray(int i, Array array) throws SQLException {

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setDate(int i, Date date, Calendar calendar) throws SQLException {

    }

    @Override
    public void setTime(int i, Time time, Calendar calendar) throws SQLException {

    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {

    }

    @Override
    public void setNull(int i, int i1, String s) throws SQLException {

    }

    @Override
    public void setURL(int i, URL url) throws SQLException {

    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setRowId(int i, RowId rowId) throws SQLException {

    }

    @Override
    public void setNString(int i, String s) throws SQLException {

    }

    @Override
    public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setNClob(int i, NClob nClob) throws SQLException {

    }

    @Override
    public void setClob(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setBlob(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void setNClob(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {

    }

    @Override
    public void setObject(int i, Object o, int i1, int i2) throws SQLException {

    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void setCharacterStream(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setCharacterStream(int i, Reader reader) throws SQLException {

    }

    @Override
    public void setNCharacterStream(int i, Reader reader) throws SQLException {

    }

    @Override
    public void setClob(int i, Reader reader) throws SQLException {

    }

    @Override
    public void setBlob(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setNClob(int i, Reader reader) throws SQLException {

    }
}
