package bobmukjaku.bobmukjakuDemo.domain.member;

import bobmukjaku.bobmukjakuDemo.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Table(name = "member_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid; // PK

    @Column(name = "email", nullable = false, unique = true)
    private String memberEmail; // 이메일

    private String memberPassword; // 비밀번호

    @Column(name = "nickname", nullable = false, length = 30, unique = true)
    private String memberNickName; // 닉네임

    @Column(name = "certificated_at", nullable = false)
    private LocalDate certificatedAt; // 인증 날짜

    @Column(name = "rate", length = 4)
    private int rate; // 평가 점수

    @Column(name = "profile_color", length = 7)
    private String profileColor; // 프로필 배경색

    @Enumerated(EnumType.STRING)
    private Role role; // 권한 (USER, ADMIN)


    /* 회원 정보 수정 */
    // 닉네임 변경
    public void updateNickName(String nickName) {
        this.memberNickName = nickName;
    }

    // 비밀번호 변경
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.memberPassword = passwordEncoder.encode(password);
    }

    // 인증 날짜 변경
    public void updateCertificatedAt(LocalDate certificatedAt){
        this.certificatedAt = certificatedAt;
    }

    // 평가점수 변경
    public void updateRate(int rate){
        this.rate = rate;
    }

    // 프로필 색상 변경
    public void updateProfileColor(String profileColor){
        this.profileColor = profileColor;
    }

    /* 비밀번호 암호화 */
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.memberPassword = passwordEncoder.encode(memberPassword);
    }

    /* 인증날짜, 평가 점수, 프로필 색상 기본값 설정 */
    @PrePersist
    public void defaultSetting(){
        this.certificatedAt = LocalDate.now(); // 인증 날짜 default = 회원가입 날짜
        this.rate = 45; // 평가 점수 default = 45
        this.profileColor = "bg1"; // 프로필 색상 default = bg1
    }
}
