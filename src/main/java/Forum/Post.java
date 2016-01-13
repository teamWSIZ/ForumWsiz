package Forum;

import lombok.Data;

/**
 * Created by pm on 1/13/16.
 */

@Data
public class Post implements Comparable<Post> {
    private Integer channelid;
    private Integer postid;
    private String content;

    public Post() {}

    public Post(Integer channelid, String content) {
        this.channelid = channelid;
        this.content = content;
    }

    @Override
    public int compareTo(Post o) {
        return postid.compareTo(o.postid);
    }
}
