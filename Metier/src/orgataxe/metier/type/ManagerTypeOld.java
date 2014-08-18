package orgataxe.metier.type;

import orgataxe.entity.Type;
import orgataxe.entity.Vehicle;

/**
 * Created by INTI0221 on 18/08/2014.
 */
public class ManagerTypeOld implements IManagerType {
    private static final int HEAVY_WEIGHT = 3500;

    @Override
    public Type getVehicleType(Vehicle vehicle) {
        if (vehicle.getModel().getWeight() > HEAVY_WEIGHT) {
            return Type.HEAVY;
        } else {
            return Type.LIGHT;
        }
    }
}
