package com.brunosong.refactoring_exam.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int memberCount();

}
