package com.lec.spring.repository;

import com.lec.spring.domain.Post;

import java.util.List;

// Repository layer(aka. Data layer)
// DataSource (DB) 등에 대한 직접적인 접근
public interface PostRepository {

    // 새 글 작성 (insert) <- Post(작성자, 제목, 내용)
    int save(Post post);

    // 특정 id 글 내용 읽기 (select) => Post 로 리턴
    // 만약 해당 id 의 글 없으면 null 리턴
    Post findById(Long id); // findById 를 읽어와서 Post 로 리턴


    // 특정 id 조회수 +1 증가 (update)
    int incViewCnt(Long id);

    // 전체 글 목록. 최신순 (select) => List<> 로 받아옴(목록이여서)
    List<Post> findAll();

    // 특정 id 글 수정 (제목, 내용) (update)
    int update(Post post);

    // 특정 id 글 삭제하기 (delete) <= Post(id)
    int delete(Post post);

    // 페이징
    // from 부터 rows 개 만큼 select
    List<Post> selectFromRow(int from, int rows);

    // 전체 글의 개수
    int countAll();
}
