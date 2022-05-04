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
 * @param name Company name
 * @param colorName Company color name
 * @param colorHexaCode Company color hexadecimal code
 * @param complements 
 */
data class CompanyInfo (

    /* Company name */
    @field:Json(name = "name")val name: kotlin.String? = null,
    /* Company color name */
    @field:Json(name = "colorName")val colorName: kotlin.String? = null,
    /* Company color hexadecimal code */
    @field:Json(name = "colorHexaCode")val colorHexaCode: kotlin.String? = null,
    @field:Json(name = "complements")val complements: ComplementList? = null
) {
}