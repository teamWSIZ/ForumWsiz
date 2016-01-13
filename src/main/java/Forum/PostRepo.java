package Forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pm on 1/13/16.
 */

public class PostRepo {
    Map<Integer,Post> data = new HashMap<>();
    AtomicInteger idSequence = new AtomicInteger();

    public Post findOne(Integer postid) {
        return data.get(postid);
    }

    //Zapisuje do data i zwraca z poprawnym ID
    public Post save(Integer channelid, String content) {
        Post p = new Post();
        p.setChannelid(channelid);
        p.setContent(content);
        p.setPostid(idSequence.incrementAndGet());
        data.put(p.getPostid(), p);
        return p;
    }

    public List<Post> findByChannelId(Integer channelid) {
        List<Post> res = new ArrayList<>();
        for(Post p : data.values()) {
            if (p.getChannelid().equals(channelid)) res.add(p);
        }
        return res;
    }

    //sprawdzanie haseł: w service
    public void remove(Integer postid) {
        data.remove(postid);
    }

    public void removeByChannelid(Integer channelid) {
        List<Integer> removedKeys = new ArrayList<>();
        for(Integer id : data.keySet()) {
            if (data.get(id).getChannelid().equals(channelid)) removedKeys.add(id);
        }
        for(Integer id : removedKeys) data.remove(id);
    }

    public long count() {
        return data.size();
    }

}
