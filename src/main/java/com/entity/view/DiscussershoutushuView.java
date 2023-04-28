package com.entity.view;

import com.entity.DiscussershoutushuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 二手图书评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
@TableName("discussershoutushu")
public class DiscussershoutushuView  extends DiscussershoutushuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussershoutushuView(){
	}
 
 	public DiscussershoutushuView(DiscussershoutushuEntity discussershoutushuEntity){
 	try {
			BeanUtils.copyProperties(this, discussershoutushuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
