package fontys.s3.Carspacebackend.domain;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TimeHelperTest {
    @BeforeEach()
    void freezeTime(){
        TimeHelper.EnterDebugMode();
    }

    @Test
    void testFreezeAndGetTime(){
        Instant time = TimeHelper.Now();
        Instant expectedTime = Instant.parse("2022-07-10T15:30:00.00Z");
        assertEquals(expectedTime, time);
    }

    @Test
    void testFreezeAndGetTimes(){
        Instant expectedTime = Instant.parse("2022-09-10T15:30:00.00Z");
        TimeHelper.SetTime("2022-09-10T15:30:00.00Z");
        Instant time = TimeHelper.Now();
        assertEquals(expectedTime, time);
    }

    @AfterEach
    public void resetTime(){
        TimeHelper.QuitDebugMode();
    }

}
