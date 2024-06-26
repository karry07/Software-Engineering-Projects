package com.dao;

import com.entity.Job_Post_CommentsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.Job_Post_CommentsVO;
import com.entity.view.Job_Post_CommentsView;


/**
 * 招聘信息评论表
 * 
 * @author 
 * @email 

 */
public interface Job_Post_CommentsDao extends BaseMapper<Job_Post_CommentsEntity> {
	
	List<Job_Post_CommentsVO> selectListVO(@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);
	
	Job_Post_CommentsVO selectVO(@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);
	
	List<Job_Post_CommentsView> selectListView(@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);

	List<Job_Post_CommentsView> selectListView(Pagination page,@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);
	
	Job_Post_CommentsView selectView(@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);
}
