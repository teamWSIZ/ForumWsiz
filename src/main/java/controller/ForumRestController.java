package controller;

import java.util.concurrent.atomic.AtomicLong;

import Forum.Channel;
import Forum.ChannelRepo;
import Forum.ForumService;
import Forum.PostRepo;
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
    }

    @RequestMapping(value = {"/channel/add"})
    @ResponseBody
    public Rest addDataset(@RequestParam(value="name") String name,
                           @RequestParam(value = "password") String pass) {
        Channel ch = service.addChannel(name, pass);
        Rest ans = new Rest();
        ans.setResult(ch);
        return ans;
    }






}
