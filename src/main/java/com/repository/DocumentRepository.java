package com.repository;

import com.models.documents.Documents;
import com.models.documents.Livre;
import com.models.documents.Media;
import com.models.enums.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
@Repository
public interface DocumentRepository extends JpaRepository<Documents,Integer> {
    public Optional<List<Documents>> findAllByTitreContaining(String titre);
    public Optional<List<Documents>> findAllByAuteur(String auteur);
    public Optional<List<Documents>> findAllByAnneeDePublication(int anne);
    @Query("select l from Livre l where l.genre = :genre")
    public Optional<List<Documents>> findAllByGenre(Genres genre);

//    @Query("select m from Media m where m.titre = :titre")
//    public Optional<List<Media>> findMediaByTitre(String titre);
//    @Query("select l from Livre l where l.titre = :auteur")
//    public Optional<List<Livre>> findBooksByAuteur(String auteur);
//    @Query("select m from Media m where m.titre = :auteur")
//    public Optional<List<Media>> findMediaByAuteur(String auteur);
}
