package orgataxe.entity;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public class Model extends Entity {
    private int weight;
    private String designation;

    public Model(int weight, String designation) {
        this(0, weight, designation);
    }

    public Model(int id, int weight, String designation) {
        super(id);

        this.weight = weight;
        this.designation = designation;
    }

    public int getWeight() {
        return weight;
    }

    public String getDesignation() {
        return designation;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (super.equals(o)) {
            return true;
        }

        orgataxe.entity.Model model = (orgataxe.entity.Model) o;

        if (weight != model.weight) {
            return false;
        }
        if (designation != null ? !designation.equals(model.designation) : model.designation != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + weight;
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        return result;
    }
}
