package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `should conjured item decrease quality by 2 before sellin`() {
        val items = listOf(Item("Conjured test", 3, 3))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(1, app.items[0].quality)
        assertEquals(2, app.items[0].sellIn)

    }

    @Test
    fun `should conjured item decrease quality by 4 after sellin`() {
        val items = listOf(Item("Conjured test", 0, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
        assertEquals(-1, app.items[0].sellIn)
    }

}


