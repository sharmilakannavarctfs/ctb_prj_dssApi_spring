package com.ctfs.api.session;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ctfs.api.pojos.request.GetPortfoliosRequestPojo;
import com.ctfs.api.pojos.request.GetTermInterestRatesRequestPojo;
import com.ctfs.api.pojos.request.GetRegisteredPlanTransactionsRequestPojo;
import com.ctfs.api.pojos.request.GetTermRequestPojo;
import com.ctfs.api.pojos.request.LinkedAccountsRequestPojo;
import com.ctfs.api.pojos.request.PortfolioDetailsRequestPojo;
import com.ctfs.api.pojos.request.ProductDetailsRequestPojo;
import com.ctfs.api.pojos.request.ProductTransactionsRequestPojo;
import com.ctfs.api.pojos.request.ProductsByCategoryRequestPojo;
import com.ctfs.api.pojos.request.CustomerProductsRequestPojo;
import com.ctfs.api.pojos.request.DemandInterestRateRequestPojo;
import com.ctfs.api.pojos.request.DemandPeriodicLineFeaturesRequestPojo;


@Component
public class SessionContext {

	private String jwtToken;
	private String webSessionID;
	private List<GetPortfoliosRequestPojo> portfoliosRequests;
	private List<PortfolioDetailsRequestPojo> portfolioDetailsRequests;
	private List<ProductDetailsRequestPojo> productDetailsRequests;
	private List<LinkedAccountsRequestPojo> linkedAccountsRequests;
	private List<ProductTransactionsRequestPojo> productTransactionsRequests;
	private List<GetTermInterestRatesRequestPojo> termInterestRatesRequests;
	private List<GetRegisteredPlanTransactionsRequestPojo> registeredPlanTransactionsRequest;
	private List<GetTermRequestPojo> TermRequests;
	private List<ProductsByCategoryRequestPojo> ProductsByCategoryRequest;
	private List<CustomerProductsRequestPojo> customerProductsRequests;
	private List<DemandInterestRateRequestPojo> demandInterestRatesRequests;
	private List<DemandPeriodicLineFeaturesRequestPojo> demandPeriodicLineFeaturesRequests;
	
	public List<DemandPeriodicLineFeaturesRequestPojo> getDemandPeriodicLineFeaturesRequests() {
		return demandPeriodicLineFeaturesRequests;
	}
	public void setDemandPeriodicLineFeaturesRequests(
			List<DemandPeriodicLineFeaturesRequestPojo> demandPeriodicLineFeaturesRequests) {
		this.demandPeriodicLineFeaturesRequests = demandPeriodicLineFeaturesRequests;
	}
	public List<DemandInterestRateRequestPojo> getDemandInterestRatesRequests() {
		return demandInterestRatesRequests;
	}
	public void setDemandInterestRatesRequests(List<DemandInterestRateRequestPojo> demandInterestRatesRequests) {
		this.demandInterestRatesRequests = demandInterestRatesRequests;
	}
	public List<CustomerProductsRequestPojo> getCustomerProductsRequests() {
		return customerProductsRequests;
	}
	public void setCustomerProductsRequests(List<CustomerProductsRequestPojo> customerProductsRequests) {
		this.customerProductsRequests = customerProductsRequests;
	}
	public List<ProductsByCategoryRequestPojo> getProductsByCategoryRequest() {
		return ProductsByCategoryRequest;
	}
	public void setProductsByCategoryRequest(List<ProductsByCategoryRequestPojo> productsByCategoryRequest) {
		ProductsByCategoryRequest = productsByCategoryRequest;
	}
	public List<GetTermRequestPojo> getTermRequests() {
		return TermRequests;
	}
	public void setTermRequests(List<GetTermRequestPojo> termRequests) {
		TermRequests = termRequests;
	}
	public List<GetTermInterestRatesRequestPojo> getTermInterestRatesRequests() {
		return termInterestRatesRequests;
	}
	public void setTermInterestRatesRequests(List<GetTermInterestRatesRequestPojo> termInterestRatesRequests) {
		this.termInterestRatesRequests = termInterestRatesRequests;
	}
	public List<GetRegisteredPlanTransactionsRequestPojo> getRegisteredPlanTransactionsRequest() {
		return registeredPlanTransactionsRequest;
	}
	public void setRegisteredPlanTransactionsRequest(
			List<GetRegisteredPlanTransactionsRequestPojo> registeredPlanTransactionsRequest) {
		this.registeredPlanTransactionsRequest = registeredPlanTransactionsRequest;
	}
	public List<ProductTransactionsRequestPojo> getProductTransactionsRequests() {
		return productTransactionsRequests;
	}
	public void setProductTransactionsRequests(List<ProductTransactionsRequestPojo> productTransactionsRequests) {
		this.productTransactionsRequests = productTransactionsRequests;
	}
	public List<LinkedAccountsRequestPojo> getLinkedAccountsRequests() {
		return linkedAccountsRequests;
	}
	public void setLinkedAccountsRequests(List<LinkedAccountsRequestPojo> linkedAccountsRequests) {
		this.linkedAccountsRequests = linkedAccountsRequests;
	}
	public List<ProductDetailsRequestPojo> getProductDetailsRequests() {
		return productDetailsRequests;
	}
	public void setProductDetailsRequests(List<ProductDetailsRequestPojo> productDetailsRequests) {
		this.productDetailsRequests = productDetailsRequests;
	}
	public List<PortfolioDetailsRequestPojo> getPortfolioDetailsRequests() {
		return portfolioDetailsRequests;
	}
	public void setPortfolioDetailsRequests(List<PortfolioDetailsRequestPojo> portfolioDetailsRequests) {
		this.portfolioDetailsRequests = portfolioDetailsRequests;
	}
	public List<GetPortfoliosRequestPojo> getPortfoliosRequests() {
		return portfoliosRequests;
	}
	public void setPortfoliosRequests(List<GetPortfoliosRequestPojo> portfoliosRequests) {
		this.portfoliosRequests = portfoliosRequests;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getWebSessionID() {
		return webSessionID;
	}
	public void setWebSessionID(String webSessionID) {
		this.webSessionID = webSessionID;
	}
	@Override
	public String toString() {
		return "SessionContext [jwtToken=" + jwtToken + ", webSessionID=" + webSessionID + "]";
	}
}
