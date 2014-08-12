package orgataxe.metier;

import orgataxe.entity.Vehicle;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public interface IManagerVehicle extends IManagerGeneric<Vehicle> {
    public int getTaxeAt(Vehicle vehicle);
    public int getTaxeAt(Vehicle vehicle, int year);
    public int getTaxeTotal(Vehicle vehicle);
}
