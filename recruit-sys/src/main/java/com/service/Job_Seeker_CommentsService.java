package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.Job_Seeker_CommentsEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.Job_Seeker_CommentsVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.Job_Seeker_CommentsView;


/**
 * 求职者信息评论表
 *
 * @author 
 * @email 
 * @date
 */
public interface Job_Seeker_CommentsService extends IService<Job_Seeker_CommentsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<Job_Seeker_CommentsVO> selectListVO(Wrapper<Job_Seeker_CommentsEntity> wrapper);
   	
   	Job_Seeker_CommentsVO selectVO(@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);
   	
   	List<Job_Seeker_CommentsView> selectListView(Wrapper<Job_Seeker_CommentsEntity> wrapper);
   	
   	Job_Seeker_CommentsView selectView(@Param("ew") Wrapper<Job_Seeker_CommentsEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<Job_Seeker_CommentsEntity> wrapper);
}

