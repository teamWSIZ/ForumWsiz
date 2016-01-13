package Offline;

import Forum.Channel;
import Forum.ChannelRepo;
import Forum.ForumService;
import Forum.PostRepo;

/**
 * Created by pm on 1/13/16.
 */
public class StartTest1 {
    public static void main(String[] args) {
        ChannelRepo chRepo = new ChannelRepo();
        PostRepo pRepo = new PostRepo();
        ForumService service = new ForumService(pRepo, chRepo);

        chRepo.save("Wall", "XXX");
        Channel ch = chRepo.findByName("Wall");
        System.out.println(chRepo.exists(ch.getChannelid()));

        Integer id = ch.getChannelid();
        pRepo.save(id, "Abra kadabra");
        pRepo.save(id, "Abra kadabra");
        pRepo.save(id, "Abra kadabra");
        pRepo.save(id, "Abra kadabra");

        System.out.println(pRepo.count());

        chRepo.save("Xiao", "11");    //2?
        for (int i = 0; i < 6; i++) {
            service.savePost(2, "Wuu"  + i);
        }


        System.out.println(pRepo.findByChannelId(2));
        System.out.println(pRepo.count());
        service.removeChannel(2,"xx");
        System.out.println(pRepo.count());
        service.removePost(2,"11",1);
        System.out.println(pRepo.count());
        service.removeChannel(2,"11");
        System.out.println(pRepo.count());

    }
}
