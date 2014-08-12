package orgataxe.event;

import java.util.EventListener;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public interface UpdateEventListener extends EventListener {
    public void update(UpdateEvent event);
}
