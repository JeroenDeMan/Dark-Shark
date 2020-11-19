package be.darkshark.parkshark.domain.entity;

import be.darkshark.parkshark.domain.entity.parkinglot.ParkingLot;
import be.darkshark.parkshark.domain.entity.person.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Allocation {
    @Id
    @SequenceGenerator(name = "allocation_seq", sequenceName = "allocation_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
    @Column(name = "id")
    private long id;
    @Column (name = "licence_plate")
    private String licencePlate;
    @OneToOne
    private Member member;
    @ManyToOne
    private ParkingLot parkingLot;

    @Column (name = "start_time")
    private LocalDateTime startTime;

    @Column (name = "end_time")
    private LocalDateTime endTime;

    @Enumerated
    private AllocationStatus status;

    public Allocation() {
        startTime = LocalDateTime.now();
        status = AllocationStatus.ACTIVE;
    }

    public long getId() {
        return id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public Member getMember() {
        return member;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime () {this.endTime = LocalDateTime.now();
    this.status = AllocationStatus.STOPPED;}

    public void setMember(Member member) {
        this.member = member;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public AllocationStatus getStatus() {
        return status;
    }

    public Allocation(String licencePlate) {
        this.licencePlate = licencePlate;
        startTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", member=" + member +
                ", parkingLot=" + parkingLot +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
