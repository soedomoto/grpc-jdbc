package org.soedomoto.jdbc.grpcserver.impl;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.soedomoto.jdbc.grpc.connection.ConnectionServiceGrpc;
import org.soedomoto.jdbc.grpc.connection.Data;
import org.soedomoto.jdbc.grpc.connection.ServiceMessages;
import org.soedomoto.jdbc.grpcserver.JdbcClient;
import org.soedomoto.jdbc.grpcserver.JdbcClientCollection;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class ConnectionService extends ConnectionServiceGrpc.ConnectionServiceImplBase {
    @Autowired
    JdbcClientCollection jdbcClientCollection;

    @Override
    public void connect(ServiceMessages.ConnectRequest request, StreamObserver<Data.Connection> responseObserver) {
        try {
            String driverPassword = request.getDriverPassword();
            if (driverPassword.equalsIgnoreCase("null")) driverPassword = "";

            JdbcClient jdbcClient = jdbcClientCollection.connect(request.getConnectionName(), request.getDriverUrl(),
                    request.getDriverClass(), request.getJdbcUrl(), request.getDriverUsername(), driverPassword);

            responseObserver.onNext(Data.Connection.newBuilder()
                    .setConnectionName(jdbcClient.name)
                    .setDriverClass(jdbcClient.driverClass)
                    .setDriverUrl(jdbcClient.driverUrl)
                    .setJdbcUrl(jdbcClient.url)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            io.grpc.Status status = Status.INTERNAL.withDescription(ex.getMessage()).withCause(ex.getCause());
            responseObserver.onError(status.asRuntimeException());
        }
    }
}
