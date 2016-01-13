package Controller;

import Forum.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumRestController implements InitializingBean {
    ChannelRepo chRepo;
    PostRepo pRepo;
    ForumService service;

    @Override
    public void afterPropertiesSet() throws Exception {
        chRepo = new ChannelRepo();
        pRepo = new PostRepo();
        service = new ForumService(pRepo, chRepo);
        service.addChannel("wall", "");
    }

    ////////// CHANNELS

    @RequestMapping(value = {"/channel/add"})
    @ResponseBody
    public Rest addChannel(@RequestParam(value = "name") String name,
                           @RequestParam(value = "password") String pass) {
        Channel ch = service.addChannel(name, pass);
        Rest ans = new Rest();
        ans.setResult(ch);
        return ans;
    }

    @RequestMapping(value = {"/channel/delete"})
    @ResponseBody
    public Rest delChannel(@RequestParam(value = "name") String name,
                           @RequestParam(value = "password") String pass) {
        service.removeChannelByName(name, pass);
        return new Rest();
    }

    @RequestMapping(value = {"/channel/list"})
    @ResponseBody
    public Rest listChannels() {
//        return new Rest("Metoda niedostępna w wersji produkcyjnej systemu");
        Rest r = new Rest();
        r.setResult(chRepo.findAll());
        return r;
    }

    ///////// POSTS

    //Zwraca id posta (może się przydać przy usuwaniu)
    @RequestMapping(value = {"/post/add"})
    @ResponseBody
    public Rest addPost(@RequestParam(value = "channelname") String name,
                        @RequestParam(value = "content") String content) {
        Post p = service.addPost(name, content);
        if (p==null) return new Rest("Błąd przy zapisie posta");
        Rest r = new Rest();
        r.setResult(p);
        return r;
    }

    @RequestMapping(value = {"/post/delete"})
    @ResponseBody
    public Rest delPost(@RequestParam(value = "channelname", defaultValue = "wall") String name,
                        @RequestParam(value = "password", defaultValue = "") String pass,
                        @RequestParam(value = "postid") Integer postId){
        if (name.equals("wall")) return new Rest("Z 'wall' nie można kasować");
        if (!service.checkPassword(name, pass)) return new Rest("Nieprawidłowa autoryzacja");
        pRepo.remove(postId);
        return new Rest();
    }

    @RequestMapping(value = {"/post/list"})
    @ResponseBody
    public Rest listPost(@RequestParam(value = "channelname", defaultValue = "wall") String name,
                         @RequestParam(value = "password", defaultValue = "") String pass,
                         @RequestParam(value = "afterid", defaultValue = "-1") Integer afterId){
        if (!service.checkPassword(name, pass)) return new Rest("Nieprawidłowa autoryzacja");
        Rest r = new Rest();
        r.setResult(service.listPostsAfter(name, afterId));
        return r;
    }





}
