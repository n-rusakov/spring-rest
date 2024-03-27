package com.example.springrest.service.impl;

import com.example.springrest.entity.User;
import com.example.springrest.exception.EntityNotFoundException;
import com.example.springrest.repository.UserRepository;
import com.example.springrest.service.CRUDService;
import com.example.springrest.utils.BeanUtils;
import com.example.springrest.web.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements CRUDService<User> {
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь с id {0} не найден", id)));
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());

        BeanUtils.copyNonNullProperties(user, existedUser);

        return userRepository.save(existedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllFiltered(UserFilter userFilter) {
        return userRepository.findAll(PageRequest.of(
                userFilter.getPageNumber(), userFilter.getPageSize()))
                .getContent();
    }
}
