package com.company.pastebook.repositories;

import com.company.pastebook.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository <Notification, Object> {
}
