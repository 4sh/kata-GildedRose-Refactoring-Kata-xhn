package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->

            if (!ItemType.SULFURAS.isItemOfType(item)) {
                when {
                    ItemType.BACKSTAGE.isItemOfType(item) && item.sellIn < 6 -> {
                        item.addQuality(3)
                    }

                    ItemType.BACKSTAGE.isItemOfType(item) && item.sellIn < 11 -> {
                        item.addQuality(2)
                    }

                    listOf(ItemType.BACKSTAGE, ItemType.BRIE).any { it.isItemOfType(item) } -> item.addQuality(1)
                    else -> {
                        if (ItemType.CONJURED.isItemOfType(item)) {
                            item.addQuality(-2)
                        } else {
                            item.addQuality(-1)
                        }
                    }
                }

                item.sellIn -= 1

                if (item.sellIn < 0) {
                    if (ItemType.BRIE.isItemOfType(item)) {
                        item.addQuality(1)
                    } else {
                        if (!ItemType.BACKSTAGE.isItemOfType(item)) {
                            if (ItemType.CONJURED.isItemOfType(item)) {
                                item.addQuality(-2)
                            } else {
                                item.addQuality(-1)
                            }
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

enum class ItemType(val label: String) {
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BRIE("Aged Brie"),
    CONJURED("Conjured") {
        override fun isItemOfType(item: Item): Boolean {
            return item.name.startsWith(label)
        }
    }
    ;
    
    open fun isItemOfType(item: Item) = item.name == label
}
