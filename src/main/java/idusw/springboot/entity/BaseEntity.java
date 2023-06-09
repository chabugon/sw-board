package idusw.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
* JPA Auditing 을 위한 공통 추상 클래스
 *
 * Entity (개체) : Persistence 관점 = DB 관점(table, index, view, sequence ...) vs 파일
 * Relationship : Relation DBMS, join ....
 * Service Layer - Repository Layer 사이에서 동작 정보 표현(전달)
 * 참고) Domain, DTO 객체 : Controller - Service, controller - View 사이에서 정보 전달
 */


@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;


    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
