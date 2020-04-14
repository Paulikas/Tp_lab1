package lab_1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
@Getter @Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "FULL_NAME")
    private String fullName;

    @ManyToOne
    @JoinColumn(name="HOTEL_ID")
    private Hotel hotel;

}