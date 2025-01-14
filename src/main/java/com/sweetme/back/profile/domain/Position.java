package com.sweetme.back.profile.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_position")
@Getter
@Setter
public class Position {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "position_id")
        private Long id;

        @Column(nullable = false)
        private String name;

    }

