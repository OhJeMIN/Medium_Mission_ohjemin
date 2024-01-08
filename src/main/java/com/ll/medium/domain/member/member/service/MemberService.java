package com.ll.medium.domain.member.member.service;
import com.ll.medium.domain.base.genFile.entity.GenFile;
import com.ll.medium.domain.base.genFile.service.GenFileService;
import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.repository.MemberRepository;
import com.ll.medium.global.app.AppConfig;
import com.ll.medium.global.rsData.RsData;
import com.ll.medium.global.ut.Exception.DataNotFoundException;
import com.ll.medium.standard.util.Ut.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    final private MemberRepository memberRepository;
    final private PasswordEncoder passwordEncoder;
    private final GenFileService genFileService;

    public RsData<Member> join(String username, String password) {

        if (findByUsername(username).isPresent()) {
            return RsData.of("400-2", "이미 존재하는 회원입니다.");
        }

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        memberRepository.save(member);
        return RsData.of("200", "%s님 환영합니다. 회원가입이 완료되었습니다. 로그인 후 이용해주세요.".formatted(member.getUsername()), member);
    }

    public Member joinPaid(String username, String password, boolean paid) {
        Member user = new Member();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPaid(paid);
        memberRepository.save(user);
        return user;
    }


    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public RsData<Member> whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImgUrl) {
        Optional<Member> opMember = findByUsername(username);

        if (opMember.isPresent()) return RsData.of("200", "이미 존재합니다.", opMember.get());

        String filePath = Ut.str.hasLength(profileImgUrl) ? Ut.file.downloadFileByHttp(profileImgUrl, AppConfig.getTempDirPath()) : "";
        if (findByUsername(username).isPresent()) {
            return RsData.of("400-2", "이미 존재하는 회원입니다.");
        }
        return join(username, "", nickname, false, filePath);
    }

    private RsData<Member> join(String username, String password, String nickname, boolean paid, String profileImgFilePath) {
        if (findByUsername(username).isPresent()) {
            return RsData.of("400-2", "이미 존재하는 회원입니다.");
        }

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setPaid(paid);
        memberRepository.save(member);

        if (Ut.str.hasLength(profileImgFilePath)) {
            saveProfileImg(member, profileImgFilePath);
        }

        return RsData.of("200", "%s님 환영합니다. 회원가입이 완료되었습니다. 로그인 후 이용해주세요.".formatted(member.getUsername()), member);
    }

    private void saveProfileImg(Member member, String profileImgFilePath) {
        genFileService.save(member.getModelName(), member.getId(), "common", "profileImg", 1, profileImgFilePath);
    }

    public String getProfileImgUrl(Member member) {
        return Optional.ofNullable(member)
                .flatMap(this::findProfileImgUrl)
                .orElse("https://placehold.co/30x30?text=UU");
    }

    private Optional<String> findProfileImgUrl(Member member) {
        return genFileService.findBy(
                        member.getModelName(), member.getId(), "common", "profileImg", 1
                )
                .map(GenFile::getUrl);
    }
}
