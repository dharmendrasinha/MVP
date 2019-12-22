package com.ci.project.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cust_no")
    private String custNo;

    @Column(name = "cust_contact_id")
    private String custContactId;

    @Column(name = "cust_contact_last_name")
    private String custContactLastName;

    @Column(name = "cust_contact_first_name")
    private String custContactFirstName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustNo() {
        return custNo;
    }

    public Customer custNo(String custNo) {
        this.custNo = custNo;
        return this;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustContactId() {
        return custContactId;
    }

    public Customer custContactId(String custContactId) {
        this.custContactId = custContactId;
        return this;
    }

    public void setCustContactId(String custContactId) {
        this.custContactId = custContactId;
    }

    public String getCustContactLastName() {
        return custContactLastName;
    }

    public Customer custContactLastName(String custContactLastName) {
        this.custContactLastName = custContactLastName;
        return this;
    }

    public void setCustContactLastName(String custContactLastName) {
        this.custContactLastName = custContactLastName;
    }

    public String getCustContactFirstName() {
        return custContactFirstName;
    }

    public Customer custContactFirstName(String custContactFirstName) {
        this.custContactFirstName = custContactFirstName;
        return this;
    }

    public void setCustContactFirstName(String custContactFirstName) {
        this.custContactFirstName = custContactFirstName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", custNo='" + getCustNo() + "'" +
            ", custContactId='" + getCustContactId() + "'" +
            ", custContactLastName='" + getCustContactLastName() + "'" +
            ", custContactFirstName='" + getCustContactFirstName() + "'" +
            "}";
    }
}
