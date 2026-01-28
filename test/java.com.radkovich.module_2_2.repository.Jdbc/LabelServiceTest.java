import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.repository.LabelRepository;
import main.com.radkovich.module_2_3.service.LabelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class LabelServiceTest {

    @Mock
    private LabelRepository labelRepository;
    private LabelService labelService;
    private Label testLabel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        labelService = new LabelService(labelRepository);
        testLabel = new Label(1L, "Test name", Status.ACTIVE);
    }

    @Test
    public void testGetById() {
        when(labelRepository.getById(anyLong())).thenReturn(testLabel);

        Label label = labelService.getLabelById(1L);

        assertNotNull(label);
        assertEquals("Test name", label.getName());
        assertEquals(Status.ACTIVE, label.getStatus());
    }

    @Test
    public void testGetAll() {
        List<Label> labels = new ArrayList<>();
        labels.add(testLabel);

        when(labelRepository.getAll()).thenReturn(labels);

        List<Label> result = labelService.getAllLabels();

        assertNotNull(result);
        assertEquals(labels.size(), result.size());
        assertEquals("Test name", result.get(0).getName());
        assertEquals(Status.ACTIVE, result.get(0).getStatus());
    }

    @Test
    public void testSave() {
        when(labelRepository.save(any(Label.class))).thenReturn(testLabel);

        Label label = labelService.saveLabel(testLabel);

        assertNotNull(label);
        assertEquals("Test name", label.getName());
        assertEquals(Status.ACTIVE, label.getStatus());

        verify(labelRepository, times(1)).save(testLabel);
    }

    @Test
    public void testUpdate() {
        Label label = new Label(1L, "Test name for update", Status.ACTIVE);

        when(labelRepository.update(any(Label.class))).thenReturn(label);

        Label labelToUpdate = labelService.updateLabel(label);

        assertNotNull(labelToUpdate);
        assertEquals("Test name for update", labelToUpdate.getName());

        verify(labelRepository, times(1)).update(label);
    }

    @Test
    public void testDelete() {
        labelService.deleteLabelById(1L);
        verify(labelRepository, times(1)).deleteById(1L);
    }
}
