package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.vo.GeneraluserVO;
import com.utils.PageUtils;
import com.entity.GeneraluserEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GeneraluserView;


/**
 * 用户
 *
 * @author 
 * @email 
 * @date
 */
public interface GeneraluserService extends IService<GeneraluserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GeneraluserVO> selectListVO(Wrapper<GeneraluserEntity> wrapper);
   	
   	GeneraluserVO selectVO(@Param("ew") Wrapper<GeneraluserEntity> wrapper);
   	
   	List<GeneraluserView> selectListView(Wrapper<GeneraluserEntity> wrapper);
   	
   	GeneraluserView selectView(@Param("ew") Wrapper<GeneraluserEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GeneraluserEntity> wrapper);
}

