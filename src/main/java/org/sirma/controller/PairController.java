package org.sirma.controller;
/*
import com.opencsv.exceptions.CsvException;
import org.sirma.data.PairData;
import org.sirma.exception.ParseDateException;
import org.sirma.service.CSVFileParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PairController {

    private final CSVFileParser csvFileParser;

    public PairController(CSVFileParser csvFileParser) {
        this.csvFileParser = csvFileParser;
    }

    @GetMapping("/pair")
    public String veiwPairs(Model model) {
       return "hello";
    }

	@PostMapping("/upload")
    public ModelAndView fileUpload(@RequestParam("file") final MultipartFile file) {
        if (file.isEmpty()) {
            //return new ModelAndView("status", "message", "Please select a file and upload again");
        }

        List<PairData> pairs = new ArrayList<>();
        try {
            pairs = csvFileParser.parseCSVFile(file);
            pairs.forEach(e -> System.out.println(e + " : " + ChronoUnit.DAYS.between(e.getBDate(), e.getEDate())));
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (ParseDateException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        final var list = new ArrayList<Object>();
        list.add(pairs);
        final var view = new ModelAndView("status", "pairs", list);
        //model.addAttribute("pairs", list);
        //model.addAttribute("message", "Fuck");
        return view;
    }
    }

    package org.sirma.controller;
*/
import com.opencsv.exceptions.CsvException;
import org.sirma.data.PairData;
import org.sirma.exception.ParseDateException;
import org.sirma.service.CSVFileParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/")
public class PairController {

    private final CSVFileParser csvFileParser;

    public PairController(CSVFileParser csvFileParser) {
        this.csvFileParser = csvFileParser;
    }

    @GetMapping("/pair")
    public String veiwPairs(Model model) {
        return "hello";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
           // return new ModelAndView("status", "message", "Please select a file and upload again");
        }

        try {
            // read and write the file to the selected path folder
            byte[] bytes = file.getBytes();
            String body = new String(bytes);

            final var pairs = csvFileParser.parseCSVFile(file);
            pairs.forEach(e -> System.out.println(e + " : " + ChronoUnit.DAYS.between(e.getBDate(), e.getEDate())));
            //Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            //Files.write(path, bytes);
            System.out.println("FUCK");
            model.addAttribute("data", pairs);
            model.addAttribute("message", "Dimitar");
;            return "status";//ModelAndView("status", "data", pairs);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (ParseDateException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

