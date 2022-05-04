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
 * @param id Smarttag identifier
 * @param smarttagCode Smarttag nfc's code
 * @param badgeId Badge identifier
 * @param badgeReference Badge reference
 * @param badgeName Badge name
 * @param badgeDescription Badge description
 * @param productId Product identifier
 * @param productReference Product reference
 * @param productName Product name
 * @param productDescription Product description
 * @param productComplements 
 */
data class SmartTagListItem (

    /* Smarttag identifier */
    @field:Json(name = "id")val id: kotlin.String? = null,
    /* Smarttag nfc's code */
    @field:Json(name = "smarttagCode")val smarttagCode: kotlin.String? = null,
    /* Badge identifier */
    @field:Json(name = "badgeId")val badgeId: kotlin.String? = null,
    /* Badge reference */
    @field:Json(name = "badgeReference")val badgeReference: kotlin.String? = null,
    /* Badge name */
    @field:Json(name = "badgeName")val badgeName: kotlin.String? = null,
    /* Badge description */
    @field:Json(name = "badgeDescription")val badgeDescription: kotlin.String? = null,
    /* Product identifier */
    @field:Json(name = "productId")val productId: kotlin.String? = null,
    /* Product reference */
    @field:Json(name = "productReference")val productReference: kotlin.String? = null,
    /* Product name */
    @field:Json(name = "productName")val productName: kotlin.String? = null,
    /* Product description */
    @field:Json(name = "productDescription")val productDescription: kotlin.String? = null,
    @field:Json(name = "productComplements")val productComplements: ComplementList? = null
) {
}