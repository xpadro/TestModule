package xpadro.testmanager.runtime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SystemTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSystem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].processed", is(true)))
                .andExpect(jsonPath("$[0].belowThreshold", is(true)))
                .andExpect(jsonPath("$[1].processed", is(true)))
                .andExpect(jsonPath("$[1].associated", is(false)))
                .andExpect(jsonPath("$[2].processed", is(true)))
                .andExpect(jsonPath("$[2].belowThreshold", is(true)));
    }
}
