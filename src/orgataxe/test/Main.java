package orgataxe.test;

import orgataxe.database.DAOModel;
import orgataxe.database.DAOOwner;
import orgataxe.database.DAOVehicle;
import orgataxe.entity.Model;
import orgataxe.entity.Owner;
import orgataxe.entity.Type;
import orgataxe.entity.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public class Main {
    public static void main(String args[]) {
        DAOOwner daoOwner = new DAOOwner();
        DAOVehicle daoVehicle = new DAOVehicle();
        DAOModel daoModel = new DAOModel();

        Owner owner1 = new Owner("antoine", "lucas", "1 rue de gigant", null);

        System.out.println("Owner creation test :");
        Owner ownerResult = daoOwner.create(owner1);
        if (ownerResult == null) {
            System.out.println("- Owner creation failed.");
        } else {
            System.out.println("- Good. Id = " + ownerResult.getId());
        }

        System.out.println("Retrieving all Owners test :");
        List<Owner> owners = daoOwner.getAll();

        System.out.println("- display results :");
        for (Owner owner : owners) {
            System.out.println("    - Owner(id = " + owner.getId() + ", firstName = " + owner.getFirstName() + ", surname = " + owner.getFamilyName() + ", address = " + owner.getAddress());
        }

        System.out.println("Retrieving fist Owner test :");
        Owner ownerResult2 = daoOwner.getById(1);
        if (ownerResult2 == null) {
            System.out.println("- Not found.");
        } else {
            System.out.println("- Good. Id = " + ownerResult2.getId());
        }

//        System.out.println("Deleting Owner test :");
//        System.out.println("- Delete owner " + ownerResult.getId());
//        System.out.println("    - " + daoOwner.delete(ownerResult.getId()));

        System.out.println("Retrieving fist Model test :");
        Model modelResult1 = daoModel.getById(1);
        if (modelResult1 == null) {
            System.out.println("- Not found.");
        } else {
            System.out.println("- Good. Id = " + modelResult1.getDesignation());
        }

        System.out.println("Retrieving all Vehicles test :");
        List<Vehicle> vehicles = daoVehicle.getAll();

        System.out.println("- display results :");
        for (Vehicle vehicle : vehicles) {
            System.out.println("    - Vehicle(id = " + vehicle.getId() + ", licence = " + vehicle.getLicencePlate() + ", startDate = " + vehicle.getStartDate() + ", owner = " + vehicle.getOwner().getFirstName() + ", model = " + vehicle.getModel().getDesignation());
        }

        Vehicle vehicle1 = new Vehicle("00PPP75", new Date(), ownerResult, Type.LIGHT, modelResult1);

        System.out.println("Vehicle creation test :");
        Vehicle vehicleResult = daoVehicle.create(vehicle1);
        if (vehicleResult == null) {
            System.out.println("- Vehicle creation failed.");
        } else {
            System.out.println("- Good. Id = " + vehicleResult.getId());
        }

        System.out.println("End of execution.");
    }
}
