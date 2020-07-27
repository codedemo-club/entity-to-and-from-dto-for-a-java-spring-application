package club.codedemo.demo.controller;

import club.codedemo.demo.dto.PostDto;
import club.codedemo.demo.entity.Post;
import club.codedemo.demo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostRestController {

    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public PostDto save(@RequestBody PostDto postDto) throws ParseException {
        Post post = this.convertToEntity(postDto);
        return this.convertToDto(this.postService.save(post));
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable Long id) {
        return this.convertToDto(this.postService.findById(id));
    }

    @GetMapping
    public List<PostDto> getAll() {
        return this.postService.findAll()
                               .stream().map(this::convertToDto)
                               .collect(Collectors.toList());
    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setSubmissionDate(post.getSubmissionDate(), "GMT+8:00");
        return postDto;
    }

    private Post convertToEntity(PostDto postDto) throws ParseException {
        Post post = modelMapper.map(postDto, Post.class);
        post.setSubmissionDate(postDto.getSubmissionDateConverted(
                "GMT+8:00"));

        return post;
    }

}
