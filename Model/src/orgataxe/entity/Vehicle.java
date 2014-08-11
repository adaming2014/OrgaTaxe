package orgataxe.entity;

import java.util.Date;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public class Vehicle extends Entity {
    private String licencePlate;
    private Date startDate;

    private Owner owner;
    private Type type;
    private Model model;

    public Vehicle(String licencePlate, Date startDate, Owner owner, Type type, Model model) {
        this(0, licencePlate, startDate, owner, type, model);
    }

    public Vehicle(int id, String licencePlate, Date startDate, Owner owner, Type type, Model model) {
        super(id);

        this.licencePlate = licencePlate;
        this.startDate = startDate;
        this.owner = owner;
        this.type = type;
        this.model = model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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

        orgataxe.entity.Vehicle vehicle = (orgataxe.entity.Vehicle) o;

        if (licencePlate != null ? !licencePlate.equals(vehicle.licencePlate) : vehicle.licencePlate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (licencePlate != null ? licencePlate.hashCode() : 0);
        return result;
    }
}
