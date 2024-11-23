package com.example.domain.model

data class PlantsResponse(
    val data: List<Data>,
    val links: Links,
    val meta: Meta
)