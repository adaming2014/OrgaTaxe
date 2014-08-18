package orgataxe.metier;

import orgataxe.entity.Vehicle;

/**
 * Created by INTI0221 on 18/08/2014.
 */
public interface IManagerTaxe {
    public int getTaxe(Vehicle vehicle);
    public int getTaxeAt(Vehicle vehicle, int year);
    public int getTaxeTotal(Vehicle vehicle);
}
