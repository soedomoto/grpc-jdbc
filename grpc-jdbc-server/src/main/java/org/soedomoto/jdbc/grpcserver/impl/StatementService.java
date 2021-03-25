package org.soedomoto.jdbc.grpcserver.impl;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.joda.time.DateTime;
import org.lognet.springboot.grpc.GRpcService;
import org.soedomoto.jdbc.grpc.statement.Data;
import org.soedomoto.jdbc.grpc.statement.ServiceMessages;
import org.soedomoto.jdbc.grpc.statement.StatementServiceGrpc;
import org.soedomoto.jdbc.grpcserver.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class StatementService extends StatementServiceGrpc.StatementServiceImplBase {
    @Autowired
    JdbcClientCollection jdbcClientCollection;
    @Autowired
    StatementCollection statementCollection;
    @Autowired
    PreparedStatementCollection preparedStatementCollection;
    @Autowired
    ResultSetCollection resultSetCollection;

    @Override
    public void createStatement(ServiceMessages.CreateStatementRequest request, StreamObserver<Data.Statement> responseObserver) {
        try {
            String id = JdbcClient.UUIDtime().toString();
            JdbcClient jdbcClient = jdbcClientCollection.get(request.getConnection().getConnectionName());

            Statement statement = jdbcClient.connection.createStatement();
            statementCollection.statementList.put(id, statement);

            responseObserver.onNext(Data.Statement.newBuilder()
                    .setConnection(request.getConnection())
                    .setId(id)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void prepareStatement(ServiceMessages.PrepareStatementRequest request, StreamObserver<Data.PreparedStatement> responseObserver) {
        try {
            String id = JdbcClient.UUIDtime().toString();
            JdbcClient jdbcClient = jdbcClientCollection.get(request.getConnection().getConnectionName());

            PreparedStatement statement = jdbcClient.connection.prepareStatement(request.getSql());
            preparedStatementCollection.preparedStatementList.put(id, statement);

            responseObserver.onNext(Data.PreparedStatement.newBuilder()
                    .setId(id)
                    .setConnection(request.getConnection())
                    .build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void executeQuery(ServiceMessages.ExecuteQueryRequest request, StreamObserver<Data.ResultSet> responseObserver) {
        try {
            String id = JdbcClient.UUIDtime().toString();
            ResultSet rs = null;

            if (request.getTypeCase() == ServiceMessages.ExecuteQueryRequest.TypeCase.STATEMENT) {
                Statement statement = statementCollection.statementList.get(request.getStatement().getId());
                rs = statement.executeQuery(request.getSql());
            }

            else if (request.getTypeCase() == ServiceMessages.ExecuteQueryRequest.TypeCase.PREPARED_STATEMENT) {
                PreparedStatement statement = preparedStatementCollection.preparedStatementList.get(request.getPreparedStatement().getId());
                for (Data.Column column : request.getParamsList()) {
                    if (column.getValueCase() == Data.Column.ValueCase.BOOL_VALUE) {
                        statement.setBoolean(column.getIndex(), column.getBoolValue());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.INT_VALUE) {
                        statement.setInt(column.getIndex(), column.getIntValue());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.BYTE_VALUE) {
                        statement.setByte(column.getIndex(), column.getByteValue().byteAt(0));
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.LONG_VALUE) {
                        statement.setLong(column.getIndex(), column.getLongValue());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.BYTES_VALUE) {
                        statement.setBytes(column.getIndex(), column.getBytesValue().toByteArray());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.BIGDECIMAL_VALUE) {
                        statement.setBigDecimal(column.getIndex(), new BigDecimal(column.getBigdecimalValue(), MathContext.DECIMAL64));
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.SHORT_VALUE) {
                        statement.setShort(column.getIndex(), (short) column.getShortValue());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.DOUBLE_VALUE) {
                        statement.setDouble(column.getIndex(), column.getDoubleValue());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.FLOAT_VALUE) {
                        statement.setFloat(column.getIndex(), column.getFloatValue());
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.DATE_VALUE) {
                        statement.setDate(column.getIndex(), new Date(new DateTime(column.getDateValue()).getMillis()));
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.TIME_VALUE) {
                        statement.setTime(column.getIndex(), new Time(new DateTime(column.getTimeValue()).getMillis()));
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.TIMESTAMP_VALUE) {
                        statement.setTimestamp(column.getIndex(), new Timestamp(new DateTime(column.getTimestampValue()).getMillis()));
                    }
                    else if (column.getValueCase() == Data.Column.ValueCase.STRING_VALUE) {
                        statement.setString(column.getIndex(), column.getStringValue());
                    }
                }
                rs = statement.executeQuery();
            }

            resultSetCollection.resultSetList.put(id, rs);
            responseObserver.onNext(Data.ResultSet.newBuilder()
                    .setId(id)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void executeUpdate(ServiceMessages.ExecuteQueryRequest request, StreamObserver<Data.ExecuteUpdateResult> responseObserver) {
        try {
            int rs = 0;

            if (request.getTypeCase() == ServiceMessages.ExecuteQueryRequest.TypeCase.STATEMENT) {
                Statement statement = statementCollection.statementList.get(request.getStatement().getId());
                rs = statement.executeUpdate(request.getSql());
            }

            else if (request.getTypeCase() == ServiceMessages.ExecuteQueryRequest.TypeCase.PREPARED_STATEMENT) {
                PreparedStatement statement = preparedStatementCollection.preparedStatementList.get(request.getPreparedStatement().getId());
                rs = statement.executeUpdate();
            }
            responseObserver.onNext(Data.ExecuteUpdateResult.newBuilder()
                    .setUpdatedRows(rs)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void resultSetMetaData(ServiceMessages.ResultSetMetaDataRequest request, StreamObserver<Data.Metadata> responseObserver) {
        try {
            ResultSet rs = resultSetCollection.resultSetList.get(request.getResultSet().getId());
            ResultSetMetaData rsmd = rs.getMetaData();

            List<Data.ColumnMetadata> columnMetadata = new ArrayList<>();
            for (int c=1; c<= rsmd.getColumnCount(); c++) {
                columnMetadata.add(Data.ColumnMetadata.newBuilder()
                        .setIndex(c)
                        .setName(rsmd.getColumnName(c))
                        .setTypeName(rsmd.getColumnTypeName(c))
                        .setType(rsmd.getColumnType(c))
                        .setClassName(rsmd.getColumnClassName(c))
                        .setDisplaySize(rsmd.getColumnDisplaySize(c))
                        .setLabel(rsmd.getColumnLabel(c))
                        .build());
            }

            responseObserver.onNext(Data.Metadata.newBuilder()
                    .setColumnCount(rsmd.getColumnCount())
                    .addAllColumnMetadatas(columnMetadata)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void resultSetData(ServiceMessages.ResultSetDataRequest request, StreamObserver<Data.Record> responseObserver) {
        try {
            ResultSet rs = resultSetCollection.resultSetList.get(request.getResultSet().getId());
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                Data.Record.Builder pbR = Data.Record.newBuilder();
                for (int c = 1; c <= rsmd.getColumnCount(); c++) {
                    String name = rsmd.getColumnName(c);
                    String className = rsmd.getColumnClassName(c);

                    Data.Column.Builder column = Data.Column.newBuilder().setIndex(c).setName(name);

                    if (className.equalsIgnoreCase(Boolean.class.getName())) {
                        column.setBoolValue(rs.getBoolean(c));
                    } else if (className.equalsIgnoreCase(Byte.class.getName())) {
                        column.setByteValue(ByteString.copyFrom(new byte[]{rs.getByte(c)}));
                    } else if (className.equalsIgnoreCase(Long.class.getName())) {
                        column.setLongValue(rs.getLong(c));
                    } else if (className.equalsIgnoreCase("byte[]")) {
                        column.setBytesValue(ByteString.copyFrom(rs.getBytes(c)));
                    } else if (className.equalsIgnoreCase(BigDecimal.class.getName())) {
                        column.setBigdecimalValue(rs.getBigDecimal(c).doubleValue());
                    } else if (className.equalsIgnoreCase(Integer.class.getName())) {
                        column.setIntValue(rs.getInt(c));
                    } else if (className.equalsIgnoreCase(Short.class.getName())) {
                        column.setShortValue(rs.getShort(c));
                    } else if (className.equalsIgnoreCase(Double.class.getName())) {
                        column.setDoubleValue(rs.getDouble(c));
                    } else if (className.equalsIgnoreCase(Float.class.getName())) {
                        column.setFloatValue(rs.getFloat(c));
                    } else if (className.equalsIgnoreCase(Date.class.getName())) {
                        column.setDateValue(new DateTime(rs.getDate(c)).toString("yyyy-MM-dd"));
                    } else if (className.equalsIgnoreCase(Time.class.getName())) {
                        column.setTimeValue(new DateTime(rs.getTime(c)).toString("HH:mm:ss.SSSSSS"));
                    } else if (className.equalsIgnoreCase(Timestamp.class.getName())) {
                        column.setTimestampValue(new DateTime(rs.getTimestamp(c).getTime()).toString());
                    } else if (className.equalsIgnoreCase(Blob.class.getName())) {

                    } else if (className.equalsIgnoreCase(Clob.class.getName())) {

                    } else {
                        column.setStringValue(rs.getString(c));
                    }

                    pbR.addColumns(column.build());
                }

                responseObserver.onNext(pbR.build());
            }

            responseObserver.onCompleted();
        } catch (Exception ex) {
            Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }
}
