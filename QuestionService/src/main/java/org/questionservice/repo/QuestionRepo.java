package org.questionservice.repo;

import org.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findAllByCategoryIgnoreCase(String category);

    @Query(value = "select * from question q where q.category=:category order by RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQUestionsByCategory(String category, int numQ);
}
