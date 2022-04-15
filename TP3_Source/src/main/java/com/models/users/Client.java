package com.models.users;
import com.models.Emprunt;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clientNumber;
    private String clientName;
    private String clientAdress;
    private String clientPhone;
    @ToString.Exclude
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;
}
