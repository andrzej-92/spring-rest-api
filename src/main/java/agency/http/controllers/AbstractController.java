package agency.http.controllers;
import org.springframework.beans.factory.annotation.Value;

class AbstractController {

    @Value("${pagination.per_page}")
    int perPage;
}
