<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@page import="com.crm.bean.UserInfo" %>


 <% 
 
 HttpSession Session = request.getSession();
 UserInfo user = (UserInfo)Session.getAttribute("loginuser");
 if("系统管理员".equals(user.getRole().getTiaomu())){
	 response.getWriter().write("[{\"text\":\"客户关系管理系统\",\"state\":\"open\",\"children\":[{\"text\":\"基础数据\",\"state\":\"closed\",\"children\":[{\"text\":\"数据字典管理\"},{\"text\":\"用户管理\"}]}]}]"); 
	 //response.getWriter().write("[{\"text\":\"客户关系管理系统\",\"state\":\"open\",\"children\":[{\"text\":\"营销管理\",\"state\":\"closed\",\"children\":[{\"text\":\"销售机会管理\"},{\"text\":\"客户开发计划\"}]},{\"text\":\"客户管理\",\"state\":\"closed\",\"children\":[{\"text\":\"客户信息管理\"},{\"text\":\"客户流失管理\"}]},{\"text\":\"服务管理\",\"state\":\"closed\",\"children\":[{\"text\":\"服务创建\"},{\"text\":\"服务分配\"},{\"text\":\"服务处理\"},{\"text\":\"服务反馈\"},{\"text\":\"服务归档\"}]},{\"text\":\"统计报表\",\"state\":\"closed\",\"children\":[{\"text\":\"客户贡献分析\"},{\"text\":\"客户构成分析\"},{\"text\":\"客户服务分析\"},{\"text\":\"客户流失分析\"}]},{\"text\":\"基础数据\",\"state\":\"closed\",\"children\":[{\"text\":\"数据字典管理\"},{\"text\":\"查询产品信息\"},{\"text\":\"查询库存\"}]}]}]");  
	  }else if("高管".equals(user.getRole().getTiaomu())){
		 response.getWriter().write("[{\"text\":\"客户关系管理系统\",\"state\":\"open\",\"children\":[{\"text\":\"统计报表\",\"state\":\"closed\",\"children\":[{\"text\":\"客户贡献分析\"},{\"text\":\"客户构成分析\"},{\"text\":\"客户服务分析\"},{\"text\":\"客户流失分析\"}]}]}]");  
	  }else if("销售主管".equals(user.getRole().getTiaomu())){
			 response.getWriter().write("[{\"text\":\"客户关系管理系统\",\"state\":\"open\",\"children\":[{\"text\":\"营销管理\",\"state\":\"closed\",\"children\":[{\"text\":\"销售机会管理\"},{\"text\":\"客户开发计划\"}]},{\"text\":\"服务管理\",\"state\":\"closed\",\"children\":[{\"text\":\"服务分配\"},{\"text\":\"服务归档\"}]},{\"text\":\"统计报表\",\"state\":\"closed\",\"children\":[{\"text\":\"客户贡献分析\"},{\"text\":\"客户构成分析\"},{\"text\":\"客户服务分析\"},{\"text\":\"客户流失分析\"}]}]}]");  
		}else if("客户经理".equals(user.getRole().getTiaomu())){
			 response.getWriter().write("[{\"text\":\"客户关系管理系统\",\"state\":\"open\",\"children\":[{\"text\":\"营销管理\",\"state\":\"closed\",\"children\":[{\"text\":\"销售机会管理\"},{\"text\":\"客户开发计划\"}]},{\"text\":\"客户管理\",\"state\":\"closed\",\"children\":[{\"text\":\"客户信息管理\"},{\"text\":\"客户流失管理\"}]},{\"text\":\"服务管理\",\"state\":\"closed\",\"children\":[{\"text\":\"服务创建\"},{\"text\":\"服务处理\"},{\"text\":\"服务反馈\"},{\"text\":\"服务归档\"}]},{\"text\":\"基础数据\",\"state\":\"closed\",\"children\":[{\"text\":\"查询产品信息\"},{\"text\":\"查询库存\"}]}]}]");  		
		}
%>  