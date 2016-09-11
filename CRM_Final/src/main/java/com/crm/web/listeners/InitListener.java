package com.crm.web.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.crm.bean.DataDirectory;
import com.crm.biz.DataDirectoryBiz;
import com.crm.util.LuceneUtil;


public class InitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();
		// 取出spring容器
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		if (ac != null) {
			DataDirectoryBiz biz = (DataDirectoryBiz) ac.getBean("dataDirectoryBizImpl");
			Map<String, List<DataDirectory>> map = biz.getAllDataDirectory();
			if (map!=null && !map.isEmpty()) {
				sc.setAttribute("dataDirectory", map);
			}
			List<DataDirectory> list=new ArrayList<DataDirectory>();
			Iterator<String> keys=map.keySet().iterator();
			while (keys.hasNext()) {
				String key=keys.next();
				list.add(map.get(key).get(0));
			}
			LuceneUtil lucene=new LuceneUtil(list, sc.getRealPath("/"));
			sc.setAttribute("dataDirectoryType", list);
			try {
				lucene.index();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}
