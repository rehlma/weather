package de.dojo.weather.data.repository

object WeatherData {
    const val Condition = "ww"
    const val Temperature = "TTT"
    const val DewPoint = "Td"
    const val Pressure = "PPPP"

    object Wind {
        const val Speed = "FF"
        const val Direction = "DD"
        const val Gusts = "FX1"
    }

    object Precipitation {
        const val Value = "RR1c"
        const val Probability = "wwP"
        const val Duration = "DRR1"
    }

    const val CloudCoverage = "N"
    const val Visibility = "VV"

    object Sun {
        const val Duration = "SunD1"
        const val Irradiance = "Rad1h"
    }

    const val FogProbability = "wwM"
    const val Humidity = "humidity"
}


val WeatherCondition: Map<Int, String> = mapOf(
    0 to "Bewölkungsentwicklung nicht beobachtet",
    1 to "Bewölkung abnehmend",
    2 to "Bewölkung unverändert",
    3 to "Bewölkung zunehmend",

    4 to "Sicht durch Rauch oder Asche vermindert",
    5 to "trockener Dunst (relative Feuchte < 80 %)",
    6 to "verbreiteter Schwebstaub, nicht vom Wind herangeführt",
    7 to "Staub oder Sand bzw. Gischt, vom Wind herangeführt",
    8 to "gut entwickelte Staub- oder Sandwirbel",
    9 to "Staub- oder Sandsturm im Gesichtskreis, aber nicht an der Station",

    10 to "feuchter Dunst (relative Feuchte > 80 %)",
    11 to "Schwaden von Bodennebel",
    12 to "durchgehender Bodennebel",
    13 to "Wetterleuchten sichtbar, kein Donner gehört",
    14 to "Niederschlag im Gesichtskreis, nicht den Boden erreichend",
    15 to "Niederschlag in der Ferne (> 5 km), aber nicht an der Station",
    16 to "Niederschlag in der Nähe (< 5 km), aber nicht an der Station",
    17 to "Gewitter (Donner hörbar), aber kein Niederschlag an der Station",
    18 to "Markante Böen im Gesichtskreis, aber kein Niederschlag an der Station",
    19 to "Tromben (trichterförmige Wolkenschläuche) im Gesichtskreis",

    20 to "nach Sprühregen oder Schneegriesel",
    21 to "nach Regen",
    22 to "nach Schneefall",
    23 to "nach Schneeregen oder Eiskörnern",
    24 to "nach gefrierendem Regen",
    25 to "nach Regenschauer",
    26 to "nach Schneeschauer",
    27 to "nach Graupel- oder Hagelschauer",
    28 to "nach Nebel",
    29 to "nach Gewitter",

    30 to "leichter oder mäßiger Sandsturm, an Intensität abnehmend",
    31 to "leichter oder mäßiger Sandsturm, unveränderte Intensität",
    32 to "leichter oder mäßiger Sandsturm, an Intensität zunehmend",
    33 to "schwerer Sandsturm, an Intensität abnehmend",
    34 to "schwerer Sandsturm, unveränderte Intensität",
    35 to "schwerer Sandsturm, an Intensität zunehmend",
    36 to "leichtes oder mäßiges Schneefegen, unter Augenhöhe",
    37 to "starkes Schneefegen, unter Augenhöhe",
    38 to "leichtes oder mäßiges Schneetreiben, über Augenhöhe",
    39 to "starkes Schneetreiben, über Augenhöhe",

    40 to "Nebel in einiger Entfernung",
    41 to "Nebel in Schwaden oder Bänken",
    42 to "Nebel, Himmel erkennbar, dünner werdend",
    43 to "Nebel, Himmel nicht erkennbar, dünner werdend",
    44 to "Nebel, Himmel erkennbar, unverändert",
    45 to "Nebel, Himmel nicht erkennbar, unverändert",
    46 to "Nebel, Himmel erkennbar, dichter werdend",
    47 to "Nebel, Himmel nicht erkennbar, dichter werdend",
    48 to "Nebel mit Reifansatz, Himmel erkennbar",
    49 to "Nebel mit Reifansatz, Himmel nicht erkennbar",

    50 to "unterbrochener leichter Sprühregen",
    51 to "durchgehend leichter Sprühregen",
    52 to "unterbrochener mäßiger Sprühregen",
    53 to "durchgehend mäßiger Sprühregen",
    54 to "unterbrochener starker Sprühregen",
    55 to "durchgehend starker Sprühregen",
    56 to "leichter gefrierender Sprühregen",
    57 to "mäßiger oder starker gefrierender Sprühregen",
    58 to "leichter Sprühregen mit Regen",
    59 to "mäßiger oder starker Sprühregen mit Regen",

    60 to "unterbrochener leichter Regen oder einzelne Regentropfen",
    61 to "durchgehend leichter Regen",
    62 to "unterbrochener mäßiger Regen",
    63 to "durchgehend mäßiger Regen",
    64 to "unterbrochener starker Regen",
    65 to "durchgehend starker Regen",
    66 to "leichter gefrierender Regen",
    67 to "mäßiger oder starker gefrierender Regen",
    68 to "leichter Schneeregen",
    69 to "mäßiger oder starker Schneeregen",

    70 to "unterbrochener leichter Schneefall oder einzelne Schneeflocken",
    71 to "durchgehend leichter Schneefall",
    72 to "unterbrochener mäßiger Schneefall",
    73 to "durchgehend mäßiger Schneefall",
    74 to "unterbrochener starker Schneefall",
    75 to "durchgehend starker Schneefall",
    76 to "Eisnadeln (Polarschnee)",
    77 to "Schneegriesel",
    78 to "Schneekristalle",
    79 to "Eiskörner (gefrorene Regentropfen)",

    80 to "leichter Regenschauer",
    81 to "mäßiger oder starker Regenschauer",
    82 to "äußerst heftiger Regenschauer",
    83 to "leichter Schneeregenschauer",
    84 to "mäßiger oder starker Schneeregenschauer",
    85 to "leichter Schneeschauer",
    86 to "mäßiger oder starker Schneeschauer",
    87 to "leichter Graupelschauer",
    88 to "mäßiger oder starker Graupelschauer",
    89 to "leichter Hagelschauer",
    90 to "mäßiger oder starker Hagelschauer",

    91 to "Gewitter in der letzten Stunde, zurzeit leichter Regen",
    92 to "Gewitter in der letzten Stunde, zurzeit mäßiger oder starker Regen",
    93 to "Gewitter in der letzten Stunde, zurzeit leichter Schneefall/Schneeregen/Graupel/Hagel",
    94 to "Gewitter in der letzten Stunde, zurzeit mäßiger oder starker Schneefall/Schneeregen/Graupel/Hagel",
    95 to "leichtes oder mäßiges Gewitter mit Regen oder Schnee",
    96 to "leichtes oder mäßiges Gewitter mit Graupel oder Hagel",
    97 to "starkes Gewitter mit Regen oder Schnee",
    98 to "starkes Gewitter mit Sandsturm",
    99 to "starkes Gewitter mit Graupel oder Hagel",
)
