package com.springbootProjects.newsportal.controller;

import com.springbootProjects.newsportal.model.Author;
import com.springbootProjects.newsportal.model.News;
import com.springbootProjects.newsportal.repository.AuthorRepo;
import com.springbootProjects.newsportal.repository.NewsRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

 @Autowired
    private NewsRepo newsRepo;

 @Autowired
 private AuthorRepo authorRepo;

    @GetMapping("")
    public Collection<News> getAllNews(){
        return newsRepo.findAll();
    };
    @GetMapping("/{id}")
    public Optional<News> getNewsById(@PathVariable Long id){
        return newsRepo.findById(id);
    }
    @GetMapping("/author/{author_id}")
    public ResponseEntity<List<News>> getNewsByAuthor(@PathVariable Long author_id){

        Optional<Author> existingAuthor = authorRepo.findById(author_id);

        if(existingAuthor.isPresent()){
            Author author = existingAuthor.get();
            List<News> newsList = newsRepo.findByAuthor(author);
            return ResponseEntity.ok(newsList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public News addNews(@RequestBody News news){
        System.out.println(news);
        return newsRepo.save(news);

    }
    @PutMapping("/edit/{id}")
    public ResponseEntity editNews(@PathVariable Long id , @RequestBody News newNews){
        Optional<News> exists = newsRepo.findById(id);
        if(exists.isPresent()){
            News news = exists.get();
            news.setTitle(newNews.getTitle());
            news.setContent(newNews.getContent());
            News savedNews = newsRepo.save(news);
            return ResponseEntity.ok(savedNews);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id){
        newsRepo.deleteById(id);
    }
}
