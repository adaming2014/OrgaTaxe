package orgataxe.database;

import com.sun.istack.internal.Nullable;
import orgataxe.connection.GlobalConnection;
import orgataxe.entity.Model;
import orgataxe.entity.Owner;
import orgataxe.entity.Vehicle;
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
public class DAOVehicle extends DAOGeneric<Vehicle> implements IDAOVehicle {
    private static final String TABLE_NAME = "vehicle";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ID_OWNER = "id_owner";
    private static final String FIELD_ID_MODEL = "id_model";
    private static final String FIELD_LICENCE = "licence";
    private static final String FIELD_START_DATE = "start_date";

    private static final String TABLE_OWNER = "owner";
    private static final String FIELD_OWNER_ID = "id";
    private static final String FIELD_OWNER_FIRSTNAME = "first_name";
    private static final String FIELD_OWNER_FAMILYNAME = "family_name";
    private static final String FIELD_OWNER_ADDRESS = "address";

    private static final String TABLE_MODEL = "model";
    private static final String FIELD_MODEL_ID = "id";
    private static final String FIELD_MODEL_WEIGHT = "weight";
    private static final String FIELD_MODEL_DESIGNATION = "designation";

    private final static String requestGetAll =
            "SELECT *"
                    + " FROM " + TABLE_NAME + ", " + TABLE_OWNER + ", " + TABLE_MODEL
                    + " WHERE " + FIELD_ID_OWNER + " = " + TABLE_OWNER + "." + FIELD_OWNER_ID
                    + " AND " + FIELD_ID_MODEL + " = " + TABLE_MODEL + "." + FIELD_MODEL_ID;

    private final static String requestGetById =
            "SELECT *"
                    + " FROM " + TABLE_NAME + ", " + TABLE_OWNER + ", " + TABLE_MODEL
                    + " WHERE " + FIELD_ID_OWNER + " = " + TABLE_OWNER + "." + FIELD_OWNER_ID
                    + " AND " + FIELD_ID_MODEL + " = " + TABLE_MODEL + "." + FIELD_MODEL_ID
                    + " AND " + FIELD_ID + " = ?";

    private final static String requestCreate =
            "INSERT INTO " + TABLE_NAME + " (" + FIELD_ID_OWNER + ", " + FIELD_ID_MODEL + ", " + FIELD_LICENCE + ", " + FIELD_START_DATE + ")"
                    + " VALUES (?, ?, ?, ?)";

    private final static String requestMaxId =
            "SELECT MAX(" + FIELD_ID + ") FROM " + TABLE_NAME;

    private final static String requestUpdate =
            "UPDATE " + TABLE_NAME
                    + " SET " + FIELD_ID_OWNER + " = ?, " + FIELD_ID_MODEL + " = ?, " + FIELD_LICENCE + " = ?, " + FIELD_START_DATE + " = ?"
                    + " WHERE " + FIELD_ID + " = ?";

    private final static String requestDelete =
            "DELETE FROM " + TABLE_NAME
                    + " WHERE " + FIELD_ID + " = ?";

    @Override
    public
    @Nullable
    List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = GlobalConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(requestGetAll);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                Owner owner = new Owner(Integer.valueOf(results.getInt(FIELD_OWNER_ID)), results.getString(FIELD_OWNER_FIRSTNAME), results.getString(FIELD_OWNER_FAMILYNAME), results.getString(FIELD_OWNER_ADDRESS), null);
                Model model = new Model(Integer.valueOf(results.getInt(FIELD_MODEL_ID)), results.getInt(FIELD_MODEL_WEIGHT), results.getString(FIELD_MODEL_DESIGNATION));
                vehicles.add(new Vehicle(Integer.valueOf(results.getInt(FIELD_ID)), results.getString(FIELD_LICENCE), results.getDate(FIELD_START_DATE), owner, model));
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public
    @Nullable
    Vehicle getById(int id) {
        Vehicle vehicle = null;

        try (Connection connection = GlobalConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(requestGetById)) {

            statement.setInt(1, id);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    Owner owner = new Owner(Integer.valueOf(results.getInt(FIELD_OWNER_ID)), results.getString(FIELD_OWNER_FIRSTNAME), results.getString(FIELD_OWNER_FAMILYNAME), results.getString(FIELD_OWNER_ADDRESS), null);
                    Model model = new Model(Integer.valueOf(results.getInt(FIELD_MODEL_ID)), results.getInt(FIELD_MODEL_WEIGHT), results.getString(FIELD_MODEL_DESIGNATION));
                    vehicle = new Vehicle(Integer.valueOf(results.getInt(FIELD_ID)), results.getString(FIELD_LICENCE), results.getDate(FIELD_START_DATE), owner, model);
                } else {
                    OTLogger.logError("Query error : " + statement.toString());

                    return null;
                }
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return null;
        }

        return vehicle;
    }

    @Override
    public
    @Nullable
    Vehicle create(Vehicle entity) {
        try (Connection connection = GlobalConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(requestCreate)) {

                statement.setInt(1, entity.getOwner().getId());
                statement.setInt(2, entity.getModel().getId());
                statement.setString(3, entity.getLicencePlate());
                statement.setDate(4, new java.sql.Date(entity.getStartDate().getTime()));

                int results = statement.executeUpdate();
                if (results != 1) {
                    OTLogger.logError("Update error : " + statement.toString());

                    return null;
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(requestMaxId);
                 ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    entity.setId(resultSet.getInt(1));
                } else {
                    OTLogger.logError("Query error : " + statement.toString());

                    return null;
                }
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return null;
        }

        return entity;
    }

    @Override
    public
    @Nullable
    Vehicle update(Vehicle entity) {
        if (entity.getId() == 0) {
            OTLogger.logError("Can't update a Vehicle with no Id value");

            throw new InvalidParameterException("Can't update a Vehicle with no Id value");
        }

        try (Connection connection = GlobalConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(requestUpdate)) {

            statement.setInt(1, entity.getOwner().getId());
            statement.setInt(2, entity.getModel().getId());
            statement.setString(3, entity.getLicencePlate());
            statement.setDate(4, new java.sql.Date(entity.getStartDate().getTime()));
            statement.setInt(5, entity.getId());

            int results = statement.executeUpdate();
            if (results != 1) {
                OTLogger.logError("Update error : " + statement.toString());

                return null;
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return null;
        }

        return entity;
    }

    @Override
    public boolean delete(Vehicle entity) {
        if (entity.getId() == 0) {
            OTLogger.logError("Can't delete a Vehicle with no Id value");

            throw new InvalidParameterException("Can't delete a Vehicle with no Id value");
        }

        return delete(entity.getId());
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = GlobalConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(requestDelete)) {

            statement.setInt(1, id);

            int results = statement.executeUpdate();
            if (results != 1) {
                OTLogger.logError("Update error : " + statement.toString());

                return false;
            }
        } catch (SQLException e) {
            OTLogger.logError(e.getSQLState());

            return false;
        }

        return true;
    }
}
