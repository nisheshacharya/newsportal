package com.springbootProjects.newsportal.repository;

import com.springbootProjects.newsportal.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

}
