/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.codingmentor.tp.homework.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

    private String title;

    @ManyToOne
    private Author author;

    @ElementCollection
    @Column(name = "color")

    protected List<ColorType> colorType = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private LengthOfItems lengthOfItem;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfPublish;

    public Item() {
        //default
    }

    public LengthOfItems getLengthOfItem() {
        return lengthOfItem;
    }

    public void setLengthOfItem(LengthOfItems lengthOfItem) {
        this.lengthOfItem = lengthOfItem;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ColorType> getColorType() {
        return colorType;
    }

    public void setColorType(List<ColorType> colorType) {
        this.colorType = colorType;
    }

    public Date getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    @Override
    public String toString() {
        return "Item{" + "title=" + title + ", author=" + author + ", colorType=" + colorType + ", length=" + lengthOfItem + ", dateOfPublish=" + dateOfPublish + '}';
    }

}
