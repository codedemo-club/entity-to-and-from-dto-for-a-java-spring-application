package club.codedemo.demo.repository;

import club.codedemo.demo.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
}
