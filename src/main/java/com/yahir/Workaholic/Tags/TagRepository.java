package com.yahir.Workaholic.Tags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    
    Boolean existsByTagName(String tagName);

/*     Void saveTag(String tag_name); */
}
