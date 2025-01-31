package io.github.evgenyjdev.chemvis.controller;

import io.github.evgenyjdev.chemvis.request.ChemDrawReq;
import io.github.evgenyjdev.chemvis.service.DrawService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/draw")
public class DrawController {

    private final DrawService drawService;

    private final Logger logger = LoggerFactory.getLogger(DrawController.class);

    @Autowired
    public DrawController(DrawService drawService) {
        this.drawService = drawService;
    }

    private ResponseEntity<byte[]> prepareResponse(byte[] image) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/svg+xml");
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<byte[]> drawSmiles(@RequestParam(value = "smiles") String smiles) {
        logger.info("GET: drawing molecule by SMILES: " + smiles);
        byte[] svg = drawService.draw(smiles);
        return prepareResponse(svg);
    }

    @PostMapping
    public ResponseEntity<byte[]> drawAny(@RequestBody ChemDrawReq chemDrawReq) {
        logger.info("POST: drawing molecule by description: " + chemDrawReq.getDescription());
        String descriptionStr = chemDrawReq.getDescription();
        byte[] image = drawService.draw(descriptionStr);
        return prepareResponse(image);
    }

    @PostMapping("by_text")
    public ResponseEntity<byte[]> drawAny(@RequestParam("description") String description) {
        logger.info("POST: drawing molecule by description: " + description);
        byte[] image = drawService.draw(description);
        return prepareResponse(image);
    }

    @PostMapping("by_file")
    public ResponseEntity<byte[]> drawAnyByFile(@RequestParam("description") MultipartFile description)
                throws IOException {
        String descriptionStr = new String(description.getBytes());
        logger.info("GET: drawing molecule by description in file: " + descriptionStr);
        byte[] image = drawService.draw(descriptionStr);
        return prepareResponse(image);
    }

}
