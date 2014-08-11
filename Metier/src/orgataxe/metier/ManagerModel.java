package orgataxe.metier;

import orgataxe.database.IDAOModel;
import orgataxe.entity.Model;

/**
 * Created by INTI0221 on 07/08/2014.
 */
public class ManagerModel extends ManagerEntity<Model> implements IManagerModel {
    private IDAOModel daoModel;

    public ManagerModel(IDAOModel dao) {
        super(dao);

        this.daoModel = dao;
    }
}
