package com.gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            adjustQualityForItem(item);
            adjustSellInForItem(item);
            if (item.sellIn < 0) {
                adjustQualityForExpiredItem(item);
            }
        }
    }

    private void adjustQualityForItem(Item item) {
        if (item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseItemQuality(item);
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.sellIn < 11) increaseItemQuality(item);
                if (item.sellIn < 6) increaseItemQuality(item);
            }
        } else {
            decreaseItemQuality(item);
            if (item.name.equals("Conjured")) {
                decreaseItemQuality(item);
            }
        }
    }

    private void adjustSellInForItem(Item item) {
        item.sellIn -= 1;
    }

    private void adjustQualityForExpiredItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseItemQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            decreaseItemQuality(item);

            if (item.name.equals("Conjured")) {
                decreaseItemQuality(item);
            }
        }
    }

    private void increaseItemQuality(Item item) {
        if (item.quality < 50) item.quality += 1;
    }

    private void decreaseItemQuality(Item item) {
        if (item.quality > 0) item.quality -= 1;
    }
}
