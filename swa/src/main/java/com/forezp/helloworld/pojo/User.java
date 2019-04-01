package com.forezp.helloworld.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Pandy
 * @Date: 2019/3/30 23:06
 * @Version 1.0
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;

    @Column
    private String password;
}
