<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<c:url var="readUrl" value="/goods/list"/>
<c:url var="createUrl" value="/goods/create"/>
<c:url var="updateUrl" value="/goods/update"/>
<c:url var="deleteUrl" value="/goods/delete"/>
<c:url var="vendorComboUrl" value="/vendor/combo"/>
<c:url var="vendorCreateUrl" value="/vendor/create"/>
<c:url var="vendorUpdateUrl" value="/vendor/update"/>
<c:url var="vendorDeleteUrl" value="/vendor/delete"/>

<c:url var="vendorReadUrl" value="/vendor/list"/>
<title>Insert title here</title>
</head>
<body>
<br><p/><br><p/><br><p/>
${readUrl }
<script>
$(document).ready(function()
{
	if(!"${vendors}")
	{
		location.href="${vendorComboUrl}";
	}
})
</script>
<kendo:grid title="그리드" name="vendor" pageable="true" sortable="true" scrollable="true" height="450">
	<kendo:grid-editable mode="incell"/>
	<kendo:grid-toolbar>
		<kendo:grid-toolbarItem name="create" text="생성"/>
		<kendo:grid-toolbarItem name="save" text="저장"/>
	</kendo:grid-toolbar>
	<kendo:grid-columns>
		<kendo:grid-column title="회사번호" field="viNum" editable="false"/>
		<kendo:grid-column title="회사이름" field="viName"/>
		<kendo:grid-column title="회사설명" field="viDesc"/>
		<kendo:grid-column title="회사주소" field="viAddress"/>
		<kendo:grid-column title="회사번호" field="viPhone"/>
		<kendo:grid-column title="생성일자" field="viCredat"/>
		<kendo:grid-column title="생성시간" field="viCretim"/>	
		<kendo:grid-column command="destroy" title="삭제" />
	</kendo:grid-columns>
	
	<kendo:dataSource pageSize="20" batch="true">
		<kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${vendorReadUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-create url="${vendorCreateUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-update url="${vendorUpdateUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-destroy url="${vendorDeleteUrl}" dataType="json" type="POST" contentType="application/json"/>
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
			<kendo:dataSource-schema-model id="viNum">
				<kendo:dataSource-schema-model-fields>
					<kendo:dataSource-schema-model-field name="viName" type="string">
						<kendo:dataSource-schema-model-field-validation required="true"/>
					</kendo:dataSource-schema-model-field>
				</kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		</kendo:dataSource-schema>
	</kendo:dataSource>
</kendo:grid>


<kendo:grid title="그리드" name="goods" pageable="true" sortable="true" scrollable="true" height="450">
	<kendo:grid-editable mode="incell"/>
	<kendo:grid-toolbar>
		<kendo:grid-toolbarItem name="create" text="생성"/>
		<kendo:grid-toolbarItem name="save" text="저장"/>
	</kendo:grid-toolbar>
	<kendo:grid-columns>
		<kendo:grid-column title="제품번호" field="giNum" editable="false"/>
		<kendo:grid-column title="제품이름" field="giName"/>
		<kendo:grid-column title="제품설명" field="giDesc"/>
		<kendo:grid-column title="회사번호" field="viNum">
			<kendo:grid-column-values value="${vendors}"/>
		</kendo:grid-column>
		<kendo:grid-column title="생산일자" field="giCredat"/>
		<kendo:grid-column title="생산시간" field="giCretim"/>
		<kendo:grid-column command="destroy" title="삭제" />
	</kendo:grid-columns>
	
	<kendo:dataSource pageSize="20" batch="true">
		<kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-create url="${createUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-update url="${updateUrl}" dataType="json" type="POST" contentType="application/json"/>
			<kendo:dataSource-transport-destroy url="${deleteUrl}" dataType="json" type="POST" contentType="application/json" />
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
</form>
</body>
</html>