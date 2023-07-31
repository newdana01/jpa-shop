package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private String name;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String city;
    private String zipcode;
    private String street;
}
