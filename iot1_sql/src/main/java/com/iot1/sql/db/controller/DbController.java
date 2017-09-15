package com.iot1.sql.db.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot1.sql.db.dto.DataBase;
import com.iot1.sql.db.dto.DbInfo;
import com.iot1.sql.db.dto.Table;
import com.iot1.sql.db.service.DbService;

@Controller
public class DbController {
	@Autowired
	DbService ds;

	@RequestMapping(value = "/db/list/tree", method = RequestMethod.POST)
	public @ResponseBody List<DbInfo> getDbInfoList(@RequestBody DbInfo di) {
		return ds.getDbInfoList(di);
	}

	@RequestMapping(value = "/db/connecte", method = RequestMethod.POST)
	public @ResponseBody ModelMap getConnectDB(@RequestBody DbInfo di, ModelMap map) {
		try {
			if (ds.isConnecteDB(di)) {
				map.put("databaseList", ds.getDataBaseList());
			} else {
				map.put("error", "데이터베이스에 접속하지 못했습니다.");
			}
		} catch (Exception e) {
			map.put("error", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/db/table/list", method = RequestMethod.POST)
	public @ResponseBody ModelMap getTableList(@RequestBody DataBase database, ModelMap map) {
		try {
			map.put("tableList", ds.getTableList(database));
		} catch (Exception e) {
			map.put("error", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/db/table/info", method = RequestMethod.POST)
	public @ResponseBody ModelMap getTableInfo(@RequestBody Table table, ModelMap map) {
		try {
			map.put("tableList", ds.getColumnList(table));
			map.put("key", "tableList"); 
		} catch (Exception e) {
			map.put("error", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/db/run/sql", method = RequestMethod.POST)
	public @ResponseBody ModelMap getSqlResult(@RequestBody Map<String, String> pm, ModelMap map) 
	{		
		try
		{
			map.put("resultMap", ds.runSql(pm));	
			map.put("error", "값이 없는값 이거나 데이터가 제대로 입력되지않앗습니다.");
			map.put("key", "resultMap");
			map.put("msg", "S");		
		}
		catch(Exception e)
		{
			map.put("error", e.getMessage());
			map.put("msg", "F");
		}
		return map;
	}
}
