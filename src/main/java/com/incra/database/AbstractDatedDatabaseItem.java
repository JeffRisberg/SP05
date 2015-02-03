package com.incra.database;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * The <i>AbstractDatedDatabaseItem</i> class is a database item with standard fields for tracking dates.
 *
 * @author Jeffrey Risberg
 * @since 09/10/11
 */
@MappedSuperclass
public abstract class AbstractDatedDatabaseItem extends AbstractDatabaseItem {
    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_updated")
    private Date lastUpdated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
