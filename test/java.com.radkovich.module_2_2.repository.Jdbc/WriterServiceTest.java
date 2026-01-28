import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.model.Writer;
import main.com.radkovich.module_2_3.repository.WriterRepository;
import main.com.radkovich.module_2_3.service.WriterService;
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
import static org.mockito.Mockito.times;

public class WriterServiceTest {
    @Mock
    private WriterRepository writerRepository;
    private WriterService writerService;
    private Writer testWriter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        writerService = new WriterService(writerRepository);
        testWriter = new Writer(1L, "Test FN", "Test LN", new ArrayList<>(), Status.ACTIVE);
    }

    @Test
    public void testGetById() {
        when(writerRepository.getById(anyLong())).thenReturn(testWriter);

        Writer writer = writerService.getWriterById(1L);

        assertNotNull(writer);
        assertEquals("Test FN", writer.getFirstName());
        assertEquals("Test LN", writer.getLastName());
        assertEquals(Status.ACTIVE, writer.getStatus());
    }

    @Test
    public void testGetAll() {
        List<Writer> writers = new ArrayList<>();
        writers.add(testWriter);

        when(writerRepository.getAll()).thenReturn(writers);

        List<Writer> result = writerService.getAllWriters();

        assertNotNull(result);
        assertEquals(writers.size(), result.size());
        assertEquals("Test FN", result.get(0).getFirstName());
        assertEquals("Test LN", result.get(0).getLastName());
        assertEquals(Status.ACTIVE, result.get(0).getStatus());
    }

    @Test
    public void testSave() {
        when(writerRepository.save(any(Writer.class))).thenReturn(testWriter);

        Writer writer = writerService.saveWriter(testWriter);

        assertNotNull(writer);
        assertEquals("Test FN", writer.getFirstName());
        assertEquals("Test LN", writer.getLastName());
        assertEquals(Status.ACTIVE, writer.getStatus());

        verify(writerRepository, times(1)).save(testWriter);
    }

    @Test
    public void testUpdate() {
        Writer writer = new Writer(1L, "Test FN", "Test LN", new ArrayList<>(), Status.ACTIVE);

        when(writerRepository.update(any(Writer.class))).thenReturn(writer);

        Writer writerToUpdate = writerService.updateWriter(testWriter);
        assertNotNull(writerToUpdate);
        assertEquals(writer.getId(), writerToUpdate.getId());
        assertEquals("Test FN", writer.getFirstName());
        assertEquals("Test LN", writer.getLastName());
        assertEquals(Status.ACTIVE, writer.getStatus());

        verify(writerRepository, times(1)).update(testWriter);
    }

    @Test
    public void testDelete() {
        writerService.deleteWriterById(1L);
        verify(writerRepository, times(1)).deleteById(1L);
    }
}
