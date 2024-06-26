package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.EducationinfoEntity;
import com.entity.view.EducationinfoView;
import com.entity.vo.EducationinfoVO;
import com.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;


/**
 * 学历信息
 *
 * @author 
 * @email 
 * @date
 */
public interface EducationinfoService extends IService<EducationinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<EducationinfoVO> selectListVO(Wrapper<EducationinfoEntity> wrapper);
   	
   	EducationinfoVO selectVO(@Param("ew") Wrapper<EducationinfoEntity> wrapper);
   	
   	List<EducationinfoView> selectListView(Wrapper<EducationinfoEntity> wrapper);
   	
   	EducationinfoView selectView(@Param("ew") Wrapper<EducationinfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<EducationinfoEntity> wrapper);
}

