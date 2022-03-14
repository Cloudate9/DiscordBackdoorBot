package io.github.cloudate9.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute

object InputUtils {
    /**
     * This checks if a string (generally an input obtained from a user) is true, false, or invalid.
     * @param input The user's input
     * @return True if input is true, false if input is false, or null if invalid.
     */
    fun inputBooleanCheck(input: String?): Boolean? {
        return when (input?.lowercase()) {
            "true", "y", "yes" -> true
            "false", "n", "no" -> false
            else -> {
                println(
                    colorize(
                        "Your input of \"$input\" is invalid! Please enter \"y\" for yes, or \"n\" for no.",
                        Attribute.RED_TEXT()
                    )
                )
                null
            }
        }
    }

    fun inputNaturalNumberCheck(input: String?): Long? {
        if (input?.toLongOrNull() != null && input.toLong() > 0) return input.toLong()
        println(
            colorize(
                "Your input of \"$input\" is invalid! Please enter a natural number.",
                Attribute.RED_TEXT()
            )
        )
        return null
    }

    /**
     * This checks if a string (generally an input obtained from a user) is a whole number, in the form of Long.
     * @param input The user's input
     * @return The number in Long if valid, or null if invalid.
     */
    fun inputWholeNumberCheck(input: String?): Long? {
        if (input?.toLongOrNull() != null && input.toLong() >= 0) return input.toLong()
        println(
            colorize(
                "Your input of \"$input\" is invalid! Please enter a natural number.",
                Attribute.RED_TEXT()
            )
        )
        return null
    }

    /**
     * This checks if a string (generally an input obtained from a user) is a valid string.
     * @param input The user's input
     * @return The string if valid, or null if invalid.
     */
    fun inputStringCheck(input: String?): String? {
        if (input == null || input == "") {
            println(
                colorize(
                    "Your input is invalid! Please enter a valid string.",
                    Attribute.RED_TEXT()
                )
            )
            return null
        }
        return input
    }
}