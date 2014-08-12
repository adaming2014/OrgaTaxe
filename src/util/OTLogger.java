package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by INTI0221 on 12/08/2014.
 */
public class OTLogger {
    public static Logger getLogger() {
        return LogManager.getLogger("OrgaTaxe.DAO");
    }

    public static void logError(String message) {
        getLogger().log(Level.ERROR, message);
    }
}
