package com.crm.biz;

import java.util.Map;

import com.crm.bean.KuCun;
import com.crm.web.model.PageModel;

public interface KuCunBiz {
	public PageModel<KuCun> searchKuCun(Map<String, Object> map);
}
