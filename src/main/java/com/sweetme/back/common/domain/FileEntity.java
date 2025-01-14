package com.sweetme.back.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_file")
@Getter
@Setter
public class FileEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;
}
