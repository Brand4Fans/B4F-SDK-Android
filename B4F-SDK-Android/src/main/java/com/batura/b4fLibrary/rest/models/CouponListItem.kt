/**
 * Third Party B4F Service
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package com.batura.b4fLibrary.rest.models

import com.squareup.moshi.Json
import org.joda.time.DateTime

/**
 * 
 * @param id Coupon identifier
 * @param state 
 * @param campaignId Campaign identifier
 * @param campaignName Campaign name
 * @param campaignShortDescription Campaign short description
 * @param campaignLongDescription Campaign long description
 * @param campaignPublishDate Campaign publish date
 * @param campaignStartDate Campaign start date
 * @param campaignEndDate Campaign end date
 * @param campaignCouponMaxEntry Max number of use for coupon campaign
 * @param campaignCouponMaxRedeemDate Max redeem date for coupon campaign
 * @param campaignCouponConfShop Must be confirmed by shop in coupon campaign?
 * @param campaignCouponCampaignCode Campaign code for coupon campaign
 * @param campaignRaffleMaxContestant Max number de contestant for raffle campaign
 * @param campaignRaffleDate Raffle date for raffle campaign
 * @param campaignRaffleCongratsWin Text shown for raffle winners
 * @param campaignTypeId Campaign type identifier
 * @param company 
 * @param complements 
 */
data class CouponListItem (

    /* Coupon identifier */
    @field:Json(name = "id")val id: kotlin.String? = null,
    @field:Json(name = "state")val state: CouponState? = null,
    /* Campaign identifier */
    @field:Json(name = "campaignId")val campaignId: kotlin.String? = null,
    /* Campaign name */
    @field:Json(name = "campaignName")val campaignName: kotlin.String? = null,
    /* Campaign short description */
    @field:Json(name = "campaignShortDescription")val campaignShortDescription: kotlin.String? = null,
    /* Campaign long description */
    @field:Json(name = "campaignLongDescription")val campaignLongDescription: kotlin.String? = null,
    /* Campaign publish date */
    @field:Json(name = "campaignPublishDate")val campaignPublishDate: DateTime? = null,
    /* Campaign start date */
    @field:Json(name = "campaignStartDate")val campaignStartDate: DateTime? = null,
    /* Campaign end date */
    @field:Json(name = "campaignEndDate")val campaignEndDate: DateTime? = null,
    /* Max number of use for coupon campaign */
    @field:Json(name = "campaignCouponMaxEntry")val campaignCouponMaxEntry: kotlin.Int? = null,
    /* Max redeem date for coupon campaign */
    @field:Json(name = "campaignCouponMaxRedeemDate")val campaignCouponMaxRedeemDate: DateTime? = null,
    /* Must be confirmed by shop in coupon campaign? */
    @field:Json(name = "campaignCouponConfShop")val campaignCouponConfShop: kotlin.Boolean? = null,
    /* Campaign code for coupon campaign */
    @field:Json(name = "campaignCouponCampaignCode")val campaignCouponCampaignCode: kotlin.String? = null,
    /* Max number de contestant for raffle campaign */
    @field:Json(name = "campaignRaffleMaxContestant")val campaignRaffleMaxContestant: kotlin.Int? = null,
    /* Raffle date for raffle campaign */
    @field:Json(name = "campaignRaffleDate")val campaignRaffleDate: DateTime? = null,
    /* Text shown for raffle winners */
    @field:Json(name = "campaignRaffleCongratsWin")val campaignRaffleCongratsWin: kotlin.String? = null,
    /* Campaign type identifier */
    @field:Json(name = "campaignTypeId")val campaignTypeId: CampaignType? = null,
    @field:Json(name = "company")val company: CompanyInfo? = null,
    @field:Json(name = "sponsors")val sponsors: List<CampaignSponsorListItem>? = null,
    @field:Json(name = "complements")val complements: ComplementList? = null
) {


}