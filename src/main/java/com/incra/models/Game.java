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

    @Basic
    private String name;

    @Column(name = "custom_css")
    private String customCSS;

    @OneToMany(mappedBy = "game")
    private List<Episode> episodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomCSS() {
        return customCSS;
    }

    public void setCustomCSS(String customCSS) {
        this.customCSS = customCSS;
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