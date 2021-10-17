package com.mac.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long>{

    //SpringDataJpa 의 기본 메소드만으로 해결가능하나 가독성을 위해 사용. 상황에따라 선택해서 사용하자.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
