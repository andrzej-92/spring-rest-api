package agency.services.policies;

import agency.entity.Owner;
import agency.repositories.OwnerRepository;
import agency.repositories.PoliciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agency.entity.Policy;

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
}
