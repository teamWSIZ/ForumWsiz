package Controller;

import Forum.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ForumRestController implements InitializingBean {
    ChannelRepo chRepo;
    PostRepo pRepo;
    ForumService service;
    private final String version = "0.1.0";

    @Override
    public void afterPropertiesSet() throws Exception {
        chRepo = new ChannelRepo();
        pRepo = new PostRepo();
        service = new ForumService(pRepo, chRepo);
        service.addChannel("wall", "");
    }

    @RequestMapping(value = {"/"})
    @ResponseBody
    public Rest greeting() {
        Rest r = new Rest();
        r.setResult("Forum WSIZ API version " + version);
        return r;
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
        service.delChannel(name, pass);
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


    @RequestMapping(value = {"/channel/join"})
    @ResponseBody
    public Rest joinChannel(@RequestParam(value = "name") String name,
                           @RequestParam(value = "password") String pass) {
        if (service.checkPassword(name, pass)) return new Rest();
        else return new Rest("Nieprawidłowa autoryzacja");
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
        r.setResult(service.listPosts(name, afterId));
        return r;
    }





}
