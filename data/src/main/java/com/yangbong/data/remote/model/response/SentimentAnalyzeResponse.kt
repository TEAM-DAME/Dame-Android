package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SentimentAnalyzeResponse(
    @SerializedName("document")
    val document: Document,
    @SerializedName("sentences")
    val sentences: List<Sentence>
)

data class Document(
    @SerializedName("confidence")
    val confidence: Confidence,
    @SerializedName("sentiment")
    val sentiment: String
)

data class Confidence(
    @SerializedName("negative")
    val negative: Double,
    @SerializedName("neutral")
    val neutral: Double,
    @SerializedName("positive")
    val positive: Double
)

data class Sentence(
    @SerializedName("confidence")
    val confidence: Confidence,
    @SerializedName("content")
    val content: String,
    @SerializedName("highlights")
    val highlights: List<Highlight>,
    @SerializedName("length")
    val length: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("sentiment")
    val sentiment: String
)

data class Highlight(
    @SerializedName("length")
    val length: Int,
    @SerializedName("offset")
    val offset: Int
)
