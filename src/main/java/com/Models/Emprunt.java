package com.Models;
import com.Models.Documents.Documents;
import com.Models.Users.Client;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Client client;
    private LocalDateTime dateTime;
    @OneToOne
    private Documents document;
    private LocalDateTime returnDateTime;
}