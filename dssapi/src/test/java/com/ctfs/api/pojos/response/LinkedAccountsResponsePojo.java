package com.ctfs.api.pojos.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LinkedAccountsResponsePojo {
    private ArrayList<LinkedAccount> linkedAccounts;

}
