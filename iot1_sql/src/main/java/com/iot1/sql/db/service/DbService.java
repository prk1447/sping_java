package com.iot1.sql.db.service;

import java.util.List;
import java.util.Map;

import com.iot1.sql.db.dto.Column;
import com.iot1.sql.db.dto.DataBase;
import com.iot1.sql.db.dto.DbInfo;
import com.iot1.sql.db.dto.Table;

public interface DbService 
{
	public DbInfo getDbInfo(DbInfo di);
	
	public List<DbInfo> getDbInfoList(DbInfo di);
	
	public boolean isConnecteDB(DbInfo pDi) throws Exception;
	
	public List<DataBase> getDataBaseList() throws Exception;
	
	public List<Table> getTableList(DataBase database) throws Exception;
	
	public List<Column> getColumnList(Table table) throws Exception;
	
	public Map<String, Object> runSql(Map<String, Object> pm) throws Exception;
	
	public Map<String,Map<String, String>> getMakeXml(DataBase database) throws Exception;
}
