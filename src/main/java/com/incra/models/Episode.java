package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Episode in a game
 *
 * @author Jeff Risberg
 * @since February 2014
 */
@Entity
@Table(name = "episode")
public class Episode extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Basic
    private String title;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "seq_num")
    private Integer seqNum;

    @Column(name = "min_score_points")
    private Integer minScorePoints;

    @Column(name = "image_file_name", nullable = true)
    private String imageFileName;

    /**
     * Default Constructor
     */
    public Episode() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public Integer getMinScorePoints() {
        return minScorePoints;
    }

    public void setMinScorePoints(Integer minScorePoints) {
        this.minScorePoints = minScorePoints;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episode)) return false;

        Episode rubric = (Episode) o;

        if (!title.equals(rubric.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Episode[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}