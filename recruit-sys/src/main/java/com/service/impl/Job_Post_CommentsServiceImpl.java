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


import com.dao.Job_Post_CommentsDao;
import com.entity.Job_Post_CommentsEntity;
import com.service.Job_Post_CommentsService;
import com.entity.vo.Job_Post_CommentsVO;
import com.entity.view.Job_Post_CommentsView;

@Service("job_post_commentsService")
public class Job_Post_CommentsServiceImpl extends ServiceImpl<Job_Post_CommentsDao, Job_Post_CommentsEntity> implements Job_Post_CommentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<Job_Post_CommentsEntity> page = this.selectPage(
                new Query<Job_Post_CommentsEntity>(params).getPage(),
                new EntityWrapper<Job_Post_CommentsEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<Job_Post_CommentsEntity> wrapper) {
		  Page<Job_Post_CommentsView> page =new Query<Job_Post_CommentsView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<Job_Post_CommentsVO> selectListVO(Wrapper<Job_Post_CommentsEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public Job_Post_CommentsVO selectVO(Wrapper<Job_Post_CommentsEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<Job_Post_CommentsView> selectListView(Wrapper<Job_Post_CommentsEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public Job_Post_CommentsView selectView(Wrapper<Job_Post_CommentsEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
