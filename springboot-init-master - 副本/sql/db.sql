use yuapi;

-- 接口信息
create table if not exists yuapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestParams` text not null comment '请求参数',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0 - 关闭  1 - 开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('傅浩', '谭浩然', 'www.milo-rempel.io', '段俊驰', '孟昊天', 0, '于潇然', 85399);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陈立辉', '吕志泽', 'www.margit-weimann.info', '戴伟宸', '董伟诚', 0, '蔡弘文', 713207);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('邱修杰', '阎伟祺', 'www.norberto-schinner.name', '许哲瀚', '金立诚', 0, '梁思远', 42030);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('白明杰', '梁浩', 'www.antione-witting.org', '苏旭尧', '范致远', 0, '赵博文', 2217870361);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('郑明辉', '周烨华', 'www.sheila-bednar.biz', '罗炫明', '宋擎宇', 0, '薛子涵', 461);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('任天翊', '郑雨泽', 'www.elliot-willms.net', '姜弘文', '曹峻熙', 0, '吕擎苍', 60599);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('万嘉熙', '周文', 'www.hong-lemke.info', '尹昊强', '邱振家', 0, '罗弘文', 1413587);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('黄鹏飞', '朱熠彤', 'www.maisha-dickinson.name', '李鹏', '周瑾瑜', 0, '罗绍齐', 976743);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('蔡鹏涛', '江伟祺', 'www.lenard-bednar.name', '许弘文', '陆钰轩', 0, '丁思', 648993633);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钟驰', '宋瑾瑜', 'www.brock-kuphal.info', '尹煜祺', '郑越彬', 0, '萧语堂', 9913879);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('石明', '陆果', 'www.glenn-gusikowski.org', '苏明', '杜立轩', 0, '马明杰', 90249);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('汪文博', '潘子涵', 'www.jenell-mraz.net', '江智辉', '龙天宇', 0, '董子骞', 72618171);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('范子默', '朱风华', 'www.jason-schulist.com', '宋金鑫', '黎鹏', 0, '沈天磊', 4);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('白越泽', '毛绍辉', 'www.karen-huel.info', '毛弘文', '孙立诚', 0, '余思聪', 292);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('余绍齐', '叶思远', 'www.lewis-hauck.org', '苏煜祺', '余炫明', 0, '苏瑾瑜', 608661);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('崔立果', '万君浩', 'www.jaymie-senger.biz', '丁煜城', '黎伟诚', 0, '邹子默', 94826519);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('唐峻熙', '蔡明轩', 'www.stacia-sauer.com', '汪熠彤', '金航', 0, '马智辉', 4728);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('薛雨泽', '潘伟泽', 'www.jim-stanton.name', '阎思淼', '徐思淼', 0, '丁煜祺', 72);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('赵智辉', '曾睿渊', 'www.sean-kessler.io', '吕雨泽', '李擎宇', 0, '邵鹏飞', 441);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('韦鑫鹏', '严烨华', 'www.sonny-mccullough.net', '汪智辉', '韩雨泽', 0, '金晋鹏', 72);

-- 用户调用接口关系表
create table if not exists yuapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';