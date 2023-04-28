package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.TushufenleiDao;
import com.entity.TushufenleiEntity;
import com.service.TushufenleiService;
import com.entity.vo.TushufenleiVO;
import com.entity.view.TushufenleiView;

@Service("tushufenleiService")
public class TushufenleiServiceImpl extends ServiceImpl<TushufenleiDao, TushufenleiEntity> implements TushufenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TushufenleiEntity> page = this.selectPage(
                new Query<TushufenleiEntity>(params).getPage(),
                new EntityWrapper<TushufenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TushufenleiEntity> wrapper) {
		  Page<TushufenleiView> page =new Query<TushufenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<TushufenleiVO> selectListVO(Wrapper<TushufenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TushufenleiVO selectVO(Wrapper<TushufenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TushufenleiView> selectListView(Wrapper<TushufenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TushufenleiView selectView(Wrapper<TushufenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
