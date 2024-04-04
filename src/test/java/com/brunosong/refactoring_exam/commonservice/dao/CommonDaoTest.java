package com.brunosong.refactoring_exam.commonservice.dao;

import com.brunosong.refactoring_exam.member.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


//@Import(CommonDao.class)

@MybatisTest
//@Sql({"/schema.sql", "/data.sql"})
class CommonDaoTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    void test(){
        memberMapper.memberCount();
    }

}