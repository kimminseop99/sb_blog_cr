package com.example.blog.domain.post.service;

import com.example.blog.domain.post.entity.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.global.DataNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getList(){
        return postRepository.findAll();
    }

    public void create(String title, String content){
        Post post = Post.builder()
                .createDate(LocalDateTime.now())
                .title(title)
                .content(content)
                .build();
        postRepository.save(post);
    }

    public Post getPost(Long id){
        Optional<Post> post = this.postRepository.findById(id);
        if(post.isPresent()){
            return post.get();
        }else{
            throw new DataNotFoundException("post not found");
        }
    }

}
