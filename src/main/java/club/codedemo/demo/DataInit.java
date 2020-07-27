package club.codedemo.demo;

import club.codedemo.demo.entity.Post;
import club.codedemo.demo.entity.User;
import club.codedemo.demo.repository.PostRepository;
import club.codedemo.demo.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User user = new User();
        this.userRepository.save(user);

        for(int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setUser(user);
            post.setTitle(RandomString.make(4));
            post.setUrl("http://" + RandomString.make(10));
            post.setSubmissionDate(new Date());
            this.postRepository.save(post);
        }
    }
}
