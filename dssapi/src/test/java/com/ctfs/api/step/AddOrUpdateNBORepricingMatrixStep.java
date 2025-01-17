package com.ctfs.api.step;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.offerservices.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.dss.utils.AtomicServicesDBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class AddOrUpdateNBORepricingMatrixStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(AddOrUpdateNBORepricingMatrixStep.class);
	
	@Autowired
	private AddOrUpdateNboRepricingMatrixRequest requestObj;
	
	static AddOrUpdateNboRepricingMatrixRequest res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
	
    @Autowired
    private AddOrUpdateNBORepricingMatrixService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to addorUpdateANBORepricingMatrix request {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
	public void post_operation(String alternateDesc, 
			String clientProdCode, 
			String currentPrimaryDisclosure, 
			String cycle, 
			String id, 
			String newAltDisclosure, 
			String newPrimaryDisclosure, 
			String offerCode, 
			String offerStatus, 
			String primaryDescr, 
			String productChangeOption, 
			String promoId, 
			String retentionId, 
			String tempDuration, 
			String treatmentCode, 
			String treatmentDescription, 
			String tsysLetterId) throws Throwable {
//    	requestObj.setAlternateDesc(alternateDesc);
    	requestObj.setClientProdCode(clientProdCode);
    	requestObj.setCurrentPrimaryDisclosure(currentPrimaryDisclosure);
    	requestObj.setCycle(cycle);
    	requestObj.setId(id);
    	requestObj.setNewAltDisclosure(newAltDisclosure);
    	requestObj.setNewPrimaryDisclosure(newPrimaryDisclosure);
    	requestObj.setOfferCode(offerCode);
    	requestObj.setOfferStatus(offerStatus);
    	requestObj.setPrimaryDescr(primaryDescr);
    	requestObj.setProductChangeOption(productChangeOption);
    	requestObj.setPromoId(promoId);
    	requestObj.setRetentionId(retentionId);
    	requestObj.setTempDuration(tempDuration);
    	requestObj.setTreatmentCode(treatmentCode);
    	requestObj.setTreatmentDescription(treatmentDescription);
    	requestObj.setTsysLetterId(tsysLetterId);
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
			System.out.println("inside step one Here");
			service.addOrUpdateNboRM(requestObj);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    
    

	
	
	@Then("validate the status code as {string}")
	public void validate_addUpdateNbo(String statusCode) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("addupdateResponse");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			if(response.getStatusCode() ==200) {
				res_obj = response.getBody().as(AddOrUpdateNboRepricingMatrixRequest.class);
				if(res_obj.getId()!=null) {
						Assert.assertEquals(requestObj.getClientProdCode(),res_obj.getClientProdCode());	
						Assert.assertEquals(requestObj.getCurrentPrimaryDisclosure(),res_obj.getCurrentPrimaryDisclosure());
						Assert.assertEquals(requestObj.getCycle(),res_obj.getCycle());
						Assert.assertEquals(requestObj.getNewAltDisclosure(), res_obj.getNewAltDisclosure());
						Assert.assertEquals(requestObj.getPrimaryDescr(),res_obj.getPrimaryDescr() );
						Assert.assertEquals(requestObj.getOfferCode(),res_obj.getOfferCode() );
						Assert.assertEquals(requestObj.getOfferStatus(), res_obj.getOfferStatus());
						Assert.assertEquals(requestObj.getPrimaryDescr(), res_obj.getPrimaryDescr());
						Assert.assertEquals(requestObj.getProductChangeOption(), res_obj.getProductChangeOption());
						Assert.assertEquals(requestObj.getPromoId(),res_obj.getPromoId() );
						Assert.assertEquals(requestObj.getRetentionId(), res_obj.getRetentionId());
						Assert.assertEquals(requestObj.getTempDuration(), res_obj.getTempDuration());
						Assert.assertEquals(requestObj.getTreatmentCode(), res_obj.getTreatmentCode());
						Assert.assertEquals(requestObj.getTreatmentDescription(), res_obj.getTreatmentDescription());
						Assert.assertEquals(requestObj.getTsysLetterId(),res_obj.getTsysLetterId() );
//						System.out.println(AtomicServicesDBUtils.getRecord(Database.db_pduser, "CTFSNBOREPRICINGMATRIX","ID", requestObj.getId(), "TREATMENTCD"));
					}
				if(res_obj.getId()==null) {
					Assert.assertEquals(null,res_obj.getClientProdCode());	
					Assert.assertEquals(null,res_obj.getCurrentPrimaryDisclosure());
					Assert.assertEquals(null,res_obj.getCycle());
					Assert.assertEquals(null, res_obj.getNewAltDisclosure());
					Assert.assertEquals(null,res_obj.getPrimaryDescr() );
					Assert.assertEquals(null,res_obj.getOfferCode() );
					Assert.assertEquals(null, res_obj.getOfferStatus());
					Assert.assertEquals(null, res_obj.getPrimaryDescr());
					Assert.assertEquals(null, res_obj.getProductChangeOption());
					Assert.assertEquals(null,res_obj.getPromoId() );
					Assert.assertEquals(null, res_obj.getRetentionId());
					Assert.assertEquals(null, res_obj.getTempDuration());
					Assert.assertEquals(null, res_obj.getTreatmentCode());
					Assert.assertEquals(null, res_obj.getTreatmentDescription());
					Assert.assertEquals(null,res_obj.getTsysLetterId() );
				}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
