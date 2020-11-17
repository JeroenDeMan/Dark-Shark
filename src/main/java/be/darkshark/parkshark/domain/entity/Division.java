package be.darkshark.parkshark.domain.entity;

import be.darkshark.parkshark.domain.entity.person.Employee;

import javax.persistence.*;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @SequenceGenerator(name = "division_seq", sequenceName = "division_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @ManyToOne(optional = false)
//    @JoinColumn(name = "id")
    private Employee director;

    @ManyToOne(fetch = FetchType.LAZY)
    private Division parentDivision;

    public Division(String name, String originalName, Employee director, Division parentDivision) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parentDivision = parentDivision;
    }

    public Division() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Employee getDirector() {
        return this.director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public Division getParentDivision() {
        return parentDivision;
    }

    public void setParentDivision(Division parentDivision) {
        this.parentDivision = parentDivision;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                '}';
    }

}
