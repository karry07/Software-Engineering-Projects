package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.vo.EmploymentVO;
import com.utils.PageUtils;
import com.entity.EmploymentEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.entity.view.EmploymentView;


/**
 * 招聘信息
 *
 * @author 
 * @email 
 * @date
 */
public interface EmploymentService extends IService<EmploymentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<EmploymentVO> selectListVO(Wrapper<EmploymentEntity> wrapper);
   	
   	EmploymentVO selectVO(@Param("ew") Wrapper<EmploymentEntity> wrapper);
   	
   	List<EmploymentView> selectListView(Wrapper<EmploymentEntity> wrapper);
   	
   	EmploymentView selectView(@Param("ew") Wrapper<EmploymentEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<EmploymentEntity> wrapper);
}

