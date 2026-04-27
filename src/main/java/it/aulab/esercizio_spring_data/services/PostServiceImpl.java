package it.aulab.esercizio_spring_data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.models.Post;
import it.aulab.esercizio_spring_data.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> readAll() {
       return postRepository.findAll();
    }

    @Override 
    public Post read(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            return optionalPost.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id: " + id + " not found");
        }
    }
    // Era necessario inserire questa funzione? Ci sta cercare un post tramite id? Probabilmente mi sfugge qualcosa,ma non capisco a che pro farlo.

    @Override
    public List<Post> readByTitle(String title){
        if (title == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return postRepository.findByTitle(title);
        }
    }

    @Override
    public List<Post> readByBody(String body){
        if (body == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return postRepository.findByBody(body);
        }
    }

    @Override
    public List<Post> read(String title, String body) {
        if (title == null || body == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return postRepository.findByTitleAndBody(title, body);
        }
    }


    @Override
    public Post create(Post post) {
        if (post.getTitle() == null || post.getBody() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return postRepository.save(post);
        }
        
    }

    @Override
    public Post update(Long id, Post post) {
        if (postRepository.existsById(id)) {
            post.setId(id);
            return postRepository.save(post);
        }else{
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            List<Comment> postComments = post.getComments();
            for (Comment comment : postComments) {
                comment.setPost(null);
            }
            postRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
