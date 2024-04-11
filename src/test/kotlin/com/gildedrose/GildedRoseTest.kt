package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.StringBuilder

internal class GildedRoseTest {

    private val sb: StringBuilder = StringBuilder()

    @Test
    fun foo() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)

    }

    @Test
    fun `no regression test`() {
        val expectedString = """
            OMGHAI!
            -------- day 0 --------
            name, sellIn, quality
            +5 Dexterity Vest, 10, 20
            Aged Brie, 2, 0
            Elixir of the Mongoose, 5, 7
            Sulfuras, Hand of Ragnaros, 0, 80
            Sulfuras, Hand of Ragnaros, -1, 80
            Backstage passes to a TAFKAL80ETC concert, 15, 20
            Backstage passes to a TAFKAL80ETC concert, 10, 49
            Backstage passes to a TAFKAL80ETC concert, 5, 49
            Conjured Mana Cake, 3, 6

            -------- day 1 --------
            name, sellIn, quality
            +5 Dexterity Vest, 9, 19
            Aged Brie, 1, 1
            Elixir of the Mongoose, 4, 6
            Sulfuras, Hand of Ragnaros, 0, 80
            Sulfuras, Hand of Ragnaros, -1, 80
            Backstage passes to a TAFKAL80ETC concert, 14, 21
            Backstage passes to a TAFKAL80ETC concert, 9, 50
            Backstage passes to a TAFKAL80ETC concert, 4, 50
            Conjured Mana Cake, 2, 5
            
            
        """.trimIndent()

        println("OMGHAI!")

        val items = listOf(
            Item("+5 Dexterity Vest", 10, 20), //
            Item("Aged Brie", 2, 0), //
            Item("Elixir of the Mongoose", 5, 7), //
            Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            Item("Sulfuras, Hand of Ragnaros", -1, 80),
            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            Item("Conjured Mana Cake", 3, 6)
        )

        val app = GildedRose(items)
        val days = 2

        for (i in 0..days - 1) {
            println("-------- day $i --------")
            println("name, sellIn, quality")
            for (item in items) {
                println(item)
            }
            println()
            app.updateQuality()
        }

        assertEquals(expectedString, sb.toString())
    }

    private fun println(s: Any = "") {
        sb.append(s.toString())
        sb.append("\n")
    }

}
