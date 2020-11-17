package com.xhf.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "checkcode")
    private String checkcode;
    @Column(name = "phone_check_code")
    private String phonecheckcode;


}
