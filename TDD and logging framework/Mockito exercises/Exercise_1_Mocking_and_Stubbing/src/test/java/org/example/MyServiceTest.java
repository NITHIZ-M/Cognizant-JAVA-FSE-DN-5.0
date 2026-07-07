package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyServiceTest {

    @Test
    public void testExternalApi() {

        // Create Mock Object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub Method
        when(mockApi.getData()).thenReturn("Mock Data");

        // Inject Mock into Service
        MyService service = new MyService(mockApi);

        // Call Service Method
        String result = service.fetchData();

        // Verify Result
        assertEquals("Mock Data", result);
    }
}