package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.InterviewEntity;
import com.entity.view.InterviewView;
import com.entity.vo.InterviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterviewDao extends BaseMapper<InterviewEntity> {

    List<InterviewVO> selectListVO(@Param("ew") Wrapper<InterviewEntity> wrapper);

    InterviewVO selectVO(@Param("ew") Wrapper<InterviewEntity> wrapper);

    List<InterviewView> selectListView(@Param("ew") Wrapper<InterviewEntity> wrapper);

    List<InterviewView> selectListView(Pagination page, @Param("ew") Wrapper<InterviewEntity> wrapper);

    InterviewView selectView(@Param("ew") Wrapper<InterviewEntity> wrapper);
}
