package Forum;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pm on 1/13/16.
 */
public class ChannelRepo {
    Map<Integer, Channel> data = new HashMap<>();
    AtomicInteger idSequence = new AtomicInteger();


    public boolean exists(Integer channelid) {
        return data.get(channelid)!=null;
    }

    public Channel save(String name, String pass) {
        if (findByName(name)!=null) return null;    //channel już istnieje
        Channel nch = new Channel();
        nch.setName(name);
        nch.setPass(pass);
        nch.setChannelid(idSequence.incrementAndGet());
        data.put(nch.getChannelid(), nch);
        return nch;
    }


    public void remove(Integer channelid) {
        data.remove(channelid);
    }


    public Channel findByName(String name){
        for(Channel ch : data.values()) {
            if (ch.name.equals(name)) return ch;
        }
        return null;
    }

    public Channel findOne(Integer channelID){
        return data.get(channelID);
    }

}
