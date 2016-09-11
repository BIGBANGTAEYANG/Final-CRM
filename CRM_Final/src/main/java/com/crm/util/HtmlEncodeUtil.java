package com.crm.util;

public class HtmlEncodeUtil {

	
	public static String htmlEncode(String string) {
		if(null == string || "".equals(string))
			return null;
		else{
			String result = string;
			result = result.replaceAll(" ", "&nbsp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
			result = result.replaceAll("'", "&#39;");
			result = result.replaceAll("\n", "<br>");
			return (result.toString());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(HtmlEncodeUtil.htmlEncode("<script>alert('hello');</script>"));
	}
}
