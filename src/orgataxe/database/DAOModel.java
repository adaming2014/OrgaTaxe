package orgataxe.database;

import com.sun.istack.internal.Nullable;
import orgataxe.connection.GlobalConnection;
import orgataxe.connection.NoConnectionException;
import orgataxe.entity.Model;

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
public class DAOModel extends DAOGeneric<Model> implements IDAOModel {
    private static final String TABLE_NAME = "model";
    private static final String FIELD_ID = "id";
    private static final String FIELD_WEIGHT = "weight";
    private static final String FIELD_DESIGNATION = "designation";

    private final static String requestGetAll =
            "SELECT *"
                    + " FROM " + TABLE_NAME;

    private final static String requestGetById =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + FIELD_ID + " = ?";

    private final static String requestCreate =
            "INSERT INTO " + TABLE_NAME + " (" + FIELD_WEIGHT + ", " + FIELD_DESIGNATION + ")"
                    + " VALUES (?, ?)";

    private final static String requestMaxId =
            "SELECT MAX(" + FIELD_ID + ") FROM " + TABLE_NAME;

    private final static String requestUpdate =
            "UPDATE " + TABLE_NAME
                    + " SET " + FIELD_WEIGHT + " = ?, " + FIELD_DESIGNATION + " = ?"
                    + " WHERE " + FIELD_ID + " = ?";

    private final static String requestDelete =
            "DELETE FROM " + TABLE_NAME
                    + " WHERE " + FIELD_ID + " = ?";

    @Override
    public List<Model> getAll() {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        List<Model> models = new ArrayList<>();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestGetAll);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                models.add(new Model(Integer.valueOf(results.getInt(FIELD_ID)), Integer.valueOf(results.getString(FIELD_WEIGHT)), results.getString(FIELD_DESIGNATION)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return models;
    }

    @Override
    public
    @Nullable
    Model getById(int id) {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        Model model = null;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestGetById);
            statement.setInt(1, id);

            ResultSet results = statement.executeQuery();

            if (results.next()) {
                model = new Model(results.getInt(FIELD_ID), results.getInt(FIELD_WEIGHT), results.getString(FIELD_DESIGNATION));
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return model;
    }

    @Override
    public
    @Nullable
    Model create(Model entity) {
        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestCreate);
            statement.setInt(1, entity.getWeight());
            statement.setString(2, entity.getDesignation());

            int results = statement.executeUpdate();
            if (results != 1) {
                return null;
            }

            statement = connection.prepareStatement(requestMaxId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return entity;
    }

    @Override
    public
    @Nullable
    Model update(Model entity) {
        if (entity.getId() == 0) {
            throw new InvalidParameterException("Can't update an entity that have no id value");
        }

        Connection connection = GlobalConnection.getConnection();
        if (connection == null) {
            throw new NoConnectionException();
        }

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(requestUpdate);
            statement.setInt(1, entity.getWeight());
            statement.setString(2, entity.getDesignation());
            statement.setInt(3, entity.getId());

            int results = statement.executeUpdate();
            if (results != 1) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return entity;
    }

    @Override
    public boolean delete(Model entity) {
        if (entity.getId() == 0) {
            throw new InvalidParameterException("Can't update an entity that have no id value");
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
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return true;
    }
}
