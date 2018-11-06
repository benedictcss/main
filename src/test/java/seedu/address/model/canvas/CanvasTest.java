package seedu.address.model.canvas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalOperationException;
import seedu.address.model.ModelManager;
import seedu.address.testutil.CanvasGenerator;
import seedu.address.testutil.PreviewImageGenerator;
import seedu.address.testutil.TypicalImages;

//@author j-lum

class CanvasTest {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    @Test
    void getLayers() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        assertTrue(canvas.getLayers() instanceof ArrayList);
        assertTrue(canvas.getLayers().size() == 1);
    }

    @Test
    void addLayer() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        canvas.addLayer(PreviewImageGenerator.getPreviewImage(TypicalImages.IMAGE_LIST[1]));
        assertTrue(canvas.getLayers().size() == 2);
    }

    @Test
    void getCurrentLayer() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        assertTrue(canvas.getCurrentLayer() instanceof Layer);
    }

    @Test
    void setCurrentLayer() {
        Canvas canvas = CanvasGenerator.getCanvasWithTwoLayers();
        canvas.setCurrentLayer(Index.fromOneBased(2));
        assertTrue(canvas.getCurrentLayer().getName().equals("Layer 2"));
    }

    @Test
    void removeLayer() {
        Canvas canvas = CanvasGenerator.getCanvasWithTwoLayers();
        try {
            canvas.removeLayer(Index.fromOneBased(2));
        } catch (IllegalOperationException e) {
            logger.severe("This should not happen!");
            assertNull(e);
        }
        assertTrue(canvas.getLayers().size() == 1);
    }

    @Test
    void swapLayer() {
        Canvas canvas = CanvasGenerator.getCanvasWithTwoLayers();
        Index to = Index.fromOneBased(1);
        Index from = Index.fromOneBased(2);
        try {
            canvas.swapLayer(to, from);
        } catch (IllegalOperationException e) {
            assertNull(e);
        }
        canvas.setCurrentLayer(to);
        assertTrue(canvas.getCurrentLayer().getName().equals("Layer 2"));
        assertThrows(IllegalOperationException.class, ()->canvas.swapLayer(to, to));
    }

    @Test
    void getHeight() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        assertTrue(canvas.getCurrentLayer().getHeight() == canvas.getHeight());
    }

    @Test
    void setHeight() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        int newHeight = 100;
        canvas.setHeight(newHeight);
        assertTrue(canvas.getHeight() == newHeight);
    }

    @Test
    void getWidth() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        assertTrue(canvas.getCurrentLayer().getWidth() == canvas.getWidth());
    }

    @Test
    void setWidth() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        int newWidth = 100;
        canvas.setHeight(newWidth);
        assertTrue(canvas.getHeight() == newWidth);
    }

    @Test
    void isCanvasAuto() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        assertFalse(canvas.isCanvasAuto());
    }

    @Test
    void setCanvasAuto() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        canvas.setCanvasAuto(true);
        assertTrue(canvas.isCanvasAuto());
    }

    @Test
    void getBackgroundColor() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        assertTrue(canvas.getBackgroundColor().equals("none"));
    }

    @Test
    void setBackgroundColor() {
        Canvas canvas = CanvasGenerator.getInitialCanvas();
        String newColor = "rgba(0,0,0,0.0)";
        canvas.setBackgroundColor(newColor);
        assertTrue(canvas.getBackgroundColor().equals(newColor));
    }
}