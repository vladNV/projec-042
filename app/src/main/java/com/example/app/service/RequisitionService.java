package com.example.app.service;

import com.example.app.domain.Requisition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequisitionService {

    void createNewRequisition(Requisition requestDocument);

    List<Requisition> retrieveRequestDocument();

}
