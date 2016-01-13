package Forum;

/**
 * Created by pm on 1/13/16.
 */

//Dodatkowe operacje

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

    public Post savePost(Integer channelid, String content) {
        if (!chRepo.exists(channelid)) return null;
        return pRepo.save(channelid, content);
    }

    public void removePost(Integer channelid, String password, Integer postid ) {
        if (!checkPassword(channelid, password)) return;
        pRepo.remove(postid);
    }


    public Channel addChannel(String name, String pass) {
        if (chRepo.findByName(name)!=null) return null;
        return chRepo.save(name, pass);
    }

    public void removeChannel(Integer channelid, String pass) {
        if (!checkPassword(channelid,pass)) return;
        pRepo.removeByChannelid(channelid);
        chRepo.remove(channelid);
    }





}
