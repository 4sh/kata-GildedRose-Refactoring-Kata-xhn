package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality > 0) {
                    if (item.name != "Sulfuras, Hand of Ragnaros") {
                        item.decrementQuality()
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.incrementQuality()

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.incrementQuality()
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.incrementQuality()
                            }
                        }
                    }
                }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.sellIn--
            }

            if (item.sellIn < 0) {
                if (item.name != "Aged Brie") {
                    if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.quality > 0) {
                            if (item.name != "Sulfuras, Hand of Ragnaros") {
                                item.decrementQuality()
                            }
                        }
                    } else {
                        item.quality = 0
                    }
                } else {
                    if (item.quality < 50) {
                        item.incrementQuality()
                    }
                }
            }
        }
    }

    private fun Item.incrementQuality() {
        quality = quality + 1
    }

    private fun Item.decrementQuality() {
        quality = quality - 1
    }

}