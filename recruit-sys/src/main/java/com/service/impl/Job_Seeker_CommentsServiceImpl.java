package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.Job_Seeker_CommentsDao;
import com.entity.Job_Seeker_CommentsEntity;
import com.service.Job_Seeker_CommentsService;
import com.entity.vo.Job_Seeker_CommentsVO;
import com.entity.view.Job_Seeker_CommentsView;

@Service("job_seeker_commentsService")
public class Job_Seeker_CommentsServiceImpl extends ServiceImpl<Job_Seeker_CommentsDao, Job_Seeker_CommentsEntity> implements Job_Seeker_CommentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<Job_Seeker_CommentsEntity> page = this.selectPage(
                new Query<Job_Seeker_CommentsEntity>(params).getPage(),
                new EntityWrapper<Job_Seeker_CommentsEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<Job_Seeker_CommentsEntity> wrapper) {
		  Page<Job_Seeker_CommentsView> page =new Query<Job_Seeker_CommentsView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<Job_Seeker_CommentsVO> selectListVO(Wrapper<Job_Seeker_CommentsEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public Job_Seeker_CommentsVO selectVO(Wrapper<Job_Seeker_CommentsEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<Job_Seeker_CommentsView> selectListView(Wrapper<Job_Seeker_CommentsEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public Job_Seeker_CommentsView selectView(Wrapper<Job_Seeker_CommentsEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
