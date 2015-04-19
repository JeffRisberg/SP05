package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;

/**
 * Objective in a episode
 *
 * @author Jeff Risberg
 * @since February 2014
 */
@Entity
@Table(name = "objective")
public class Objective extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Basic
    private String name;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    @Basic
    @Column(name = "seq_num")
    private Integer seqNum;

    @Basic
    private Integer minScorePoints;

    @Basic
    @Column(name = "image_file_name", nullable = true)
    private String imageFileName;

    /**
     * Default Constructor
     */
    public Objective() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
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
        if (!(o instanceof Objective)) return false;

        Objective rubric = (Objective) o;

        if (!name.equals(rubric.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Objective[title=");
        sb.append(name);
        sb.append("]");

        return sb.toString();
    }
}