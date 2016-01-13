package Forum;

import lombok.Data;

/**
 * Created by pm on 1/13/16.
 */

@Data
public class Channel {
    private Integer cid;
    private String name;
    private String pass;

    public Channel() {}

    public Channel(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}
