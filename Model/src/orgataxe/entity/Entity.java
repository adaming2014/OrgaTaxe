package orgataxe.entity;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public abstract class Entity {
    protected int id;

    protected Entity() {
    }

    protected Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        orgataxe.entity.Entity entity = (orgataxe.entity.Entity) o;

        if (id != entity.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
