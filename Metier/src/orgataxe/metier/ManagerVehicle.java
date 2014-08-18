package orgataxe.metier;

import orgataxe.database.IDAOVehicle;
import orgataxe.entity.Type;
import orgataxe.entity.Vehicle;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public class ManagerVehicle extends ManagerEntity<Vehicle> implements IManagerVehicle {
    public ManagerVehicle(IDAOVehicle dao) {
        super(dao);
    }
}
