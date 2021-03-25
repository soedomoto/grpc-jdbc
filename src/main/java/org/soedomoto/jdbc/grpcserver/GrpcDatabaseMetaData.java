package org.soedomoto.jdbc.grpcserver;

import io.grpc.Status;
import org.soedomoto.jdbc.grpc.database_metadata.ServiceMessages;
import org.soedomoto.jdbc.grpc.statement.Data;

import java.sql.*;
import java.util.Arrays;

public class GrpcDatabaseMetaData implements DatabaseMetaData {
    private final GrpcConnection grpcConnection;

    public GrpcDatabaseMetaData(GrpcConnection grpcConnection) {
        this.grpcConnection = grpcConnection;
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .allProceduresAreCallable(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .allTablesAreSelectable(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getURL() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getURL(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getUserName() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getUserName(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .isReadOnly(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .nullsAreSortedHigh(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .nullsAreSortedLow(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .nullsAreSortedAtStart(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .nullsAreSortedAtEnd(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getDatabaseProductName(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getDatabaseProductVersion(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getDriverName() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getDriverName(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getDriverVersion() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getDriverVersion(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getDriverMajorVersion() {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getDriverMajorVersion(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int getDriverMinorVersion() {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getDriverMinorVersion(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .usesLocalFiles(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .usesLocalFilePerTable(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsMixedCaseIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .storesUpperCaseIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .storesLowerCaseIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .storesMixedCaseIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsMixedCaseQuotedIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .storesUpperCaseQuotedIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .storesLowerCaseQuotedIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .storesMixedCaseQuotedIdentifiers(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getIdentifierQuoteString(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getSQLKeywords() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getSQLKeywords(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getNumericFunctions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getNumericFunctions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getStringFunctions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getStringFunctions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getSystemFunctions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getSystemFunctions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getTimeDateFunctions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getTimeDateFunctions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getSearchStringEscape() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getSearchStringEscape(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getExtraNameCharacters() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getExtraNameCharacters(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsAlterTableWithAddColumn(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsAlterTableWithDropColumn(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsColumnAliasing(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .nullPlusNonNullIsNull(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsConvert() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsConvert(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsConvert(int i, int i1) throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsConvertFromTo(ServiceMessages.SupportsConvertRequest.newBuilder()
                            .setFromType(i)
                            .setToType(i1)
                            .build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsTableCorrelationNames(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsDifferentTableCorrelationNames(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsExpressionsInOrderBy(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsOrderByUnrelated(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsGroupBy() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsGroupBy(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsGroupByUnrelated(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsGroupByBeyondSelect(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsLikeEscapeClause(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsMultipleResultSets(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsMultipleTransactions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsNonNullableColumns(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsMinimumSQLGrammar(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCoreSQLGrammar(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsExtendedSQLGrammar(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsANSI92EntryLevelSQL(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsANSI92IntermediateSQL(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsANSI92FullSQL(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsIntegrityEnhancementFacility(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsOuterJoins(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsFullOuterJoins(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsLimitedOuterJoins(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getSchemaTerm() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getSchemaTerm(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getProcedureTerm() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getProcedureTerm(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getCatalogTerm() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getCatalogTerm(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .isCatalogAtStart(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public String getCatalogSeparator() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.String result = grpcConnection.remoteDatabaseMetadataService
                    .getCatalogSeparator(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSchemasInDataManipulation(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSchemasInProcedureCalls(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSchemasInTableDefinitions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSchemasInIndexDefinitions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSchemasInPrivilegeDefinitions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCatalogsInDataManipulation(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCatalogsInProcedureCalls(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCatalogsInTableDefinitions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCatalogsInIndexDefinitions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCatalogsInPrivilegeDefinitions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsPositionedDelete(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsPositionedUpdate(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSelectForUpdate(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsStoredProcedures(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSubqueriesInComparisons(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSubqueriesInExists(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSubqueriesInIns(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsSubqueriesInQuantifieds(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsCorrelatedSubqueries(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsUnion() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsUnion(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsUnionAll() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsUnionAll(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsOpenCursorsAcrossCommit(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsOpenCursorsAcrossRollback(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsOpenStatementsAcrossCommit(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsOpenStatementsAcrossRollback(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxBinaryLiteralLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxCharLiteralLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxColumnNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxColumnsInGroupBy(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxColumnsInIndex(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxColumnsInOrderBy(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxColumnsInSelect(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxColumnsInTable(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxConnections() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxConnections(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxCursorNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxIndexLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxIndexLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxSchemaNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxProcedureNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxCatalogNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxRowSize() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxRowSize(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .doesMaxRowSizeIncludeBlobs(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxStatementLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxStatementLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxStatements() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxStatements(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxTableNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxTableNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxTablesInSelect(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getMaxUserNameLength() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getMaxUserNameLength(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Integer result = grpcConnection.remoteDatabaseMetadataService
                    .getDefaultTransactionIsolation(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsTransactions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsTransactions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int i) throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsTransactionIsolationLevel(ServiceMessages.supportsTransactionIsolationLevelRequest.newBuilder()
                            .setLevel(i)
                            .build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsDataDefinitionAndDataManipulationTransactions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .supportsDataManipulationTransactionsOnly(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .dataDefinitionCausesTransactionCommit(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        try {
            org.soedomoto.jdbc.grpc.database_metadata.Data.Boolean result = grpcConnection.remoteDatabaseMetadataService
                    .dataDefinitionIgnoredInTransactions(org.soedomoto.jdbc.grpc.database_metadata.Data.Empty.newBuilder().build());
            return result.getValue();
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getProcedures(String s, String s1, String s2) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getProcedures(ServiceMessages.GetProceduresRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchemaPattern(s1)
                            .setProcedureNamePattern(s2)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getProcedureColumns(String s, String s1, String s2, String s3) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getProcedureColumns(ServiceMessages.GetProcedureColumnsRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchemaPattern(s1)
                            .setProcedureNamePattern(s2)
                            .setColumnNamePattern(s3)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getTables(String s, String s1, String s2, String[] strings) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getTables(ServiceMessages.GetTablesRequest.newBuilder()
                            .setConnection(grpcConnection.remoteConnection)
                            .setCatalog(s)
                            .setSchemaPattern(s1)
                            .setTableNamePattern(s2)
                            .addAllTypes(Arrays.asList(strings))
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getSchemas(ServiceMessages.GetMetadataRequest.newBuilder()
                            .setConnection(grpcConnection.remoteConnection)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getCatalogs(ServiceMessages.GetMetadataRequest.newBuilder()
                            .setConnection(grpcConnection.remoteConnection)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getTableTypes(ServiceMessages.GetMetadataRequest.newBuilder()
                            .setConnection(grpcConnection.remoteConnection)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getColumns(String s, String s1, String s2, String s3) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getColumns(ServiceMessages.GetColumnsRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchemaPattern(s1)
                            .setTableNamePattern(s2)
                            .setColumnNamePattern(s3)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getColumnPrivileges(String s, String s1, String s2, String s3) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getColumnPrivileges(ServiceMessages.GetColumnPrivilegesRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .setColumnNamePattern(s3)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getTablePrivileges(String s, String s1, String s2) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getTablePrivileges(ServiceMessages.GetTablePrivilegesRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchemaPattern(s1)
                            .setTableNamePattern(s2)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getBestRowIdentifier(String s, String s1, String s2, int i, boolean b) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getBestRowIdentifier(ServiceMessages.GetBestRowIdentifierRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .setScope(i)
                            .setNullable(b)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getVersionColumns(String s, String s1, String s2) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getVersionColumns(ServiceMessages.GetMetadataColumnsRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getPrimaryKeys(String s, String s1, String s2) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getPrimaryKeys(ServiceMessages.GetMetadataColumnsRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getImportedKeys(String s, String s1, String s2) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getImportedKeys(ServiceMessages.GetMetadataColumnsRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getExportedKeys(String s, String s1, String s2) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getExportedKeys(ServiceMessages.GetMetadataColumnsRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getCrossReference(String s, String s1, String s2, String s3, String s4, String s5) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getCrossReference(ServiceMessages.GetCrossReferenceRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setParentCatalog(s)
                            .setParentSchema(s1)
                            .setParentTable(s2)
                            .setForeignCatalog(s3)
                            .setForeignSchema(s4)
                            .setForeignTable(s5)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getTypeInfo(ServiceMessages.GetMetadataRequest.newBuilder()
                            .setConnection(grpcConnection.remoteConnection)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public ResultSet getIndexInfo(String s, String s1, String s2, boolean b, boolean b1) throws SQLException {
        try {
            Data.ResultSet remoteResultSet = grpcConnection.remoteDatabaseMetadataService
                    .getIndexInfo(ServiceMessages.GetIndexInfoRequest.newBuilder()
                            .setParent(ServiceMessages.GetMetadataRequest.newBuilder()
                                    .setConnection(grpcConnection.remoteConnection)
                                    .build())
                            .setCatalog(s)
                            .setSchema(s1)
                            .setTable(s2)
                            .setUnique(b)
                            .setApproximate(b1)
                            .build());
            return new GrpcResultSet(grpcConnection, remoteResultSet);
        } catch (Exception ex) {
            Status status = Status.fromThrowable(ex);
            throw new SQLException(status.getDescription());
        }
    }

    @Override
    public boolean supportsResultSetType(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsResultSetConcurrency(int i, int i1) throws SQLException {
        return false;
    }

    @Override
    public boolean ownUpdatesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean ownDeletesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean ownInsertsAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean othersUpdatesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean othersDeletesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean othersInsertsAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean updatesAreDetected(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean deletesAreDetected(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean insertsAreDetected(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getUDTs(String s, String s1, String s2, int[] ints) throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGetGeneratedKeys() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getSuperTypes(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSuperTables(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAttributes(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsResultSetHoldability(int i) throws SQLException {
        return false;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getSQLStateType() throws SQLException {
        return 0;
    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {
        return false;
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSchemas(String s, String s1) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        return false;
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFunctions(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFunctionColumns(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPseudoColumns(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        return false;
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
