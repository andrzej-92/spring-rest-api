package agency.services.policies;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.stereotype.Service;

@Service
public class PolicyGenerator {

    class ThreadGenerator extends Thread {

        public void run() {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/invoices/default.twig.html");
            JtwigModel model = JtwigModel.newModel().with("name", "Andrew");

            String html = template.render(model);

            Pdf pdf = new Pdf();
            pdf.addPageFromString(html);

            try {
                pdf.saveAs("storage/policies/test.pdf");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void generate() {
        ThreadGenerator generator = new ThreadGenerator();
        generator.start();
    }
}
