package com.ci.project.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "project_no")
    private String projectNo;

    @Column(name = "cis_program_type")
    private String cisProgramType;

    @Column(name = "proj_eff_date")
    private Instant projEffDate;

    @Column(name = "cust_no")
    private String custNo;

    @Column(name = "cust_contact_id")
    private String custContactId;

    @Column(name = "proj_name")
    private String projName;

    @Column(name = "proj_address")
    private String projAddress;

    @Column(name = "proj_addl_address")
    private String projAddlAddress;

    @Column(name = "proj_city")
    private String projCity;

    @Column(name = "proj_state")
    private String projState;

    @Column(name = "proj_zip_code")
    private String projZipCode;

    @Column(name = "proj_county")
    private String projCounty;

    @Column(name = "proj_est_start_date")
    private Instant projEstStartDate;

    @Column(name = "proj_est_completion_date")
    private Instant projEstCompletionDate;

    @Column(name = "proj_est_drawdown_date")
    private Instant projEstDrawdownDate;

    @Column(name = "proj_cust_int_ind")
    private String projCustIntInd;

    @OneToOne
    @JoinColumn(unique = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public Project projectNo(String projectNo) {
        this.projectNo = projectNo;
        return this;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getCisProgramType() {
        return cisProgramType;
    }

    public Project cisProgramType(String cisProgramType) {
        this.cisProgramType = cisProgramType;
        return this;
    }

    public void setCisProgramType(String cisProgramType) {
        this.cisProgramType = cisProgramType;
    }

    public Instant getProjEffDate() {
        return projEffDate;
    }

    public Project projEffDate(Instant projEffDate) {
        this.projEffDate = projEffDate;
        return this;
    }

    public void setProjEffDate(Instant projEffDate) {
        this.projEffDate = projEffDate;
    }

    public String getCustNo() {
        return custNo;
    }

    public Project custNo(String custNo) {
        this.custNo = custNo;
        return this;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustContactId() {
        return custContactId;
    }

    public Project custContactId(String custContactId) {
        this.custContactId = custContactId;
        return this;
    }

    public void setCustContactId(String custContactId) {
        this.custContactId = custContactId;
    }

    public String getProjName() {
        return projName;
    }

    public Project projName(String projName) {
        this.projName = projName;
        return this;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjAddress() {
        return projAddress;
    }

    public Project projAddress(String projAddress) {
        this.projAddress = projAddress;
        return this;
    }

    public void setProjAddress(String projAddress) {
        this.projAddress = projAddress;
    }

    public String getProjAddlAddress() {
        return projAddlAddress;
    }

    public Project projAddlAddress(String projAddlAddress) {
        this.projAddlAddress = projAddlAddress;
        return this;
    }

    public void setProjAddlAddress(String projAddlAddress) {
        this.projAddlAddress = projAddlAddress;
    }

    public String getProjCity() {
        return projCity;
    }

    public Project projCity(String projCity) {
        this.projCity = projCity;
        return this;
    }

    public void setProjCity(String projCity) {
        this.projCity = projCity;
    }

    public String getProjState() {
        return projState;
    }

    public Project projState(String projState) {
        this.projState = projState;
        return this;
    }

    public void setProjState(String projState) {
        this.projState = projState;
    }

    public String getProjZipCode() {
        return projZipCode;
    }

    public Project projZipCode(String projZipCode) {
        this.projZipCode = projZipCode;
        return this;
    }

    public void setProjZipCode(String projZipCode) {
        this.projZipCode = projZipCode;
    }

    public String getProjCounty() {
        return projCounty;
    }

    public Project projCounty(String projCounty) {
        this.projCounty = projCounty;
        return this;
    }

    public void setProjCounty(String projCounty) {
        this.projCounty = projCounty;
    }

    public Instant getProjEstStartDate() {
        return projEstStartDate;
    }

    public Project projEstStartDate(Instant projEstStartDate) {
        this.projEstStartDate = projEstStartDate;
        return this;
    }

    public void setProjEstStartDate(Instant projEstStartDate) {
        this.projEstStartDate = projEstStartDate;
    }

    public Instant getProjEstCompletionDate() {
        return projEstCompletionDate;
    }

    public Project projEstCompletionDate(Instant projEstCompletionDate) {
        this.projEstCompletionDate = projEstCompletionDate;
        return this;
    }

    public void setProjEstCompletionDate(Instant projEstCompletionDate) {
        this.projEstCompletionDate = projEstCompletionDate;
    }

    public Instant getProjEstDrawdownDate() {
        return projEstDrawdownDate;
    }

    public Project projEstDrawdownDate(Instant projEstDrawdownDate) {
        this.projEstDrawdownDate = projEstDrawdownDate;
        return this;
    }

    public void setProjEstDrawdownDate(Instant projEstDrawdownDate) {
        this.projEstDrawdownDate = projEstDrawdownDate;
    }

    public String getProjCustIntInd() {
        return projCustIntInd;
    }

    public Project projCustIntInd(String projCustIntInd) {
        this.projCustIntInd = projCustIntInd;
        return this;
    }

    public void setProjCustIntInd(String projCustIntInd) {
        this.projCustIntInd = projCustIntInd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Project customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projectNo='" + getProjectNo() + "'" +
            ", cisProgramType='" + getCisProgramType() + "'" +
            ", projEffDate='" + getProjEffDate() + "'" +
            ", custNo='" + getCustNo() + "'" +
            ", custContactId='" + getCustContactId() + "'" +
            ", projName='" + getProjName() + "'" +
            ", projAddress='" + getProjAddress() + "'" +
            ", projAddlAddress='" + getProjAddlAddress() + "'" +
            ", projCity='" + getProjCity() + "'" +
            ", projState='" + getProjState() + "'" +
            ", projZipCode='" + getProjZipCode() + "'" +
            ", projCounty='" + getProjCounty() + "'" +
            ", projEstStartDate='" + getProjEstStartDate() + "'" +
            ", projEstCompletionDate='" + getProjEstCompletionDate() + "'" +
            ", projEstDrawdownDate='" + getProjEstDrawdownDate() + "'" +
            ", projCustIntInd='" + getProjCustIntInd() + "'" +
            "}";
    }
}
