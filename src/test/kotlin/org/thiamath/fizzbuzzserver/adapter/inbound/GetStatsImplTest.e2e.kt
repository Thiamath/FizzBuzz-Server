package org.thiamath.fizzbuzzserver.adapter.inbound

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.thiamath.fizzbuzzserver.adapter.outbound.StatsStoreImpl

@SpringBootTest
@AutoConfigureMockMvc
class GetStatsImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var statsStore: StatsStoreImpl

    @AfterEach
    fun clearStatsStore() {
        statsStore.clear()
    }

    @Test
    fun `should return stats response`() {
        mockMvc.perform(get("/stats"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.mostUsed").isEmpty)

    }

    @Test
    fun `should return correct stats`() {
        // Having
        val mostUsed = mapOf("int1" to 1, "int2" to 2)
        listOf(
            mostUsed,
            mapOf("int1" to 3, "int2" to 4),
            mostUsed,
            mapOf("int1" to 7, "int2" to 8),
            mostUsed,
        ).forEach {
            mockMvc.perform(
                get("/fizzbuzz")
                    .param("int1", it["int1"].toString())
                    .param("int2", it["int2"].toString())
            )
                .andExpect(status().isOk)
        }

        // Expect
        mockMvc.perform(get("/stats"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.mostUsed").isNotEmpty)
            .andExpect(jsonPath("$.data.mostUsed.request.int1").value(mostUsed["int1"]))
            .andExpect(jsonPath("$.data.mostUsed.request.int2").value(mostUsed["int2"]))
            .andExpect(jsonPath("$.data.mostUsed.count").value(3))
    }

    @Test
    fun `should return any as most used if there's no most used one`() {
        // Having
        listOf(
            mapOf("int1" to 1, "int2" to 2),
            mapOf("int1" to 3, "int2" to 4),
            mapOf("int1" to 5, "int2" to 6),
            mapOf("int1" to 7, "int2" to 8),
            mapOf("int1" to 9, "int2" to 10),
        ).forEach {
            mockMvc.perform(
                get("/fizzbuzz")
                    .param("int1", it["int1"].toString())
                    .param("int2", it["int2"].toString())
            )
                .andExpect(status().isOk)
        }

        // Expect
        mockMvc.perform(get("/stats"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.mostUsed").isNotEmpty)
            .andExpect(jsonPath("$.data.mostUsed.count").value(1))
    }
}
