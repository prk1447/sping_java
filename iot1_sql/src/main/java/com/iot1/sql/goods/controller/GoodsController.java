package com.iot1.sql.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot1.sql.goods.dao.GoodsDao;
import com.iot1.sql.goods.dto.GoodsInfo;
import com.iot1.sql.goods.service.GoodsService;

@Controller
public class GoodsController 
{
	@Autowired
	GoodsService gs;
	@Autowired
	GoodsDao gDao;
	
	@RequestMapping(value="/goods/list", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> getGoodsInfoList(@RequestBody GoodsInfo gi)
	{
		return gs.getGoodsInfoList(gi);
	}
	
	@RequestMapping(value="/goods/create", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> saveGoodsInfoList(@RequestBody GoodsInfo[] goodsList, GoodsInfo gi)
	{
		gs.insertGoods(goodsList);
		return gs.getGoodsInfoList(gi);
	}
	
	@RequestMapping(value="/goods/update", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> updateGoodsInfoList(@RequestBody GoodsInfo[] goodsList, GoodsInfo gi)
	{
		for(GoodsInfo gi2 : goodsList){
			gDao.updateGoodsInfoList(gi2);
		}
		return gs.getGoodsInfoList(gi);
	}
	
	@RequestMapping(value="/goods/delete", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> deleteGoodsInfoList(@RequestBody GoodsInfo[] goodsList, GoodsInfo gi)
	{
		for(GoodsInfo gi2 : goodsList){
			System.out.println(gi2);
			gDao.deleteGoodsInfoList(gi2);
		}
		return gs.getGoodsInfoList(gi);
	}
	
	@RequestMapping(value="/goods/createone", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> saveGoodsInfo(@RequestBody GoodsInfo gi)
	{
		gs.insertGoods(gi);
		return gs.getGoodsInfoList(gi);
	}
	
	@RequestMapping(value="/goods/updateone", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> updateGoodsInfo(@RequestBody GoodsInfo gi)
	{
		gDao.updateGoodsInfoList(gi);
		return gs.getGoodsInfoList(gi);
	}
	
	@RequestMapping(value="/goods/deleteone", method=RequestMethod.POST)
	public @ResponseBody List<GoodsInfo> deleteGoodsInfo(@RequestBody GoodsInfo gi)
	{
		gDao.deleteGoodsInfoList(gi);
		return gs.getGoodsInfoList(gi);
	}
}
