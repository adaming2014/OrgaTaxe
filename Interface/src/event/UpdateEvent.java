package event;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class UpdateEvent {
    Object source;

    public UpdateEvent(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }
}
