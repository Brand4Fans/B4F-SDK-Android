package com.batura.b4fLibrary.rest.models

/**
 *
 * Values: 0,1,2,3,4
 */
enum class CampaignType(val value: String){

    UNKNOWN("0"),
    COUPON("1"),
    RAFFLE("2");

    companion object{
        fun getCampaignType(value: String): CampaignType? {
            return CampaignType.values().find { campaignType ->
                campaignType.value == value
            }
        }
    }
}