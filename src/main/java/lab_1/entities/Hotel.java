package lab_1.entities;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Named;
import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Hotel.findAll", query = "select h from Hotel as h")
})
@Table(name = "HOTEL")
@Getter @Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "hotel",
    cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany(mappedBy = "hotels")
    private List<Guest> guests = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
