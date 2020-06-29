package com.dev.testquest.dao;

import com.dev.testquest.model.Status;

public interface StatusDao {
    Status create(Status status);

    Status getByStatusName(String statusName);
}
