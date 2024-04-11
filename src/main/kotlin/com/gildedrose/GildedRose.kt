package com.gildedrose

private const val AGED_BRIE = "Aged Brie"
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
private const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            when (item.name) {
                AGED_BRIE -> {
                    if (item.quality < 50) {
                        item.incrementQuality()
                    }
                }

                BACKSTAGE_PASS -> {
                    if (item.quality < 50) {
                        item.incrementQuality()

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
                SULFURAS -> {}
                else -> {
                    if (item.quality > 0) {
                        item.decrementQuality()
                    }
                }
            }

            if (item.name != SULFURAS) {
                item.sellIn--
            }

            if (item.sellIn < 0) {
                if (item.name != AGED_BRIE) {
                    if (item.name != BACKSTAGE_PASS) {
                        if (item.quality > 0) {
                            if (item.name != SULFURAS) {
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
