package com.brunosong.refactoring_exam.member.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;


@MybatisTest
@Sql({ "/db/member-schema.sql", "/db/member-data.sql"} )
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    void 멤버의_총카운트_가져온다(){
        Assertions.assertThat(memberMapper.memberCount()).isEqualTo(2);
    }

}