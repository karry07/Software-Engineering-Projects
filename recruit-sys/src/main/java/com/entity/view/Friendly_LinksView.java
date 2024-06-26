package com.entity.view;

import com.entity.Friendly_LinksEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 友情链接
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date
 */
@TableName("friendly_links")
public class Friendly_LinksView  extends Friendly_LinksEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Friendly_LinksView(){
	}
 
 	public Friendly_LinksView(Friendly_LinksEntity friendly_linksEntity){
 	try {
			BeanUtils.copyProperties(this, friendly_linksEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
