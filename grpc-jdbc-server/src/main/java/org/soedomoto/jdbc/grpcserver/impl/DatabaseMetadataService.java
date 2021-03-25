package org.soedomoto.jdbc.grpcserver.impl;

import com.google.common.collect.Lists;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.soedomoto.jdbc.grpc.database_metadata.DatabaseMetadataServiceGrpc;
import org.soedomoto.jdbc.grpc.database_metadata.ServiceMessages;
import org.soedomoto.jdbc.grpc.statement.Data;
import org.soedomoto.jdbc.grpcserver.JdbcClient;
import org.soedomoto.jdbc.grpcserver.JdbcClientCollection;
import org.soedomoto.jdbc.grpcserver.ResultSetCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.util.Arrays;

@GRpcService
public class DatabaseMetadataService extends DatabaseMetadataServiceGrpc.DatabaseMetadataServiceImplBase {
    @Autowired
    JdbcClientCollection jdbcClientCollection;
    @Autowired
    ResultSetCollection resultSetCollection;

    @Override
    public void getCatalogs(ServiceMessages.GetCatalogsRequest request, StreamObserver<Data.ResultSet> responseObserver) {
        try {
            String id = JdbcClient.UUIDtime().toString();

            JdbcClient jdbcClient = jdbcClientCollection.get(request.getConnection().getConnectionName());
            ResultSet rs = jdbcClient.connection.getMetaData().getCatalogs();

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
    public void getSchemas(ServiceMessages.GetSchemasRequest request, StreamObserver<Data.ResultSet> responseObserver) {
        try {
            String id = JdbcClient.UUIDtime().toString();

            JdbcClient jdbcClient = jdbcClientCollection.get(request.getConnection().getConnectionName());
            ResultSet rs = jdbcClient.connection.getMetaData().getSchemas();

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
    public void getTables(ServiceMessages.GetTablesRequest request, StreamObserver<Data.ResultSet> responseObserver) {
        try {
            String id = JdbcClient.UUIDtime().toString();

            JdbcClient jdbcClient = jdbcClientCollection.get(request.getConnection().getConnectionName());
            ResultSet rs = jdbcClient.connection.getMetaData().getTables(request.getCatalog(),
                    request.getSchemaPattern(), request.getTableNamePattern(),
                    request.getTypesList().toArray(new String[0]));

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
}
