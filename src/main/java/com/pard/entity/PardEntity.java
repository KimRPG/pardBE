package com.pard.entity;

import com.pard.dto.JoinDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pard")
@Data
public class PardEntity {
    @Id
    @Column(length = 20)
    private String name;
    private int age;
    @Column(length = 20)
    private String part;

    //one to many 학교
    private String imgURL;

    public PardEntity(JoinDto dto) {
        this.name = dto.getName();
        this.age = dto.getAge();
        this.part = dto.getPart();
//        this.imgURL = imgURL;
    }

}
