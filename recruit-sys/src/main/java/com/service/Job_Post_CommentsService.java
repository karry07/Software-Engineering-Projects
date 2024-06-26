package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.Job_Post_CommentsEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.Job_Post_CommentsVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.Job_Post_CommentsView;


/**
 * 招聘信息评论表
 *
 * @author 
 * @email 
 * @date
 */
public interface Job_Post_CommentsService extends IService<Job_Post_CommentsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<Job_Post_CommentsVO> selectListVO(Wrapper<Job_Post_CommentsEntity> wrapper);
   	
   	Job_Post_CommentsVO selectVO(@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);
   	
   	List<Job_Post_CommentsView> selectListView(Wrapper<Job_Post_CommentsEntity> wrapper);
   	
   	Job_Post_CommentsView selectView(@Param("ew") Wrapper<Job_Post_CommentsEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<Job_Post_CommentsEntity> wrapper);
}

