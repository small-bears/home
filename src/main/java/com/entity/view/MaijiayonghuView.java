package com.entity.view;

import com.entity.MaijiayonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 卖家用户
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-04-23 15:10:36
 */
@TableName("maijiayonghu")
public class MaijiayonghuView  extends MaijiayonghuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public MaijiayonghuView(){
	}
 
 	public MaijiayonghuView(MaijiayonghuEntity maijiayonghuEntity){
 	try {
			BeanUtils.copyProperties(this, maijiayonghuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
