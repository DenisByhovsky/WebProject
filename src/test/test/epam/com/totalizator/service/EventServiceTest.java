package test.epam.com.totalizator.service;

import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.EventService;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class EventServiceTest {

    private static final Logger LOGGER = LogManager.getLogger(EventServiceTest .class.getName());

    private EventService eventService = ServiceFactory.getInstance().getEventService();

    @Test
    public void takeActiveTariffsTest() {
        try {
            Assert.assertNotNull(eventService.fetchAllFinishedMatches());
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,"Error in test fetch all finished matches");
        }
    }
}
