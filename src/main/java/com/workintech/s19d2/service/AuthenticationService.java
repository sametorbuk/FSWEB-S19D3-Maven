package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository,  RoleRepository roleRepository ,PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public Member register(String email , String password){
        Optional<Member> foundMember = memberRepository.findByEmail(email);
        if(foundMember.isPresent()){
            throw new RuntimeException("User with given email already exist" + email);
        }

        String encodedPassword=passwordEncoder.encode(password);
       Optional <Role> userRole=roleRepository.findByAuthority("USER");

        Set<Role> roles = new HashSet<>();
        roles.add(userRole.get());


        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRoles((List<Role>) roles);


       return  memberRepository.save(member);

    }




    public Member registerAdmin(String email , String password){
        Optional<Member> foundMember = memberRepository.findByEmail(email);
        if(foundMember.isPresent()){
            throw new RuntimeException("User with given email already exist" + email);
        }
        String encodedPassword=passwordEncoder.encode(password);
        Optional <Role> userRole=roleRepository.findByAuthority("ADMIN");


        Set<Role> roles = new HashSet<>();
        roles.add(userRole.get());

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRoles((List<Role>) roles);


        return  memberRepository.save(member);

    }
}
