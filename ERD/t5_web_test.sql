SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'db326'
  AND TABLE_NAME LIKE 't5_%'
;

SELECT * FROM t5_authority;
SELECT * FROM t5_user ORDER BY id DESC;
SELECT * FROM t5_user_authorities;
SELECT * FROM t5_post ORDER BY id DESC;
SELECT * FROM t5_comment ORDER BY id DESC;
SELECT * FROM t5_attachment ORDER BY id DESC;

-- 특정 id 의 사용자 조회
SELECT
    id "id"
     , username "username"
     , password "password"
     , email "email"
     , name "name"
     , regdate "regdate"
FROM t5_user
WHERE 1 = 1
  AND id = 1
;

-- 특정 사용자의 authority 조회
SELECT a.id "id", a.name "name"
    FROM t5_authority a, t5_user_authorities u
WHERE a.id = u.authority_id AND u.user_id = 2;


-- 글(Post) 조회
SELECT
    p.id "p_id",
    p.subject "p_subject",
    p.content "p_content",
    p.viewcnt "p_viewcnt",
    p.regdate "p_regdate",
    u.id "u_id",
    u.username "u_username",
    u.name "u_name",
    u.email "u_email",
    u.regdate "u_regdate"
FROM
    t5_post p, t5_user u
WHERE
    p.user_id = u.id
;


-- 페이징 실습용 다량의 데이터
INSERT INTO t5_post(user_id, subject, content)
SELECT user_id, subject, content FROM t5_post;

SELECT count(*) FROM t5_post;

SELECT * FROM t5_post ORDER BY id desc LIMIT 5;

SELECT * FROM t5_post ORDER BY id desc LIMIT 5, 5;


# --------------------------------
# 특정글 의 (댓글 + 사용자) 정보
SELECT
    c.id "c_id",
    c.content "c_content",
    c.regdate "c_regdate",
    c.post_id "c_post_id",
    u.id "u_id",
    u.username "u_username",
    u.password "u_password",
    u.name "u_name",
    u.email "u_email",
    u.regdate "u_regdate"
FROM t5_comment c, t5_user u
WHERE c.user_id = u.id AND c.post_id = 1
ORDER BY c.id DESC
;
