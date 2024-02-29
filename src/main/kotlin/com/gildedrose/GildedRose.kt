package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            item.itemType?.dayPassedUpdateItem(item)
        }
    }

}

private fun Item.addQuality(value: Int) {
    quality += value
    quality = quality.coerceIn(0, 50)
}

enum class ItemType(val label: String) {
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert") {
        override fun dayPassedQualityUpdate(item: Item)
            = when {
                item.sellIn < 0 -> {
                    QualityUpdate(-item.quality)
                }
                item.sellIn < 5 -> {
                    QualityUpdate(3)
                }
                item.sellIn < 10 -> {
                    QualityUpdate(2)
                }
                else -> QualityUpdate(1)
            }
    },
    SULFURAS("Sulfuras, Hand of Ragnaros") {
        override fun dayPassedUpdateItem(item: Item) {
            // never update sulfuras
        }
    },
    BRIE("Aged Brie") {
        override fun dayPassedQualityUpdate(item: Item): QualityUpdate {
            return BASIC.dayPassedQualityUpdate(item) * -1
        }
    },
    CONJURED("Conjured") {
        override fun isItem(item: Item): Boolean {
            return item.name.startsWith(label)
        }

        override fun dayPassedQualityUpdate(item: Item): QualityUpdate {
            return BASIC.dayPassedQualityUpdate(item) * 2
        }
    },
    BASIC("basic") {
        override fun isItem(item: Item): Boolean {
            return true
        }

        override fun dayPassedQualityUpdate(item: Item): QualityUpdate {
            if (item.sellIn >= 0) {
                return QualityUpdate(-1)
            } else {
                return QualityUpdate(-2)
            }
        }
    }
    ;

    open fun isItem(item: Item): Boolean = item.name == label

    open fun dayPassedUpdateItem(item: Item) {
        item.dayPassedUpdateSellIn()
        item.addQuality(dayPassedQualityUpdate(item).qualityInc)
    }
    open fun dayPassedQualityUpdate(item: Item): QualityUpdate = QualityUpdate(0)
}

data class QualityUpdate(val qualityInc: Int) {
    operator fun times(mul: Int): QualityUpdate {
        return QualityUpdate(qualityInc * mul)
    }
}

val Item.itemType get() = ItemType.entries.firstOrNull { it.isItem(this) }


fun Item.dayPassedUpdateSellIn() { sellIn -- }

