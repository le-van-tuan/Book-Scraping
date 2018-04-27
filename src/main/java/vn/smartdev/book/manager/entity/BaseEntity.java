package vn.smartdev.book.manager.entity;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class BaseEntity implements Serializable {

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;

    @Column(name = "version_no")
    private int versionNo = 1;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    @PrePersist
    protected void onCreate() {
        this.lastUpdatedAt = this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedAt = Calendar.getInstance().getTime();
    }
}
