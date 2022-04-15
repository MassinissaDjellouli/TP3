package com.models.documents;
import com.models.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private int documentId;
    private String titre;
    private String auteur;
    private String editeur;
    private int anneeDePublication;
    private int tempsEmprunt;
    private int nbExemplaires;

    public static int setMediaTempsEmprunts(MediaType type) {
        switch (type){
            case cd -> { return 2;}
            case dvd -> { return 1;}
            default -> {return 1;}
        }
    }
}


