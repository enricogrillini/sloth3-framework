package it.eg.sloth.spring.context.repository;

import it.eg.sloth.core.db.FilteredQuery;
import it.eg.sloth.core.db.Query;
import it.eg.sloth.spring.context.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_DOCUMENT_INSERT = "INSERT INTO DOCUMENT (Iddocument, Name, DocumentDate, Cost, Flagactive) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_DOCUMENT = "Select * From Document Where idDocument = ? Order By 1";

    private static final String SQL_DOCUMENTS = "Select *\n" +
            "From Document\n" +
            "/*W*/\n" +
            "Order By 1";

    public List<Document> selectFilteredQuery(Integer idDocument) {
        return new FilteredQuery(jdbcTemplate, SQL_DOCUMENTS)
                .addBaseFilter("idDocument = ?", idDocument)
                .selectTable(Document.class);
    }

    public List<Document> selectQuery(Integer idDocument) {
        return new Query(jdbcTemplate, SQL_DOCUMENT)
                .addParameter(idDocument)
                .selectTable(Document.class);
    }

    @Transactional
    public void insertError(Integer idDocument, boolean error) {
        insert(idDocument);
        if (error) {
            throw new RuntimeException();
        }
    }

    @Transactional
    private void insert(Integer idDocument) {
        Document document = new Document();
        document.setIdDocument(idDocument);
        document.setName("Prova");
        document.setDocumentDate(LocalDateTime.now());
        document.setCost(123.45);
        document.setFlagActive("S");

        Integer result = jdbcTemplate.update(SQL_DOCUMENT_INSERT, document.getIdDocument(), document.getName(), document.getDocumentDate(), document.getCost(), document.getFlagActive());
    }
}
