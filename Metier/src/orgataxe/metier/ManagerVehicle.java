package orgataxe.metier;

import orgataxe.database.IDAOVehicle;
import orgataxe.entity.Vehicle;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public class ManagerVehicle extends ManagerEntity<Vehicle> implements IManagerVehicle {
    private IDAOVehicle daoVehicle;

    public ManagerVehicle(IDAOVehicle dao) {
        super(dao);

        this.daoVehicle = dao;
    }
}
