package com.anurag.cse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "knowledge_documents")
public class KnowledgeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 100)
    private String category;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    private String tags;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public KnowledgeDocument() {}

    public KnowledgeDocument(Long id, String title, String category, String content, String tags, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.tags = tags;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static KnowledgeDocumentBuilder builder() {
        return new KnowledgeDocumentBuilder();
    }

    public static class KnowledgeDocumentBuilder {
        private Long id;
        private String title;
        private String category;
        private String content;
        private String tags;
        private LocalDateTime createdAt;

        public KnowledgeDocumentBuilder id(Long id) { this.id = id; return this; }
        public KnowledgeDocumentBuilder title(String title) { this.title = title; return this; }
        public KnowledgeDocumentBuilder category(String category) { this.category = category; return this; }
        public KnowledgeDocumentBuilder content(String content) { this.content = content; return this; }
        public KnowledgeDocumentBuilder tags(String tags) { this.tags = tags; return this; }
        public KnowledgeDocumentBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public KnowledgeDocument build() {
            KnowledgeDocument doc = new KnowledgeDocument();
            doc.setId(this.id);
            doc.setTitle(this.title);
            doc.setCategory(this.category);
            doc.setContent(this.content);
            doc.setTags(this.tags);
            doc.setCreatedAt(this.createdAt);
            return doc;
        }
    }
}
