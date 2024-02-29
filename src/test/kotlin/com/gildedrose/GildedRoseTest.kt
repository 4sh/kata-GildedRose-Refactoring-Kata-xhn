package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `should conjured item decrease quality by 2 before sellin`() {
        assertQualityUpdate(item = Item("Conjured test", 3, 10), expectedQuality = 8)
    }

    @Test
    fun `should conjured item decrease quality by 4 after sellin`() {
        assertQualityUpdate(item = Item("Conjured test", 0, 10), expectedQuality = 6)
    }

    fun assertQualityUpdate(item: Item, expectedQuality: Int) {
        val sellIn = item.sellIn
        val app = GildedRose(listOf(item))
        app.updateQuality()
        assertEquals(
            expectedQuality,
            app.items[0].quality,
            "expected quality of item '${item.name}' was '$expectedQuality' but actual is '${item.quality}'"
        )
        assertEquals(sellIn - 1, app.items[0].sellIn)
    }

}


