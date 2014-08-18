package orgataxe.metier;

import orgataxe.entity.Type;
import orgataxe.entity.Vehicle;
import orgataxe.metier.type.IManagerType;
import orgataxe.metier.type.ManagerTypeEuropean;
import orgataxe.metier.type.ManagerTypeOld;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by INTI0221 on 18/08/2014.
 */
public class ManagerTaxe implements IManagerTaxe {
    private static final int EUROPEAN_UPDATE_YEAR = 2020;

    private IManagerType managerTypeOld;
    private IManagerType managerTypeEuropean;

    public ManagerTaxe() {
        this.managerTypeOld = new ManagerTypeOld();
        this.managerTypeEuropean = new ManagerTypeEuropean();
    }

    @Override
    public int getTaxe(Vehicle vehicle) {
        int currentYear = GregorianCalendar.getInstance().get(Calendar.YEAR);

        return getTaxeAt(vehicle, currentYear);
    }

    @Override
    public int getTaxeAt(Vehicle vehicle, int year) {
        Type vehicleType = getManagerTypeAt(year).getVehicleType(vehicle);

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

    @Override
    public int getTaxeTotal(Vehicle vehicle) {
        return 0;
    }

    private IManagerType getManagerTypeAt(int year) {
        if (year >= EUROPEAN_UPDATE_YEAR) {
            return this.managerTypeEuropean;
        }

        return this.managerTypeOld;
    }
}
