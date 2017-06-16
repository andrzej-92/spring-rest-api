package agency.services.policies;

import agency.entity.Owner;
import agency.repositories.OwnerRepository;
import agency.repositories.PoliciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agency.entity.Policy;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PolicyService {

    @Autowired
    private PolicyGenerator generator;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PoliciesRepository policiesRepository;

    public void generatePDF(Policy policy) {

        Owner owner = this.ownerRepository.findAll().get(0);

        generator.setOwner(owner);
        generator.generate(policy);
    }

    public void markAsClosed(Policy policy) {

        policy.markAsClosed();
        this.policiesRepository.save(policy);
        this.generatePDF(policy);
        // make additional stuff here, send email, notifications... etc.
    }

    public ArrayList<Object> getRecentPoliciesHistory() {

        ArrayList<Object> history = new ArrayList<>();


        Calendar today = Calendar.getInstance();
        int current = today.get(Calendar.DAY_OF_YEAR);

        while (today.get(Calendar.DAY_OF_YEAR) > current - 7) {

            SimpleDateFormat formated = new SimpleDateFormat("YYYY-MM-dd");
            String date = formated.format(today.getTimeInMillis());

            SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM");
            String display = displayFormat.format(today.getTimeInMillis());

            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            Date from = Date.from(today.toInstant());

            today.set(Calendar.HOUR_OF_DAY, 23);
            today.set(Calendar.MINUTE, 59);
            today.set(Calendar.SECOND, 59);
            Date to = Date.from(today.toInstant());

            Map <String, Object> historyItem = new HashMap<>();
            historyItem.put("value", this.policiesRepository.findByClosetAtBeetween(from, to));
            historyItem.put("label", date);
            historyItem.put("display", display);
            history.add(historyItem);

            today.set(Calendar.DAY_OF_YEAR, today.get(Calendar.DAY_OF_YEAR) - 1);
        }

        return history;
    }
}
