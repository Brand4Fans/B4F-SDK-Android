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
 * @param selectTargetUpdateType 
 * @param locationCountry 
 * @param locationProvince 
 * @param locationLocation 
 * @param locationGuidNv 
 */
data class LocationVCModel (

    @field:Json(name = "selectTargetUpdateType") val selectTargetUpdateType: kotlin.String? = null,
    @field:Json(name = "locationCountry")val locationCountry: LocationModel? = null,
    @field:Json(name = "locationProvince")val locationProvince: LocationModel? = null,
    @field:Json(name = "locationLocation")val locationLocation: LocationModel? = null,
    @field:Json(name = "locationGuidNv")val locationGuidNv: kotlin.String? = null
) {
}