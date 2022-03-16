package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDay;

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


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Friendship> userFriends;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Friendship> friendsUser;


//    @OneToMany(mappedBy = "friendRequested")
//    @JsonIgnore
//    private Set<FriendRequest> friendRequested;
//
//
//    @OneToMany(mappedBy = "friendRequests")
//    @JsonIgnore
//    private Set<FriendRequest> friendRequests;

    public void setUserPosts(Set<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public Set<Post> getUserTimeline() {
        return userTimeline;
    }

    public void setUserTimeline(Set<Post> userTimeline) {
        this.userTimeline = userTimeline;
    }

    public Set<Reaction> getUserReactions() {
        return userReactions;
    }

    public void setUserReactions(Set<Reaction> userReactions) {
        this.userReactions = userReactions;
    }

    public Set<Photo> getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(Set<Photo> userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Set<Album> getUserAlbum() {
        return userAlbum;
    }

    public void setUserAlbum(Set<Album> userAlbum) {
        this.userAlbum = userAlbum;
    }

    public Set<Friendship> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(Set<Friendship> userFriends) {
        this.userFriends = userFriends;
    }

    public Set<Friendship> getFriendsUser() {
        return friendsUser;
    }

    public void setFriendsUser(Set<Friendship> friendsUser) {
        this.friendsUser = friendsUser;
    }
//
//    public Set<FriendRequest> getFriendRequested() {
//        return friendRequested;
//    }
//
//    public void setFriendRequested(Set<FriendRequest> friendRequested) {
//        this.friendRequested = friendRequested;
//    }
//
//    public Set<FriendRequest> getFriendRequests() {
//        return friendRequests;
//    }
//
//    public void setFriendRequests(Set<FriendRequest> friendRequests) {
//        this.friendRequests = friendRequests;
//    }


    // Constructors

    public User() {
    }
    public User(Long id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String password, LocalDate birthDay, String gender, String mobileNumber, String dateJoined, boolean isActive) {
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
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

    public Set<Post> getUserPosts() {
        return userPosts;}

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