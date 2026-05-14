package com.example.siri_dhanyahub_1.data

object RecipeRepository {
    val sampleRecipes = listOf(
        Recipe(
            id = 1,
            name = "Navane Idli",
            milletType = "Navane",
            imageUrl = "idli",
            healthBenefit = "Rich in fiber, great for diabetics.",
            description = "Soft and healthy foxtail millet idlis",
            steps = listOf(
                "Soak Navane (foxtail millet) for 6 hours",
                "Grind with urad dal to a smooth batter",
                "Ferment overnight",
                "Pour into idli moulds and steam for 12 minutes",
                "Serve with coconut chutney"
            )
        ),
        Recipe(
            id = 2,
            name = "Sajje Roti",
            milletType = "Sajje",
            imageUrl = "roti",
            healthBenefit = "High protein for muscle building.",
            description = "Crispy pearl millet flatbread",
            steps = listOf(
                "Mix Sajje flour with warm water and salt",
                "Knead into a soft dough",
                "Roll into thin circles",
                "Cook on hot tawa for 2 minutes each side",
                "Serve with groundnut chutney"
            )
        ),
        Recipe(
            id = 3,
            name = "Ragi Mudde",
            milletType = "Ragi",
            imageUrl = "mudde",
            healthBenefit = "Classic energy booster.",
            description = "Traditional Karnataka finger millet balls",
            steps = listOf(
                "Boil water and add Ragi flour",
                "Stir continuously on low flame",
                "Cook until it forms a thick dough",
                "Shape into round balls",
                "Serve with saaru (rasam)"
            )
        ),
        Recipe(
            id = 4,
            name = "Baragu Khichdi",
            milletType = "Baragu",
            imageUrl = "khichdi",
            healthBenefit = "Low glycemic index.",
            description = "Healthy proso millet khichdi",
            steps = listOf(
                "Wash Baragu millet and moong dal",
                "Sauté onions, tomatoes with spices",
                "Add millet and dal, mix well",
                "Pressure cook for 3 whistles",
                "Garnish with ghee and coriander"
            )
        ),
        Recipe(
            id = 5,
            name = "Navane Upma",
            milletType = "Navane",
            imageUrl = "upma",
            healthBenefit = "Good for digestion.",
            description = "Quick and nutritious foxtail millet upma",
            steps = listOf(
                "Dry roast Navane millet for 3 minutes",
                "Sauté mustard seeds, curry leaves, onions",
                "Add vegetables and cook for 5 minutes",
                "Add millet and double the water",
                "Cover and cook for 10 minutes"
            )
        )
    )
}