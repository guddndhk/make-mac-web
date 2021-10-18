package com.mac.book.springboot.domain.user;

import com.mac.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //JPA로 데이터베이스로 저장할시 Enum 값을 어떤 형태로 저장할지 결정한다 기본적으로 int로 된 숫자 숫자로 저장되면 데이터 베이스에서
    //확인시 그 값이 무슨 코드를 의미하는지 알 수 없다. 그래서 문자열 String 으로 저장 되도록 선언해준다
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {

        this.name = name;
        this.email = email;
        this.picture = picture;
        this. role = role;
    }

    public User update(String name, String picture) {

        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {

        return this.role.getKey();
    }
}
