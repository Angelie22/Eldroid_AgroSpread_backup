package com.agrospread.agrospread.agrospread.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.RecoData

class RecommendationViewModel : ViewModel() {

    // LiveData to hold the list of recommendations
    private val _recommendations = MutableLiveData<List<RecoData>>()
    val recommendations: LiveData<List<RecoData>> get() = _recommendations

    // Initialize with some mock data (can be fetched from a repository later)
    init {
        loadRecommendations()
    }

    private fun loadRecommendations() {
        val recommendationsList = listOf(
            RecoData(
                "Fruits from Seeds?",
                "Some examples include species of walnuts such as heartnut, and even apples such as the ‘Antanovka’ apple (yes, you heard that right!) are one of the varieties that will grow true from seed.",
                R.drawable.a
            ),
            RecoData(
                "Non-flower vegetables Fertilizer",
                "These are the ones where we only eat the leaves or roots (carrots, chard, lettuce, broccoli, etc.). These veggies need a high nitrogen (N) fertilizer, so the first number should be the largest.",
                R.drawable.n
            ),
            RecoData(
                "Inorganic Fertilizer",
                "Inorganic or chemical fertilizers are useful for quick, plant-available nutrients. As these do not need to break down before they’re plant-available, they are often widespread in use and easy to apply.",
                R.drawable.`in`
            ),
            RecoData(
                "Alaska Fish Emulsion Fertilizer",
                "For leafy crops, such as collard greens, chard, lettuce, spinach, and endive, Alaska Fish Emulsion is an excellent product, with a 5-1-1 NPK ratio.",
                R.drawable.f
            ),
            RecoData(
                "Arbico Organics Earthworm Castings",
                "Even though they’re not as widely known as conventional fertilizers, worm castings are a wonderful source of dozens of micronutrients, trace minerals, and healthy microbes.",
                R.drawable.e
            ),
            RecoData(
                "Burpee Bone Meal",
                "Because of its high nitrogen and phosphorus content (6-8-0 NPK), bone meal is great for alliums such as onions and garlic, which are long-season, heavy feeders.",
                R.drawable.b
            ),
            RecoData(
                "Dr. Earth Bulb Food",
                "There is a large selection of Dr. Earth products available, including all-purpose vegetable fertilizers and crop-specific types, in both liquids and granules.",
                R.drawable.d
            ),
            RecoData(
                "Down To Earth Acid Mix",
                "Some garden-variety plants prefer a slightly acidic substrate. Cucumbers, radishes, garlic, and carrots are just a few of these, and blueberries and raspberries (although these are admittedly fruits, not vegetables) are on that list as well.",
                R.drawable.doo
            ),
            RecoData(
                "Ecoscraps Leafy Greens Plant Food",
                "Because it’s relatively well-balanced, however, it can also be useful for other crops that enjoy a bit of extra nitrogen, such as onions, corn, and brassicas like broccoli and cabbage.",
                R.drawable.er
            ),
            RecoData(
                "Espoma Garden-Tone",
                "Cucurbits, asparagus, herbs, tomatoes, and kale, among others, will all benefit from application. Espoma Garden-Tone granules can be applied prior to planting at three pounds per 50 square feet to condition the site.",
                R.drawable.ga
            ),
            RecoData(
                "Foxfarm Grow Big, Big Bloom, and Tiger Bloom",
                "They’re designed to support healthy growth, budding, and blooming throughout the entire life cycle, so even though each has a different ratio of elements, the overall effect of the nutrients contained in the three types works well during various phases of growth.",
                R.drawable.thr
            ),
            RecoData(
                "Vigoro Tomato and Vegetable",
                "This slow-release plant food includes additional calcium to help prevent blossom end-rot, and it can be applied throughout the season as needed, according to package instructions.",
                R.drawable.v
            ),
            )
        _recommendations.postValue(recommendationsList)
    }
}
