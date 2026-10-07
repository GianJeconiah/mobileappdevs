package com.example.ch07.util

fun parseTags(text: String): List<String> = text
    .split(",")
    .map { tag ->
        tag.trim()
            .lowercase()
            .removePrefix("#")
            .trim()
    }
    .filter { it.isNotEmpty() }
    .distinct()

fun formatTags(tags: List<String>): String = tags.joinToString(", ")
