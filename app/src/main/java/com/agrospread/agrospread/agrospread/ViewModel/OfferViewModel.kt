package com.agrospread.agrospread.agrospread.ViewModel

import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.OfferData

class OfferViewModel : ViewModel() {
    private val _offerList = listOf(
        OfferData("Bolo", "Use: A bolo is a large cutting tool similar to a machete, commonly used for clearing vegetation, chopping weeds, and harvesting crops like sugar cane.\n" +
                "Estimated Price: \$5 - \$20\n" +
                "Price in PHP: ₱275 - ₱1,100", R.drawable.bolo),
        OfferData("Watering Can", "Use: A watering can is a portable container, usually with a spout, used to water plants manually.\n" +
                "Estimated Price: \$10 - \$30\n" +
                "Price in PHP: ₱550 - ₱1,650", R.drawable.can),
        OfferData("Cultivator", "Use: A cultivator is a tool used to prepare the soil by stirring and pulverizing it, either before planting or after the crop has begun growing.\n" +
                "Estimated Price:\n" +
                "Handheld: \$20 - \$50\n" +
                "Motorized: \$300 - \$800\n" +
                "Price in PHP:\n" +
                "Handheld: ₱1,100 - ₱2,750\n" +
                "Motorized: ₱16,500 - ₱44,000", R.drawable.cultivator),
        OfferData(
            "Slasher",
            "Use: A slasher is a tool used for cutting down long grass, weeds, and other vegetation.\n" +
                    "Estimated Price: \$10 - \$40\n" +
                    "Price in PHP: ₱550 - ₱2,200",
            R.drawable.slasher
        ),
        OfferData(
            "Wheel Barrow",
            "Use: A wheelbarrow is a single-wheeled cart used for carrying materials such as soil, compost, or harvested crops around the farm.\n" +
                    "Estimated Price: \$50 - \$150\n" +
                    "Price in PHP: ₱2,750 - ₱8,250",
            R.drawable.wheelbarrow
        ),
        OfferData(
            "Harvester",
            "Use: A harvester is a machine used for harvesting crops efficiently. Types include combine harvesters for grains.\n" +
                    "Estimated Price: \$100,000 - \$500,000\n" +
                    "Price in PHP: ₱5,500,000 - ₱27,500,000",
            R.drawable.harvester
        ),
        OfferData(
            "Pick Mattock",
            "Use: A pick mattock is a hand tool used for digging and chopping, featuring a pick on one side and a mattock blade on the other.\n" +
                    "Estimated Price: \$20 - \$40\n" +
                    "Price in PHP: ₱1,100 - ₱2,200",
            R.drawable.mattock
        ),
        OfferData(
            "Mowers",
            "Use: Mowers are used to cut grass or plants. Types include lawn mowers for small areas and larger mowers for fields.\n" +
                    "Estimated Price:\n" +
                    "Lawn Mower: \$100 - \$500\n" +
                    "Field Mower: \$2,000 - \$10,000\n" +
                    "Price in PHP:\n" +
                    "Lawn Mower: ₱5,500 - ₱27,500\n" +
                    "Field Mower: ₱110,000 - ₱550,000",
            R.drawable.mowers
        ),
        OfferData(
            "Plows",
            "Use: Plows are used for turning over the soil to prepare it for planting. They are typically pulled by tractors.\n" +
                    "Estimated Price: \$500 - \$5,000\n" +
                    "Price in PHP: ₱27,500 - ₱275,000",
            R.drawable.plows
        ),
        OfferData(
            "Tractor",
            "Use: A tractor is a powerful vehicle used for pulling various farm equipment and machinery.\n" +
                    "Estimated Price: \$10,000 - \$100,000\n" +
                    "Price in PHP: ₱550,000 - ₱5,500,000",
            R.drawable.tractor
        ),
        OfferData(
            "Wagon",
            "Use: A wagon is used for transporting materials such as hay, grains, and other produce around the farm.\n" +
                    "Estimated Price: \$500 - \$5,000\n" +
                    "Price in PHP: ₱27,500 - ₱275,000",
            R.drawable.wagon
        )
    )
    // Expose data as a LiveData for observing changes
    val offerList = _offerList
}
