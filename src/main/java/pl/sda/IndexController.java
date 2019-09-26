package pl.sda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.*;
import java.util.Random;

@Controller
class IndexController {

    private final Votes votes;

    private final Random random = new Random();
    private final static Logger LOG = LoggerFactory.getLogger(IndexController.class);

    private final String color = String.format(
            "rgb(%s, %s, %s)",
            random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255)
    );

    {
        LOG.info("Initialized background with color {}.", color);
    }

    IndexController(Votes votes) {
        this.votes = votes;
    }

    private String readTimestamp() {

        String filename = "buildtime.txt";

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filename).getInputStream()))
        ) {
            String timestamp = reader.readLine();

            LOG.debug("Read timestamp: {} from file {}.", timestamp, filename);

            return timestamp;
        } catch (IOException e) {
            LOG.debug("Failed to read timestamp from file {}.", filename);
            return "";
        }
    }

    @GetMapping("/")
    public String home(final Model model) {

        LOG.debug("Rendering home view.");

        String timestamp = readTimestamp();

        model.addAttribute("color", color);
        model.addAttribute("cats", votes.getCats());
        model.addAttribute("dogs", votes.getDogs());
        model.addAttribute("timestamp", timestamp);
        return "index";
    }

    @GetMapping("/help")
    public String help(final Model model) {

        LOG.debug("Rendering help view.");

        String timestamp = readTimestamp();
        model.addAttribute("color", color);
        model.addAttribute("timestamp", timestamp);
        return "help";
    }

    @PostMapping("/cats")
    @ResponseStatus(HttpStatus.OK)
    public void cats() {
        votes.increaseCats();
        LOG.info("Total votes on cats increased to {}.", votes.getCats());
    }

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.OK)
    public void dogs() {
        votes.increaseDogs();
        LOG.info("Total votes on dogs increased to {}.", votes.getDogs());
    }

}
