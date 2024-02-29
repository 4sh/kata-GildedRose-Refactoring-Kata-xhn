package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ItemTypeTest {
    @Test
    fun foo() {
        assertItemType(Item(name = "+5 Dexterity Vest", sellIn = 10, quality = 20), ItemType.BASIC)
        assertItemType(Item(name = "Aged Brie", sellIn = 2, quality = 0), ItemType.BRIE)
        assertItemType(Item(name = "Elixir of the Mongoose", sellIn = 5, quality = 7), ItemType.BASIC)
        assertItemType(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80), ItemType.SULFURAS)
        assertItemType(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -1, quality = 80), ItemType.SULFURAS)
        assertItemType(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 15, quality = 20), ItemType.BACKSTAGE)
        assertItemType(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 49), ItemType.BACKSTAGE)
        assertItemType(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 49), ItemType.BACKSTAGE)
        assertItemType(Item(name = "Conjured Mana Cake", sellIn = 3, quality = 6), ItemType.CONJURED)
    }

    private fun assertItemType(item: Item, itemType: ItemType) {
        assertEquals(itemType, item.itemType)
    }

}