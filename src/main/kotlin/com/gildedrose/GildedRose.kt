package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->

            if (!ItemTypes.SULFURAS.isItem(item)) {
                when {
                    ItemTypes.BACKSTAGE.isItem(item) && item.sellIn < 6 -> {
                        item.addQuality(3)
                    }

                    ItemTypes.BACKSTAGE.isItem(item) && item.sellIn < 11 -> {
                        item.addQuality(2)
                    }

                    listOf(ItemTypes.BACKSTAGE, ItemTypes.BRIE).any { it.isItem(item) } -> item.addQuality(1)
                    else -> item.addQuality(-1)
                }
                if (ItemTypes.CONJURED.isItem(item)) {
                    item.addQuality(-1)
                }

                item.sellIn -= 1

                if (item.sellIn < 0) {
                    if (ItemTypes.BRIE.isItem(item)) {
                        item.addQuality(1)
                    } else {
                        if (!ItemTypes.BACKSTAGE.isItem(item)) {
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

enum class ItemTypes(val label: String) {
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BRIE("Aged Brie"),
    CONJURED("Conjured") {
        override fun isItem(item: Item): Boolean {
            return item.name.startsWith(label)
        }
    } ;
    
    open fun isItem(item:Item): Boolean = item.name == label
}

