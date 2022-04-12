package com.repository;

import com.models.documents.Documents;
import com.models.documents.Livre;
import com.models.documents.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DocumentRepository extends JpaRepository<Documents,Long> {
    @Query("select l from Livre l where l.titre = :titre")
    public Optional<List<Livre>> findBooksByTitre(String titre);
    @Query("select m from Media m where m.titre = :titre")
    public Optional<List<Media>> findMediaByTitre(String titre);
    @Query("select l from Livre l where l.titre = :auteur")
    public Optional<List<Livre>> findBooksByAuteur(String auteur);
    @Query("select m from Media m where m.titre = :auteur")
    public Optional<List<Media>> findMediaByAuteur(String auteur);
}
