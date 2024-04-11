package com.gildedrose

private const val AGED_BRIE = "Aged Brie"
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
private const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == SULFURAS) continue
            when (item.name) {
                AGED_BRIE -> {
                    item.incrementQuality()
                }

                BACKSTAGE_PASS -> {
                    when (item.sellIn) {
                        in 0..5 -> item.incrementQuality(3)
                        in 6..10 -> item.incrementQuality(2)
                        else -> item.incrementQuality()
                    }
                }

                else -> {
                    item.decrementQuality()
                }
            }


            item.sellIn--

            if (item.sellIn < 0) {
                when (item.name) {
                    AGED_BRIE -> {
                        item.incrementQuality()
                    }

                    BACKSTAGE_PASS -> {
                        item.quality = 0
                    }

                    else -> {
                        item.decrementQuality()
                    }
                }
            }
        }
    }

    private fun Item.incrementQuality(value: Int = 1) {
        quality = (quality + value).coerceIn(0, 50)
    }

    private fun Item.decrementQuality() {
        if (quality > 0) {
            quality -= 1
        }
    }

}
