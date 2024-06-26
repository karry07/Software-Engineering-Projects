package com.entity.view;

import com.entity.EducationinfoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 学历信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date
 */
@TableName("educationinfo")
public class EducationinfoView  extends EducationinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public EducationinfoView(){
	}
 
 	public EducationinfoView(EducationinfoEntity educationInfoEntity){
 	try {
			BeanUtils.copyProperties(this, educationInfoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
