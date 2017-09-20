<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<c:url var="readUrl" value="/db/run/sql"/>

<kendo:grid title="그리드" name="sqlGrid" pageable="true" sortable="true" scrollable="true" height="450">
	<kendo:grid-editable mode="incell"/>
	<kendo:grid-toolbar>
		<kendo:grid-toolbarItem name="create" text="생성"/>
		<kendo:grid-toolbarItem name="save" text="저장"/>
	</kendo:grid-toolbar>
	<kendo:grid-columns>
		<kendo:grid-column title="<% %>" field="${columns}" editable="false"/>
		<kendo:grid-column title="${list}" field="${columns}"/>
		<kendo:grid-column title="${list}" field="${columns}"/>
		<kendo:grid-column title="${list}" field="${columns}">
		</kendo:grid-column>
		<kendo:grid-column title="${columnName}" field="${columns}"/>
		<kendo:grid-column title="${columnName}" field="${columns}"/>
		<kendo:grid-column command="destroy" title="삭제" />
	</kendo:grid-columns>
	
	<kendo:dataSource pageSize="20" batch="true">
		<kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-parameterMap>
				<script>
				function parameterMap(options, type)
				{
					if(type === "read")
					{
						return JSON.stringify(options);
					}
					else
					{
						var str = JSON.stringify(options.models);
						return str;
					}
				}
				</script>
			</kendo:dataSource-transport-parameterMap>
		</kendo:dataSource-transport> 
		
		<kendo:dataSource-schema>
			<kendo:dataSource-schema-model id="giNum">
				<kendo:dataSource-schema-model-fields>
					<kendo:dataSource-schema-model-field name="giName" type="string">
						<kendo:dataSource-schema-model-field-validation required="true"/>
					</kendo:dataSource-schema-model-field>
					<kendo:dataSource-schema-model-field name="viNum" defaultValue="1" >
							<kendo:dataSource-schema-model-field-validation required="true" min="1"/>
					</kendo:dataSource-schema-model-field>
				</kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		</kendo:dataSource-schema>
	</kendo:dataSource>
</kendo:grid>