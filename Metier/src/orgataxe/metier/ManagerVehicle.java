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
    private static final int HEAVY_WEIGHT = 3500;
    private static final int EUROPEAN_HEAVY_WEIGHT = 3000;

    private IDAOVehicle daoVehicle;

    public ManagerVehicle(IDAOVehicle dao) {
        super(dao);

        this.daoVehicle = dao;
    }

    private Type getVehicleType(Vehicle vehicle) {
        int currentYear = GregorianCalendar.getInstance().get(Calendar.YEAR);

        return getVehicleType(vehicle, currentYear);
    }

    private Type getVehicleType(Vehicle vehicle, int year) {
        if (year >= 2020 && vehicle.getModel().getWeight() >= EUROPEAN_HEAVY_WEIGHT) {
            return Type.HEAVY;
        } else if (vehicle.getModel().getWeight() > HEAVY_WEIGHT) {
            return Type.HEAVY;
        } else {
            return Type.LIGHT;
        }
    }

    public int getTaxeAt(Vehicle vehicle) {
        int currentYear = GregorianCalendar.getInstance().get(Calendar.YEAR);

        return getTaxeAt(vehicle, currentYear);
    }

    public int getTaxeAt(Vehicle vehicle, int year) {
        Type vehicleType = getVehicleType(vehicle, year);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(vehicle.getStartDate());

        int startYear = calendar.get(Calendar.YEAR);

        switch (vehicleType) {
            case LIGHT:
                if (startYear > year) {
                    return 0;
                } else if (startYear + 5 <= year) {
                    return 150;
                } else {
                    return 100;
                }
            case HEAVY:
                if (startYear > year) {
                    return 0;
                } else if (startYear + 10 <= year) {
                    return 800;
                } else {
                    return 500;
                }
        }

        return 0;
    }

    public int getTaxeTotal(Vehicle vehicle) {
        return 0;
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = super.getAll();
        for (Vehicle vehicle : vehicles) {
            vehicle.setType(getVehicleType(vehicle));
        }

        return vehicles;
    }

    @Override
    public Vehicle getById(int id) {
        Vehicle vehicle = super.getById(id);
        vehicle.setType(getVehicleType(vehicle));

        return vehicle;
    }

    @Override
    public Vehicle create(Vehicle entity) {
        Vehicle vehicle = super.create(entity);
        vehicle.setType(getVehicleType(vehicle));

        return vehicle;
    }

    @Override
    public Vehicle update(Vehicle entity) {
        Vehicle vehicle = super.update(entity);
        vehicle.setType(getVehicleType(vehicle));

        return vehicle;
    }
}
