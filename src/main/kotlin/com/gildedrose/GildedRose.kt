package com.gildedrose

const val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val BRIE = "Aged Brie"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->

            if (item.name != SULFURAS) {
                when {
                    item.name == BACKSTAGE && item.sellIn < 6 -> {
                        item.addQuality(3)
                    }

                    item.name == BACKSTAGE && item.sellIn < 11 -> {
                        item.addQuality(2)
                    }

                    item.name in listOf(BACKSTAGE, BRIE) -> item.addQuality(1)
                    else -> item.addQuality(-1)
                }

                item.sellIn -= 1

                if (item.sellIn < 0) {
                    if (item.name == BRIE) {
                        item.addQuality(1)
                    } else {
                        if (item.name != BACKSTAGE) {
                            item.addQuality(-1)
                        } else {
                            item.addQuality(-item.quality)
                        }
                    }
                }
            }
        }
    }

    private fun Item.addQuality(value: Int) {
        quality += value
        quality = quality.coerceIn(0, 50)
    }
}

