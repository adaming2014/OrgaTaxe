package orgataxe.database;

import com.sun.istack.internal.Nullable;
import orgataxe.connection.GlobalConnection;
import orgataxe.connection.NoConnectionException;
import orgataxe.entity.Owner;
import util.OTLogger;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public class DAOOwner extends DAOGeneric<Owner> implements IDAOOwner {
    private static final String TABLE_NAME = "owner";
    private final static String requestGetAll =
            "SELECT *"
                    + " FROM " + TABLE_NAME;
    private static final String FIELD_ID = "id";
    private final static String requestGetById =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + FIELD_ID + " = ?";
    private final static String requestMaxId =
            "SELECT MAX(" + FIELD_ID + ") FROM " + TABLE_NAME;
    private final static String requestDelete =
            "DELETE FROM " + TABLE_NAME
                    + " WHERE " + FIELD_ID + " = ?";
    private static final String FIELD_FIRSTNAME = "first_name";
    private static final String FIELD_FAMILYNAME = "family_name";
    private static final String FIELD_ADDRESS = "address";
    private final static String requestCreate =
            "INSERT INTO " + TABLE_NAME + " (" + FIELD_FIRSTNAME + ", " + FIELD_FAMILYNAME + ", " + FIELD_ADDRESS + ")"
                    + " VALUES (?, ?, ?)";
    private final static String requestUpdate =
            "UPDATE " + TABLE_NAME
                    + " SET " + FIELD_FIRSTNAME + " = ?, " + FIELD_FAMILYNAME + " = ?, " + FIELD_ADDRESS + " = ?"
                    + " WHERE " + FIELD_ID + " = ?";

    @Override
    public List<Owner> getAll() {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        List<Owner> owners = new ArrayList<>();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestGetAll);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                owners.add(new Owner(results.getInt(FIELD_ID), results.getString(FIELD_FIRSTNAME), results.getString(FIELD_FAMILYNAME), results.getString(FIELD_ADDRESS), null));
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                OTLogger.logError(e.getSQLState());
            }
        }

        return owners;
    }

    @Override
    public
    @Nullable
    Owner getById(int id) {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        Owner owner = null;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestGetById);
            statement.setInt(1, id);

            ResultSet results = statement.executeQuery();

            if (results.next()) {
                owner = new Owner(results.getInt(FIELD_ID), results.getString(FIELD_FIRSTNAME), results.getString(FIELD_FAMILYNAME), results.getString(FIELD_ADDRESS), null);
            } else {
                OTLogger.logError("Query error : " + statement.toString());

                return null;
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                OTLogger.logError(e.getSQLState());
            }
        }

        return owner;
    }

    @Override
    public
    @Nullable
    Owner create(Owner entity) {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestCreate);
            statement.setString(1, entity.getFamilyName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getAddress());

            int results = statement.executeUpdate();
            if (results != 1) {
                OTLogger.logError("Update error : " + statement.toString());

                return null;
            }

            statement = connection.prepareStatement(requestMaxId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            } else {
                OTLogger.logError("Query error : " + statement.toString());

                return null;
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                OTLogger.logError(e.getSQLState());
            }
        }

        return entity;
    }

    @Override
    public
    @Nullable
    Owner update(Owner entity) {
        if (entity.getId() == 0) {
            OTLogger.logError("Can't update an Owner with no Id value");

            throw new InvalidParameterException("Can't update an Owner with no Id value");
        }

        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestUpdate);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getFamilyName());
            statement.setString(3, entity.getAddress());
            statement.setInt(4, entity.getId());

            int results = statement.executeUpdate();
            if (results != 1) {
                OTLogger.logError("Update error : " + statement.toString());

                return null;
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                OTLogger.logError(e.getSQLState());
            }
        }

        return entity;
    }

    @Override
    public boolean delete(Owner entity) {
        if (entity.getId() == 0) {
            OTLogger.logError("Can't delete an Owner with no Id value");

            throw new InvalidParameterException("Can't delete an Owner with no Id value");
        }

        return delete(entity.getId());
    }

    @Override
    public boolean delete(int id) {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestDelete);
            statement.setInt(1, id);

            int results = statement.executeUpdate();
            if (results != 1) {
                OTLogger.logError("Update error : " + statement.toString());

                return false;
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                OTLogger.logError(e.getSQLState());
            }
        }

        return true;
    }
}
