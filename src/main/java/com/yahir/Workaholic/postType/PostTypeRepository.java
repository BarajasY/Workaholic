package com.yahir.Workaholic.postType;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yahir.Workaholic.Postings.Postings;

public interface PostTypeRepository extends JpaRepository<PostType, Integer> {
    
}
