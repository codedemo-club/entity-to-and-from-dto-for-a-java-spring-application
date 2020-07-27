package club.codedemo.demo.service;

import club.codedemo.demo.entity.Post;
import club.codedemo.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Override
    public List<Post> findAll() {
        return (List<Post>) this.postRepository.findAll();
    }
}
