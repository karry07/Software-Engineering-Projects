package com.entity.view;

import com.entity.CompanyinfoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 企业信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date
 */
@TableName("companyinfo")
public class CompanyinfoView  extends CompanyinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public CompanyinfoView(){
	}
 
 	public CompanyinfoView(CompanyinfoEntity companyinfoEntity){
 	try {
			BeanUtils.copyProperties(this, companyinfoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
