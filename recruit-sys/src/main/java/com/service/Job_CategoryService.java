package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.Job_CategoryEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.Job_CategoryVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.Job_CategoryView;


/**
 * 岗位分类
 *
 * @author 
 * @email 
 * @date
 */
public interface Job_CategoryService extends IService<Job_CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<Job_CategoryVO> selectListVO(Wrapper<Job_CategoryEntity> wrapper);
   	
   	Job_CategoryVO selectVO(@Param("ew") Wrapper<Job_CategoryEntity> wrapper);
   	
   	List<Job_CategoryView> selectListView(Wrapper<Job_CategoryEntity> wrapper);
   	
   	Job_CategoryView selectView(@Param("ew") Wrapper<Job_CategoryEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<Job_CategoryEntity> wrapper);
}

