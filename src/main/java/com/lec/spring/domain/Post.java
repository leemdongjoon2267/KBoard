package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // builder pattern 사용 가능
public class Post {
    private Long id;
//    private String user; // 작성자
    private String subject; // 제목
    private String content; // 내용
    private LocalDateTime regDate; // 작성일
    private Long viewCnt; // 조회수

    private User user; // 글 작성자 (FK)

    // 첨부파일
    @ToString.Exclude
    @Builder.Default // builder 제공안함
    private List<Attachment> fileList = new ArrayList<>(); // 비어있는 List 준비
}
