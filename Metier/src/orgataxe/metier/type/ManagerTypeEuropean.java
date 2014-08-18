package orgataxe.metier.type;

import orgataxe.entity.Type;
import orgataxe.entity.Vehicle;

/**
 * Created by INTI0221 on 18/08/2014.
 */
public class ManagerTypeEuropean implements IManagerType {
    private static final int EUROPEAN_HEAVY_WEIGHT = 3000;

    @Override
    public Type getVehicleType(Vehicle vehicle) {
        if (vehicle.getModel().getWeight() > EUROPEAN_HEAVY_WEIGHT) {
            return Type.HEAVY;
        } else {
            return Type.LIGHT;
        }
    }
}
