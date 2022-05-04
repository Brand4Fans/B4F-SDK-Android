package com.batura.b4fLibrary.rest.adapters

import com.batura.b4fLibrary.rest.models.CampaignType
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

internal class CampaignTypeAdapter {
    @ToJson
    fun toJson(campaignType: CampaignType): String {
        return campaignType.value
    }

    @FromJson
    fun fromJson(campaignType: String): CampaignType {
        return CampaignType.getCampaignType(campaignType)!!
    }
}