package com.springbootProjects.newsportal.controller;
import com.springbootProjects.newsportal.model.Author;
import com.springbootProjects.newsportal.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping("")
    public Collection<Author> getAuthors(){
    return authorRepo.findAll();
    }

    @GetMapping("/{author_id}")
    public Optional<Author> getAuthorById( @PathVariable Long author_id){
        return authorRepo.findById(author_id);
    }

    @PostMapping("/add")
    public Author addAuthor(@RequestBody Author author){
        return authorRepo.save(author);
    }

    @PutMapping("/editAuthor/{author_id}")
    public HttpStatus editAuthor(@PathVariable Long author_id, @RequestBody Author author){
        Optional<Author> existingAuthor = authorRepo.findById(author_id);
        if(existingAuthor.isPresent()){
            Author newAuthor = existingAuthor.get();
            newAuthor.setName(author.getName());
            authorRepo.save(newAuthor);
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }

}
