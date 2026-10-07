package com.example.ch07.util

fun formatRelativeTime(now: Long, then: Long): String {
    val diffMs = maxOf(0L, now - then)
    val minuteMs = 60_000L
    val hourMs = 60 * minuteMs
    val dayMs = 24 * hourMs
    val monthMs = 30 * dayMs
    val yearMs = 365 * dayMs

    return when {
        diffMs < minuteMs -> "baru saja"
        diffMs < hourMs -> "${diffMs / minuteMs} menit lalu"
        diffMs < dayMs -> "${diffMs / hourMs} jam lalu"
        diffMs < monthMs -> "${diffMs / dayMs} hari lalu"
        diffMs < yearMs -> "${diffMs / monthMs} bulan lalu"
        else -> "${diffMs / yearMs} tahun lalu"
    }
}
