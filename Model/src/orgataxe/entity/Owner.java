package orgataxe.entity;

import java.util.List;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public class Owner extends Entity {
    private String firstName;
    private String familyName;
    private String address;
    private List<Vehicle> vehicles;

    public Owner(String firstName, String familyName, String address, List<Vehicle> vehicles) {
        this(0, firstName, familyName, address, vehicles);
    }

    public Owner(int id, String firstName, String familyName, String address, List<Vehicle> vehicles) {
        super(id);

        this.firstName = firstName;
        this.familyName = familyName;
        this.address = address;
        this.vehicles = vehicles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

        orgataxe.entity.Owner owner = (orgataxe.entity.Owner) o;

        if (address != null ? !address.equals(owner.address) : owner.address != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(owner.firstName) : owner.firstName != null) {
            return false;
        }
        if (familyName != null ? !familyName.equals(owner.familyName) : owner.familyName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (familyName != null ? familyName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
