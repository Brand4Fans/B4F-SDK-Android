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
 * @param id Campaign identifier
 * @param name Campaign name
 * @param shortDescription Campaign short description
 * @param longDescription Campaign long description
 * @param publishDate Campaign publish date
 * @param startDate Campaign start date
 * @param endDate Campaign end date
 * @param couponMaxEntry Max number of use for coupon campaign
 * @param couponMaxRedeemDate Max redeem date for coupon campaign
 * @param couponConfShop Must be confirmed by shop in coupon campaign?
 * @param couponCampaignCode Campaign code for coupon campaign
 * @param raffleMaxContestant Max number de contestant for raffle campaign
 * @param raffleDate Raffle date for raffle campaign
 * @param raffleCongratsWin Text shown for raffle winners
 * @param typeId Campaign type identifier
 * @param typeName Campaign type name
 * @param company 
 * @param complements 
 * @param badgesList List of campaign badges associated
 * @param userLinkedBadgesList List of badges linked to user associated with the campaign
 * @param userAvailableBadgesList List of badges linked to user not associated with the campaign
 */
data class CampaignDetail (

    /* Campaign identifier */
    @field:Json(name = "id")val id: kotlin.String? = null,
    /* Campaign name */
    @field:Json(name = "name")val name: kotlin.String? = null,
    /* Campaign short description */
    @field:Json(name = "shortDescription")val shortDescription: kotlin.String? = null,
    /* Campaign long description */
    @field:Json(name = "longDescription")val longDescription: kotlin.String? = null,
    /* Campaign publish date */
    @field:Json(name = "publishDate")val publishDate: DateTime? = null,
    /* Campaign start date */
    @field:Json(name = "startDate")val startDate: DateTime? = null,
    /* Campaign end date */
    @field:Json(name = "endDate")val endDate: DateTime? = null,
    /* Max number of use for coupon campaign */
    @field:Json(name = "couponMaxEntry")val couponMaxEntry: kotlin.Int? = null,
    /* Max redeem date for coupon campaign */
    @field:Json(name = "couponMaxRedeemDate")val couponMaxRedeemDate: DateTime? = null,
    /* Must be confirmed by shop in coupon campaign? */
    @field:Json(name = "couponConfShop")val couponConfShop: kotlin.Boolean? = null,
    /* Campaign code for coupon campaign */
    @field:Json(name = "couponCampaignCode")val couponCampaignCode: kotlin.String? = null,
    /* Max number de contestant for raffle campaign */
    @field:Json(name = "raffleMaxContestant")val raffleMaxContestant: kotlin.Int? = null,
    /* Raffle date for raffle campaign */
    @field:Json(name = "raffleDate")val raffleDate: DateTime? = null,
    /* Text shown for raffle winners */
    @field:Json(name = "raffleCongratsWin")val raffleCongratsWin: kotlin.String? = null,
    /* Campaign type identifier */
    @field:Json(name = "typeId")val typeId: CampaignType? = null,
    /* Campaign type name */
    @field:Json(name = "typeName")val typeName: kotlin.String? = null,
    @field:Json(name = "company")val company: CompanyInfo? = null,
    @field:Json(name = "sponsors")val sponsors: List<NewsSponsorListItem>? = null,
    @field:Json(name = "complements")val complements: ComplementList? = null,
    /* List of campaign badges associated */
    @field:Json(name = "badgesList")val badgesList: List<CampaignDetailBadge>? = null,
    /* List of badges linked to user associated with the campaign */
    @field:Json(name = "userLinkedBadgesList")val userLinkedBadgesList: List<CampaignDetailUserLinkedBadge>? = null,
    /* List of badges linked to user not associated with the campaign */
    @field:Json(name = "userAvailableBadgesList")val userAvailableBadgesList: List<CampaignDetailUserAvailableBadge>? = null
) {



}