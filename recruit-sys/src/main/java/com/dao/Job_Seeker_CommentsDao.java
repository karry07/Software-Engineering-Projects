package com.dao;

import com.entity.Job_Seeker_CommentsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.Job_Seeker_CommentsVO;
import com.entity.view.Job_Seeker_CommentsView;


/**
 * 求职者信息评论表
 * 
 * @author 
 * @email 

 */
public interface Job_Seeker_CommentsDao extends BaseMapper<Job_Seeker_CommentsEntity> {
	
	List<Job_Seeker_CommentsVO> selectListVO(@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);
	
	Job_Seeker_CommentsVO selectVO(@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);
	
	List<Job_Seeker_CommentsView> selectListView(@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);

	List<Job_Seeker_CommentsView> selectListView(Pagination page,@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);
	
	Job_Seeker_CommentsView selectView(@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);
}
