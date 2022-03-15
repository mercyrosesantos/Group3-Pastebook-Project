package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "sequence_users", allocationSize = 1)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Date birthDay;

    @Column
    private String gender;

    @Column
    private String mobileNumber;

    @Column
    private String dateJoined;

    @Column
    private boolean isActive = true;

    @Column(length = 2000)
    private String aboutMe;

    @Column
    private String url;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column
    private boolean enabled = true;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Post> userPosts;

    @OneToMany(mappedBy = "timelineUser")
    @JsonIgnore
    private Set<Post> userTimeline;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Reaction> userReactions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Photo> userPhoto;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Album> userAlbum;

    @OneToOne
    @JoinColumn (name = "photoId", nullable = true)
    private Photo photo;

    // Constructors

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Date birthDay, String gender, String mobileNumber, String dateJoined, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateJoined = dateJoined;
        this.isActive = isActive;
    }
// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}