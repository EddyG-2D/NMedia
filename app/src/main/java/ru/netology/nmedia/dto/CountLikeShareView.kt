package ru.netology.nmedia.dto

fun count(count: Int): String {
    return when {
        count < 1000 -> count.toString()
        count < 10_000 -> {
            val decimal = (count % 1000) / 100
            val integer = count / 1000
            if (decimal == 0) {
                String.format("%dK", integer)
            } else {
                String.format("%d,%dK", integer, decimal)
            }
        }
        count < 1_000_000 -> {
            val decimal = (count % 1000) / 100
            val integer = count / 1000
            if (decimal == 0) {
                String.format("%dK", integer)
            } else {
                String.format("%dK", integer, decimal)
            }
        }
        count < 100_000_000 -> {
            val decimal = (count % 1_000_000) / 100_000
            val integer = count / 1_000_000
            if (decimal == 0) {
                String.format("%dM", integer)
            } else {
                String.format("%d,%dM", integer, decimal)
            }
        }
        else -> String.format("%.1fM", count / 1_000_000.0)
    }
}