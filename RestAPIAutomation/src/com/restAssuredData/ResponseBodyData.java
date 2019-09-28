package com.restAssuredData;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ResponseBodyData {
	
	public static Object getResponseData(Response response, Enum<?> valueType) {
		List<Object> list = new ArrayList<Object>();
		
		switch ((DataFormatTypes) valueType) {
		case XML:
			list.add(new XmlPath(response.asString()));
			break;
			
		case JSON:
			list.add(new JsonPath(response.asString()));
			break;

		default:
			System.out.println("Format Not Specify");
			break;
		}
		
		return list.get(0);
	}
	
	public static String getValueFromResponse(Response response, Enum<?> valueType, String value) {
		String valueFromPath = StringUtils.EMPTY;
		JsonPath jsonPath = null;
		XmlPath xmlPath = null;
		
		if (DataFormatTypes.JSON == valueType) {
			jsonPath = (JsonPath) getResponseData(response, valueType);
			valueFromPath = jsonPath.get(value);
		}
		else {
			xmlPath = (XmlPath) getResponseData(response, valueType);
			valueFromPath = xmlPath.get(value);
		}
		
		return valueFromPath;
	}

}
