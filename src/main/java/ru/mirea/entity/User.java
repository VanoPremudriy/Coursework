package ru.mirea.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(max=10)
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Email
    @NotBlank(message = "Почта не должна быть пустой")
    @Size(max=40)
    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "real_last_name")
    private String realLastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<User> friends;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Song> loveSongs;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Band> loveBands;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Song> playerSongs;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<User> invites;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Boolean isUserHasSongInPlayer(Song song){
        boolean isTrue1 = false;
        AtomicBoolean isTrue = new AtomicBoolean(false);
        playerSongs.forEach(song1 -> {
            if (song1.getSongName().equals(song.getSongName())) {
                isTrue.set(true);
            }
        });
        isTrue1 = isTrue.get();
        return isTrue1;
    }

    public Boolean isUserHasSongInLoves(Song song){
        AtomicBoolean isTrue = new AtomicBoolean(false);
        loveSongs.forEach(song1 -> {
            if (song1.getSongName().equals(song.getSongName())) {
                isTrue.set(true);
            }
        });
        return isTrue.get();
    }

    public Boolean isUserHasBandInLove(Band band){
        AtomicBoolean isTrue = new AtomicBoolean(false);
        loveBands.forEach(band1 -> {
            if (band1.getBandName().equals(band.getBandName())){
                isTrue.set(true);
            }
        });
        return isTrue.get();
    }

    public Boolean areTheyFriends(User isFriend){
        AtomicBoolean isTrue = new AtomicBoolean(false);
        friends.forEach(user -> {
            if (user.getUsername().equals(isFriend.getUsername())){
                isTrue.set(true);
            }
        });
        return isTrue.get();
    }

    public Boolean isHasInvite(User isInvite){
        AtomicBoolean isTrue = new AtomicBoolean(false);
        invites.forEach(user -> {
            if (user.getUsername().equals(isInvite.getUsername())){
                isTrue.set(true);
            }
        });
        return isTrue.get();
    }

}
