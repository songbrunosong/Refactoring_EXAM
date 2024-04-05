package com.brunosong.refactoring_exam.commonservice.dao;

import com.brunosong.refactoring_exam.commonservice.domain.MetaBulkVo;
import com.brunosong.refactoring_exam.commonservice.domain.MetaItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonDao {

    List<MetaItemVo> getCourseCode();

    List<MetaItemVo> getAreaCode();

    int getEqualGoodsTypeAndCourseName(MetaBulkVo metaBulkVo);

    /* 추후예제를 위해서 남겨둔다. */
    List<MetaItemVo> goodsTypeAndCourseNameList(String topId);

    int deleteTempMetaTable();

    int insertTempMetaTable(List<MetaBulkVo> metaBulkVoList);

}
