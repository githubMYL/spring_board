package org.myexam.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Configs {
    @Id
    @Column(name="code_", length=45)    // code 는 html 예약어 이기 때문에 바꿔줌
    private String code;

    @Lob
    @Column(name="value_")  // oracle 에서는 문제가 없지만 h2DB 에서는 예약어이기 때문에 test 시 문제 발생
    private String value;

}
