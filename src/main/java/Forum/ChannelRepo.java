package Forum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pm on 1/13/16.
 */
public class ChannelRepo {
    Map<Integer, Channel> data = new HashMap<>();
    AtomicInteger id = new AtomicInteger();


    public boolean exists(Integer channelid) {
        return data.get(channelid)!=null;
    }

    public Channel save(Channel ch) {
        if (ch.getCid()==null) {
            ch.setCid(id.incrementAndGet());
            data.put(ch.getCid(), ch);
        } else {
            data.put(ch.getCid(), ch);
        }
        return ch;

    }

    public void remove(Integer channelid) {
        data.remove(channelid);
    }

    public Channel findByName(String name){
        for(Channel ch : data.values()) {
            if (ch.getName().equals(name)) return ch;
        }
        return null;
    }

    public Channel findOne(Integer channelID){
        return data.get(channelID);
    }

    public Collection<Channel> findAll() {
        return data.values();
    }

}
