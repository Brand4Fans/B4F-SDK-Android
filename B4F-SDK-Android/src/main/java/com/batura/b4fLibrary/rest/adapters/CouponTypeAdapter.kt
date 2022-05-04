package com.batura.b4fLibrary.rest.adapters

import com.batura.b4fLibrary.rest.models.CouponState
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

internal class CouponTypeAdapter {
    @ToJson
    fun toJson(campaignType: CouponState): Int {
        return campaignType.value
    }

    @FromJson
    fun fromJson(couponState: Int): CouponState {
        return CouponState.getCouponState(couponState)!!
    }
}