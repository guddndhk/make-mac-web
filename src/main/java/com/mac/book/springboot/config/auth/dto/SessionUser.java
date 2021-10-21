package com.mac.book.springboot.config.auth.dto;

import com.mac.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {

        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

    /*
    해당 클래스는 인증된 사용자 정보만 필요.
    User 클래스를 사용하지 않고 SessionUser 클래스를 사용하는 것은 User 클래스는 직렬화를 구현하지 않았기 때문
    만약 User 클래스를 직렬화로 구현해 사용한다면 User 클래스는 엔티티이기 때문 엔티티 클래스는 언제 다른 엔티티 클래스와
    관계가 형성될지 모른다. @OneToMany,@ManyToMany 같은 엔티티를 가지고 있다면 직렬화 대상에 자식까지 포함이 되어
    성능, 부수효과가 발생할 확률이 높아진다 그렇기에 직렬화를 구현한 SessionDto를 하나 추가로 만드는것이 운영 즉 유지보수에 용이하다.
     */