package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.InterviewDao;
import com.entity.InterviewEntity;
import com.entity.view.InterviewView;
import com.entity.vo.InterviewVO;
import com.service.InterviewService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("InterviewService")
public class InterviewServiceImpl extends ServiceImpl<InterviewDao, InterviewEntity> implements InterviewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InterviewEntity> page = this.selectPage(
                new Query<InterviewEntity>(params).getPage(),
                new EntityWrapper<InterviewEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<InterviewEntity> wrapper) {
        Page<InterviewView> page = new Query<InterviewView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<InterviewVO> selectListVO(Wrapper<InterviewEntity> wrapper) {
       return baseMapper.selectListVO(wrapper);
    }

    @Override
    public InterviewVO selectVO(Wrapper<InterviewEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<InterviewView> selectListView(Wrapper<InterviewEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public InterviewView selectView(Wrapper<InterviewEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }
}


