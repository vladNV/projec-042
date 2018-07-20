package com.example.app.service.impl;

import com.example.app.domain.Requisition;
import com.example.app.repository.RequisitionRepository;
import com.example.app.service.RequisitionService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequisitionServiceImpl implements RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Override
    public void createNewRequisition(@NonNull Requisition requestDocument) {
        requisitionRepository.save(requestDocument);
    }

    @Override
    public List<Requisition> retrieveRequestDocument() {
        return ListUtils.emptyIfNull(requisitionRepository.findAll());
    }
}
