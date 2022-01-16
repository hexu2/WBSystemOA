package com.njwangbo.oa.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.entity.WorkFlowNode;
import com.njwangbo.oa.entity.WorkNodeAction;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.WorkFlowService;

public class WorkFlowAction {
	WorkFlowService workFlowService = (WorkFlowService) ApplicationContext
			.getBean("workFlowService");

	/**
	 * 查询历史审批记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryHistoryAudit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<WorkNodeAction> historyList = new ArrayList<WorkNodeAction>();
		String tableID = request.getParameter("tableID");
		String workID = request.getParameter("workID");

		// System.out.println("tableID:" + tableID + ",workID:" + workID);
		historyList = workFlowService.queryHistoryByTableIdAndWorkId(tableID,
				workID);
		request.setAttribute("historyList", historyList);
		for (WorkNodeAction workNodeAction : historyList) {
			System.out.println("getId" + workNodeAction.getId());
			System.out.println("NodeID" + workNodeAction.getNodeID());
			System.out.println("NodeName" + workNodeAction.getNodeName());
			System.out.println("TableID" + workNodeAction.getTableID());
			System.out.println("OpenTime" + workNodeAction.getOpenTime());
			System.out.println("CloseTime" + workNodeAction.getCloseTime());
			System.out.println("Status" + workNodeAction.getStatus());
			System.out.println("StatusName" + workNodeAction.getStatusName());
			System.out.println("WaitingUsers"
					+ workNodeAction.getWaitingUsers());
			System.out.println("WaitingUsersName"
					+ workNodeAction.getWaitingUsersName());
			System.out.println("getDealUser" + workNodeAction.getDealUser());
			System.out.println("DealUserName"
					+ workNodeAction.getDealUserName());
			System.out.println("DealAdvices" + workNodeAction.getDealAdvices());
			System.out.println("CreateTime" + workNodeAction.getCreateTime());
			System.out
					.println("_______________________________________________________");

		}
		return "success";
	}

	/**
	 * 获取当前审批的数据节点名称
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String auditBefor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取工作流名称
		String workID = request.getParameter("workID");

		// 获取当前审批的数据节点名称
		String tableID = request.getParameter("tableID");

		WorkFlowNode node = workFlowService.queryCurrentNode(tableID, Integer
				.valueOf(workID));
		System.out.println("node"+node);
		if(null == node){
			request.setAttribute("errorMsg", "该申请人此记录尚未提交");
			return "unSubmit";
		}
		// 从数据库获取工作流名称
		String workFlowName = workFlowService.queryByworkId(Integer
				.valueOf(workID));
		request.setAttribute("workName", workFlowName);
		request.setAttribute("nodeName", node.getNodeName());
		request.setAttribute("workID", workID);
		request.setAttribute("tableID", tableID);
		request.setAttribute("nodeID", node.getNodeID());

		if (node.getNodeID() == 105 || node.getNodeID() == 205
				|| node.getNodeID() == 305) {
			request.setAttribute("errorMsg", "该工作流已经结束");
			return "error";
		}
		
		// 控制审批权限
		int dealUser = ((User) request.getSession().getAttribute("user")).getId();// 获得当前用户id
		System.out.println(dealUser);
		String[] waitingUserStr = node.getWaitingUser().split(",");// 获得待审批用户id
		boolean flag = false;
		for (String string : waitingUserStr) {// String 代表待处理人的id为String类型
			if (dealUser == Integer.valueOf(string)) {
				flag = true;
			}
		}
		while(!flag){
			request.setAttribute("errorMsg", "对不起没有审批权限");
			return "error";
		}
		return "success";
	}

	/**
	 * 关闭当前节点打开下一个节点
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String audit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String workID = request.getParameter("workID");
		String tableID = request.getParameter("tableID");
		String dealAdvices = request.getParameter("dealAdvices");
		String dealType = request.getParameter("dealType");
		int dealUser = ((User) request.getSession().getAttribute("user"))
				.getId();
		try {
			workFlowService.nextNode(tableID, Integer.valueOf(workID), String
					.valueOf(dealUser), dealAdvices, dealType);
			
			request.setAttribute("operator", "1000");
			request.setAttribute("workID", workID);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operator", "9999");
			request.setAttribute("workID", workID);

		}
		return "success";
	}

}
