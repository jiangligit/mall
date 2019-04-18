#表结构
drop database if exists db_mall;
create database db_mall default character set  utf8 collate  utf8_general_ci;


create table permission
(
    id         bigint auto_increment comment 'ID'
        primary key,
    name       varchar(20) not null comment '权限名称',
    expression varchar(20) not null comment '权限表达式',
    constraint permission_expression_uindex
        unique (expression),
    constraint permission_name_uindexa
        unique (name)
)
    comment '权限';

create table role
(
    id   bigint auto_increment comment 'ID'
        primary key,
    name varchar(20) not null comment '角色名称',
    constraint role_name_uindex
        unique (name)
)
    comment '角色';

create table role_permission
(
    r_id bigint not null comment '角色ID',
    p_id bigint not null comment '权限ID'
)
    comment '角色权限中间表';

create table user
(
    id       bigint auto_increment comment 'ID'
        primary key,
    username varchar(20) not null comment '用户名',
    password varchar(50) not null comment '用户名',
    constraint User_username_uindex
        unique (username)
)
    comment '用户';

create table user_role
(
    u_id bigint not null comment '用户ID',
    r_id bigint not null comment '角色ID'
)
    comment '用户角色中间表';