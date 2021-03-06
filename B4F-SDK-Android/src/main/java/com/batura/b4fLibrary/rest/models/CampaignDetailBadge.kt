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

/**
 * 
 * @param campaignBadgeId Identifier of the relationship between the campaign and the badge
 * @param badgeId Badge identifier
 * @param badgeName Badge name
 * @param badgeDescription Badge description
 * @param badgeReference Badge reference
 * @param badgeSmarttagCount Number of smarttag associated with the badge
 * @param productId Product identifier
 * @param productName Product name
 * @param productDescription Product description
 * @param companyId Company identifier
 * @param badgeTypeId Badge type identifier
 * @param productComplements 
 */
data class CampaignDetailBadge (

    /* Identifier of the relationship between the campaign and the badge */
    @field:Json(name = "campaignBadgeId")val campaignBadgeId: kotlin.String? = null,
    /* Badge identifier */
    @field:Json(name = "badgeId")val badgeId: kotlin.String? = null,
    /* Badge name */
    @field:Json(name = "badgeName")val badgeName: kotlin.String? = null,
    /* Badge description */
    @field:Json(name = "badgeDescription")val badgeDescription: kotlin.String? = null,
    /* Badge reference */
    @field:Json(name = "badgeReference")val badgeReference: kotlin.String? = null,
    /* Number of smarttag associated with the badge */
    @field:Json(name = "badgeSmarttagCount")val badgeSmarttagCount: kotlin.Int? = null,
    /* Product identifier */
    @field:Json(name = "productId")val productId: kotlin.String? = null,
    /* Product name */
    @field:Json(name = "productName")val productName: kotlin.String? = null,
    /* Product description */
    @field:Json(name = "productDescription")val productDescription: kotlin.String? = null,
    /* Company identifier */
    @field:Json(name = "companyId")val companyId: kotlin.String? = null,
    /* Badge type identifier */
    @field:Json(name = "badgeTypeId")val badgeTypeId: kotlin.String? = null,
    @field:Json(name = "productComplements")val productComplements: ComplementList? = null
) {
}