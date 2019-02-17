/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travel.quicktravel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "luggage")
@NamedQueries({
    @NamedQuery(name = "Luggage.findAll", query = "SELECT l FROM Luggage l")})
public class Luggage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pas_id")
    private int pasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jou_id")
    private int jouId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "code")
    private String code;
    @JoinColumn(name = "book", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    public Luggage() {
    }

    public Luggage(Integer id) {
        this.id = id;
    }

    public Luggage(Integer id, int pasId, int jouId, String code) {
        this.id = id;
        this.pasId = pasId;
        this.jouId = jouId;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPasId() {
        return pasId;
    }

    public void setPasId(int pasId) {
        this.pasId = pasId;
    }

    public int getJouId() {
        return jouId;
    }

    public void setJouId(int jouId) {
        this.jouId = jouId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Luggage)) {
            return false;
        }
        Luggage other = (Luggage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travel.quicktravel.entities.Luggage[ id=" + id + " ]";
    }
    
}
