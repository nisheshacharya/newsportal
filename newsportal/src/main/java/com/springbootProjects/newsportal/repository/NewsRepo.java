package com.springbootProjects.newsportal.repository;

import com.springbootProjects.newsportal.model.Author;
import com.springbootProjects.newsportal.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NewsRepo extends JpaRepository<News, Long> {
    List<News> findByAuthor(Author author);
}
