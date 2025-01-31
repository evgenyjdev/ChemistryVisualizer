package io.github.evgenyjdev.chemvis.service;

import com.epam.indigo.Indigo;
import com.epam.indigo.IndigoObject;
import com.epam.indigo.IndigoRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawService {

    private final Indigo indigo;
    private final IndigoRenderer renderer;

    @Autowired
    public DrawService(Indigo indigo, IndigoRenderer renderer) {
        this.indigo = indigo;
        indigo.setOption("render-margins", 10, 10);
        indigo.setOption("render-output-format", "svg");
        this.renderer = renderer;
    }

    public byte[] draw(String description) {
        IndigoObject molecule = indigo.loadQueryMolecule(description);
        molecule.layout();
        return renderer.renderToBuffer(molecule);
    }
}
