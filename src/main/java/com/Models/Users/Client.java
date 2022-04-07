package com.Models.Users;
import com.Models.Dette;
import com.Models.Emprunt;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "dette")
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
    @OneToOne(mappedBy = "client",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Dette dette;
    @ToString.Exclude
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;
}
