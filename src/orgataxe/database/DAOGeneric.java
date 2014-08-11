package orgataxe.database;

import com.sun.istack.internal.Nullable;
import orgataxe.entity.Entity;

import java.util.List;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public abstract class DAOGeneric<T extends Entity> implements IDAOGeneric<T> {
    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public
    @Nullable
    T getById(int id) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public
    @Nullable
    T create(T entity) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public
    @Nullable
    T update(T entity) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public boolean delete(T entity) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
