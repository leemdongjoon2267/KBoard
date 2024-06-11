-- OAuth2

select * from t5_user order by id desc;

alter table t5_user
add column provider varchar(40);

alter table t5_user
add column providerId varchar(200);