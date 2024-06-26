package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.CompanyinfoEntity;
import com.entity.view.CompanyinfoView;
import com.entity.vo.CompanyinfoVO;
import com.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 企业信息
 *
 * @author 
 * @email 
 * @date
 */
public interface CompanyinfoService extends IService<CompanyinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CompanyinfoVO> selectListVO(Wrapper<CompanyinfoEntity> wrapper);
   	
   	CompanyinfoVO selectVO(@Param("ew") Wrapper<CompanyinfoEntity> wrapper);
   	
   	List<CompanyinfoView> selectListView(Wrapper<CompanyinfoEntity> wrapper);
   	
   	CompanyinfoView selectView(@Param("ew") Wrapper<CompanyinfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CompanyinfoEntity> wrapper);
}

