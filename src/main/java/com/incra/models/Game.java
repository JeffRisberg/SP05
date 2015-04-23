package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.List;

/**
 * Stores game info
 */
@Entity
@Table(name = "game")
public class Game extends AbstractDatedDatabaseItem {

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "download_price")
    private Double downloadPrice;

    @OneToMany(mappedBy = "game")
    private List<Episode> episodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getDownloadPrice() {
        return downloadPrice;
    }

    public void setDownloadPrice(Double downloadPrice) {
        this.downloadPrice = downloadPrice;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game site = (Game) o;

        if (!name.equals(site.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}