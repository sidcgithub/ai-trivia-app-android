package com.triviagenai.triviagen.core.presentation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object MainMenuRoute : Route()

    @Serializable
    data object RoundSetupRoute : Route()

    @Serializable
    data object TriviaGameRoute : Route()

    @Serializable
    data object ResultsRoute : Route()

    @Serializable
    data object AnswersRoute : Route()
}