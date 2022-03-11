package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {

    //Properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photos_seq")
    @SequenceGenerator(name = "photos_seq", sequenceName = "sequence_photos", allocationSize = 1)
    private long id;

    @Column
    private String caption;

    @Column
    private Date photoTimestamp;

    @Column
    private boolean isActive=true;

    @Column
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn (name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn (name = "albumId", nullable = true)
    private Album album;

    @OneToMany(mappedBy = "photo")
    @JsonIgnore
    private Set<User> userPhoto;



    //Constructors


    public Photo() {
    }

    public Photo(String caption, Date photoTimestamp, boolean isActive, byte[] image) {
        this.caption = caption;
        this.photoTimestamp = photoTimestamp;
        this.isActive = isActive;
        this.image = image;
    }

    //Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getPhotoTimestamp() {
        return photoTimestamp;
    }

    public void setPhotoTimestamp(Date photoTimestamp) {
        this.photoTimestamp = photoTimestamp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
