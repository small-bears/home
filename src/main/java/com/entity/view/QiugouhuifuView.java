package com.entity.view;

import com.entity.QiugouhuifuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 求购回复
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
@TableName("qiugouhuifu")
public class QiugouhuifuView  extends QiugouhuifuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public QiugouhuifuView(){
	}
 
 	public QiugouhuifuView(QiugouhuifuEntity qiugouhuifuEntity){
 	try {
			BeanUtils.copyProperties(this, qiugouhuifuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
