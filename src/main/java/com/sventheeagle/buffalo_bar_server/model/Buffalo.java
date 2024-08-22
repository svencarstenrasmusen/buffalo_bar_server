package com.sventheeagle.buffalo_bar_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buffalo")
public class Buffalo {
    @Id
    @UuidGenerator
    private String id;

    @OneToOne
    @JoinColumn(name = "scalper", nullable = false)
    private Player scalper;

    @OneToOne
    @JoinColumn(name = "snaggee", nullable = false)
    private Player snaggee;

    @Column(updatable = false)
    private LocalDateTime dateTime;

    private double latitude;

    private double longitude;

    @PrePersist
    protected void onCreate() {
        this.dateTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getScalper() {
        return scalper;
    }

    public void setScalper(Player scalper) {
        this.scalper = scalper;
    }

    public Player getSnaggee() {
        return snaggee;
    }

    public void setSnaggee(Player snaggee) {
        this.snaggee = snaggee;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
