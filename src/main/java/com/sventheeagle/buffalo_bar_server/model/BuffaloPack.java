package com.sventheeagle.buffalo_bar_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buffalo_pack")
public class BuffaloPack {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "buffalo_id", nullable = false)
    private Buffalo buffalo;

    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

    public Buffalo getBuffalo() {
        return buffalo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBuffalo(Buffalo buffalo) {
        this.buffalo = buffalo;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }
}
