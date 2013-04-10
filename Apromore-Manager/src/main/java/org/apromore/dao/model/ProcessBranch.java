package org.apromore.dao.model;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * ProcessBranch generated by hbm2java
 */
@Entity
@Table(name = "process_branch")
@Configurable("processBranch")
@Cacheable(true)
@Cache(type = CacheType.SOFT_WEAK, isolation = CacheIsolationType.SHARED, expiry = 60000, size = 1000, alwaysRefresh = true, disableHits = true, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class ProcessBranch implements Serializable {

    private Integer id;
    private String branchName;
    private String creationDate;
    private String lastUpdate;
    private String ranking;

    private Process process;
    private ProcessModelVersion currentProcessModelVersion;
    private ProcessModelVersion sourceProcessModelVersion;
    private Set<ProcessModelVersion> processModelVersions = new HashSet<ProcessModelVersion>(0);


    /**
     * Public Constructor.
     */
    public ProcessBranch() {
    }



    /**
     * returns the Id of this Object.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the Id of this Object
     * @param id the new Id.
     */
    public void setId(final Integer id) {
        this.id = id;
    }


    @Column(name = "branch_name", length = 1000)
    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(final String newBranchName) {
        this.branchName = newBranchName;
    }

    /**
     * Get the process for the Object.
     * @return Returns the process.
     */
    @Column(name = "creation_date", length = 35)
    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final String newCreationDate) {
        this.creationDate = newCreationDate;
    }

    /**
     * Get the process for the Object.
     * @return Returns the process.
     */
    @Column(name = "last_update", length = 35)
    public String getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final String newLastUpdate) {
        this.lastUpdate = newLastUpdate;
    }

    /**
     * Get the process for the Object.
     * @return Returns the process.
     */
    @Column(name = "ranking", length = 10)
    public String getRanking() {
        return this.ranking;
    }

    public void setRanking(final String newRanking) {
        this.ranking = newRanking;
    }


    @ManyToOne
    @JoinColumn(name = "processId")
    public Process getProcess() {
        return this.process;
    }

    public void setProcess(final Process newProcess) {
        this.process = newProcess;
    }

    @ManyToOne
    @JoinColumn(name = "currentProcessModelVersion")
    public ProcessModelVersion getCurrentProcessModelVersion() {
        return this.currentProcessModelVersion;
    }

    public void setCurrentProcessModelVersion(final ProcessModelVersion newCurrentProcessModelVersion) {
        this.currentProcessModelVersion = newCurrentProcessModelVersion;
    }

    @ManyToOne
    @JoinColumn(name = "sourceProcessModelVersion")
    public ProcessModelVersion getSourceProcessModelVersion() {
        return this.sourceProcessModelVersion;
    }

    public void setSourceProcessModelVersion(final ProcessModelVersion newSourceProcessModelVersion) {
        this.sourceProcessModelVersion = newSourceProcessModelVersion;
    }

    @OneToMany(mappedBy = "processBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("versionNumber ASC")
    public Set<ProcessModelVersion> getProcessModelVersions() {
        return this.processModelVersions;
    }

    public void setProcessModelVersions(final Set<ProcessModelVersion> newProcessModelVersions) {
        this.processModelVersions = newProcessModelVersions;
    }

}


