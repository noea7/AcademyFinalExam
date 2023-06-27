package lt.techin.kristina.autoapi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Repairman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String specialization;

    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autoservice_id")
    private Autoservice autoservice;
}
