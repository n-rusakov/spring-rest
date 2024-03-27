package com.example.springrest.service.impl;

import com.example.springrest.entity.Comment;
import com.example.springrest.exception.EntityNotFoundException;
import com.example.springrest.repository.CommentRepository;
import com.example.springrest.service.CRUDService;
import com.example.springrest.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements CRUDService<Comment> {

    private final CommentRepository commentRepository;

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException(
                        MessageFormat.format("Комментарий с id {0} не найден", id)));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment insert(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());

        BeanUtils.copyNonNullProperties(comment, existedComment);

        return commentRepository.save(existedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
