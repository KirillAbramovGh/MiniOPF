package com.netcracker.students.o3.model.users;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public interface Customer extends User
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "last_id")
    @SequenceGenerator(name="last_id",
            sequenceName="last_id")
    @Column(name = "id", updatable = false, nullable = false)
    @Override
    BigInteger getId();

    @Column(name = "login", unique = true)
    @Override
    String getLogin();

    @Column(name = "name")
    @Override
    String getName();

    @Column(name = "password")
    @Override
    String getPassword();

    /**
     * @return customer money balance
     */
    @Column(name = "moneybalance")
    BigDecimal getMoneyBalance();

    /**
     * set customer money balance
     */
    void setMoneyBalance(final BigDecimal moneyBalance);

    /**
     * @return set of connected customer services ids
     */
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToMany(targetEntity = ServiceImpl.class, fetch = FetchType.EAGER,
            mappedBy = "customer")
    Set<Service> getConnectedServices();

    /***
     * set customer connected services
     * @param connectedServices
     */
    void setConnectedServices(final Set<Service> connectedServices);

    /**
     * @return customer area id
     */
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne(targetEntity = AreaImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "areaid")
    Area getArea();

    /**
     * set customer area id
     */
    void setArea(final Area area);

    /**
     * add service to customer
     */
    void addConnectedService(Service service);
}
