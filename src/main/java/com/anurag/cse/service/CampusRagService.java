package com.anurag.cse.service;

import com.anurag.cse.entity.KnowledgeDocument;
import com.anurag.cse.repository.KnowledgeDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampusRagService {

    private static final Logger log = LoggerFactory.getLogger(CampusRagService.class);

    private final KnowledgeDocumentRepository knowledgeDocumentRepository;

    public CampusRagService(KnowledgeDocumentRepository knowledgeDocumentRepository) {
        this.knowledgeDocumentRepository = knowledgeDocumentRepository;
    }

    /**
     * Retrieve relevant knowledge documents using keyword and semantic token overlap
     */
    public List<KnowledgeDocument> retrieveRelevantDocuments(String query) {
        log.info("Searching campus knowledge base for query: '{}'", query);
        if (query == null || query.isBlank()) {
            return knowledgeDocumentRepository.findAll();
        }

        // Clean and tokenize query
        String[] tokens = query.toLowerCase()
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .split("\\s+");

        Set<KnowledgeDocument> matchedDocs = new HashSet<>();

        // 1. Direct keyword match
        matchedDocs.addAll(knowledgeDocumentRepository.searchByKeyword(query));

        // 2. Tokenized search across documents
        List<KnowledgeDocument> allDocs = knowledgeDocumentRepository.findAll();
        for (KnowledgeDocument doc : allDocs) {
            String combinedText = (doc.getTitle() + " " + doc.getContent() + " " + doc.getTags()).toLowerCase();
            long matchCount = Arrays.stream(tokens)
                    .filter(token -> token.length() > 2 && combinedText.contains(token))
                    .count();

            if (matchCount > 0) {
                matchedDocs.add(doc);
            }
        }

        if (matchedDocs.isEmpty()) {
            // Return top default documents if no specific match
            return allDocs.stream().limit(3).collect(Collectors.toList());
        }

        return new ArrayList<>(matchedDocs);
    }

    /**
     * Build RAG context prompt string
     */
    public String buildRagContext(List<KnowledgeDocument> documents) {
        StringBuilder sb = new StringBuilder();
        sb.append("CAMPUS OFFICIAL KNOWLEDGE BASE & POLICIES:\n");
        sb.append("----------------------------------------\n");

        for (KnowledgeDocument doc : documents) {
            sb.append("[").append(doc.getCategory()).append("] ").append(doc.getTitle()).append(":\n");
            sb.append(doc.getContent()).append("\n\n");
        }
        sb.append("----------------------------------------\n");
        return sb.toString();
    }
}
