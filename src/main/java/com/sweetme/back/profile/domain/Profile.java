package com.sweetme.back.profile.domain;

import com.sweetme.back.auth.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_profile")
@Getter
@Setter
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "desc", nullable = false)
    private String description;

    @Column(name = "profile_url", nullable = false)
    private String profileUrl = "";

    @Column(name = "image_path", nullable = false)
    private String imagePath = "";

    @ManyToMany
    @JoinTable(
            name = "tbl_profile_stack",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "stack_id")
    )
    private List<Stack> stacks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tbl_profile_position",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )

    private List<Position> positions = new ArrayList<>();
}