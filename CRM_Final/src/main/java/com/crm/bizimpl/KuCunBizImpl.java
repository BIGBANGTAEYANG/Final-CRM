package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.crm.bean.KuCun;
import com.crm.biz.KuCunBiz;
import com.crm.web.model.PageModel;

@Service
public class KuCunBizImpl extends BaseBizImpl implements KuCunBiz {
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public PageModel<KuCun> searchKuCun(Map<String, Object> map) {
		PageModel<KuCun> kuCunModel = new PageModel<KuCun>();

		int currPage = Integer.parseInt(map.get("page").toString());
		int sizePage = Integer.parseInt(map.get("rows").toString());

		int count = baseDao.getCount(KuCun.class, map, "getKuCunConditionCount");
		kuCunModel.setTotalCount(count);

		int total = count % sizePage == 0 ? count / sizePage : count / sizePage + 1;
		kuCunModel.setTotal(total);
		int offset = (currPage - 1) * sizePage;
		List<KuCun> cs=baseDao.findList(KuCun.class, map, "getKuCunCondition", offset, sizePage);
		kuCunModel.setList(cs);
		kuCunModel.setPage(currPage);
		return kuCunModel;
	}


}
