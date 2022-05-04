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
 * @param id Campaign id
 * @param name Campaign name
 * @param complements 
 */
data class CampaignFilterCompany (

    /* Campaign id */
    @field:Json(name = "id")val id: kotlin.String? = null,
    /* Campaign name */
    @field:Json(name = "name")val name: kotlin.String? = null,
    @field:Json(name = "complements")val complements: ComplementList? = null
) {
}