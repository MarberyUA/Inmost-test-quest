package com.dev.testquest.service.impl;

import com.dev.testquest.dao.StatusDao;
import com.dev.testquest.model.Status;
import com.dev.testquest.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusDao statusDao;

    @Override
    public Status create(Status status) {
        return statusDao.create(status);
    }

    @Override
    public Status getByStatusName(String statusName) {
        return statusDao.getByStatusName(statusName);
    }
}
