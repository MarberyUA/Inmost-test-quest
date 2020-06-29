package com.dev.testquest.service;

import com.dev.testquest.model.Status;

public interface StatusService {
    Status create(Status status);

    Status getByStatusName(String statusName);
}
