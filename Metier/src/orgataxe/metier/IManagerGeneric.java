package orgataxe.metier;

import com.sun.istack.internal.Nullable;
import orgataxe.entity.Entity;

import java.util.List;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public interface IManagerGeneric<T extends Entity> {
    /**
     * Return all entities.
     */
    List<T> getAll();

    /**
     * Return the entity if the provided id.
     * @param id The id of the entity.
     * @return The entity found. Null if not found.
     */
    @Nullable
    T getById(int id);

    /**
     * Add a new entity to the base.
     * @param entity The entity to add.
     * @return The entity added. Null if the creation failed.
     */
    @Nullable
    T create(T entity);

    /**
     * Update the entity.
     * @param entity The entity to update.
     * @return The entity updated. Null if the update failed.
     */
    @Nullable
    T update(T entity);

    /**
     * Delete the entity from the base.
     * @param entity The entity to delete.
     * @return True if the deletion succeed.
     */
    boolean delete(T entity);

    /**
     * Delete the entity from the base.
     * @param id The id of the The id of the entity.
     * @return True if the deletion succeed.
     */
    boolean delete(int id);
}
