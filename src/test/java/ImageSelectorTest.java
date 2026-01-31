import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageSelectorTest {

    ImageSelector selector = new ImageSelector();

    @Test
    public void testMorning() {
        // Happy Path
        Assertions.assertEquals("morning.jpg", selector.selectImage("09:00"));
        // Boundary
        Assertions.assertEquals("morning.jpg", selector.selectImage("07:00"));
    }

    @Test
    public void testNoon() {
        Assertions.assertEquals("noon.jpg", selector.selectImage("13:00"));
    }

    @Test
    public void testAfternoon() {
        Assertions.assertEquals("afternoon.jpg", selector.selectImage("15:00"));
    }

    @Test
    public void testEvening() {
        Assertions.assertEquals("evening.jpg", selector.selectImage("21:00"));
    }

    @Test
    public void testNight() {
        // before night
        Assertions.assertEquals("night.jpg", selector.selectImage("23:00"));
        // after night
        Assertions.assertEquals("night.jpg", selector.selectImage("02:00"));
    }

    @Test
    public void testToFail() {
        Assertions.assertEquals("night.jpgTest", selector.selectImage("23:00"));
    }

    @Test
    public void testInvalidInput() {
        Assertions.assertEquals("broken_watch.jpg", selector.selectImage("potato"));
        Assertions.assertEquals("broken_watch.jpg", selector.selectImage("25:00")); // invalid time
        Assertions.assertEquals("broken_watch.jpg", selector.selectImage(""));
        Assertions.assertEquals("broken_watch.jpg", selector.selectImage(null));
    }
}