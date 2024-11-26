package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class GetTermResponsePojo {
	
	private ArrayList<TermProductOutput> termProductOutput;
}
