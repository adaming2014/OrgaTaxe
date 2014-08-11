package orgataxe.metier;

import orgataxe.database.IDAOOwner;
import orgataxe.entity.Owner;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public class ManagerOwner extends ManagerEntity<Owner> implements IManagerOwner {
    private IDAOOwner daoOwner;

    public ManagerOwner(IDAOOwner dao) {
        super(dao);

        this.daoOwner = dao;
    }
}
