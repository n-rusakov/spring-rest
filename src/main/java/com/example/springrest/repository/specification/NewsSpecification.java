package com.example.springrest.repository.specification;

import com.example.springrest.entity.News;
import com.example.springrest.web.filter.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(byCategoryId(newsFilter.getCategoryId()))
                .and(byUserId(newsFilter.getUserId()))
                .and(byCreatedBefore(newsFilter.getCreatedBefore()));
    }

    static Specification<News> byCategoryId(Long id) {
        return (root, query, cb) -> {
            if (id == null) {
                return null;
            }
            return cb.equal(root.get("category").get("id"), id);
        };
    }

    static Specification<News> byUserId(Long id) {
        return (root, query, cb) -> {
            if (id == null) {
                return null;
            }
            return cb.equal(root.get("user").get("id"), id);
        };
    }

    static Specification<News> byCreatedBefore(Instant createdBefore) {
        return (root, query, cb) -> {
            if (createdBefore == null){
                return null;
            }

            return cb.lessThanOrEqualTo(root.get("createdAt"), createdBefore);
        };
    }

}
