package com.lablabla.blablawatering.domain.model

import java.time.LocalDateTime

data class DateTimeElement(
    val days: List<Days>,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
