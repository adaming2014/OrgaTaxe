package orgataxe.metier;

import orgataxe.database.IDAOGeneric;
import orgataxe.entity.Entity;

import java.util.List;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public class ManagerEntity<T extends Entity> implements IManagerGeneric<T> {
    protected IDAOGeneric<T> dao;

    public ManagerEntity(IDAOGeneric<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public T getById(int id) {
        return dao.getById(id);
    }

    @Override
    public T create(T entity) {
        return dao.create(entity);
    }

    @Override
    public T update(T entity) {
        return dao.update(entity);
    }

    @Override
    public boolean delete(T entity) {
        return dao.delete(entity);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }
}
