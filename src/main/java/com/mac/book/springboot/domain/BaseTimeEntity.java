package com.mac.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //Jap Entity 클래스들이 이 클래스를 상속시 해당 클래스의 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate // Entity 생성되어 저장시 시간이 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity 값을 변경할때 시간 자동 저장
    private LocalDateTime modifiedDate;

}
