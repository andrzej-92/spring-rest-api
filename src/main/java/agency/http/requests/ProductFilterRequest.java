package agency.http.requests;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductFilterRequest {

    @Getter @Setter
    private boolean active;

}
