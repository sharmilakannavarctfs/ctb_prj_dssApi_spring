package com.ctfs.api.step.ts2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.ts2.GetCustomerforCustomCodereqPojo;
import com.ctfs.api.pojos.request.ts2.Ts2custDetailInput;
import com.ctfs.api.pojos.response.GetCustomerPOJO;
import com.ctfs.api.service.ts2.GetCustomerForCustomCodeService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class GetCustomerForCustomCodeStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(GetCustomerForCustomCodeStep.class);

	@Autowired
	private GetCustomerforCustomCodereqPojo requestObj;

	static GetCustomerPOJO res_obj = null;

	//    @Autowired
	//    private DashProfileManagerUtils dpm ; 

	@Autowired
	private GetCustomerForCustomCodeService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to getCustomerforCustomCode request {string} {string} {string}")
	public void post_operation(String operatorId,
			String cardNbr, String custId) throws Throwable {
		if(!operatorId.equals("")) requestObj.setOperatorId(operatorId);
		List<String> cards = Arrays.asList(cardNbr.split(","));
		List<String> custs = Arrays.asList(custId.split(","));
		List<Ts2custDetailInput> custlist = new ArrayList<Ts2custDetailInput>();
		for (int i = 0; i<cards.size();i++) {
			Ts2custDetailInput cust = new Ts2custDetailInput();
			if(!cards.get(i).equals("")) cust.setCardNbr(cards.get(i));
			if(!custs.get(i).equals("")) cust.setCustId(custs.get(i));
			custlist.add(cust);
		}
		requestObj.setTs2custDetailInputs(custlist);
		service.getCustomerforCustomeCode(requestObj);
	}


	@Then("validate the getCustomerForCustomCode api status code as {int} for the cardnumbers {string} and statusMsg as {string}")
	public void validate(int statusCode, String custIds,String statusMsg) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("getCustomerforCustomCodeRes");
			Assert.assertEquals(statusCode,response.statusCode());
			if(response.getStatusCode() ==200) {
				res_obj = response.getBody().as(GetCustomerPOJO.class);
				if(res_obj!=null && !statusMsg.equals("")) {
					Assert.assertEquals(res_obj.getStatusMsg(), statusMsg);
					if (statusMsg.equals("passed")) {
						
						List<String> custIdList = Arrays.asList(custIds.split(","));
						for (int i = 0; i < custIdList.size(); i++) {
							Assert.assertEquals(res_obj.getCustomData().get(i).getCustId(), custIdList.get(i));
							Assert.assertNotNull(res_obj.getCustomData().get(i).getCodes());
							Assert.assertNotEquals(res_obj.getCustomData().get(i).getCodes().size(), 0);
						} 
					}
					if(statusMsg.equals("failed")) {
						List<String> custIdList = Arrays.asList(custIds.split(","));
						for (int i = 0; i < custIdList.size(); i++) {
							if(res_obj.getCustomData().get(i).getCustId().equals("null")) {
								Assert.assertEquals(res_obj.getCustomData().get(i).getCustId(), "null");
								Assert.assertEquals(res_obj.getCustomData().get(i).getCodes(), "null");
							}
						} 
					}
				}
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}





