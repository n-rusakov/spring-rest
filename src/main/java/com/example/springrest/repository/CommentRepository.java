package com.example.springrest.repository;

import com.example.springrest.entity.Category;
import com.example.springrest.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
