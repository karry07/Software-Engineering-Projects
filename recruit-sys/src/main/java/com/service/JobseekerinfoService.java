package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.JobseekerinfoEntity;
import com.entity.view.JobseekerinfoView;
import com.entity.vo.JobseekerinfoVO;
import com.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;


/**
 * 求职者信息
 *
 * @author 
 * @email 
 * @date
 */
public interface JobseekerinfoService extends IService<JobseekerinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JobseekerinfoVO> selectListVO(Wrapper<JobseekerinfoEntity> wrapper);
   	
   	JobseekerinfoVO selectVO(@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);
   	
   	List<JobseekerinfoView> selectListView(Wrapper<JobseekerinfoEntity> wrapper);
   	
   	JobseekerinfoView selectView(@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JobseekerinfoEntity> wrapper);
}

