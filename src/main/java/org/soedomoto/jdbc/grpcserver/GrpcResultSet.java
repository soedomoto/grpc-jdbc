package org.soedomoto.jdbc.grpcserver;

import io.grpc.Status;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.soedomoto.jdbc.grpc.statement.Data;
import org.soedomoto.jdbc.grpc.statement.ServiceMessages;

import javax.sql.rowset.RowSetMetaDataImpl;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class GrpcResultSet implements ResultSet {
    //    private final GrpcStatement grpcStatement;
//    private final Data.ResultSet remoteResultSet;
    private Iterator<Data.Record> records;
    private Map<Integer, Data.Column> idxColumns = new HashMap<>();
    private Map<String, Data.Column> nameColumns = new HashMap<>();
    private Data.Metadata metadata;

    public GrpcResultSet(GrpcConnection grpcConnection, Data.ResultSet remoteResultSet) throws SQLException {
        try {
            this.metadata = grpcConnection.remoteStatementService
                    .resultSetMetaData(ServiceMessages.ResultSetMetaDataRequest.newBuilder()
                            .setResultSet(remoteResultSet)
                            .build());

            this.records = grpcConnection.remoteStatementService
                    .resultSetData(ServiceMessages.ResultSetDataRequest.newBuilder()
                            .setResultSet(remoteResultSet)
                            .build());
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean next() throws SQLException {
        boolean hasNext = this.records.hasNext();
        if (hasNext) {
            Data.Record record = this.records.next();
//            metadata = record.getMetadata();
            idxColumns = new HashMap<>();
            nameColumns = new HashMap<>();
            for (Data.Column column : record.getColumnsList()) {
                idxColumns.put(column.getIndex(), column);
                nameColumns.put(column.getName().toUpperCase(), column);
            }
        }

        return hasNext;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public boolean wasNull() throws SQLException {
        return false;
    }

    @Override
    public String getString(int i) throws SQLException {
        return idxColumns.get(i).getStringValue();
    }

    @Override
    public boolean getBoolean(int i) throws SQLException {
        return idxColumns.get(i).getBoolValue();
    }

    @Override
    public byte getByte(int i) throws SQLException {
        return idxColumns.get(i).getByteValue().byteAt(0);
    }

    @Override
    public short getShort(int i) throws SQLException {
        return (short) idxColumns.get(i).getShortValue();
    }

    @Override
    public int getInt(int i) throws SQLException {
        return idxColumns.get(i).getIntValue();
    }

    @Override
    public long getLong(int i) throws SQLException {
        return idxColumns.get(i).getLongValue();
    }

    @Override
    public float getFloat(int i) throws SQLException {
        return idxColumns.get(i).getFloatValue();
    }

    @Override
    public double getDouble(int i) throws SQLException {
        return idxColumns.get(i).getDoubleValue();
    }

    @Override
    public BigDecimal getBigDecimal(int i, int i1) throws SQLException {
        return new BigDecimal(idxColumns.get(i).getBigdecimalValue(), MathContext.DECIMAL64);
    }

    @Override
    public byte[] getBytes(int i) throws SQLException {
        return idxColumns.get(i).getBytesValue().toByteArray();
    }

    @Override
    public Date getDate(int i) throws SQLException {
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd")
                .parseDateTime(idxColumns.get(i).getDateValue());
        return new Date(dt.getMillis());
    }

    @Override
    public Time getTime(int i) throws SQLException {
        DateTime dt = DateTimeFormat.forPattern("HH:mm:ss.SSSSSS")
                .parseDateTime(idxColumns.get(i).getTimeValue());
        return new Time(dt.getMillis());
    }

    @Override
    public Timestamp getTimestamp(int i) throws SQLException {
        return new Timestamp(new DateTime(idxColumns.get(i).getTimestampValue()).getMillis());
    }

    @Override
    public InputStream getAsciiStream(int i) throws SQLException {
        return null;
    }

    @Override
    public InputStream getUnicodeStream(int i) throws SQLException {
        return null;
    }

    @Override
    public InputStream getBinaryStream(int i) throws SQLException {
        return null;
    }

    @Override
    public String getString(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getStringValue();
    }

    @Override
    public boolean getBoolean(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getBoolValue();
    }

    @Override
    public byte getByte(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getByteValue().byteAt(0);
    }

    @Override
    public short getShort(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return (short) nameColumns.get(s.toUpperCase()).getShortValue();
    }

    @Override
    public int getInt(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getIntValue();
    }

    @Override
    public long getLong(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getLongValue();
    }

    @Override
    public float getFloat(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getFloatValue();
    }

    @Override
    public double getDouble(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getDoubleValue();
    }

    @Override
    public BigDecimal getBigDecimal(String s, int i) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return new BigDecimal(nameColumns.get(s.toUpperCase()).getBigdecimalValue(), MathContext.DECIMAL64);
    }

    @Override
    public byte[] getBytes(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return nameColumns.get(s.toUpperCase()).getBytesValue().toByteArray();
    }

    @Override
    public Date getDate(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd")
                .parseDateTime(nameColumns.get(s.toUpperCase()).getDateValue());
        return new Date(dt.getMillis());
    }

    @Override
    public Time getTime(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        DateTime dt = DateTimeFormat.forPattern("HH:mm:ss.SSSSSS")
                .parseDateTime(nameColumns.get(s.toUpperCase()).getTimeValue());
        return new Time(dt.getMillis());
    }

    @Override
    public Timestamp getTimestamp(String s) throws SQLException {
        if (! nameColumns.containsKey(s.toUpperCase())) throw new SQLException("No column having name " + s);
        return new Timestamp(new DateTime(nameColumns.get(s.toUpperCase()).getTimestampValue()).getMillis());
    }

    @Override
    public InputStream getAsciiStream(String s) throws SQLException {
        return null;
    }

    @Override
    public InputStream getUnicodeStream(String s) throws SQLException {
        return null;
    }

    @Override
    public InputStream getBinaryStream(String s) throws SQLException {
        return null;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public String getCursorName() throws SQLException {
        return null;
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        RowSetMetaDataImpl md = new RowSetMetaDataImpl();
        md.setColumnCount(metadata.getColumnCount());
        for (Data.ColumnMetadata col : metadata.getColumnMetadatasList()) {
            md.setColumnName(col.getIndex(), col.getName());
            md.setColumnTypeName(col.getIndex(), col.getTypeName());
            md.setColumnType(col.getIndex(), col.getType());
//            md.setcolum(col.getIndex(), col.getName());
            md.setColumnDisplaySize(col.getIndex(), col.getDisplaySize());
            md.setColumnLabel(col.getIndex(), col.getLabel());
        }

        return md;
    }

    @Override
    public Object getObject(int i) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(String s) throws SQLException {
        return null;
    }

    @Override
    public int findColumn(String s) throws SQLException {
        return 0;
    }

    @Override
    public Reader getCharacterStream(int i) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(String s) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(int i) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return false;
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return false;
    }

    @Override
    public boolean isFirst() throws SQLException {
        return false;
    }

    @Override
    public boolean isLast() throws SQLException {
        return false;
    }

    @Override
    public void beforeFirst() throws SQLException {

    }

    @Override
    public void afterLast() throws SQLException {

    }

    @Override
    public boolean first() throws SQLException {
        return false;
    }

    @Override
    public boolean last() throws SQLException {
        return false;
    }

    @Override
    public int getRow() throws SQLException {
        return 0;
    }

    @Override
    public boolean absolute(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean relative(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean previous() throws SQLException {
        return false;
    }

    @Override
    public void setFetchDirection(int i) throws SQLException {

    }

    @Override
    public int getFetchDirection() throws SQLException {
        return 0;
    }

    @Override
    public void setFetchSize(int i) throws SQLException {

    }

    @Override
    public int getFetchSize() throws SQLException {
        return 0;
    }

    @Override
    public int getType() throws SQLException {
        return 0;
    }

    @Override
    public int getConcurrency() throws SQLException {
        return 0;
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        return false;
    }

    @Override
    public boolean rowInserted() throws SQLException {
        return false;
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        return false;
    }

    @Override
    public void updateNull(int i) throws SQLException {

    }

    @Override
    public void updateBoolean(int i, boolean b) throws SQLException {

    }

    @Override
    public void updateByte(int i, byte b) throws SQLException {

    }

    @Override
    public void updateShort(int i, short i1) throws SQLException {

    }

    @Override
    public void updateInt(int i, int i1) throws SQLException {

    }

    @Override
    public void updateLong(int i, long l) throws SQLException {

    }

    @Override
    public void updateFloat(int i, float v) throws SQLException {

    }

    @Override
    public void updateDouble(int i, double v) throws SQLException {

    }

    @Override
    public void updateBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {

    }

    @Override
    public void updateString(int i, String s) throws SQLException {

    }

    @Override
    public void updateBytes(int i, byte[] bytes) throws SQLException {

    }

    @Override
    public void updateDate(int i, Date date) throws SQLException {

    }

    @Override
    public void updateTime(int i, Time time) throws SQLException {

    }

    @Override
    public void updateTimestamp(int i, Timestamp timestamp) throws SQLException {

    }

    @Override
    public void updateAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void updateBinaryStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void updateCharacterStream(int i, Reader reader, int i1) throws SQLException {

    }

    @Override
    public void updateObject(int i, Object o, int i1) throws SQLException {

    }

    @Override
    public void updateObject(int i, Object o) throws SQLException {

    }

    @Override
    public void updateNull(String s) throws SQLException {

    }

    @Override
    public void updateBoolean(String s, boolean b) throws SQLException {

    }

    @Override
    public void updateByte(String s, byte b) throws SQLException {

    }

    @Override
    public void updateShort(String s, short i) throws SQLException {

    }

    @Override
    public void updateInt(String s, int i) throws SQLException {

    }

    @Override
    public void updateLong(String s, long l) throws SQLException {

    }

    @Override
    public void updateFloat(String s, float v) throws SQLException {

    }

    @Override
    public void updateDouble(String s, double v) throws SQLException {

    }

    @Override
    public void updateBigDecimal(String s, BigDecimal bigDecimal) throws SQLException {

    }

    @Override
    public void updateString(String s, String s1) throws SQLException {

    }

    @Override
    public void updateBytes(String s, byte[] bytes) throws SQLException {

    }

    @Override
    public void updateDate(String s, Date date) throws SQLException {

    }

    @Override
    public void updateTime(String s, Time time) throws SQLException {

    }

    @Override
    public void updateTimestamp(String s, Timestamp timestamp) throws SQLException {

    }

    @Override
    public void updateAsciiStream(String s, InputStream inputStream, int i) throws SQLException {

    }

    @Override
    public void updateBinaryStream(String s, InputStream inputStream, int i) throws SQLException {

    }

    @Override
    public void updateCharacterStream(String s, Reader reader, int i) throws SQLException {

    }

    @Override
    public void updateObject(String s, Object o, int i) throws SQLException {

    }

    @Override
    public void updateObject(String s, Object o) throws SQLException {

    }

    @Override
    public void insertRow() throws SQLException {

    }

    @Override
    public void updateRow() throws SQLException {

    }

    @Override
    public void deleteRow() throws SQLException {

    }

    @Override
    public void refreshRow() throws SQLException {

    }

    @Override
    public void cancelRowUpdates() throws SQLException {

    }

    @Override
    public void moveToInsertRow() throws SQLException {

    }

    @Override
    public void moveToCurrentRow() throws SQLException {

    }

    @Override
    public Statement getStatement() throws SQLException {
        return null;
    }

    @Override
    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(int i) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(int i) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(int i) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(int i) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(String s, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(String s) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(String s) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(String s) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(String s) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(int i, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(String s, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(int i, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(String s, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int i, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String s, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public URL getURL(int i) throws SQLException {
        return null;
    }

    @Override
    public URL getURL(String s) throws SQLException {
        return null;
    }

    @Override
    public void updateRef(int i, Ref ref) throws SQLException {

    }

    @Override
    public void updateRef(String s, Ref ref) throws SQLException {

    }

    @Override
    public void updateBlob(int i, Blob blob) throws SQLException {

    }

    @Override
    public void updateBlob(String s, Blob blob) throws SQLException {

    }

    @Override
    public void updateClob(int i, Clob clob) throws SQLException {

    }

    @Override
    public void updateClob(String s, Clob clob) throws SQLException {

    }

    @Override
    public void updateArray(int i, Array array) throws SQLException {

    }

    @Override
    public void updateArray(String s, Array array) throws SQLException {

    }

    @Override
    public RowId getRowId(int i) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(String s) throws SQLException {
        return null;
    }

    @Override
    public void updateRowId(int i, RowId rowId) throws SQLException {

    }

    @Override
    public void updateRowId(String s, RowId rowId) throws SQLException {

    }

    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public void updateNString(int i, String s) throws SQLException {

    }

    @Override
    public void updateNString(String s, String s1) throws SQLException {

    }

    @Override
    public void updateNClob(int i, NClob nClob) throws SQLException {

    }

    @Override
    public void updateNClob(String s, NClob nClob) throws SQLException {

    }

    @Override
    public NClob getNClob(int i) throws SQLException {
        return null;
    }

    @Override
    public NClob getNClob(String s) throws SQLException {
        return null;
    }

    @Override
    public SQLXML getSQLXML(int i) throws SQLException {
        return null;
    }

    @Override
    public SQLXML getSQLXML(String s) throws SQLException {
        return null;
    }

    @Override
    public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {

    }

    @Override
    public void updateSQLXML(String s, SQLXML sqlxml) throws SQLException {

    }

    @Override
    public String getNString(int i) throws SQLException {
        return null;
    }

    @Override
    public String getNString(String s) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(int i) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(String s) throws SQLException {
        return null;
    }

    @Override
    public void updateNCharacterStream(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateNCharacterStream(String s, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void updateBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void updateCharacterStream(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateAsciiStream(String s, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void updateBinaryStream(String s, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void updateCharacterStream(String s, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateBlob(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void updateBlob(String s, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void updateClob(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateClob(String s, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateNClob(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateNClob(String s, Reader reader, long l) throws SQLException {

    }

    @Override
    public void updateNCharacterStream(int i, Reader reader) throws SQLException {

    }

    @Override
    public void updateNCharacterStream(String s, Reader reader) throws SQLException {

    }

    @Override
    public void updateAsciiStream(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void updateBinaryStream(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void updateCharacterStream(int i, Reader reader) throws SQLException {

    }

    @Override
    public void updateAsciiStream(String s, InputStream inputStream) throws SQLException {

    }

    @Override
    public void updateBinaryStream(String s, InputStream inputStream) throws SQLException {

    }

    @Override
    public void updateCharacterStream(String s, Reader reader) throws SQLException {

    }

    @Override
    public void updateBlob(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void updateBlob(String s, InputStream inputStream) throws SQLException {

    }

    @Override
    public void updateClob(int i, Reader reader) throws SQLException {

    }

    @Override
    public void updateClob(String s, Reader reader) throws SQLException {

    }

    @Override
    public void updateNClob(int i, Reader reader) throws SQLException {

    }

    @Override
    public void updateNClob(String s, Reader reader) throws SQLException {

    }

    @Override
    public <T> T getObject(int i, Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public <T> T getObject(String s, Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
