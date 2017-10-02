package com.iot1.sql.db.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value="/db/iot_sql", method=RequestMethod.GET)
	public String login()
	{
		return "db/iot_sql";
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
	public @ResponseBody ModelMap getSqlResult(@RequestBody Map<String, Object> pm, ModelMap map) 
	{		
		try
		{
			map.put("resultMap", ds.runSql(pm));
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

	@RequestMapping(value = "/db/donwload/{database}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getDownloadData(@PathVariable(value = "database") String databaseName) throws Exception {

	    String xml = "";
	    DataBase d = new DataBase();
	    d.setDatabase(databaseName);
	    Map<String,Map<String, String>> xmlMap = ds.getMakeXml(d);
	    Iterator<String> it = xmlMap.keySet().iterator();
	    while(it.hasNext()){
	    	String tableName = it.next();
	    	xml += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";
		    xml += "<!DOCTYPE mapper PUBLIC \"-//ibatis.apache.org//DTD Mapper 3.0//EN\" \"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd\">\r\n";
	    	xml += "<mapper namespace=\"" + tableName +"\">\r\n";
	    	Map<String, String> xmlTable = xmlMap.get(tableName);
		    xml += "<select id=\"" + tableName.toUpperCase() + "_SELECT\" parameterType=\"" + tableName +"\" resultType=\"" + tableName +"\">\r\n";
		    xml += xmlTable.get("select") + "\r\n";
		    xml += "</select>\r\n";
		    xml += "<insert id=\"" + tableName.toUpperCase() + "_INSERT\" parameterType=\"" + tableName +"\" >\r\n";
		    xml += xmlTable.get("insert") + "\r\n";
		    xml += "</insert>\r\n";
		    xml += "<delete id=\"" + tableName.toUpperCase() + "_DELETE\" parameterType=\"" + tableName +"\" >\r\n";
		    xml += xmlTable.get("delete") + "\r\n";
		    xml += "</delete>\r\n";
	    	xml += "</mapper> \r\n";
	    }
	    byte[] output = xml.getBytes();

	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("charset", "utf-8");
	    responseHeaders.setContentType(MediaType.valueOf("application/xml"));
	    responseHeaders.setContentLength(output.length);
	    responseHeaders.set("Content-disposition", "attachment; filename=filename.xml");

	    return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
	}
}
