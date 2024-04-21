package org.vas.notification.core.domain;

import org.vas.notification.core.ports.NotificationService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyByEmail(String message) {
        Log.infof("Sending email notification: %s", message);
    }
}
