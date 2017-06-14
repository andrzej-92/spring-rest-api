package agency.entity.listeners;

import agency.entity.Policy;
import agency.services.policies.PolicyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.util.StringUtils;

import java.util.Date;

public class PolicyListener extends AbstractMongoEventListener<Policy> {

    @Autowired
    private PolicyGenerator generator;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Policy> event) {

        super.onBeforeSave(event);

        Object id = event.getDBObject().get("_id");

        if (StringUtils.isEmpty(id)) {
            event.getDBObject().put("createdAt", new Date());
            generator.generate();
        }

        event.getDBObject().put("updatedAt", new Date());
    }
}