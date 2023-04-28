package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QiugouhuifuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QiugouhuifuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QiugouhuifuView;


/**
 * 求购回复
 *
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface QiugouhuifuService extends IService<QiugouhuifuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiugouhuifuVO> selectListVO(Wrapper<QiugouhuifuEntity> wrapper);
   	
   	QiugouhuifuVO selectVO(@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);
   	
   	List<QiugouhuifuView> selectListView(Wrapper<QiugouhuifuEntity> wrapper);
   	
   	QiugouhuifuView selectView(@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiugouhuifuEntity> wrapper);
   	

}

