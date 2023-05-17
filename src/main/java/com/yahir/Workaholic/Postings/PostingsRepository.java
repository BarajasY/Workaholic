package com.yahir.Workaholic.Postings;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yahir.Workaholic.Users.User;

public interface PostingsRepository extends JpaRepository<Postings, Integer> {
    
    Postings findPostingsById(Number id);

    List<Postings> findAllPostingsByUser(User user);
}
