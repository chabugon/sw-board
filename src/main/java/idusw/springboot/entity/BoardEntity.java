package idusw.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 엔티티 클래스임으로 나타내는 애노테이션
@Table(name = "board_b201912060")

@ToString   // lombok 라이브러리 사용
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

//JPS Auditing 을 활용하여서 생성한 사람, 생성일자, 수정자, 수정일자 들을 선택하여서 감사(감시하는 역할)
public class BoardEntity extends BaseEntity{
    @Id
    @SequenceGenerator(sequenceName = "board_b201912060_seq", name = "board_b201912060_seq_gen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_b201912060_seq_gen")
    // Oracle : GenerationType.SEQUENCE, Mysql : GenerationType.IDENTITY, auto_increment
    private Long bno;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String content;

    @ManyToOne
    //@JoinColumn(name = "seq")
    private MemberEntity writer; // BoardEntity : MemberEntity = N : 1,
}
