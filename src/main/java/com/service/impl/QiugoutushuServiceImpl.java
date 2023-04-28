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


import com.dao.QiugoutushuDao;
import com.entity.QiugoutushuEntity;
import com.service.QiugoutushuService;
import com.entity.vo.QiugoutushuVO;
import com.entity.view.QiugoutushuView;

@Service("qiugoutushuService")
public class QiugoutushuServiceImpl extends ServiceImpl<QiugoutushuDao, QiugoutushuEntity> implements QiugoutushuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiugoutushuEntity> page = this.selectPage(
                new Query<QiugoutushuEntity>(params).getPage(),
                new EntityWrapper<QiugoutushuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiugoutushuEntity> wrapper) {
		  Page<QiugoutushuView> page =new Query<QiugoutushuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<QiugoutushuVO> selectListVO(Wrapper<QiugoutushuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QiugoutushuVO selectVO(Wrapper<QiugoutushuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QiugoutushuView> selectListView(Wrapper<QiugoutushuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiugoutushuView selectView(Wrapper<QiugoutushuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
