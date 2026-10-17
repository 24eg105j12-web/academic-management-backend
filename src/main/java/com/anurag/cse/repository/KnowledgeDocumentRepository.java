package com.anurag.cse.repository;

import com.anurag.cse.entity.KnowledgeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface KnowledgeDocumentRepository extends JpaRepository<KnowledgeDocument, Long> {
    List<KnowledgeDocument> findByCategory(String category);

    @Query("SELECT k FROM KnowledgeDocument k WHERE " +
           "LOWER(k.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(k.tags) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<KnowledgeDocument> searchByKeyword(@Param("query") String query);
}
