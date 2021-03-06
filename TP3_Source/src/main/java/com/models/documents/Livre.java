package com.models.documents;
import com.models.enums.Genres;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Livre")
public class Livre extends Documents{
    private int nbPages;
    private Genres genre;
}
