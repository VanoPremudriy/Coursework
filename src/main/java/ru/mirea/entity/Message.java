package ru.mirea.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mess;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private User recipient;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender;

    public Message(Long id, String mess, User sender, User recipient){
        this.id = id;
        this.mess = mess;
        this.sender = sender;
        this.recipient = recipient;
    }
    public Message(){
    }
}