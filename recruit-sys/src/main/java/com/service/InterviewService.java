package com.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ApplicationEntity;
import com.entity.InterviewEntity;
import com.entity.view.InterviewView;
import com.entity.vo.InterviewVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InterviewService extends IService<InterviewEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InterviewVO> selectListVO(Wrapper<InterviewEntity> wrapper);

    InterviewVO selectVO(@Param("ew") Wrapper<InterviewEntity> wrapper);

    List<InterviewView> selectListView(Wrapper<InterviewEntity> wrapper);

    InterviewView selectView(@Param("ew") Wrapper<InterviewEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<InterviewEntity> wrapper);
}
