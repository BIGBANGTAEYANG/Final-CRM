package com.crm.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -4850011248549277466L;

	//使用Gson转json
	public String parseJson(Object jsonobject){
		Gson g = new Gson();
		return g.toJson(jsonobject);
	}

	//跨域输出
	public void outJson(Object jsonobject, HttpServletResponse response) throws IOException {
		String json = parseJson(jsonobject);
		//跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;charset=utf-8");
		

		
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
}
