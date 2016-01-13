package Forum;

import java.util.*;

/**
 * Created by pm on 1/13/16.
 */

public class ForumService {
    PostRepo pRepo;
    ChannelRepo chRepo;

    public ForumService(PostRepo pRepo, ChannelRepo chRepo) {
        this.pRepo = pRepo;
        this.chRepo = chRepo;
    }

    public boolean checkPassword(Integer channelid, String pass) {
        if (!chRepo.exists(channelid)) return false;
        return chRepo.findOne(channelid).getPass().equals(pass);
    }

    public boolean checkPassword(String channelname, String pass) {
        Channel ch = chRepo.findByName(channelname);
        if (ch==null) return false;
        return checkPassword(ch.getCid(), pass);
    }
    ////////// POSTS

    //not password protected as of now
    public Post addPost(String  channelName, String content) {
        Channel ch = chRepo.findByName(channelName);
        if (ch==null) return null;  //todo: controler could signal an error here
        Integer cid = ch.getCid();
        if (!chRepo.exists(cid)) return null;
        Post p = new Post();
        p.setChannelid(cid);
        p.setContent(content);
        pRepo.save(p);
        return p;
    }

    public void removePost(Integer channelid, String password, Integer postid ) {
        if (!checkPassword(channelid, password)) return;
        pRepo.remove(postid);
    }

    public List<Post> listPostsAfter(String channelname, Integer afterId) {
        Channel ch = chRepo.findByName(channelname);
        List<Post> all = pRepo.findByChannelId(ch.getCid());
        List<Post> r = new ArrayList<>();
        for(Post p : all) if (p.getPostid() > afterId) r.add(p);
        Collections.sort(r);
        return r;
    }

    ////////// CHANNLES

    public Channel addChannel(String name, String pass) {
        if (chRepo.findByName(name)!=null) return null;
        Channel nCh = new Channel();
        nCh.setName(name);
        nCh.setPass(pass);
        chRepo.save(nCh);
        return chRepo.save(nCh);
    }

    public void removeChannel(Integer channelid, String pass) {
        if (!checkPassword(channelid,pass)) return;
        pRepo.removeByChannelid(channelid);
        chRepo.remove(channelid);
    }

    public void removeChannelByName(String name, String pass) {
        Channel ch = chRepo.findByName(name);
        if (ch==null) return;
        Integer id = ch.getCid();
        if (!checkPassword(id,pass)) return;
        pRepo.removeByChannelid(id);
        chRepo.remove(id);
    }

    //debug method
    public Iterable<String> getChannelNames(){
        Set<String> names = new TreeSet<>();
        for(Channel ch : chRepo.findAll()) names.add(ch.getName());
        return names;
    }



}
